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
                  <label>手机号</label>
                  <md-input v-model="r_username" type="text"></md-input>
                </md-field>
              </div>
              <div class="md-layout-item md-size-100">
                <md-field>
                  <label>输入密码</label>
                  <md-input v-model="r_password" type="password"></md-input>
                </md-field>
              </div>
              <div class="md-layout-item md-size-100">
                <md-field>
                  <label>再次输入密码</label>
                  <md-input v-model="password2" type="password"></md-input>
                </md-field>
              </div>
              <div class="md-layout-item md-size-50">
                <md-field>
                  <label>验证码</label>
                  <md-input v-model="r_verification" type="text"></md-input>
                </md-field>
              </div>
              <div class="md-layout-item md-size-50">
                <md-button @click="requestCode">获取验证码</md-button>
              </div>
            </div>
            <md-button class="md-success md-rised" @click="registser">提交</md-button>
          </md-card-content>
        </md-card>
      </form>
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
            <md-button class="md-success md-rised" @click="login">登录</md-button>
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
      registser(){

        const _this = this;

        if (this.r_username =="" || this.r_password == "" || this.r_password == null || this.r_username == null || this.r_verification == null || this.r_verification == ''){
          alert("格式错误")
          return;
        }

        //注册
        const r_password = this.r_password;
        const r_username = this.r_username;
        const r_verification = this.r_verification;
        this.$axios.get("api/phone/Verification?phoneNumber="+r_username+"&password="+r_password+"&code="+r_verification).then(res=>{
          alert(res.date.msg)
          // TODO 如果失败，删除这里
          window.location.reload();
        })
        //注册完

        this.password2 = null;
        this.r_username = null;
        this.r_password = null;
        this.r_verification = null;
      },
      requestCode(){
        const r_password = this.r_password;
        const r_username = this.r_username;
        this.$axios.get("api/phone/code?phoneNumber="+r_username+"&password="+r_password).then(res=>{
          alert(res.data.msg)
        })
      },
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
          params.append("phoneNumber",username);
          params.append("password",password);
          //end

          //ajax请求
          this.$axios.post("api/phone/login",params).then(res=>{
            //以字符串方式存入账号到localStorage，并设置过期时间，取出时用JSON.parse()
            const ONE_DAY = 86400000;
            const time = new Date().getTime();
            const account = {"username":username,"password":password,"expire":new Date().setTime(time+ONE_DAY*10)};
            window.localStorage.setItem("account",JSON.stringify(account))
            console.log(account);

            //往Vuex存入token
            if (res.data.phone){
              _store.commit('saveToken',res.data.phone);
              router.push("/dashboard");
            } else {
              window.location.reload();
            }


            console.log("----------")
            console.log(res.data);
            console.log("----------")
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
        verificationCode:null,
        r_username:null,
        r_password:null,
        password2:null,
        r_verification:null,

        dataBackgroundColor:'green',
        username:null,
        password:null,
        // verification:null,
      };
    },
    mounted() {
    }
  };
</script>
