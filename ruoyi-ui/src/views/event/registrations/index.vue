<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="活动ID" prop="eventID">
        <el-input
          v-model="queryParams.eventID"
          placeholder="请输入活动ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="报名人" prop="participantName">
        <el-input
          v-model="queryParams.participantName"
          placeholder="请输入报名人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="联系方式" prop="participantContact">
        <el-input
          v-model="queryParams.participantContact"
          placeholder="请输入联系方式"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="报名人数" prop="participantCount">
        <el-input
          v-model="queryParams.participantCount"
          placeholder="请输入报名人数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="报名时间" prop="registeredAt">
        <el-date-picker clearable
          v-model="queryParams.registeredAt"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择报名时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['event:registrations:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['event:registrations:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['event:registrations:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['event:registrations:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="registrationsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="报名ID" align="center" prop="registrationID" />
      <el-table-column label="活动ID" align="center" prop="eventID" />
      <el-table-column label="报名人" align="center" prop="participantName" />
      <el-table-column label="联系方式" align="center" prop="participantContact" />
      <el-table-column label="报名人数" align="center" prop="participantCount" />
      <el-table-column label="报名时间" align="center" prop="registeredAt" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.registeredAt, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['event:registrations:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['event:registrations:remove']"
          >删除</el-button>
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

    <!-- 添加或修改活动报名对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="活动ID" prop="eventID">
          <el-input v-model="form.eventID" placeholder="请输入活动ID" />
        </el-form-item>
        <el-form-item label="报名人" prop="participantName">
          <el-input v-model="form.participantName" placeholder="请输入报名人" />
        </el-form-item>
        <el-form-item label="联系方式" prop="participantContact">
          <el-input v-model="form.participantContact" placeholder="请输入联系方式" />
        </el-form-item>
        <el-form-item label="报名人数" prop="participantCount">
          <el-input v-model="form.participantCount" placeholder="请输入报名人数" />
        </el-form-item>
        <el-form-item label="报名时间" prop="registeredAt">
          <el-date-picker clearable
            v-model="form.registeredAt"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择报名时间">
          </el-date-picker>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listRegistrations, getRegistrations, delRegistrations, addRegistrations, updateRegistrations } from "@/api/event/registrations";

export default {
  name: "Registrations",
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
      // 活动报名表格数据
      registrationsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        eventID: null,
        participantName: null,
        participantContact: null,
        participantCount: null,
        registeredAt: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        eventID: [
          { required: true, message: "活动ID不能为空", trigger: "blur" }
        ],
        participantName: [
          { required: true, message: "报名人不能为空", trigger: "blur" }
        ],
        participantContact: [
          { required: true, message: "联系方式不能为空", trigger: "blur" }
        ],
        participantCount: [
          { required: true, message: "报名人数不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询活动报名列表 */
    getList() {
      this.loading = true;
      listRegistrations(this.queryParams).then(response => {
        this.registrationsList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        registrationID: null,
        eventID: null,
        participantName: null,
        participantContact: null,
        participantCount: null,
        registeredAt: null
      };
      this.resetForm("form");
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
      this.ids = selection.map(item => item.registrationID)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加活动报名";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const registrationID = row.registrationID || this.ids
      getRegistrations(registrationID).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改活动报名";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.registrationID != null) {
            updateRegistrations(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addRegistrations(this.form).then(response => {
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
      const registrationIDs = row.registrationID || this.ids;
      this.$modal.confirm('是否确认删除活动报名编号为"' + registrationIDs + '"的数据项？').then(function() {
        return delRegistrations(registrationIDs);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('event/registrations/export', {
        ...this.queryParams
      }, `registrations_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
