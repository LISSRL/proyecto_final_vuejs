<template>
    <div class="register">
    <img alt="Vue logo" src="../assets/logo.png">
    <form v-on:submit.prevent="register">
        <input type="text" placeholder="Ingrese Nombre" v-model="user.username">
        <input type="text" placeholder="Ingrese Email" v-model="user.email">
        <input type="password" placeholder="Ingrese contraseña" v-model="user.password">
        <button type="submit">Registrar</button>
    </form>
  </div>
</template>
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