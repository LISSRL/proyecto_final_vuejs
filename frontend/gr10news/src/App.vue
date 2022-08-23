<template>
  <div id="app">
    <div class="navigation" style="background-color:rgb(3, 19, 123); box-shadow: 5px 0px 10px; ">
      <div>
        <div style=" float: left; padding-left: 5px;" >
          <img class="Liss..." title="Liseth Rojas" src='https://wpexpand.com/demo/html/kotha-pro/assets/images/me.jpg' width="60px" style="border-radius: 50%; border: 3px solid #fff;"  target="_blank" />
        </div>
        <div class="col-md-8" style="margin:15px; color: #fff; font-family:Arial, Helvetica, sans-serif; float: left;"><br><label>Rojas Lopez Lis</label>
        </div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <div class="login-register" style=" float: right;">
        <br>
          <router-link to="/" style="color: blanchedalmond">LOGIN</router-link>
          <button type="button" @click="logout" class="button" style="float: right !important; color: black">LOGOUT</button> 
        </div>
      </div>
    </div> 
    <hr>
    <div id="nav">
        <router-link to="/dashboard">INICIO</router-link>
        <router-link to="/cateory">CATEGORIAS</router-link>
        <router-link to="/notice">NOTICIAS</router-link>
        <router-link to="/register">USUARIOS</router-link>
    </div>
    <hr style="box-shadow: 5px 0px 10px;"/>
      
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
  padding: 0px;
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
  /* justify-content: space-around;
  align-items: right; */
}
</style>

