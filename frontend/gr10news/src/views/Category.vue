<template>
    <div class="register">
    <form v-on:submit.prevent="register" class="register">
         <label>Nombre:</label>
        <input type="text" placeholder="Ingrese Nombre" v-model="user.username" class="input">
        <br>
        <br>
         <label>Descripcion:</label>
        <input type="text" placeholder="Ingrese Descripcion" v-model="user.username" class="input">
        <br>
        <br>
        
        <button type="submit">Registrar</button>
    </form>
  </div>
</template>
<script>
import {mapState, mapActions} from 'vuex'
import App from '@/App.vue';
export default {
    
}
</script>