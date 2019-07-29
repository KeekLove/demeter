<template>
  <div class="content">
    <div class="md-layout">
      <div class="md-layout-item md-size-100">
        <md-button>添加菜单</md-button>
      </div>
      <div
        class="md-layout-item md-medium-size-100 md-xsmall-size-100 md-size-50"
      >
        <md-card>
          <md-card-header data-background-color="orange">
            <h4 class="title">本店菜单</h4>
            <p class="category">一个菜单</p>
          </md-card-header>
          <md-card-content>
            <md-table v-model="pageInfo.list">
              <md-table-row slot="md-table-row" slot-scope="{ item }">
                <md-table-cell md-label="菜单ID">{{ item.menuid }}</md-table-cell>
                <md-table-cell md-label="菜单名">{{ item.menuname }}</md-table-cell>
                <md-table-cell md-label="食物ID">{{ item.foodid }}</md-table-cell>
                <md-table-cell md-label="菜品">{{item.foodname }}</md-table-cell>
                <md-table-cell md-label="图标">{{ item.icon }}</md-table-cell>
                <md-table-cell md-label="价格">{{ item.prize }}</md-table-cell>
                <md-table-cell md-label="餐馆名">{{ item.resname }}</md-table-cell>
                <md-table-cell md-label="创建时间">{{ item.createtime }}</md-table-cell>
              </md-table-row>
            </md-table>
          </md-card-content>
        </md-card>
      </div>
    </div>
  </div>
</template>

<script>
import {
  StatsCard,
  OrderedTable
} from "@/components";

export default {
  components: {
    StatsCard,
    OrderedTable
  },
  data() {
    return {
      pageInfo:{}
    };
  },
  mounted() {
    this.$axios.get("api/food/listFood?pageSize=2&pageNum=1&token=token&card=ShouQuanMa&menuId=1").then(res=>{
      console.log(res.data);
      this.pageInfo = res.data;
    })
  }
};
</script>
