<template>
  <div class="app">
    <div class="carousel">
      <el-carousel :interval="4000" type="card" height="200px">
        <el-carousel-item v-for="item in pictures" :key="item">
          <img :src="item" alt="" style="max-width: 100%; height: auto;">
        </el-carousel-item>
      </el-carousel>
    </div>

    <div class="main" style="display: grid;grid-template-columns: auto auto">

      <div class="log_card" style="display: inline-block;">
        <el-card>
          <span>最近操作日志</span>
        </el-card>
        <el-timeline v-for="(operation_log, index) in operation_logList"
                     :key="operation_log.id" v-if="index < 5">
          <el-timeline-item :timestamp="operation_log.operTime"
                            :type="operation_log.jsonResult.code === 200 ? 'primary' : 'success'">
            <el-card>
              <h4>请求方法：{{ operation_log.requestMethod }}</h4>
              <p>请求地址：{{operation_log.operUrl}}</p>
              <p>{{ operation_log.operLocation }} {{ operation_log.operIp }}</p>
              <p>部门名：{{ operation_log.deptName }}  操作者：{{ operation_log.operName }}</p>
            </el-card>
          </el-timeline-item>
        </el-timeline>
      </div>

      <div class="announcements_card" style="display:inline-block;">
        <el-card class="card-box">
          <div slot="header" class="clearfix">
            <span>社区公告通知！</span>
          </div>
          <div class="coll_space" v-for="(announcement, index) in announcementsList"
               :key="announcement.id" v-if="index < 10">
            <el-collapse>
              <el-collapse-item :title="announcement.title" :name="1">
                <span class="announcement-time">时间：{{ announcement.publishedAt }}   </span>
                <span class="announcement-author">作者：{{ announcement.author }}</span>
                <div class="announcement-content" v-html="announcement.content"/>
              </el-collapse-item>
            </el-collapse>
          </div>
        </el-card>
      </div>
    </div>
  </div>
</template>

<script>

import {listAnnouncements} from "@/api/Announcement/announcements";
import {list} from "@/api/monitor/operlog"
import axios from "axios";

export default {
  props: {
    title: String,
    content: String,
    directory: Array
  },
  name: "Announcements",
  data() {
    return {
      // 公告管理数目
      announcements_total: 0,
      // 操作日志数目
      operation_log_total: 0,
      // 公告管理数据
      announcementsList: [],
      // 操作日志数据
      operation_logList: [],
      //
      pictures :[
        "/static/picture/天气之子.jpg",
        "/static/picture/天气之子2.jpg",
        "/static/picture/天气之子3.jpg",
        "/static/picture/天气之子4.jpg",
        "/static/picture/天气之子5.jpg",
        "/static/picture/天气之子6.jpg"
      ]
    };
  },
  created() {
    this.getList(); // 获取公告数据
  },
  methods: {
    /** 查询列表 */
    getList() {
      this.loading = true; // 设置加载状态为 true
      listAnnouncements().then(response => {
        this.announcementsList = response.rows; // 将获取到的公告数据赋值给 announcementsList
        this.announcements_total = response.total;
        this.announcementsList.reverse();
        this.loading = false; // 设置加载状态为 false
      }).catch(error => {
        console.error('Error while fetching announcements:', error);
        this.loading = false; // 出现错误时也要设置加载状态为 false
      });
      list().then(response => {
        this.operation_logList = response.rows;
        this.operation_log_total = response.total;
        this.loading = false;
      })
    },
  }
};


</script>

<style scoped lang="scss">
.announcements-container {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
}

.announcement-card {
  display: flex;
  width: calc(90% - 20px);
  height: 300px;
  margin: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
}

.announcement-image {
  width: 40%;
  padding: 10px;
}

.announcement-image img {
  width: 100%;
  height: 100%;
}

.announcement-details {
  flex: 1;
  padding: 10px;
}

.announcement-title {
  font-size: 18px;
  font-weight: bold;
  margin-bottom: 10px;
}

.announcement-content {
  font-size: 14px;
  color: #666;
}

.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 200px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n+1) {
  background-color: #d3dce6;
}
</style>

