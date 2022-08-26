import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Dashboard from '../views/Dashboard.vue'
import Register from '../views/Register.vue'
import Category from '../views/Category.vue'
import AllCategories from '../views/AllCategories.vue'
import Notice from '../views/Notice.vue'
import EditCategory from '../views/EditCategory.vue'
import AllNotices from '../views/AllNotices.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/dashboard',
    name: 'Dashboard',
    component: Dashboard
  },
  {
    path: '/register',
    name: 'Register',
    component: Register
  },
  {
    path: '/category',
    name: 'Category',
    component: Category,
    meta: { requireAuth: true }
  },
  {
    path: '/all-categories',
    name: 'AllCategories',
    component: AllCategories,
    meta: { requireAuth: true }
  },
  {
    path: '/all-notices',
    name: 'AllNotices',
    component: AllNotices,
    meta: { requireAuth: true }
  },
  {
    path: '/edit_category',
    name: 'EditCategory',
    component: EditCategory,
    meta: { requireAuth: true }
  },
  {
    path: '/notice',
    name: 'Notice',
    component: Notice,
    meta: { requireAuth: true }
  }  
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

router.beforeEach((to, from, next)=>{
  const protectedRoute = to.matched.some(record => record.meta.requireAuth)
  if (protectedRoute && sessionStorage.getItem('token') === null) {
    next({name: 'Home'})
  } else {
    next()
  }
})

export default router
