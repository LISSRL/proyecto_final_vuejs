<template>
  <div id="app">
    <div class="navigation">
      <div id="nav">
        <router-link to="/dashboard">Dashboard</router-link>
        <router-link to="/cateories">Categorias</router-link>
      </div>
      <div class="login-register">
          <router-link to="/">Login</router-link> |
              <router-link to="/register">Register</router-link>
              <button type="button" @click="logout" class="button">
                  Logout
               </button> 
        </div>
    </div>
    <router-view/>

</div>
</template>
<script>
import { mapActions } from 'vuex'
export default {
  methods: {
    ...mapActions(['getToken', 'logout']),
  },
  created(){
    this.getToken()
  }
}
</script>

<style>
.button{
  margin-left: 10px;
}
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav {
  padding: 30px;
  display: flex;
  padding-left: 5px;
  margin-left: 10px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
  padding: 10px;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
.navigation{
  display: flex;
  flex-direction: row;
  justify-content: space-around;
  align-items: center;
}
</style>

