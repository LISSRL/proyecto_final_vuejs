<template>
  <div class="home">
  <form v-on:submit.prevent="login">
        <input type="text" placeholder="Ingrese Email" v-model="user.usernameOrEmail">
        <input type="password" placeholder="Ingrese contraseña" v-model="user.password">
        <button type="submit">Acceder</button>
    </form>
  </div>
</template>

<script>
// import { mapActions } from 'vuex'
export default {
  name: 'Login',
  data() {
    return {
      user: {
        usernameOrEmail: '',
        password: ''
      }
    }
  },
  methods: {
    // ...mapActions(['login'])
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
                });
            }
  }
}
</script>