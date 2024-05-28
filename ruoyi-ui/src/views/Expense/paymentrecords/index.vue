<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="费用ID" prop="expenseID">
        <el-input
          v-model="queryParams.expenseID"
          placeholder="请输入费用ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="缴纳人" prop="payerName">
        <el-input
          v-model="queryParams.payerName"
          placeholder="请输入缴纳人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="缴纳时间" prop="paymentTime">
        <el-date-picker clearable
          v-model="queryParams.paymentTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择缴纳时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="缴纳账号" prop="paymentAmount">
        <el-input
          v-model="queryParams.paymentAmount"
          placeholder="请输入缴纳账号"
          clearable
          @keyup.enter.native="handleQuery"
        />
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
          v-hasPermi="['Expense:paymentrecords:add']"
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
          v-hasPermi="['Expense:paymentrecords:edit']"
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
          v-hasPermi="['Expense:paymentrecords:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['Expense:paymentrecords:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="paymentrecordsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="记录ID" align="center" prop="recordID" />
      <el-table-column label="费用ID" align="center" prop="expenseID" />
      <el-table-column label="缴纳人" align="center" prop="payerName" />
      <el-table-column label="缴纳时间" align="center" prop="paymentTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.paymentTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="缴纳账号" align="center" prop="paymentAmount" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['Expense:paymentrecords:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['Expense:paymentrecords:remove']"
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

    <!-- 添加或修改缴费记录对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="费用ID" prop="expenseID">
          <el-input v-model="form.expenseID" placeholder="请输入费用ID" />
        </el-form-item>
        <el-form-item label="缴纳人" prop="payerName">
          <el-input v-model="form.payerName" placeholder="请输入缴纳人" />
        </el-form-item>
        <el-form-item label="缴纳时间" prop="paymentTime">
          <el-date-picker clearable
            v-model="form.paymentTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择缴纳时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="缴纳账号" prop="paymentAmount">
          <el-input v-model="form.paymentAmount" placeholder="请输入缴纳账号" />
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
import { listPaymentrecords, getPaymentrecords, delPaymentrecords, addPaymentrecords, updatePaymentrecords } from "@/api/Expense/paymentrecords";

export default {
  name: "Paymentrecords",
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
      // 缴费记录表格数据
      paymentrecordsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        expenseID: null,
        payerName: null,
        paymentTime: null,
        paymentAmount: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        expenseID: [
          { required: true, message: "费用ID不能为空", trigger: "blur" }
        ],
        payerName: [
          { required: true, message: "缴纳人不能为空", trigger: "blur" }
        ],
        paymentAmount: [
          { required: true, message: "缴纳账号不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询缴费记录列表 */
    getList() {
      this.loading = true;
      listPaymentrecords(this.queryParams).then(response => {
        this.paymentrecordsList = response.rows;
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
        recordID: null,
        expenseID: null,
        payerName: null,
        paymentTime: null,
        paymentAmount: null
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
      this.ids = selection.map(item => item.recordID)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加缴费记录";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const recordID = row.recordID || this.ids
      getPaymentrecords(recordID).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改缴费记录";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.recordID != null) {
            updatePaymentrecords(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addPaymentrecords(this.form).then(response => {
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
      const recordIDs = row.recordID || this.ids;
      this.$modal.confirm('是否确认删除缴费记录编号为"' + recordIDs + '"的数据项？').then(function() {
        return delPaymentrecords(recordIDs);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('Expense/paymentrecords/export', {
        ...this.queryParams
      }, `paymentrecords_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
