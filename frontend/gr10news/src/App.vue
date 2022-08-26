<template>
  <div id="app">
    <div class="navigation" style="background-color:rgb(3, 19, 123); box-shadow: 5px 0px 10px; ">
    <div>
        <img class="Liss..." title="Liseth Rojas" src='https://wpexpand.com/demo/html/kotha-pro/assets/images/me.jpg' width="60px" style="border-radius: 50%; border: 3px solid #fff;"  target="_blank"/>
        <div class="login-register">
         <!--  <div class="col-md-8" style="margin:15px; color: #fff; font-family:Arial, Helvetica, sans-serif;"><label class="login" id="login"></label>
          </div>   -->
          <router-link to="/">LOGIN</router-link>
          <button type="button" @click="logout" class="button pull-right" style="float: right;">LOGOUT</button> 
        </div>
    </div>
    
      
    </div> 
    <div id="nav">
        <router-link to="/dashboard">INICIO</router-link>
        <router-link to="/all-categories">CATEGORIAS</router-link>
        <router-link to="/all-notices">NOTICIAS</router-link>
        <router-link to="/register">USUARIOS</router-link>
    </div>
    <hr>
      
    <router-view/>

</div>
</template>
<script>
export default {
  methods: {
        logout: function()
            {
              sessionStorage.clear();
              this.$router.push('dashboard');
            },
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

