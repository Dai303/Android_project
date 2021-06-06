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
    },
    {
      path: '/form',
      name: 'form',
      component: () => import('../view/form.vue')
    },
    {
      path: '/MemberDetail',
      name: 'MenmberDetail',
      component: () => import('../view/MemberDetail.vue')
    },
    {
      path: '/Record',
      name: 'Record',
      component: () => import('../view/Record.vue')
    }
  ]
})
