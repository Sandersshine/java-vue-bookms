import Vue from 'vue'
import VueRouter from 'vue-router'
// import HomeView from '../views/HomeView.vue'
import BookList from '@/components/home';  
import EditBook from '@/components/edit';  
import BookDetail from '@/components/detail';  
import AddBook from '@/components/add';
import searchBook from '@/components/search';

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    component: BookList
  },
  // {
  //   path: '/about',
  //   name: 'about',
  //   // route level code-splitting
  //   // this generates a separate chunk (about.[hash].js) for this route
  //   // which is lazy-loaded when the route is visited.
  //   component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  // },
  {  
    path: '/books',  
    name: 'BookList',  
    component: BookList  
  },  
  {  
    path: '/edit/:id',  
    name: 'EditBook',  
    component: EditBook,  
    props: true // 允许将URL中的参数作为props传递给路由组件  
  },  
  {  
    path: '/detail/:id',  
    name: 'BookDetail',  
    component: BookDetail,  
    props: true // 允许将URL中的参数作为props传递给路由组件  
  },  
  {  
    path: '/add', // 新增的路由路径  
    name: 'AddBook', // 新增的路由名称  
    component: AddBook // 指向的组件  
  },  
  {  
    path: '/search', // 新增的路由路径  
    name: 'searchBook', // 新增的路由名称  
    component: searchBook // 指向的组件  
  }
]

const router = new VueRouter({
  routes
})

export default router
