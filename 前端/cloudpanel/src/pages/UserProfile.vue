<template>
  <div class="content">
    <div class="md-layout">
      <div
              class="md-layout-item md-medium-size-100 md-xsmall-size-100 md-size-100"
      >
        <md-card>
          <md-card-header data-background-color="green">
            <h4 class="title">所有餐馆</h4>
            <p class="category">包含有效和无效餐馆</p>
          </md-card-header>
          <md-card-content>
            <md-table v-model="userList.list">
              <md-table-row slot="md-table-row" slot-scope="{ item }">
                <md-table-cell  md-label="用户ID">{{ item.id }}</md-table-cell>
                <md-table-cell md-label="用户名">{{ item.username }}</md-table-cell>
                <md-table-cell md-label="密码">{{ item.password }}</md-table-cell>
                <md-table-cell md-label="权限等级">{{item.privilege}}</md-table-cell>
                <md-table-cell md-label="更新时间">{{new Date(item.updateTime).toLocaleDateString()}}</md-table-cell>
                <md-button @click="selectUser(item)">选择</md-button>
              </md-table-row>
            </md-table>
          </md-card-content>
          <md-card-actions class="md-layout">
            <md-button class="md-info disabled">页码：{{userList.pageNum}}</md-button>
            <md-button class="md-layout-item md-size-49 md-warning" v-if="userList.pageNum != 1" @click="prePage">上一页</md-button>
            <md-button class="md-layout-item md-size-49 md-primary" v-if="userList.hasNextPage" @click="nextPage">下一页</md-button>
          </md-card-actions>
        </md-card>
      </div>

      <form class="md-layout-item md-size-100" v-if="update ==1">
        <md-card>
          <md-card-header :data-background-color="green">
            <h4 class="title">更新管理员信息</h4>
            <p class="category">已选择{{username}}</p>
          </md-card-header>
          <md-card-content>
            <div class="md-layout">
              <div class="md-layout-item md-small-size-100 md-size-33">
                <md-field>
                  <label>用户ID</label>
                  <md-input v-model="userId" disabled></md-input>
                </md-field>
              </div>
              <div class="md-layout-item md-small-size-100 md-size-33">
                <md-field>
                  <label>用户名</label>
                  <md-input v-model="username"></md-input>
                </md-field>
              </div>
              <div class="md-layout-item md-small-size-100 md-size-33">
                <md-field>
                  <label>密码</label>
                  <md-input v-model="password"></md-input>
                </md-field>
              </div>
              <div class="md-layout-item md-small-size-100 md-size-100">
                  <md-radio v-model="privilege" value="1">超级管理员</md-radio>
                  <md-radio v-model="privilege" value="2" class="md-primary">普通管理员</md-radio>
              </div>
              <md-button @click="updateUser">提交</md-button>
            </div>
          </md-card-content>
        </md-card>
      </form>
      <md-button @click="add = 1">添加用户</md-button>
      <form class="md-layout-item md-size-100" v-if="add == 1">
        <md-card>
          <md-card-header :data-background-color="green">
            <h4 class="title">添加管理员</h4>
            <p class="category">填写账号密码和等级</p>
          </md-card-header>
          <md-card-content>
            <div class="md-layout">
              <div class="md-layout-item md-small-size-100 md-size-33">
                <md-field>
                  <label>用户名</label>
                  <md-input v-model="username"></md-input>
                </md-field>
              </div>
              <div class="md-layout-item md-small-size-100 md-size-33">
                <md-field>
                  <label>密码</label>
                  <md-input v-model="password"></md-input>
                </md-field>
              </div>
              <div class="md-layout-item md-small-size-100 md-size-100">
                <md-radio v-model="privilege" value="1">超级管理员</md-radio>
                <md-radio v-model="privilege" value="2" class="md-primary">普通管理员</md-radio>
              </div>
              <md-button @click="addUser">添加</md-button>
            </div>
          </md-card-content>
        </md-card>
      </form>
    </div>
  </div>
</template>

<script>
import { EditProfileForm, UserCard } from "@/pages";

export default {
  components: {
    EditProfileForm,
    UserCard
  },
  methods:{
    nextPage(){
      console.log("---");
      console.log(this.userList)
      console.log("---");
      console.log(this.userList.nextPage);
      this.changePage(this.userList.nextPage);
    },
    prePage(){
      console.log(this.userList.prePage);
      this.changePage(this.userList.prePage)
    },
    changePage(pageNum){
      this.$axios.get("api/Managers/page",{
        params:{
          token:'',
          pageNum:pageNum,
        }}).then(res=>{
        console.log(res.data);
        this.userList = res.data
      })
    },

    selectUser(user){
      console.log(user);
      this.update = 1;
      this.username = user.username;
      this.password = user.password;
      this.privilege = user.privilege+"";
      this.userId = user.id;
    },
    updateUser(){
      // this.username,this.password,this.privilege,this.userId
      const _this = this;
      const id = this.userId;
      const name = this.username;
      const pwd = this.password;
      const level = this.privilege;
      console.log(id+name+pwd+level);
      this.$axios.put("api/Managers/manager?token&id="+id+"&username="+name+"&password="+pwd+"&privilege="+level).then(res=>{
        console.log(res.data);
        _this.changePage(_this.userList.pageNum)
        _this.username = null;
        _this.password = null;
        _this.privilege = null;
        _this.userId = null;
        _this.update = 0;
      })
    },

    addUser(){
      const params = new URLSearchParams;
      params.append("username",this.username);
      params.append("password",this.password);
      params.append("privilege",this.privilege);
      params.append("token","")

      this.$axios.post("api/Managers/manager",params).then(res=>{
        console.log(res.data);
      })
    }
  },
  data(){
    return{
      userList:null,
      userId:null,
      password:null,
      username:null,
      privilege:"1",

      add:0,
      update:0
    }
  },
  mounted() {
    this.$axios.get("api/Managers/page",{
        params:{
          token:'',
          pageNum:1
      }}).then(res=>{
      console.log(res.data);
      this.userList = res.data
    })
  }
};
</script>

<style lang="scss" scoped>
  .md-radio {
    display: flex;
  }
</style>