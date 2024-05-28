<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="居民ID" prop="residentID">
        <el-input
          v-model="queryParams.residentID"
          placeholder="请输入居民ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="家庭ID" prop="familyID">
        <el-input
          v-model="queryParams.familyID"
          placeholder="请输入家庭ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="与户主关系" prop="relationshipWithHead">
        <el-input
          v-model="queryParams.relationshipWithHead"
          placeholder="请输入与户主关系"
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
          v-hasPermi="['person:relationships:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="info"
          plain
          icon="el-icon-sort"
          size="mini"
          @click="toggleExpandAll"
        >展开/折叠</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table
      v-if="refreshTable"
      v-loading="loading"
      :data="relationshipsList"
      row-key="relationshipID"
      :default-expand-all="isExpandAll"
      :tree-props="{children: 'children', hasChildren: 'hasChildren'}"
    >
      <el-table-column label="居民ID" prop="residentID" />
      <el-table-column label="家庭ID" align="center" prop="familyID" />
      <el-table-column label="与户主关系" align="center" prop="relationshipWithHead" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['person:relationships:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-plus"
            @click="handleAdd(scope.row)"
            v-hasPermi="['person:relationships:add']"
          >新增</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['person:relationships:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 添加或修改关系信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="居民ID" prop="residentID">
          <el-input v-model="form.residentID" placeholder="请输入居民ID" />
        </el-form-item>
        <el-form-item label="家庭ID" prop="familyID">
          <treeselect v-model="form.familyID" :options="relationshipsOptions" :normalizer="normalizer" placeholder="请选择家庭ID" />
        </el-form-item>
        <el-form-item label="与户主关系" prop="relationshipWithHead">
          <el-input v-model="form.relationshipWithHead" placeholder="请输入与户主关系" />
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
import { listRelationships, getRelationships, delRelationships, addRelationships, updateRelationships } from "@/api/person/relationships";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";

export default {
  name: "Relationships",
  components: {
    Treeselect
  },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 显示搜索条件
      showSearch: true,
      // 关系信息表格数据
      relationshipsList: [],
      // 关系信息树选项
      relationshipsOptions: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 是否展开，默认全部展开
      isExpandAll: true,
      // 重新渲染表格状态
      refreshTable: true,
      // 查询参数
      queryParams: {
        residentID: null,
        familyID: null,
        relationshipWithHead: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        residentID: [
          { required: true, message: "居民ID不能为空", trigger: "blur" }
        ],
        familyID: [
          { required: true, message: "家庭ID不能为空", trigger: "blur" }
        ],
        relationshipWithHead: [
          { required: true, message: "与户主关系不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询关系信息列表 */
    getList() {
      this.loading = true;
      listRelationships(this.queryParams).then(response => {
        this.relationshipsList = this.handleTree(response.data, "relationshipID", "familyID");
        this.loading = false;
      });
    },
    /** 转换关系信息数据结构 */
    normalizer(node) {
      if (node.children && !node.children.length) {
        delete node.children;
      }
      return {
        id: node.relationshipID,
        label: node.residentID,
        children: node.children
      };
    },
	/** 查询关系信息下拉树结构 */
    getTreeselect() {
      listRelationships().then(response => {
        this.relationshipsOptions = [];
        const data = { relationshipID: 0, residentID: '顶级节点', children: [] };
        data.children = this.handleTree(response.data, "relationshipID", "familyID");
        this.relationshipsOptions.push(data);
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
        relationshipID: null,
        residentID: null,
        familyID: null,
        relationshipWithHead: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    /** 新增按钮操作 */
    handleAdd(row) {
      this.reset();
      this.getTreeselect();
      if (row != null && row.relationshipID) {
        this.form.familyID = row.relationshipID;
      } else {
        this.form.familyID = 0;
      }
      this.open = true;
      this.title = "添加关系信息";
    },
    /** 展开/折叠操作 */
    toggleExpandAll() {
      this.refreshTable = false;
      this.isExpandAll = !this.isExpandAll;
      this.$nextTick(() => {
        this.refreshTable = true;
      });
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      this.getTreeselect();
      if (row != null) {
        this.form.familyID = row.familyID;
      }
      getRelationships(row.relationshipID).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改关系信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.relationshipID != null) {
            updateRelationships(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addRelationships(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除关系信息编号为"' + row.relationshipID + '"的数据项？').then(function() {
        return delRelationships(row.relationshipID);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    }
  }
};
</script>
