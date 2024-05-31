<template>
  <el-card class="container">
    <el-table :data="announcementsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="公告标题" align="center" prop="title"/>
      <el-table-column label="公告内容" align="center" prop="content" show-overflow-tooltip/>
      <el-table-column label="发布时间" align="center" prop="publishedAt" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.publishedAt, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="作者" align="center" prop="author"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template v-slot="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['Announcement:announcements:edit']"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['Announcement:announcements:remove']"
          >删除
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
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="公告标题" prop="title">
          <el-input v-model="form.title" placeholder="请输入公告标题"/>
        </el-form-item>
        <el-form-item label="公告内容">
          <editor v-model="form.content" :min-height="192"/>
        </el-form-item>
        <el-form-item label="发布时间" prop="publishedAt">
          <el-date-picker clearable
                          v-model="form.publishedAt"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择发布时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="作者" prop="author">
          <el-input v-model="form.author" placeholder="请输入作者"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </el-card>
</template>
<script>
import {
  addAnnouncements,
  delAnnouncements,
  getAnnouncements,
  getAnnouncementsByAuthorId,
  updateAnnouncements
} from "@/api/Announcement/announcements";
import {getInfo} from "@/api/login";

export default {
  name: "Announcements",
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
      announcementsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        authorId: null
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
      }
    };
  },
  async created() {
    await this.getInfo(); // 等待getInfo解决
    this.getList(); // getInfo解决后立即调用getList
  },
  methods: {
    async getInfo() {
      // 假设getInfo返回一个Promise
      const response = await getInfo();
      this.queryParams.authorId = response.user.userId;
    },
    getList() {
      getAnnouncementsByAuthorId(this.queryParams.authorId).then(response => {
        this.announcementsList = response.rows;
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
        announcementID: null,
        title: null,
        content: null,
        publishedAt: null,
        author: null,
        authorId: null
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
      this.ids = selection.map(item => item.announcementID)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      getInfo().then((response) => {
        this.form.author = response.user.nickName;
        this.form.authorId = response.user.userId;
        this.form.publishedAt = new Date();
        this.open = true;
      })

      this.title = "添加公告管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const announcementID = row.announcementID || this.ids
      getAnnouncements(announcementID).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改公告管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.announcementID != null) {
            updateAnnouncements(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addAnnouncements(this.form).then(response => {
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
      const announcementIDs = row.announcementID || this.ids;
      this.$modal.confirm('是否确认删除公告管理编号为"' + announcementIDs + '"的数据项？').then(function () {
        return delAnnouncements(announcementIDs);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('Announcement/announcements/export', {
        ...this.queryParams
      }, `announcements_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
