<template>
  <div class="home">
  <form v-on:submit.prevent="login" class="login">
        <input type="text" placeholder="Ingrese Email" v-model="user.usernameOrEmail" class="input">
        <br>
        <input type="password" placeholder="Ingrese contraseña" v-model="user.password" class="input">
        <br>
        <button type="submit">Acceder</button>
    </form>
  </div>
</template>
<style>
   .login{
    display: flex;
    flex-direction: column;
    width: 60%;
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
    font-size: medium;
    background-color: antiquewhite;
   }
   .input{
    height: 30px;
   }
</style>
<script>
// import { mapActions } from 'vuex'
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
                const response = await fetch("http://localhost:8764/login", {
                method: 'POST',
                headers: {
                  'Accept': 'application/json',
                  'Content-Type': 'application/json'
                },
                body:JSON.stringify(this.user)
                });
                response.json().then(data => {
                  console.log(data);
                  this.$router.push('dashboard');
                  this.token = data.token;
                  console.log(this.token)
                }).catch(error =>{
                  alert("invalid credentials")
                });
            }
  }
}
</script>