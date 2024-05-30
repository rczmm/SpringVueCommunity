<template>
  <div class="app-container">

    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="姓名" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="身份证号" prop="idnumber">
        <el-input
          v-model="queryParams.idnumber"
          placeholder="请输入身份证号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="联系方式" prop="contact">
        <el-input
          v-model="queryParams.contact"
          placeholder="请输入联系方式"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="民族" prop="nation">
        <el-input
          v-model="queryParams.nation"
          placeholder="请输入民族"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="职业" prop="profession">
        <el-input
          v-model="queryParams.profession"
          placeholder="请输入职业"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="教育背景" prop="educationLevel">
        <el-input
          v-model="queryParams.educationLevel"
          placeholder="请输入教育背景"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="宗教信仰" prop="religiousBelief">
        <el-input
          v-model="queryParams.religiousBelief"
          placeholder="请输入宗教信仰"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="健康状况" prop="healthCondition">
        <el-input
          v-model="queryParams.healthCondition"
          placeholder="请输入健康状况"
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
          v-hasPermi="['person:residents:add']"
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
          v-hasPermi="['person:residents:edit']"
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
          v-hasPermi="['person:residents:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['person:residents:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="residentsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="姓名" align="center" prop="name" >
        <template v-slot="{row}">
          <el-button @click="showDetail(row)" >
            {{row.name}}
          </el-button>
        </template>
      </el-table-column>
      <el-table-column label="性别" align="center" prop="gender" />
      <el-table-column label="出生日期" align="center" prop="dateOfBirth" width="180" show-overflow-tooltip>
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.dateOfBirth, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="身份证号" align="center" prop="idnumber" show-overflow-tooltip/>
      <el-table-column label="联系方式" align="center" prop="contact" show-overflow-tooltip/>
      <el-table-column label="户籍地址" align="center" prop="registeredAddress" show-overflow-tooltip/>
      <el-table-column label="居住地址" align="center" prop="residentialAddress" show-overflow-tooltip/>
      <el-table-column label="民族" align="center" prop="nation" />
      <el-table-column label="电子邮箱" align="center" prop="email" show-overflow-tooltip />
      <el-table-column label="工作单位" align="center" prop="workUnit" show-overflow-tooltip/>
      <el-table-column label="职业" align="center" prop="profession" />
      <el-table-column label="教育背景" align="center" prop="educationLevel" />
      <el-table-column label="备注" align="center" prop="note" show-overflow-tooltip/>
      <el-table-column label="紧急联系人" align="center" prop="emergencyContact" show-overflow-tooltip/>
      <el-table-column label="宗教信仰" align="center" prop="religiousBelief" />
      <el-table-column label="健康状况" align="center" prop="healthCondition" />
      <el-table-column label="子女信息" align="center" prop="childrenInformation" show-overflow-tooltip/>
      <el-table-column label="婚姻状况" align="center" prop="maritalStatus" />
      <el-table-column label="操作" align="center"  fixed="right" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['person:residents:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['person:residents:remove']"
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

    <!-- 添加或修改居民信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="姓名" prop="name">
          <el-input v-model="form.name" placeholder="请输入姓名" />
        </el-form-item>
        <el-form-item label="出生日期" prop="dateOfBirth">
          <el-date-picker clearable
            v-model="form.dateOfBirth"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择出生日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="身份证号" prop="idnumber">
          <el-input v-model="form.idnumber" placeholder="请输入身份证号" />
        </el-form-item>
        <el-form-item label="联系方式" prop="contact">
          <el-input v-model="form.contact" placeholder="请输入联系方式" />
        </el-form-item>
        <el-form-item label="户籍地址" prop="registeredAddress">
          <el-input v-model="form.registeredAddress" placeholder="请输入户籍地址" />
        </el-form-item>
        <el-form-item label="居住地址" prop="residentialAddress">
          <el-input v-model="form.residentialAddress" placeholder="请输入居住地址" />
        </el-form-item>
        <el-form-item label="民族" prop="nation">
          <el-input v-model="form.nation" placeholder="请输入民族" />
        </el-form-item>
        <el-form-item label="电子邮箱" prop="email">
          <el-input v-model="form.email" placeholder="请输入电子邮箱" />
        </el-form-item>
        <el-form-item label="工作单位" prop="workUnit">
          <el-input v-model="form.workUnit" placeholder="请输入工作单位" />
        </el-form-item>
        <el-form-item label="职业" prop="profession">
          <el-input v-model="form.profession" placeholder="请输入职业" />
        </el-form-item>
        <el-form-item label="教育背景" prop="educationLevel">
          <el-input v-model="form.educationLevel" placeholder="请输入教育背景" />
        </el-form-item>
        <el-form-item label="备注" prop="note">
          <el-input v-model="form.note" placeholder="请输入备注" />
        </el-form-item>
        <el-form-item label="紧急联系人" prop="emergencyContact">
          <el-input v-model="form.emergencyContact" placeholder="请输入紧急联系人" />
        </el-form-item>
        <el-form-item label="宗教信仰" prop="religiousBelief">
          <el-input v-model="form.religiousBelief" placeholder="请输入宗教信仰" />
        </el-form-item>
        <el-form-item label="健康状况" prop="healthCondition">
          <el-input v-model="form.healthCondition" placeholder="请输入健康状况" />
        </el-form-item>
        <el-form-item label="子女信息" prop="childrenInformation">
          <el-input v-model="form.childrenInformation" placeholder="请输入子女信息" />
        </el-form-item>
        <el-form-item label="婚姻状况" prop="maritalStatus">
          <el-input v-model="form.maritalStatus" placeholder="请输入婚姻状况" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 居民详情弹出层 -->
    <el-dialog :title="title"  width="500px" :visible.sync="dialogDetailVisible">
      <el-card class="box-card">
        <el-descriptions title="个人信息" direction="vertical" :column="4" border>
          <el-descriptions-item label="姓名">{{currentResident.name}}</el-descriptions-item>
          <el-descriptions-item label="性别">
            <el-tag>{{currentResident.gender}}</el-tag>
            </el-descriptions-item>
          <el-descriptions-item label="出生日期" :span="2">{{currentResident.dateOfBirth}}</el-descriptions-item>
          <el-descriptions-item label="身份证号" :span="2">{{currentResident.idnumber}}</el-descriptions-item>
          <el-descriptions-item label="联系方式" :span="2">{{currentResident.contact}}</el-descriptions-item>
          <el-descriptions-item label="户籍地址" :span="2">{{currentResident.registeredAddress}}</el-descriptions-item>
          <el-descriptions-item label="居住地址" :span="2">{{currentResident.residentialAddress}}</el-descriptions-item>
          <el-descriptions-item label="民族">
            <el-tag>
              {{currentResident.nation}}
            </el-tag>
            </el-descriptions-item>
          <el-descriptions-item label="电子邮箱" :span="2">{{currentResident.email}}</el-descriptions-item>
          <el-descriptions-item label="工作单位">{{currentResident.workUnit}}</el-descriptions-item>
          <el-descriptions-item label="职业">{{currentResident.profession}}</el-descriptions-item>
          <el-descriptions-item label="教育背景">{{currentResident.educationLevel}}</el-descriptions-item>
          <el-descriptions-item label="宗教信仰">
            <el-tag>
              {{currentResident.religiousBelief}}
            </el-tag>
              </el-descriptions-item>
          <el-descriptions-item label="健康状况">
            <el-tag>
              {{currentResident.healthCondition}}
            </el-tag>
            </el-descriptions-item>
          <el-descriptions-item label="子女信息">{{currentResident.childrenInformation}}</el-descriptions-item>
          <el-descriptions-item label="婚姻状况">
            <el-tag>
              {{currentResident.maritalStatus}}
            </el-tag>
            </el-descriptions-item>
          <el-descriptions-item label="备注">{{currentResident.note}}</el-descriptions-item>
          <el-descriptions-item label="紧急联系人">{{currentResident.emergencyContact}}</el-descriptions-item>
          <el-descriptions-item label="创建时间">{{currentResident.createdAt}}</el-descriptions-item>
          <el-descriptions-item label="更新时间">{{currentResident.updatedAt}}</el-descriptions-item>
        </el-descriptions>
      </el-card>
    </el-dialog>
  </div>
</template>

<script>
import { listResidents, getResidents, delResidents, addResidents, updateResidents } from "@/api/person/residents";

export default {
  name: "Residents",
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
      // 居民信息表格数据
      residentsList: [],
      // 当前居民信息
      currentResident: {
        residentID: null,
        name: null,
        gender: null,
        dateOfBirth: null,
        idnumber: null,
        contact: null,
        registeredAddress: null,
        residentialAddress: null,
        createdAt: null,
        updatedAt: null,
        nation: null,
        email: null,
        workUnit: null,
        profession: null,
        educationLevel: null,
        note: null,
        emergencyContact: null,
        religiousBelief: null,
        healthCondition: null,
        childrenInformation: null,
        maritalStatus: null
      },
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 是否显示详情页面
      dialogDetailVisible: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: null,
        gender: null,
        dateOfBirth: null,
        idnumber: null,
        contact: null,
        registeredAddress: null,
        residentialAddress: null,
        createdAt: null,
        updatedAt: null,
        nation: null,
        email: null,
        workUnit: null,
        profession: null,
        educationLevel: null,
        note: null,
        emergencyContact: null,
        religiousBelief: null,
        healthCondition: null,
        childrenInformation: null,
        maritalStatus: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        name: [
          { required: true, message: "姓名不能为空", trigger: "blur" }
        ],
        gender: [
          { required: true, message: "性别不能为空", trigger: "blur" }
        ],
        dateOfBirth: [
          { required: true, message: "出生日期不能为空", trigger: "blur" }
        ],
        idnumber: [
          { required: true, message: "身份证号不能为空", trigger: "blur" }
        ],
        contact: [
          { required: true, message: "联系方式不能为空", trigger: "blur" }
        ],
        registeredAddress: [
          { required: true, message: "户籍地址不能为空", trigger: "blur" }
        ],
        residentialAddress: [
          { required: true, message: "居住地址不能为空", trigger: "blur" }
        ],
        nation: [
          { required: true, message: "民族不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询居民信息列表 */
    getList() {
      this.loading = true;
      listResidents(this.queryParams).then(response => {
        this.residentsList = response.rows;
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
        residentID: null,
        name: null,
        gender: null,
        dateOfBirth: null,
        idnumber: null,
        contact: null,
        registeredAddress: null,
        residentialAddress: null,
        createdAt: null,
        updatedAt: null,
        nation: null,
        email: null,
        workUnit: null,
        profession: null,
        educationLevel: null,
        note: null,
        emergencyContact: null,
        religiousBelief: null,
        healthCondition: null,
        childrenInformation: null,
        maritalStatus: null
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
      this.ids = selection.map(item => item.residentID)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加居民信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const residentID = row.residentID || this.ids
      getResidents(residentID).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改居民信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.residentID != null) {
            updateResidents(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addResidents(this.form).then(response => {
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
      const residentIDs = row.residentID || this.ids;
      this.$modal.confirm('是否确认删除居民信息编号为"' + residentIDs + '"的数据项？').then(function() {
        return delResidents(residentIDs);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('person/residents/export', {
        ...this.queryParams
      }, `residents_${new Date().getTime()}.xlsx`)
    },
    /** 展示居民详情 */
    showDetail(row){
      this.title = "居民详情";
      this.currentResident = row;
      this.dialogDetailVisible = true;
    }

  }
};
</script>
