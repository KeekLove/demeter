<template>
  <div class="wrapper" :class="{ 'nav-open': $sidebar.showSidebar }">
    <notifications></notifications>


    <side-bar>
      <mobile-menu slot="content"></mobile-menu>
      <sidebar-link to="/dashboard">
        <md-icon>list</md-icon>
        <p>管理本店菜单</p>
      </sidebar-link>

      <sidebar-link to="/typography">
        <md-icon>history</md-icon>
        <p>查看历史订单</p>
      </sidebar-link>

      <sidebar-link to="/table">
        <md-icon>store</md-icon>
        <p>系统实例管理</p>
      </sidebar-link>
      <sidebar-link to="/user">
        <md-icon>person</md-icon>
        <p>更新个人信息</p>
      </sidebar-link>
<!--      <sidebar-link to="/maps">-->
<!--        <md-icon>shopping_cart</md-icon>-->
<!--        <p>购买本产品</p>-->
<!--      </sidebar-link>-->
      <sidebar-link to="/notifications">
        <md-icon></md-icon>
        <p>{{this.$store.state.token ==null?"登录":"登出"}}</p>
      </sidebar-link>
<!--      <sidebar-link to="/icons">-->
<!--      <md-icon>bubble_chart</md-icon>-->
<!--      <p>Icons</p>-->
<!--    </sidebar-link>-->
    </side-bar>

    <div class="main-panel">
      <top-navbar></top-navbar>

      <dashboard-content> </dashboard-content>

      <content-footer v-if="!$route.meta.hideFooter"></content-footer>
    </div>
  </div>
</template>
<style lang="scss"></style>
<script>
import TopNavbar from "./TopNavbar.vue";
import ContentFooter from "./ContentFooter.vue";
import DashboardContent from "./Content.vue";
import MobileMenu from "@/pages/Layout/MobileMenu.vue";

export default {
  components: {
    TopNavbar,
    DashboardContent,
    ContentFooter,
    MobileMenu
  },
  data(){
    return{
    }
  }
};
</script>
