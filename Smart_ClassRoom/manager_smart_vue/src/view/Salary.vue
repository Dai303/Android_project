<template>
  <div>
    <div class="left">
    <el-aside width="100%"  style="background-color: rgb(238, 241, 246)">
      <el-menu :default-openeds="['1', '3']" default-active="3">
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
        员工表格
      </div>
      <el-table
        :data="tableData"
        style="width: 100%">
        <el-table-column type="index" label="编号" width="180">
        </el-table-column>
        <el-table-column prop="name" label="员工姓名" width="180">
        </el-table-column>
        <el-table-column prop="card" label="员工卡号" width="180">
        </el-table-column>
        <el-table-column prop="money" label="员工工资">
        </el-table-column>
        <!-- <el-table-column prop="endT" label="下班打卡时间">
        </el-table-column> -->
        <el-table-column label="操作">
          <template slot-scope="scope">
            <router-link
              :to="{
                path: '/form/' + scope.$index,
              }"
            >
              <el-button type="primary" size="mini">编辑</el-button>
            </router-link>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Home',
  data () {
    return {
      tableData: [
        {
          name: '',
          card: '',
          money: ''
        }
      ]
    }
  },
  created () {
    const that = this
    // this.form.id = this.id
    // this.form.password = this.pass
    this.$axios
      .get('http://localhost:8099/member/GetSalary')
      .then(function (response) {
        that.tableData = response.data
        // that.removal()
        console.log('response:' + response.data)
      })
  },
  methods: {
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
