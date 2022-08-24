<template>
  <div class="dashboard">
    <div class="news">
          <div class="card" v-for="notice in notices" :key="notice.id">
            <h1 class="title">{{ notice.title}}</h1>
            <p class="content">
                 {{notice.content}}
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
    name:"Dashboard",
    data(){
        return {
            notices:null,
            pagina:1
        }
    },
    mounted: async function(){
       // let direccion = "http://solodata.es/pacientes?page=" + this.pagina;
       // axios.get(direccion).then( data =>{
       //     this.Listapacientes = data.data;
       // });
        const response = await fetch("http://localhost:8764/api/notices", {
                method: 'GET',
                headers: {
                  'Accept': 'application/json',
                  'Content-Type': 'application/json'
                },
                });
                response.json().then(data => {
                  this.notices = data;
                  console.log(data);
                });
    },
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
