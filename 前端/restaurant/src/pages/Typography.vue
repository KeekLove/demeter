<template>
  <div class="content">
    <div class="md-layout">
      <div class="md-layout-item">
        <md-card>
          <md-card-header data-background-color="green">
            <h4 class="title">历史订单</h4>
            <p class="category">所有</p>
          </md-card-header>
          <md-card-content>
            <md-table v-model="pageInfo.list">
              <md-table-row slot="md-table-row" slot-scope="{ item }">
                <md-table-cell md-label="订单ID">{{item.id }}</md-table-cell>
                <md-table-cell md-label="餐厅名称">{{ item.name }}</md-table-cell>
                <md-table-cell md-label="订单号">{{ item.orderId }}</md-table-cell>
                <md-table-cell md-label="座位号">{{ item.seatNum }}</md-table-cell>
                <!--<md-table-cell md-label="菜品">{{ item.foodContext }}</md-table-cell>-->
                <md-table-cell md-label="创建时间">{{ item.createTime }}</md-table-cell>
              </md-table-row>
            </md-table>
          </md-card-content>
        </md-card>
      </div>
    </div>
  </div>
</template>

<script>import { SimpleTable, OrderedTable } from "@/components";
export default {
  props: {
    dataBackgroundColor: {
      type: String,
      default: ""
    }
  },
  components: {
    OrderedTable,
    SimpleTable
  },
  mounted() {
    this.$axios.get("api/order/listSuccess?pageSize=2&pageNum=1&token=token").then(res=>{
      console.log(res.data);
      this.pageInfo = res.data;
    })
  },
  data(){
    return{
      pageInfo:{}
    }
  }
};
</script>
