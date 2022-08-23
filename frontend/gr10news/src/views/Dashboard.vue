<template>
  <div class="dashboard">
    <h1>Ruta protegida</h1>
    <p>{{user}}</p>
    
    <div class="news">
      <div id="nav">
        <label to="/dashboard">Categoria:</label>
        <select>
          <option value="0"> -- -- </option>
          <option value="DEPORTES">DEPORTES</option>
        </select>
      </div>
      <hr>
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
        <img src='https://www.opinion.com.bo/media/opinion/images/2020/08/07/2020080722514620507.jpg' width="100%" style="border-radius: 5px; border: 3px solid #fff;"  target="_blank" />
      </div>
      <hr>
      <hr>
    </div>
    <!-- <div class="news2">
      <div class="card">
        <h1 class="title">Title</h1>
        <img src='https://erbol.com.bo/sites/default/files/BLOQUEOS.jpg' width="100px" height="100px" style="border-radius: 5px; border: 3px solid #fff;"  target="_blank" />
      </div>
      <div class="card">
        <h1 class="title">Title</h1>
        <img src='https://www.animalpolitico.com/wp-content/uploads/2012/05/Ninos_de_la_calle.jpg' width="100px" height="100px" style="border-radius: 5px; border: 3px solid #fff;"  target="_blank" />
      </div>
      <div class="card">
        <h1 class="title">Title</h1>
        <img src='https://ichef.bbci.co.uk/news/640/amz/worldservice…9124548_choque_carros__304x171_other_nocredit.jpg' width="100px" height="100px" style="border-radius: 5px; border: 3px solid #fff;"  target="_blank" />
      </div>
      <div class="card">
        <h1 class="title">Title</h1>
        <img src='https://www.opinion.com.bo/media/opinion/images/2020/08/07/2020080722514620507.jpg' width="100px" height="100px" style="border-radius: 5px; border: 3px solid #fff;"  target="_blank" />
      </div>
    </div> -->
  </div>
  
</template>
<style>
  
   .news{
    display: flex;
    flex-direction: column;
    width: 60%;
   }
   .news2{
    display: flex;
    flex-direction: column;
    width: 40%;
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
