import axios from 'axios'
// import Element from 'element-ui'
// import store from './store'
// import router from './router'
axios.defaults.baseURL = 'http://localhost:8099'
axios.interceptors.request.use(config => {
  console.log('前置拦截')
  // 可以统一设置请求头
  return config
})
