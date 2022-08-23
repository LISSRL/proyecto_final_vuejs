import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Dashboard from '../views/Dashboard.vue'
import store from '../store'
import Register from '../views/Register.vue'
import Category from '../views/Category.vue'
import Notice from '../views/Notice.vue'

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
    component: Dashboard,
    // meta: { requireAuth: true }
  },
  {
    path: '/register',
    name: 'Register',
    component: Register
  },
  {
    path: '/category',
    name: 'Category',
    component: Category
  },
  {
    path: '/notice',
    name: 'Notice',
    component: Notice
  }  
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

// Verificando si hay token en el estado
router.beforeEach((to, from, next)=>{
  const protectedRoute = to.matched.some(record => record.meta.requireAuth)
  if (protectedRoute && store.state.token === null) {
    next({name: 'Home'})
  } else {
    next()
  }
})

export default router
