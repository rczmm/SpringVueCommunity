<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="投诉人名" prop="complainantName">
        <el-input
          v-model="queryParams.complainantName"
          placeholder="请输入投诉人名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="联系方式" prop="complainantContact">
        <el-input
          v-model="queryParams.complainantContact"
          placeholder="请输入联系方式"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select v-model="status" placeholder="请选择状态">
          <el-option
            v-for="(item,index) in status"
            :key="index"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
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
          v-hasPermi="['Complaint:complaints:add']"
        >新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['Complaint:complaints:edit']"
        >修改
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['Complaint:complaints:remove']"
        >删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['Complaint:complaints:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="complaintsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="投诉人名" align="center" prop="complainantName">
        <template v-slot="{row}">
          <el-button @click="showDetail(row)">
            {{ row.complainantName }}
          </el-button>
        </template>
      </el-table-column>
      <el-table-column label="联系方式" align="center" prop="complainantContact" show-overflow-tooltip/>
      <el-table-column label="投诉内容" align="center" prop="complaintContent" show-overflow-tooltip/>
      <el-table-column label="状态" align="center" prop="status"/>
      <el-table-column label="创建时间" align="center" prop="createdAt" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createdAt, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="更新时间" align="center" prop="updatedAt" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.updatedAt, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['Complaint:complaints:edit']"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['Complaint:complaints:remove']"
          >删除
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-view"
            @click="handlingRecords(scope.row)"
            v-hasPermi="['Complaint:handlingrecords:view']"
          >去处理
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

    <!-- 添加或修改处理记录对话框   -->
    <el-dialog :visible.sync="openRecord" width="500px">
      <el-form ref="formRecord" :model="recordForm" label-width="80px">
        <el-form-item label="处理人" prop="handlerName">
          <el-input v-model="recordForm.handlerName" placeholder="请输入处理人姓名"/>
        </el-form-item>
        <el-form-item label="处理结果" prop="handleResult">
          <el-input v-model="recordForm.handleResult" type="textarea" placeholder="请输入内容"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitFormRecord">确 定</el-button>
      </div>
    </el-dialog>

    <!-- 详情页面对话框   -->
    <el-dialog title="处理详情" :visible.sync="openDetail" width="500px">

      <el-table
        :data="detailList"
        style="width: 100%"
        :row-class-name="tableRowClassName">
        <el-table-column
          prop="handlerName"
          label="处理人"
          width="180">
        </el-table-column>
        <el-table-column
          prop="handleTime"
          label="处理时间"
          width="180">
        </el-table-column>
        <el-table-column
          prop="handleResult"
          label="处理结果"
          width="180"
        >
        </el-table-column>
      </el-table>
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
      // 是否显示处理记录弹出层
      openRecord: false,
      // 是否显示详情弹出层,
      openDetail: false,
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
      ]
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询投诉管理列表 */
    getList() {
      this.loading = true;
      listComplaints(this.queryParams).then(response => {
        this.complaintsList = response.rows;
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
      this.open = true;
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
    handleDelete(row) {
      const complaintIDs = row.complaintID || this.ids;
      this.$modal.confirm('是否确认删除投诉管理编号为"' + complaintIDs + '"的数据项？').then(function () {
        return delComplaints(complaintIDs);
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
