<template>
  <div>
    <div class="left">
    <el-aside width="100%"  style="background-color: rgb(238, 241, 246)">
      <el-menu :default-openeds="['1', '3']" default-active="1-2">
        <el-submenu index="1">
          <template slot="title">
            <i class="el-icon-user-solid"></i>
            公司员工
          </template>
          <el-menu-item-group>
            <template slot="title"></template>
            <router-link
              :to="{
                path: '/Home',
              }"
            >
              <el-menu-item index="1-1">员工列表</el-menu-item>
            </router-link>
            <router-link
              :to="{
                path: '/form',
                query:{
                  operate: 0
                }
              }"
            >
              <el-menu-item index="1-2">员工录入</el-menu-item>
            </router-link>
            <!-- <router-link
              :to="{
                path: '/Home',
              }"
            >
              <el-menu-item index="1-2">员工开除</el-menu-item>
            </router-link> -->
          </el-menu-item-group>
        </el-submenu>
        <router-link
          :to="{
              path: '/Record',
          }"
        >
          <el-menu-item index="2" >
            <span slot="title">
              <i class="el-icon-s-order"></i>
              打卡记录
            </span>
          </el-menu-item>
        </router-link>
        <router-link
          :to="{
              path: '/Salary',
          }"
        >
          <el-menu-item index="3">
            <span slot="title">
              <i class="el-icon-s-finance"></i>
              员工工资
            </span>
          </el-menu-item>
        </router-link>
      </el-menu>
    </el-aside>
    </div>
    <div class="right">
      <div class="right_text">
        员工信息表
      </div>
      <el-form style="margin:20px" ref="form" :model="form" label-width="80px">
          <el-form-item label="员工名称">
            <el-input v-model="form.name"></el-input>
          </el-form-item>
          <el-form-item label="员工卡号">
            <el-button @click="getCard">获取卡号</el-button>
            <p style="color:rgb(75, 75, 75)" v-show="isShow">{{tableData.Card}}</p>
          </el-form-item>
          <el-form-item label="上班天数">
            <el-input v-model="form.day"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="onSubmit">确认</el-button>
            <el-button @click="returnBack">取消</el-button>
          </el-form-item>
        </el-form>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Home',
  data () {
    return {
      operate: 0,
      id: 0,
      isShow: false,
      tableData:
      {
        Card: null,
        Day: 0,
        id: 0,
        Money: 0,
        Name: ''
      },
      form: {
        name: '',
        id: null,
        day: 0
      },
      Boolean: false,
      flag: 0
    }
  },
  created () {
    const that = this
    // this.form.id = this.id
    // this.form.password = this.pass
    this.operate = this.$route.query.operate
    this.id = this.$route.query.id
    this.tableData.id = this.$route.query.id
    // eslint-disable-next-line eqeqeq
    if (this.operate == 1) {
      this.$axios
        .post('http://localhost:8099/member/findOne?id=' + that.id)
        .then(function (response) {
          console.log(response)
          that.tableData = response.data
          console.log(that.tableData)
          that.form = that.tableData
          console.log(that.form)
          // that.name = that.tableData.Name
          // that.removal()
          console.log('response:' + that.tableData.Name + that.form.Day)
        })
    }
  },
  methods: {
    boolean () {
      // eslint-disable-next-line eqeqeq
      if (this.form.name == '' || this.tableData.Card == null) {
        this.$message({
          type: 'warning',
          message: '请填完所有数据！'
        })
        this.Boolean = false
      } else {
        this.Boolean = true
      }
    },
    onSubmit () {
      this.boolean()
      console.log('123:' + this.operate)
      const that = this
      this.tableData.Name = this.form.name
      this.tableData.Money = this.form.day * 300
      this.tableData.Day = this.form.day
      // eslint-disable-next-line eqeqeq
      if (this.operate == 1 && this.Boolean) {
        this.$axios
          .get('http://localhost:8099/member/update?name=' + this.tableData.Name + '&card=' + this.tableData.Card + '&money=' + this.tableData.Money + '&day=' + this.tableData.Day + '&id=' + this.tableData.id)
          .then(function (response) {
          })
        this.$message({
          type: 'success',
          message: '修改成功！'
        })
        this.returnBack()
      }
      // eslint-disable-next-line eqeqeq
      if (this.operate == 0 && this.Boolean) {
        console.log('123')
        this.$axios
          .get('http://localhost:8099/member/add?name=' + this.tableData.Name + '&card=' + this.tableData.Card + '&money=' + this.tableData.Money + '&day=' + this.tableData.Day)
          .then(function (response) {
            that.flag = response.data
            console.log(that.flag)
            // eslint-disable-next-line eqeqeq
            if (that.flag == 1) {
              that.$message({
                type: 'success',
                message: '添加成功！'
              })
              that.returnBack()
            } else {
              that.$message({
                type: 'warning',
                message: '该员工存在！'
              })
            }
          })
      }
    },
    returnBack () {
      this.$router.go(-1)
    },
    getCard () {
      const that = this
      this.$axios
        .get('http://localhost:8099/member/getCard')
        .then(function (response) {
          that.tableData.Card = response.data
          that.isShow = true
          console.log('response:' + response.data)
        })
    }
  }
}
</script>

<style scoped>
a{
  text-decoration: none;
}
.left{
  width: 20%;
  height: 100%;
  float: left;
}
.right{
  width: 80%;
  height: 100%;
  float: left;
}
.right_text{
  margin: 1em;
  color: rgba(39, 119, 151, 0.822);
  font-weight: bolder;
  font-size: 20px;
}
</style>
