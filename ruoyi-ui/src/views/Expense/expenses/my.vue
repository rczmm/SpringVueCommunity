<template>
  <div class="container">
    <h2>我的账单</h2>
    <div class="box" style="display: grid;grid-template-columns: auto auto;">
      <el-card class="box-card1">
        <el-timeline v-for="(item, index) in expensesList" v-if="item.paymentStatus==='未缴费'" :key="index">
          <el-timeline-item :timestamp="item.createdAt" placement="top">
            <el-card>
              <p>缴费类型：{{ item.expenseType }}</p>
              <p>金额：{{ item.amount }}</p>
              <p>支付状态：{{ item.paymentStatus }}</p>
              <p>更新时间：{{ item.updatedAt }}
                <el-button type="text" @click="payment(item)">"去缴费"</el-button>
              </p>
            </el-card>
          </el-timeline-item>
        </el-timeline>
      </el-card>
      <el-card class="box-card1">
        <el-timeline v-for="(item, index) in expensesList" v-if="item.paymentStatus==='已缴费'" :key="index">
          <el-timeline-item :timestamp="item.createdAt" placement="top">
            <el-card>
              <p>缴费类型：{{ item.expenseType }}</p>
              <p>金额：{{ item.amount }}</p>
              <p>支付状态：{{ item.paymentStatus }}</p>
              <p>更新时间：{{ item.updatedAt }}</p>
            </el-card>
          </el-timeline-item>
        </el-timeline>
      </el-card>
    </div>
    <el-dialog :title="title" :visible.sync="openPay" width="500px" append-to-body>
      <el-form ref="form" :model="formPay" :rules="rules" label-width="80px">
        <el-form-item label="费用ID" prop="expenseID">
          <el-input v-model="formPay.expenseID" placeholder="请输入费用ID"/>
        </el-form-item>
        <el-form-item label="缴纳人" prop="payerName">
          <el-input v-model="formPay.payerName" placeholder="请输入缴纳人"/>
        </el-form-item>
        <el-form-item label="缴纳时间" prop="paymentTime">
          <el-date-picker clearable
                          v-model="formPay.paymentTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择缴纳时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="缴纳账号" prop="paymentAmount">
          <el-input v-model="formPay.paymentAmount" placeholder="请输入缴纳账号"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitFormPay">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {addExpenses, delExpenses, getExpenses, listExpenses, updateExpenses} from "@/api/Expense/expenses";
import {addPaymentrecords} from "@/api/Expense/paymentrecords";
import {getInfo} from "@/api/login";

export default {
  name: "Expenses",
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
      // 费用管理表格数据
      expensesList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        expenseType: null,
        amount: null,
        paymentStatus: null,
        createdAt: null,
        updatedAt: null,
        userId: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {},
      // 缴费弹出层
      openPay: false,
      // 缴费表单
      formPay: {}
    };
  },
  created() {
    this.getList();
  },
  methods: {
    // 缴费
    payment(item) {
      console.log(this.formPay)
      getInfo().then(response=>{
        this.formPay.payerName = response.user.nickName;
        this.formPay.paymentAmount = response.user.userId;
        this.openPay = true;
      })
      this.formPay.expenseID = item.expenseID;
      this.formPay.paymentTime = new Date();
    },
    // 缴费
    submitFormPay() {
      addPaymentrecords(this.formPay).then(response => {
        this.$modal.msgSuccess("缴费成功");
        this.openPay = false;
        this.getList();
      });
    },
    /** 查询费用管理列表 */
    getList() {
      this.loading = true;
      listExpenses(this.queryParams).then(response => {
        this.expensesList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.openPay = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        expenseID: null,
        expenseType: null,
        amount: null,
        paymentStatus: null,
        createdAt: null,
        updatedAt: null,
        userId: null
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
      this.ids = selection.map(item => item.expenseID)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加费用管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const expenseID = row.expenseID || this.ids
      getExpenses(expenseID).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改费用管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.expenseID != null) {
            updateExpenses(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addExpenses(this.form).then(response => {
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
      const expenseIDs = row.expenseID || this.ids;
      this.$modal.confirm('是否确认删除费用管理编号为"' + expenseIDs + '"的数据项？').then(function () {
        return delExpenses(expenseIDs);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('Expense/expenses/export', {
        ...this.queryParams
      }, `expenses_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
