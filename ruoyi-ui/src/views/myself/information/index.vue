<template>
  <div>
    <el-card>
      <el-descriptions class="margin-top" title="个人简介" :column="2" border>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-picture-outline"></i>
            头像
          </template>
          <img class="img" :src="avatar" alt=""/>
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-user"></i>
            账户名
          </template>
          {{ residentID }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-s-custom"></i>
            昵称
          </template>
          {{ name }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-odometer"></i>
            年龄
          </template>
          {{ age }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-male"></i>
            <i class="el-icon-female"></i>
            性别
          </template>
          <el-tag size="small">{{ gender }}</el-tag>
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-message"></i>
            邮箱Email
          </template>
          {{ email }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-mobile-phone"></i>
            手机号码
          </template>
          {{ mobilePhoneNumber }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-location-outline"></i>
            地区
          </template>
          {{ area }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-office-building"></i>
            职业
          </template>
          {{ work }}
        </el-descriptions-item>

        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-basketball"></i>
            兴趣爱好
          </template>
          {{ hobby }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-magic-stick"></i>
            个性签名
          </template>
          {{ design }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-date"></i>
            注册日期
          </template>
          {{ createDate | formatDate }}
        </el-descriptions-item>
      </el-descriptions>
    </el-card>
  </div>
</template>

<script>
import {getResidents} from "@/api/person/residents";
import {getUserProfile} from "@/api/system/user";

export default {
  name: "Residents",
  data() {
    return {
      avatar: '',
      residentID: '',
      age: '',
      email: '',
      mobilePhoneNumber: '',
      area: '',
      createDate: '',
      name: '',
      gender: '',
      work: '',
      hobby: '',
      design: '',
      userId: ''
    };
  },
  created() {
    this.getUserId();
    this.getList();
  },
  methods: {
    getUserId() {
      return new Promise((resolve, reject) => {
        getUserProfile()
          .then(response => {
            this.userId = response.data.userId;
            resolve();
          })
          .catch(error => {
            console.log(error);
            reject(error);
          })
      });
    },
    getList() {
      this.getUserId() // 先获取用户 ID
        .then(() => {
          return getResidents(this.userId);
        })
        .then(response => {
          console.log(response.data);
          this.gender = response.data.gender;
          this.data = response.data;
        })
        .catch(error => {
          console.error('Error fetching resident data:', error);
        });
    }
  },
};
</script>

<style scoped>
.img {
  width: 80px;
  height: 80px;
}
</style>
