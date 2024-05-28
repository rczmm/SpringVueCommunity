<template>
  <div id="app">
    <div id="announcements-container">
      <div class="announcement-card" v-for="(announcement, index) in announcementsList" :key="announcement.id" v-if="index < 5">
        <div class="announcement-image">
          <img src="../assets/404_images/404.png" alt="announcement image">
        </div>
        <div class="announcement-details">
          <h2 class="announcement-title">{{ announcement.title }}</h2>
          <div class="announcement-content" v-html="announcement.content"></div>
          <!-- 其他公告信息 -->
        </div>
      </div>
    </div>
  </div>
</template>

<script>

import {listAnnouncements} from "@/api/Announcement/announcements";

export default {
  props: {
    title: String,
    content: String,
    directory: Array
  },
  name: "Announcements",
  data() {
    return {
      // 公告管理表格数据
      announcementsList: [],
      // 其他数据...
    };
  },
  created() {
    this.getList(); // 获取公告数据
    console.log(this.announcementsList)
  },
  methods: {
    /** 查询公告管理列表 */
    getList() {
      this.loading = true; // 设置加载状态为 true
      listAnnouncements().then(response => {
        this.announcementsList = response.rows; // 将获取到的公告数据赋值给 announcementsList
        this.total = response.total;
        this.loading = false; // 设置加载状态为 false
      }).catch(error => {
        console.error('Error while fetching announcements:', error);
        this.loading = false; // 出现错误时也要设置加载状态为 false
      });
    },
    // 其他方法...
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
  height: 300px ;
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
</style>

