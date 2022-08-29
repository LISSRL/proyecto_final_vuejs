<template>
    <div id="all-notices" class="register-noticia"> 
        <h1>LISTA DE NOTICIAS</h1>

        <p><router-link :to="{ name: 'Notice' }" ><button class="button-crear">Crear Noticia</button></router-link></p>

        <div class="form-group">
            <input type="text" name="search" v-model="noticeSearch" placeholder="Search notices" class="search-noticia" v-on:keyup="searchNotices">
        </div>
        <div class="categorias">
            <label class="label-categoria">Categoria:</label>
             <select class="select-category" v-on:change="getByCategory($event)" >
             <option v-for="category in categories" :key="category.id" v-bind:value="category.id">{{category.name}}</option>
             </select>
        </div>
        <table class="table table-hover">
            <thead>
            <tr class="tr-header">
                <td>TITULO</td>
                <td>SUBTITULO</td>
                <td>ACCION</td>
            </tr>
            </thead>
            <tbody>
                <tr v-for="notice in notices" :key="notice.id" class="tr-tabla">
                    <td>{{ notice.title }}</td>
                    <td>{{ notice.subtitle }}</td>
                    <td>
                        <router-link :to="{name: 'EditNotice', params: { id: notice.id}}" ><button class="button-editar">Editar</button></router-link>
                        <router-link :to="{name: 'DeleteNotice', params: { id: notice.id }}" ><button class="button-eliminar">Eiminar</button></router-link>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</template>
<style>
    .input{
       height: 30px;
      }
      .register-noticia{
       border: 3px solid #f1f1f1;
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
      }
      .tr-tabla{
       background-color: aliceblue;
       min-height: 50px;
       font-size: large;
       padding: 3px;
       border-color: black;
       border-top-width: 15px;
       border-bottom-width: 15px;
      }
      table {
     border-collapse: separate;
     padding: 3px;
   }
      .tr-header{
       background-color: rgba(12, 34, 116, 0.51);
       min-height: 50px;
       font-size: large;
      }
      .tr-name{
       background-color: #f1f1f1;
      }
      .tr-action{
       background-color: azure;
      }
      .button-crear {
     background-color:rgb(15, 155, 108);
     color: white;
     padding: 7px 10px;
     margin: 8px 0;
     border: none;
     border-radius: 20%;
     cursor: pointer;
     width: 30%;
       }
       .button-editar {
     background-color:rgb(155, 134, 15);
     color: white;
     padding: 7px 10px;
     margin: 8px 0;
     border: none;
     border-radius: 10%;
     cursor: pointer;
     width: 70%;
       }
       .button-eliminar {
     background-color:red;
     color: white;
     padding: 7px 10px;
     margin: 8px 0;
     border: none;
     border-radius: 10%;
     cursor: pointer;
     width: 70%;
       }
       button:hover {
         opacity: 0.8;
       }
       .select-category{
        width: 60%;
        align-self: center;
        height: 40px;
        color: #000;
        background-color: rgba(240, 246, 255, 0.471);
        font-size: large;
       }
       .search-noticias{
        height: 40px;
        width: 200px;
        font-size: large;
       }
       .categorias{
        display: flex;
        justify-content: space-evenly;
       }
       .label-categoria{
        font-size: large;
        margin-left: 70px;
        position: relative;
        top: 10px;
       }
   </style>
<script>

    export default{
        name: 'AllNotices',
        data(){
            return{
                notices: [],
                originalNotices: [],
                noticeSearch: '',
                categories:[]
            }
        },

        created: function()
        {
            this.fetchNoticeData();
        },

        methods: {
            fetchNoticeData: async function()
            {
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

            searchNotices: function()
            {
                if(this.noticeSearch == '')
                {
                    this.noticees = this.originalNoticies;
                    return;
                }

                var searchedNotices = [];
                for(var i = 0; i < this.originalNotices.length; i++)
                {
                    var noticeName = this.originalNotices[i]['title'].toLowerCase();
                    if(noticeName.indexOf(this.noticeSearch.toLowerCase()) >= 0)
                    {
                        searchedNotices.push(this.originalNotices[i]);
                    }
                }
                this.notices = searchedNotices;
            },
                getByCategory: async function(event){
                console.log(event.target.value)
                const response = await fetch("http://localhost:8764/api/categories/"+event.target.value, {
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
         }  
        },
            mounted: async function(){
              const response = await fetch("http://localhost:8764/api/categories", {
                method: 'GET',
                headers: {
                  'Accept': 'application/json',
                  'Content-Type': 'application/json'
                },
                });
                response.json().then(data => {
                  this.categories = data;
                  console.log(data);
                });
            },
    }
</script>