<template>
    <div class="register">
    <form v-on:submit.prevent="register" class="register">
        <input type="text" placeholder="Ingrese Nombre" v-model="user.username" class="input">
        <br>
        <br>
        <input type="text" placeholder="Ingrese Email" v-model="user.email" class="input">
        <br>
        <br>
        <input type="password" placeholder="Ingrese contraseña" v-model="user.password" class="input">
        <br>
        <br>
        <button type="submit">Registrar</button>
    </form>
  </div>
</template>
<style>
   .register{
    display: flex;
    flex-direction: column;
    width: 60%;
    justify-content: center;
    align-self: center;
    align-content: center;
    margin-left: 20%;
    margin-right: 20%;
    margin-top: 20px;
    margin-bottom: 20px;
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
    name: 'Register',
    data() {
        return {
            user:  {
                username: 'flavio',
                email: '',
                password: '',
            }
        }
    },
      methods: {
       // ...mapActions(['register']),
         register: async function()
            {
                console.log(JSON.stringify(this.user))
                const response = await fetch("http://localhost:8764/user/signup", {
                method: 'POST',
                headers: {
                  'Accept': 'application/json',
                  'Content-Type': 'application/json'
                },
                body:JSON.stringify(this.user)
                });

                response.json().then(data => {
                  console.log(data);
                });
            }      
    }
}
</script>