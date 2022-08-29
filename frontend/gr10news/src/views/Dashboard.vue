<template>
  <div class="dashboard">

        <hr/>
           
          <div class="div-categories">
            <div> <h3 v-on:click="getByCategory(0)">Todo</h3></div>
          
            <div v-for="category in categories" :key="category.id"> <h3 v-on:click="getByCategory(category.id)">{{category.name}}</h3></div>
          </div>
    <div class="news">
          <div class="card" v-for="notice in notices" :key="notice.id">
            <img :src="getImage(notice.publishingDate)" width="200px"  target="_blank"/>
            <h1 class="title">{{ notice.title}}</h1>
            <p class="content">
                 {{notice.content}}
            </p>
            <h4>
                {{publishingDate}}
              </h4>
          </div> 
    </div>
  </div>
</template>
<style>
   .news{
    display: flex;
    flex-direction: row;
    width: 80%;
   }
   .card{
    width: 350px;
    font-family: 'Courier New', Courier, monospace;
    padding: 5px;
    margin: 10px;
    background-color: aliceblue;
    
   }
   .card:hover {
  opacity: 0.8;
}
   .dashboard{
    margin-top: 10px;
   }
   .div-categories{
    display: flex;
    flex-direction: row;
    justify-content:flex-start;
   }
   h3{
    padding: 10px;
    opacity: 90%;
    font-size: larger;
   }
   h3:hover{
    opacity: 100%;
   }
</style>
<script src="http://www.datejs.com/build/date.js" type="text/javascript"></script>
<script>
import {mapState, mapActions} from 'vuex'
import { RUTA_SERVIDOR } from '@/config';
import App from '@/App.vue';
export default {
    name:"Dashboard",
    data(){
        return {
            notices:null,
            categories:null,
            pagina:1,
            urlImg:'',
            numberImg:100,
            publishingDate:null
        }
    },
    methods:{
      getByCategory: async function(value){
                if(value=="0"){
                  const response = await fetch(RUTA_SERVIDOR+"/api/notices", {
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
                } else {
                const response = await fetch(RUTA_SERVIDOR+"/api/categories/"+value, {
                method: 'GET',
                headers: {
                  'Accept': 'application/json',
                  'Content-Type': 'application/json'
                },
                });
                response.json().then(data => {
                  this.notices = data.notices;
                  console.log(data);
                });
              }},
      getImage: function(date){
          this.urlImg = 'https://picsum.photos/'+this.numberImg;
          this.numberImg = this.numberImg+1;
          console.log(date.substring(0, 10))
          var mydate = new Date(date.substring(0, 10));
          var month = ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
            "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"][mydate.getMonth()];
          this.publishingDate = mydate.getDate()+' de ' + month + ' de ' + mydate.getFullYear();

         // this.publishingDate= mydate.toString("MMMM yyyy");
          return this.urlImg;
      },
      changeDate: function(date){

      }
    },
    mounted: async function(){
        console.log(RUTA_SERVIDOR,process.env.VUE_APP_BACKEND_PROTOCOL)
        const response = await fetch(RUTA_SERVIDOR+"/api/notices", {
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
        const responseCategories = await fetch(RUTA_SERVIDOR+"/api/categories", {
                method: 'GET',
                headers: {
                  'Accept': 'application/json',
                  'Content-Type': 'application/json'
                },
                });
                responseCategories.json().then(data => {
                  this.categories = data;
                  console.log(data);
                });
    },
    created() {
        this.dashboard(this.token);
    },
    components: { App }
}
</script>
