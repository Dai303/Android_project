<template>
<div class="backgorund">
  <div class="login">
      <div class="title">
        <i class="el-icon-orange" style="margin-right:250px"></i>
        象   雨   橙
      </div>
      <input class="input" v-model="form.id" type="text" style="margin-top:80px" placeholder="请输入用户名" />
      <input class="input" v-model="form.password" type="password" placeholder="请输入密码" />
      <!-- <router-link :to="{path: '/Home'}"> -->
        <div class="button" @click="login">
          Login
        </div>
      <!-- </router-link> -->
  </div>
</div>
</template>

<script>
export default {
  name: 'Login',
  data () {
    return {
      id: '',
      pass: '',
      form: {
        id: '',
        password: ''
      },
      infor: 0
    }
  },
  methods: {
    removal () {
      if (this.infor === 0) {
        this.$message({
          type: 'warning',
          message: '没有该用户，请重新输入！'
        })
      }
      if (this.infor === 1) {
        this.$message.error('密码错误，请重新输入！')
      }
      if (this.infor === 2) {
        this.$message({
          type: 'success',
          message: '登陆成功！'
        })
        this.$router.push('Home')
      }
      this.infor = 0
    },
    login () {
      const that = this
      // this.form.id = this.id
      // this.form.password = this.pass
      this.$axios
        .post('http://localhost:8099/manager/login', this.form)
        .then(function (response) {
          that.infor = response.data
          that.removal()
          console.log('response:' + response.data)
        })
      // this.removal()
    }
  }
}
</script>

<style scoped>
.backgorund{
  top: 0;
  right: 0;
  width: 100%;
  height: 100%;
  position: fixed;
  /* background-image: linear-gradient(45deg,#292b4b, #6b7e8b); */
  background-image: linear-gradient(45deg,#367a76, #f3eca9);
  /* background-image: linear-gradient(45deg,#d44795, #f3a9cb); */
  /* background: antiquewhite; */
}
.login{
    width: 450px;
    padding: 50px;
    height: 400px;
    margin: 100px auto;
    text-align: center;
    border-radius: 16px;
    background: rgba(0, 0, 0, 0.158);
    /* background: rgba(255, 255, 255, 0.158); */
}
.title{
  width:100%;
  font-size:2rem;
  font-weight:bolder;
  color: aliceblue;
}
.input{
  width: 400px;
  height: 40px;
  border-radius: 16px;
  margin: 25px;
  border-style: none;
  padding-left: 15px;
  font-size: 16px;
  float: left;
  color: rgb(75, 75, 75);
  outline: none;
}
.button{
  width:400px;
  height: 40px;
  border-radius: 16px;
  margin: 25px;
  border-style: none;
  /* background: rgb(19, 179, 125); */
  background: rgb(77, 192, 138);
  color: aliceblue;
  padding-top:10px;
  font-size: 23px;
  float: left;
  cursor: pointer;
}
</style>
