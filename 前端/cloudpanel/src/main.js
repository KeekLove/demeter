// =========================================================
// * Vue Material Dashboard - v1.2.1
// =========================================================
//
// * Product Page: https://www.creative-tim.com/product/vue-material-dashboard
// * Copyright 2019 Creative Tim (https://www.creative-tim.com)
// * Licensed under MIT (https://github.com/creativetimofficial/vue-material-dashboard/blob/master/LICENSE.md)
//
// * Coded by Creative Tim
//
// =========================================================
//
// * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from "vue";
import VueRouter from "vue-router";
import App from "./App";
import store from "./store";
import axios from 'axios';
Vue.prototype.$axios = axios;

// router setup
import routes from "./routes/routes";

// Plugins
import GlobalComponents from "./globalComponents";
import GlobalDirectives from "./globalDirectives";
import Notifications from "./components/NotificationPlugin";

// MaterialDashboard plugin
import MaterialDashboard from "./material-dashboard";

import Chartist from "chartist";

// configure router
const router = new VueRouter({
  routes, // short for routes: routes
  linkExactActiveClass: "nav-item active",
});

router.beforeEach((to, from, next) => {
  const userInfo = JSON.parse(window.localStorage.getItem('account'));
  console.log(userInfo);

  // userInfo不为空，expire未过期时。state.token为空时 设置token
  if (userInfo != null && store.state.token == null && userInfo.expire>new Date().getTime()){
    const params = new URLSearchParams();
    params.append('username',userInfo.username);
    params.append('password',userInfo.password);
    //ajax请求
    axios.post("api/operationArea/login",params).then(res=>{
      //更新过期时间
      const ONE_DAY = 86400000;
      const time = new Date().getTime();
      userInfo.expire = new Date().setTime(time+ONE_DAY*10);
      window.localStorage.setItem('account',JSON.stringify(userInfo))
      //往Vuex存入token
      store.commit('saveToken',res.data.token);
      store.commit('savePrivilege',res.data.privilege);
      console.log(store.state.token);
      router.push("dashboard");
    });

  }

  if (to.meta.requireToken){
    if (store.state.token == null){
      next({path:"/login"});
    }else {
      next();
    }
  }else {
    next();
  }
})

Vue.prototype.$Chartist = Chartist;

Vue.use(VueRouter);
Vue.use(MaterialDashboard);
Vue.use(GlobalComponents);
Vue.use(GlobalDirectives);
Vue.use(Notifications);

/* eslint-disable no-new */
new Vue({
  el: "#app",
  render: h => h(App),
  router,
  store,
  data: {
    Chartist: Chartist
  }
});
