<template>
  <div class="container" style="display: grid; gird-template-columns: auto auto">

    <p>待举行的活动</p>

    <div class="card" v-for="item in eventsList" :key="item.name">
      <el-collapse>
        <el-collapse-item :title="item.name">
          <span>
          |地点: {{ item.location }} | 时间: {{ item.dateTime }} | 负责人: {{ item.personName }}
          </span>
          <el-button style="float: right; padding: 3px 0" @click="handleJoin(item)">我要报名</el-button>
          <el-button style="float: right; padding: 3px 0" @click="handleOrder(item)">我要预约</el-button>
          <div>{{ item.description }}</div>
        </el-collapse-item>
      </el-collapse>
    </div>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />


    <el-dialog :title="title" :visible.sync="openJoin" width="500px" append-to-body>
      <el-form ref="form" :model="formJoin" :rules="rules" label-width="80px">
        <el-form-item label="报名人" prop="participantName">
          <el-input v-model="formJoin.participantName" placeholder="请输入报名人"/>
        </el-form-item>
        <el-form-item label="联系方式" prop="participantContact">
          <el-input v-model="formJoin.participantContact" placeholder="请输入联系方式"/>
        </el-form-item>
        <el-form-item label="报名人数" prop="participantCount">
          <el-input v-model="formJoin.participantCount" placeholder="请输入报名人数"/>
        </el-form-item>
        <el-form-item label="报名时间" prop="registeredAt">
          <el-date-picker clearable
                          v-model="formJoin.registeredAt"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择报名时间">
          </el-date-picker>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitFormJoin">确 定</el-button>
        <el-button @click="cancel">清 空</el-button>
      </div>
    </el-dialog>

    <el-dialog :title="title" :visible.sync="openOrder" width="500px" append-to-body>
      <el-form ref="form" :model="formOrder" :rules="rules" label-width="80px">
        <el-form-item label="活动ID" prop="eventID">
          <el-input v-model="formOrder.eventID" placeholder="请输入活动ID"/>
        </el-form-item>
        <el-form-item label="姓名" prop="reserverName">
          <el-input v-model="formOrder.reserverName" placeholder="请输入姓名"/>
        </el-form-item>
        <el-form-item label="联系方式" prop="reserverContact">
          <el-input v-model="formOrder.reserverContact" placeholder="请输入联系方式"/>
        </el-form-item>
        <el-form-item label="开始时间" prop="startTime">
          <el-date-picker clearable
                          v-model="formOrder.startTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择开始时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="结束时间" prop="endTime">
          <el-date-picker clearable
                          v-model="formOrder.endTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择结束时间">
          </el-date-picker>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitFormRecord">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {addEvents, delEvents, getEvents, listEvents, updateEvents} from "@/api/event/events";
import {getInfo} from "@/api/login";
import ScrollPane from "@/layout/components/TagsView/ScrollPane.vue";
import {addRegistrations, listRegistrations} from "@/api/event/registrations";
import {addReservations} from "@/api/event/reservations";

export default {
  name: "Events",
  components: {ScrollPane},
  data() {
    return {
      // 日期选择器配置
      pickerOptions: {
        shortcuts: [
          {
            text: '今天',
            onClick(picker) {
              picker.$emit('pick', new Date());
            }
          }, {
            text: '明天',
            onClick(picker) {
              const date = new Date();
              date.setTime(date.getTime() + 3600 * 1000 * 24);
              picker.$emit('pick', date);
            }
          }, {
            text: '一周后',
            onClick(picker) {
              const date = new Date();
              date.setTime(date.getTime() + 3600 * 1000 * 24 * 7);
              picker.$emit('pick', date);
            }
          }]
      },
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
      // 活动表格数据
      eventsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: null,
        dateTime: null,
        location: null,
        description: null,
        status: null,
        createdAt: null,
        updatedAt: null,
        personName: null,
        personId: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        name: [
          {required: true, message: "活动名不能为空", trigger: "blur"}
        ],
        dateTime: [
          {required: true, message: "活动时间不能为空", trigger: "blur"}
        ],
      },
      // 活动报名弹出层
      openJoin: false,
      // 活动预约弹出层
      openOrder: false,
      //
      formJoin: {
        eventID: null,
        participantName: null,
        participantContact: null,
        participantCount: null,
        registeredAt: null,
      },
      //
      formOrder: {
        eventID: null,
        reserverName: null,
        reserverContact: null,
        startTime: null,
        endTime: null,
        status: null,
        createdAt: null,
        updatedAt: null
      }
    }
  },
  created() {
    this.getList();
  },
  methods: {
    // 报名弹出层
    handleJoin(item) {
      this.formJoin.eventID = item.eventID;
      this.formJoin.participantCount = 1;
      this.formJoin.registeredAt = new Date();
      getInfo().then(reponse => {
        this.formJoin.participantName = reponse.user.nickName;
        this.formJoin.participantContact = reponse.user.phonenumber;
      })
      this.openJoin = true;
    },
    // 提交报名
    submitFormJoin() {
      this.formJoin.participantName =
      addRegistrations(this.formJoin).then(response => {
        this.$modal.msgSuccess("报名成功");
        this.openJoin = false;
      });
    },
    // 预约弹出层
    handleOrder(item) {
      this.formOrder.startTime = new Date();
      this.formOrder.endTime = new Date();
      getInfo().then(reponse => {
        this.formOrder.reserverName = reponse.user.nickName;
        this.formOrder.reserverContact = reponse.user.phonenumber;
      })
      this.formOrder.eventID = item.eventID;

      this.openOrder = true;
      console.log(this.formOrder)
    },
    // 提交预约
    submitFormRecord() {
      addReservations(this.formOrder).then(reponse => {
        this.$modal.msgSuccess("预约成功");
        this.openOrder = false;
      })
    },
    /** 查询活动列表 */
    getList() {
      this.queryParams.status = "待举行"
      this.loading = true;
      listEvents(this.queryParams).then(response => {
        this.eventsList = response.rows;
        this.total = response.total;
        this.loading = false;
      })

    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        eventID: null,
        name: null,
        dateTime: null,
        location: null,
        description: null,
        status: null,
        createdAt: null,
        updatedAt: null,
        personName: null,
        personId: null
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
      this.ids = selection.map(item => item.eventID)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加活动";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const eventID = row.eventID || this.ids
      getEvents(eventID).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改活动";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.eventID != null) {
            updateEvents(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addEvents(this.form).then(response => {
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
      const eventIDs = row.eventID || this.ids;
      this.$modal.confirm('是否确认删除活动编号为"' + eventIDs + '"的数据项？').then(function () {
        return delEvents(eventIDs);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('event/events/export', {
        ...this.queryParams
      }, `events_${new Date().getTime()}.xlsx`)
    },

  }
};
</script>
