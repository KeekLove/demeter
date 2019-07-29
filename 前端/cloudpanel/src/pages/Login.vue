<template>
  <div>
    <div class="md-layout" style="margin: 0 auto;">
      <form class="md-layout-item md-size-50 md-small-size-100" v-if="this.$store.state.token == null">
        <md-card>
          <md-card-header :data-background-color="dataBackgroundColor">
            <h4 class="title">Edit Profile</h4>
            <p class="category">Complete your profile</p>
          </md-card-header>

          <md-card-content>
            <div class="md-layout">
              <div class="md-layout-item md-size-100">
                <md-field>
                  <label>User Name</label>
                  <md-input v-model="username" type="text"></md-input>
                </md-field>
              </div>
              <div class="md-layout-item md-size-100">
                <md-field>
                  <label>Password</label>
                  <md-input v-model="password" type="password"></md-input>
                </md-field>
              </div>
            </div>
            <md-button class="md-success md-rised" @click="login">提交</md-button>
          </md-card-content>
        </md-card>
      </form>
      <md-button @click="logOut" v-if="this.$store.state.token">登出</md-button>
    </div>
  </div>
</template>

<script>
export default {
  methods: {
    login(){
      const username = this.username;
      const password = this.password;
      const _store = this.$store
      const router = this.$router;
      console.log(username);
      console.log(password);
      if (this.password !=null || this.password!=''){
        //start 设置以post方式传参时的参数
        const params = new URLSearchParams;
        params.append("username",username);
        params.append("password",password);
        //end

        //ajax请求
        this.$axios.post("api/operationArea/login",params).then(res=>{
          //以字符串方式存入账号到localStorage，并设置过期时间，取出时用JSON.parse()
          const ONE_DAY = 86400000;
          const time = new Date().getTime();
          const account = {"username":username,"password":password,"expire":new Date().setTime(time+ONE_DAY*10)};
          window.localStorage.setItem("account",JSON.stringify(account))
          console.log(account);

          //往Vuex存入token
          _store.commit('saveToken',res.data.token);
          _store.commit('savePrivilege',res.data.privilege);
          router.push("/dashboard");
        })
      }
    },
    logOut(){
      window.localStorage.setItem("account",null);
      window.location.reload();
    }
  },
  data(){
    return{
      dataBackgroundColor:'green',
      username:null,
      password:null
    };
  },
  mounted() {
  }
};
</script>
