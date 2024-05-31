<template>

  <el-card :title="title">

    <el-card>
      <p>{{ this.title}} </p>
    </el-card>

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
    <el-button type="primary" @click="submitForm">发 布</el-button>

  </el-card>

</template>

<script>

import {
  addAnnouncements,
  listAnnouncements,
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
        title: null,
        content: null,
        publishedAt: null,
        author: null,
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
  created() {
    getInfo().then((response) => {
      this.title = "发布公告"
      this.form.author = response.user.nickName;
      this.form.authorId = response.user.userId;
      this.form.publishedAt = new Date();
      this.reset();
    });
  },
  methods: {
    /** 查询公告管理列表 */
    getList() {
      this.loading = true;
      listAnnouncements(this.queryParams).then(response => {
        this.announcementsList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
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
      getInfo().then((response) => {
        console.log(response);
        this.form.author = response.user.nickName;
        this.form.authorId = response.user.userId;
        this.form.publishedAt = new Date();
        this.open = true;
      })
      this.resetForm("form");
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
  }
};
</script>
