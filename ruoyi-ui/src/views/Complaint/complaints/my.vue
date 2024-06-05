<template>
  <div class="container">
    <h2>我的投诉 <el-button type="text" @click="handleAdd" v-hasPermi="['Complaint:complaints:add']">我要投诉</el-button></h2>
    <div class="box" style="display: grid;grid-template-columns: auto auto auto;">
      <el-card class="box-card1">
        <el-timeline v-for="(item, index) in complaintsList" v-if="item.status==='待处理'" :key="index">
          <el-timeline-item :timestamp="item.updatedAt
" placement="top">
            <el-card>
              <p>编号：{{item.complaintID}}</p>
              投诉内容：<pre v-html="item.complaintContent"></pre>
              <p>状态：{{ item.status }}</p>
              <span>更新时间：{{ item.updatedAt }}</span>
              <el-button type="text"  @click="handleDelete(item.complaintID)" v-hasPermi="['Complaint:complaints:remove']">撤销</el-button>
            </el-card>
          </el-timeline-item>
        </el-timeline>
      </el-card>
      <el-card class="box-card2">
        <el-timeline v-for="(item, index) in complaintsList" v-if="item.status==='处理中'" :key="index">
          <el-timeline-item :timestamp="item.updatedAt
" placement="top">
            <el-card>
              <p>投诉内容：{{ item.expenseType }}</p>
              <p>联系方式：{{ item.complainantContact }}</p>
              <p>状态：{{ item.status }}</p>
              <p>更新时间：{{ item.updatedAt }}</p>
            </el-card>
          </el-timeline-item>
        </el-timeline>
      </el-card>
      <el-card class="box-card3">
        <el-timeline v-for="(item, index) in complaintsList" v-if="item.status==='已处理'" :key="index">
          <el-timeline-item :timestamp="item.updatedAt
" placement="top">
            <el-card>
              <p>投诉内容：{{ item.expenseType }}</p>
              <p>联系方式：{{ item.complainantContact }}</p>
              <p>状态：{{ item.status }}</p>
              <p>更新时间：{{ item.updatedAt }}</p>
            </el-card>
          </el-timeline-item>
        </el-timeline>
      </el-card>
    </div>
    <!-- 添加或修改投诉管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="投诉人名" prop="complainantName">
          <el-input v-model="form.complainantName" placeholder="请输入投诉人名"/>
        </el-form-item>
        <el-form-item label="联系方式" prop="complainantContact">
          <el-input v-model="form.complainantContact" placeholder="请输入联系方式"/>
        </el-form-item>
        <el-form-item label="投诉内容">
          <editor v-model="form.complaintContent" :min-height="192"/>
        </el-form-item>
        <el-form-item label="创建时间" prop="createdAt">
          <el-date-picker clearable
                          v-model="form.createdAt"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择创建时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="更新时间" prop="updatedAt">
          <el-date-picker clearable
                          v-model="form.updatedAt"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择更新时间">
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
import {
  addComplaints,
  delComplaints,
  getComplaints,
  listComplaints,
  updateComplaints
} from "@/api/Complaint/complaints";

import {addHandlingrecords, listHandlingrecords} from "@/api/Complaint/handlingrecords";
import {getInfo} from "@/api/login";

export default {
  name: "Complaints",
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
      // 投诉管理表格数据
      complaintsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        complainantName: null,
        complainantContact: null,
        complaintContent: null,
        status: null,
        createdAt: null,
        updatedAt: null
      },
      // 表单参数
      form: {},
      // 处理记录表单参数
      recordForm: {
        complaintID: null,
        handlerName: null,
        handleTime: null,
        handleResult: null
      },
      // 处理记录查询参数
      recordQueryForm: {
        pageNum: 1,
        pageSize: 10,
        complaintID: null,
        handlerName: null,
        handleTime: null,
        handleResult: null
      },
      // 表单校验
      rules: {
        complainantName: [
          {required: true, message: "投诉人名不能为空", trigger: "blur"}
        ],
        complainantContact: [
          {required: true, message: "联系方式不能为空", trigger: "blur"}
        ],
        complaintContent: [
          {required: true, message: "投诉内容不能为空", trigger: "blur"}
        ],
      },
      // 是否显示详情弹出层,
      openComplaint:false,
      //
      detailList: [],
      //
      status: [
        {
          "label": "处理中",
          "value": 0
        }, {
          "label": "待处理",
          "value": 0
        }, {
          "label": "已处理",
          "value": 0
        }
      ],
      //
      formComplaint:{
        complainantName: null,
        complainantContact: null,
        complaintContent: null,
        status: null,
        createdAt: null,
        updatedAt: null
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询投诉管理列表 */
    getList() {

      getInfo().then(response => {
        this.loading = true;
        this.queryParams.complainantName = response.user.nickName;
        listComplaints(this.queryParams).then(response => {
          this.complaintsList = response.rows;
          this.total = response.total;
          this.loading = false;
        });
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
        complaintID: null,
        complainantName: null,
        complainantContact: null,
        complaintContent: null,
        status: null,
        createdAt: null,
        updatedAt: null
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
      this.ids = selection.map(item => item.complaintID)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      getInfo().then(response=>{
        this.form.complainantName = response.user.nickName;
        this.form.complainantContact = response.user.phonenumber;
        this.form.createdAt = new Date();
        this.form.updatedAt = new Date();
        this.open = true;
      })

      this.title = "添加投诉管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const complaintID = row.complaintID || this.ids
      getComplaints(complaintID).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改投诉管理";
      });
    },
    // 去处理按钮操作
    handlingRecords(row) {
      this.openRecord = true;
      this.recordForm.complaintID = row.complaintID;
      this.recordForm.handleTime = new Date();
    },
    // 提交处理记录按钮
    submitFormRecord() {
      this.$refs["formRecord"].validate(valid => {
        if (valid) {
          addHandlingrecords(this.recordForm).then(Response => {
            this.$modal.msgSuccess("处理完毕");
            this.openRecord = false;
          })
        }
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.complaintID != null) {
            updateComplaints(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addComplaints(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(complaintID) {
      this.$modal.confirm('是否确认删除投诉管理编号为"' + complaintID + '"的数据项？').then(function () {
        return delComplaints(complaintID);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('Complaint/complaints/export', {
        ...this.queryParams
      }, `complaints_${new Date().getTime()}.xlsx`)
    },
    //
    showDetail(row) {
      this.detailList = [];
      this.openDetail = true;
      this.recordQueryForm.complaintID = row.complaintID;
      console.log(this.recordQueryForm)
      listHandlingrecords(this.recordQueryForm).then(reponse => {
        this.detailList = reponse.rows;
      })
    },
    //
    tableRowClassName(row) {
      if (row.handleResult === "完成") {
        return 'success-row';
      } else {
        return 'warning-row';
      }
    }
  }
};
</script>
