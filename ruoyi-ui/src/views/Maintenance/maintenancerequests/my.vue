<template>

<div class="container">

  <el-row :gutter="10" class="mb8">
    <el-col :span="1.5">
      <el-button
        type="primary"
        plain
        icon="el-icon-plus"
        size="mini"
        @click="handleAdd"
        v-hasPermi="['Maintenance:maintenancerequests:add']"
      >新增
      </el-button>
    </el-col>
  </el-row>

  <el-card class="container">
    <el-table :data="maintenanceRequestList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="报修人" align="center" prop="requesterName">
        <template v-slot="{row}">
          <el-button type="text" @click="showDetail(row)">
            {{row.requesterName}}
          </el-button>
        </template>
      </el-table-column>
      <el-table-column label="联系方式" align="center" prop="requesterContact" show-overflow-tooltip/>
      <el-table-column label="报修内容" align="center" prop="requestContent" show-overflow-tooltip/>
      <el-table-column label="更新时间" align="center" prop="updatedAt" width="180">
        <template v-slot="scope">
          <span>{{ parseTime(scope.row.updatedAt, '{y}-{m}-{d} {h}:{m}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="状态" align="center" prop="status"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template v-slot="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['Maintenance:maintenancerequests:edit']"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['Maintenance:maintenancerequests:remove']"
          >删除
          </el-button>
          <el-button
           size="mini"
           type="text"
           icon="el-icon-download"
           @click="handleDetail(scope.row)"
           v-hasPermi="['Maintenance:maintenancerecords:list']"
          >查看详情
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 新增维修对话框   -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="报修人" prop="requesterName">
          <el-input v-model="form.requesterName" placeholder="请输入报修人"/>
        </el-form-item>
        <el-form-item label="联系方式">
          <el-input v-model="form.requesterContact" :min-height="192"/>
        </el-form-item>
        <el-form-item label="报修内容">
          <editor v-model="form.requestContent" :min-height="192"/>
        </el-form-item>
        <el-form-item label="更新时间" prop="updatedAt">
          <el-date-picker clearable
                          v-model="form.updatedAt"
                          type="date"
                          value-format="yyyy-MM-dd hh:mm:ss"
                          placeholder="请选择发布时间">
          </el-date-picker>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </el-card>

  <!-- 查看维修记录对话框  -->
  <el-dialog :title="title" :visible.sync="openDetail" width="500px" append-to-body>
    <el-timeline>
      <el-timeline-item
        v-for="(record, index) in recordList"
        :key="index"
        :timestamp="record.timestamp">
        {{record.content}}
      </el-timeline-item>
    </el-timeline>
  </el-dialog>

  <!-- 报修详情对话框 -->
  <el-dialog title="" :visible.sync="dialogDetailVisible" width="500px" append-to-body>
    <el-descriptions title="报修信息" direction="vertical" :column="2" border>
      <el-descriptions-item label="报修人">
        {{ currentRequest.requesterName }}
      </el-descriptions-item>
      <el-descriptions-item label="联系方式" >
        {{ currentRequest.requesterContact}}
      </el-descriptions-item>
      <el-descriptions-item label="报修内容" :span="2" >
        <pre v-html="currentRequest.requestContent"></pre>
      </el-descriptions-item>
      <el-descriptions-item label="更新时间">
        {{currentRequest.updatedAt}}
      </el-descriptions-item>
      <el-descriptions-item label="状态">
        <el-tag>
          {{currentRequest.status}}
        </el-tag>
      </el-descriptions-item>
    </el-descriptions>
  </el-dialog>
</div>

</template>
<script>
import {getInfo} from "@/api/login";
import {
  addMaintenancerequests, delMaintenancerequests,
  getMaintenancerequests,
  listMaintenancerequests,
  updateMaintenancerequests
} from "@/api/Maintenance/maintenancerequests";
import {listMaintenancerecords} from "@/api/Maintenance/maintenancerecords";
import {constant} from "@riophae/vue-treeselect/src/utils";

export default {
  name: "my",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 公告管理表格数据
      maintenanceRequestList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        requesterName: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        title: [
          {required: true, message: "公告标题不能为空", trigger: "blur"}
        ],
        content: [
          {required: true, message: "公告内容不能为空", trigger: "blur"}
        ],
      },
      //
      recordList :[],
      //
      openDetail: false,
      //
      currentRequest:{
        requestID: null,
        requesterName: null,
        requesterContact: null,
        requestContent: null,
        status: null,
        createdAt: null,
        updatedAt: null
      },
      //
      dialogDetailVisible:false
    };
  },
  async created() {
    await this.getInfo(); // 等待getInfo解决
    this.getList(); // getInfo解决后立即调用getList
  },
  methods: {
    showDetail(row){
      this.title = "报修详情";
      this.currentRequest = row;
      this.dialogDetailVisible = true;
    },
    // 获取用户信息
    async getInfo() {
      // 假设getInfo返回一个Promise
      const response = await getInfo();
      this.queryParams.requesterName = response.user.nickName;
    },
    // 获取管理列表
    getList() {
      console.log(this.queryParams)
      listMaintenancerequests(this.queryParams).then(response => {
        this.maintenanceRequestList = response.rows;
        this.total = response.total;
      })
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        requestID: null,
        requesterName: null,
        requesterContact: null,
        requestContent: null,
        status: null,
        createdAt: null,
        updatedAt: null
      };
      this.resetForm("form");
    },
    //
    handleDetail(row){
      this.recordList = [];
      listMaintenancerecords().then(response => {
        const list = response.rows;
        for (let i = 0; i < list.length; i++) {
          if (list[i].requestID === row.requestID) {
            this.recordList.push({
              content: list[i].repairerName + "|" + list[i].repairResult,
              timestamp: list[i].repairTime,
            });
            this.openDetail = true;
          }
        }
      });
      console.log(this.recordList)
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.requestID)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      getInfo().then((response) => {
        this.form.requesterName = response.user.nickName;
        this.form.createdAt = new Date();
        this.form.updatedAt = new Date();
        this.form.requesterContact = response.user.phonenumber;
        this.open = true;
      })

      this.title = "添加维修管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const requestID = row.requestID || this.ids
      getMaintenancerequests(requestID).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改维修请求";
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.requestID != null) {
            updateMaintenancerequests(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addMaintenancerequests(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const requestIDs = row.requestID || this.ids;
      this.$modal.confirm('是否确认删除公告管理编号为"' + requestIDs + '"的数据项？').then(function () {
        return delMaintenancerequests(requestIDs);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('Maintenance/maintenancerequests/export', {
        ...this.queryParams
      }, `maintenancerequests_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
