<template>
  <div class="dashboard">
    <h1>Ruta protegida</h1>
    <p>{{user}}</p>
    <div class="news">
          <div class="card">
            <h1 class="title">Title</h1>
            <p class="content">
              Lorem Ipsum is simply dummy text of the printing and typesetting industry. 
              Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, 
              when an unknown printer took a galley of type and scrambled it to make a type specimen book.
               It has survived not only five centuries, but also the leap into electronic typesetting, 
               remaining essentially unchanged. It was popularised in the 1960s with the release 
               of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software 
               like Aldus PageMaker including versions of Lorem Ipsum.
            </p>
          </div> 
          <div class="card">
            <h1 class="title">Title2</h1>
            <p class="content">
              Lorem Ipsum is simply dummy text of the printing and typesetting industry. 
              Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, 
              when an unknown printer took a galley of type and scrambled it to make a type specimen book.
               It has survived not only five centuries, but also the leap into electronic typesetting, 
               remaining essentially unchanged. It was popularised in the 1960s with the release 
               of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software 
               like Aldus PageMaker including versions of Lorem Ipsum.
            </p>
          </div> 
          <div class="card">
            <h1 class="title">Title3</h1>
            <p class="content">
              Lorem Ipsum is simply dummy text of the printing and typesetting industry. 
              Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, 
              when an unknown printer took a galley of type and scrambled it to make a type specimen book.
               It has survived not only five centuries, but also the leap into electronic typesetting, 
               remaining essentially unchanged. It was popularised in the 1960s with the release 
               of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software 
               like Aldus PageMaker including versions of Lorem Ipsum.
            </p>
          </div>  
    </div>>
  </div>
</template>
<style>
   .news{
    display: flex;
    flex-direction: column;
    width: 60%;
   }
   .card{
    width: 100%;
    font-family: 'Courier New', Courier, monospace;
    padding: 5px;
    margin: 10px;
   }
   .dashboard{
    margin-top: 10px;
   }
</style>
<script>
import {mapState, mapActions} from 'vuex'
import App from '@/App.vue';
export default {
    created() {
        this.dashboard(this.token);
    },
    computed: {
        ...mapState(["token", "user"])
    },
    methods: {
        ...mapActions(["dashboard"])
    },
    components: { App }
}
</script>
