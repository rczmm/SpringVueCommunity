<template>
  <div class="app-container">

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
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['person:relationships:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <e-charts class="chart" :option="option"/>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改关系信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="居民ID" prop="residentID">
          <el-input v-model="form.residentID" placeholder="请输入居民ID" />
        </el-form-item>
        <el-form-item label="家庭ID" prop="familyID">
          <el-input v-model="form.familyID" placeholder="请输入家庭ID" />
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
import {getResidents} from "@/api/person/residents";

export default {
  name: "Relationships",
  data() {
    return {
      option: null,
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
      // 关系信息表格数据
      relationshipsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        residentID: null,
        familyID: null,
        relationshipWithHead: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        residentID: [
          {required: true, message: "居民ID不能为空", trigger: "blur"}
        ],
        familyID: [
          {required: true, message: "家庭ID不能为空", trigger: "blur"}
        ],
        relationshipWithHead: [
          {required: true, message: "与户主关系不能为空", trigger: "blur"}
        ]
      },
      //
      familyIds: [],
      //
      links: [],
      //
      nodes:[],
      //
      nodeId:0
    };
  },
  created() {
    this.getList();
    this.getOptions()
  },
  methods: {
    /** 查询关系信息列表 */
    getList() {
      this.loading = true;
      listRelationships(this.queryParams).then(response => {
        this.relationshipsList = response.data;
        this.loading = false;
        for(const item of Object.keys(this.relationshipsList)){
          this.familyIds.push(
            {"name":item}
          )
          this.nodes.push(
            {
              "id":"f"+item,
              "name":item,
              "symbolSize": 40,
              "value":"家庭",
              "category": item-1
            }
          )
        }
        for(const items of Object.values(this.relationshipsList)){
          for(const item of items){
            getResidents(item.residentID).then(response=>{
              const name = response.data.name;
              this.links.push(
                {"source":"r"+item.residentID,"target":"f"+item.familyID,"category":item.relationshipWithHead}
              );
              this.nodes.push(
                {
                  "id":"r"+item.residentID,
                  "name":name,
                  "symbolSize": 20,
                  "value": "居民",
                  "category": item.familyID -1
                }
              )
            })
          }
        }
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
      this.ids = selection.map(item => item.relationshipID)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加关系信息";
      this.getList();
      this.getOptions()
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const relationshipID = row.relationshipID || this.ids
      getRelationships(relationshipID).then(response => {
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
      const relationshipIDs = row.relationshipID || this.ids;
      this.$modal.confirm('是否确认删除关系信息编号为"' + relationshipIDs + '"的数据项？').then(function () {
        return delRelationships(relationshipIDs);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('person/relationships/export', {
        ...this.queryParams
      }, `relationships_${new Date().getTime()}.xlsx`)
    },
    //
    getOptions() {
      this.option = {
        title: {
          text: '人物与家庭关系图',
          top: 'bottom',
          left: 'right'
        },
        tooltip: {},
        legend: [
          {
            data: this.familyIds
          }
        ],
        animationDuration: 1500,
        animationEasingUpdate: 'quinticInOut',
        series: [
          {
            name: 'Les Miserables',
            type: 'graph',
            legendHoverLink: false,
            layout: 'circular',
            data: this.nodes,
            links: this.links,
            categories: this.familyIds,
            roam: true,
            label: {
              show: false,
              position: 'right',
              formatter: '{b}'
            },
            edgeLabel: {
              normal: {
                show: true,
                textStyle: {
                  fontSize: 14,
                },
                formatter:function(params){
                  return params.data.category;
                }
              }
            },
            lineStyle: {
              color: 'source',
              curveness: 0.3
            },
            emphasis: {
              focus: 'adjacency',
              lineStyle: {
                width: 10
              }
            }
          }
        ]
      }
    }
  }
}
</script>

<style scoped>
.chart{
  width: 100%;
  height: 400px;
}
</style>
