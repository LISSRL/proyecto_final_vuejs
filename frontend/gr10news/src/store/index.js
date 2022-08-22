import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    token: null,
    user: {
      username: ''
    }
  },
  mutations: {
    setToken(state, payload) {
      state.token = payload
    },
    setUser(state, payload) {
      state.user.username = payload.username,
      state.user.email = payload.email
    },
    logout() {
      localStorage.removeItem('token')
      location.reload()
    }
  },
  actions: {
    async login({ commit }, user) {
      try {
        console.log("login");
        const res = await fetch('http://localhost:8764' + '/authentication/login', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify(user)
        })
        const userDB = await res.json()
        commit('setToken', userDB.data.token)
        localStorage.setItem('token', userDB.data.token)
      } catch (error) {
        console.log('Error: ', error)
      }
    },
    async register( user ) {
      try {
        console.log(JSON.stringify(user),user.username)
        const res = await fetch('http://localhost:8764' + '/user/signup', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },  
          body: JSON.stringify(user)
        })
        const userDB = await res.json()
        consol.log(userDB)
      } catch (error) {
        console.log('Error: ', error)
      }
    },
    async dashboard({commit}, auth_token){
      try {
        const res = await fetch('http://localhost:8764' + '/dashboard',  {
          method: 'GET',
          headers: {
            'Content-Type': 'application/json',
            'auth-token': auth_token
          },
        })
        const userDB = await res.json()
        commit('setUser', {username: userDB.data.user.username})
      } catch (error) {
        console.log('Error: ', error)
      }
    },
    getToken({commit}) {
      if(localStorage.getItem('token')) {
        commit('setToken', localStorage.getItem('token'))
      } else {
        commit('setToken', null)
      }
    },
    logout ({ commit }) {
      commit('logout')
    }
  },
  modules: {
  }
})