<template>
  <div>
    <vue-echarts :options="options" ref="chart"></vue-echarts>
  </div>
</template>

<script>
import * as echarts from 'echarts'
import ECharts from 'vue-echarts'

export default {
  components: {
    'vue-echarts': ECharts
  },
  data() {
    return {
      options: {
        // ECharts配置项
      }
    };
  },
  created() {
    this.fetchData();
  },
  methods: {
    async fetchData() {
      try {
        // 假设您从API获取关系数据
        const relationships = await this.$api.getRelationships();
        this.initChart(relationships);
      } catch (error) {
        console.error('Error fetching relationships:', error);
      }
    },
    initChart(relationships) {
      // 准备ECharts的数据
      const nodes = relationships.map(item => ({
        name: item.residentName, // 假设有居民名称字段
        value: item.residentId,
        // 其他节点属性
      }));
      const links = relationships.map(item => ({
        source: 'family', // 假设所有居民都连接到家庭节点
        target: item.residentId
        // 其他边属性
      }));

      // 设置ECharts配置项
      this.options = {
        title: {
          text: '关系图'
        },
        tooltip: {},
        legend: {
          data: ['居民']
        },
        animation: true,
        series: [{
          name: '居民',
          type: 'graph',
          layout: 'force', // 使用力导向布局
          data: nodes,
          links: links,
          force: {
            repulsion: 50
          },
          lineStyle: {
            color: 'source',
            curveness: 0.3
          }
        }]
      };
    }
  }
};

</script>
