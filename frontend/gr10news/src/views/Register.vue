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
        <button type="submit" class="button-register">Registrar</button>
    </form>
  </div>
</template>
<style>

   .input{
    height: 30px;
   }
   .register{
    border: 3px solid #f1f1f1;
    display: flex;
    flex-direction: column;
    width: 50%;
    justify-content: center;
    align-self: center;
    align-content: center;
    margin-left: 10%;
    margin-right: 10%;
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
// import { mapActions } from 'vuex'
export default {
    name: 'Register',
    data() {
        return {
            user:  {
                username: '',
                email: '',
                password: '',
            }
        }
    },
      methods: {
         register: async function()
            {
                const response = await fetch("http://localhost:8764/user/signup", {
                method: 'POST',
                headers: {
                  'Accept': 'application/json',
                  'Content-Type': 'application/json'
                },
                body:JSON.stringify(this.user)
                });

                response.json().then(data => {
                  this.$router.push('dashboard');
                  console.log(data);
                });
            }      
    }
}
</script>