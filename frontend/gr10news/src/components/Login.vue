<template>
  <div class="home">
     
  <form v-on:submit.prevent="login" class="login">
       <img class="imagen-login" src='../assets/login-icon.jpg' width="300px" style="border-radius: 50%; border: 3px solid #fff;"  target="_blank"/>
   
        <input type="text" placeholder="Ingrese nombre de usuario o email" v-model="user.usernameOrEmail" class="input">
        <br>
        <input type="password" placeholder="Ingrese contraseña" v-model="user.password" class="input">
        <br>
        <button type="submit" class="button-register">Acceder</button>
    </form>
  </div>
</template>
<style>
   .login{
    border: 3px solid #f1f1f1;
    display: flex;
    flex-direction: column;
    width: 50%;
    justify-content: center;
    align-self: center;
    align-content: center;
    margin-left: 20%;
    margin-right: 20%;
    margin-top: 30px;
    margin-bottom: 30px;
    padding-top: 20px;
    padding-bottom: 20px;
    padding: 20px;
    font-size:large;
    background-color:#8ab59794;
   }
   .imagen-login{
    align-self: center;
   }
   .input{
    width: 100%;
  padding: 12px 20px;
  margin: 8px 0;
  min-height: 50px;
  display: inline-block;
  border: 1px solid #ccc;
  box-sizing: border-box;
  font-size:large;
   }
   .button-register {
  background-color:rgb(15, 155, 108);
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: 100%;
  
}
button:hover {
  opacity: 0.8;
}
   
</style>
<script>
import { RUTA_SERVIDOR } from '@/config';
export default {
  name: 'Login',
  data() {
    return {
      user: {
        usernameOrEmail: '',
        password: ''
      },
      token:'',
    }
  },
  methods: {
    login: async function()
            {
                console.log(JSON.stringify(this.user))
                const response = await fetch(RUTA_SERVIDOR+"/login", {
                method: 'POST',
                headers: {
                  'Accept': 'application/json',
                  'Content-Type': 'application/json'
                },
                body:JSON.stringify(this.user)
                });
                response.json().then(data => {          
                  sessionStorage.setItem('token', data.token);
                  this.$router.push('dashboard');
                  this.token = data.token;
                }).catch(error =>{
                  alert("invalid credentials")
                });
            }
  }
}
</script>