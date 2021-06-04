// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import Element from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import router from './router'
import axios from 'axios'
import './axios.js'

Vue.use(Element, { size: 'small', zIndex: 3000 })
Vue.config.productionTip = false

Vue.prototype.$axios = axios
axios.defaults.headers.post['Content-Type'] = 'application/json;charset=UTF-8'

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
