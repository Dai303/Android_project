<template>
  <div>
    <div class="left">
    <el-aside width="100%"  style="background-color: rgb(238, 241, 246)">
      <el-menu :default-openeds="['1', '3']" default-active="1-1">
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
          <el-form-item label="员工姓名">
            <el-input v-model="form.name" :disabled="true"></el-input>
          </el-form-item>
          <el-form-item label="员工卡号">
            <el-input v-model="form.card" :disabled="true"></el-input>
          </el-form-item>
          <el-form-item label="上班天数">
            <el-input v-model="form.day" :disabled="true"></el-input>
          </el-form-item>
          <el-form-item label="员工工资">
            <el-input v-model="form.money" :disabled="true"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="danger" @click="open">确认开除</el-button>
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
        day: 0,
        card: '',
        money: 0
      },
      Boolean: false
    }
  },
  created () {
    const that = this
    this.id = this.$route.query.id
    this.tableData.id = this.$route.query.id
    // eslint-disable-next-line eqeqeq
    this.$axios
      .post('http://localhost:8099/member/findOne?id=' + that.id)
      .then(function (response) {
        that.tableData = response.data
        that.form = that.tableData
      })
  },
  methods: {
    onSubmit () {
      this.$axios
        .get('http://localhost:8099/member/delete?id=' + this.tableData.id)
        .then(function (response) {
          // that.tableData = response.data
          // that.removal()
        })
      this.returnBack()
    },
    open () {
      this.$confirm('此操作将永久开除该员工, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
        center: true
      }).then(() => {
        this.onSubmit()
        this.$message({
          type: 'success',
          message: '删除成功!'
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
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
