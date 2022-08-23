<template>
    <div class="register">
    <form v-on:submit.prevent="register" class="register">
         <label>Titulo:</label>
        <input type="text" placeholder="Ingrese Titulo" v-model="user.username" class="input">
        <br>
        <br>
         <label>Subtitulo:</label>
        <input type="text" placeholder="Ingrese Subtitulo" v-model="user.username" class="input">
        <br>
        <br>
        <label>Contenido:</label>
        <input type="text" placeholder="Ingrese contenido" v-model="user.email" class="input">
        <br>
        <br>
        <label>Autor:</label>
        <input type="text" placeholder="Ingrese Autor" v-model="user.username" class="input">
        <br>
        <br>
        <label>Categoria:</label>
        <input type="text" placeholder="Ingrese categotia" v-model="user.email" class="input">
        <br>
        <br>
        <label>Url-Imagen:</label>
        <input type="text" placeholder="Ingrese Url-Imagen" v-model="user.email" class="input">
        <br>
        <br>
        <label>Fecha:</label>
        <input type="text" placeholder="Ingrese fecha" v-model="user.password" class="input">
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