import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/view/Login'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld
    },
    {
      path: '/Home',
      name: 'Home',
      component: () => import('../view/Home.vue')
    }
  ]
})
