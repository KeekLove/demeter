<template>
  <md-card class="md-card-profile">
    <div class="md-card-avatar">
      <img class="img" :src="cardUserImage" />
    </div>

    <md-card-content>
      <h6 class="category text-gray">用户信息</h6>
      <h4 class="card-title">/*用户名*/{{name}}</h4>
      <p class="card-description">
        /*预留信息位置*/
      </p>
    </md-card-content>
  </md-card>
</template>
<script>
export default {
  name: "user-card",
  props: {
    cardUserImage: {
      type: String,
      default: require("@/assets/img/faces/marc.jpg")
    }
  },
  data() {
    return {
      data:""
    };
  },

  getInformation(){

  },
  mounted() {
    this.$axios.get("api").then(res=>{
      console.log(res.data);
      this.data = res.data.data;
    })
  }
};
</script>
<style></style>
