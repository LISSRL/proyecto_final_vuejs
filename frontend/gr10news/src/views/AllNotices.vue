<template>
    <div id="all-notices" class="register"> 
        <h1>LISTA DE NOTICIAS</h1>

        <p><router-link :to="{ name: 'Notice' }" class="btn btn-primary">Crear Noticia</router-link></p>

        <div class="form-group">
            <input type="text" name="search" v-model="noticeSearch" placeholder="Search notices" class="form-control" v-on:keyup="searchNotices">
        </div>
        <label>Categoria:</label>
        <select class="categoryselect" v-on:change="getValue($event)" >
        <option v-for="category in categories" :key="category.id" v-bind:value="category.id">{{category.name}}</option>
        </select>
        <table class="table table-hover">
            <thead>
            <tr>
                <td>TITULO</td>
                <td>SUBTITULO</td>
                <td>ACCION</td>
            </tr>
            </thead>
            <tbody>
                <tr v-for="notice in notices" :key="notice.id">
                    <td>{{ notice.title }}</td>
                    <td>{{ notice.subtitle }}</td>
                    <td>
                        <router-link :to="{name: 'EditNotice', params: { id: notice.id}}" class="btn btn-primary">Edit</router-link>
                        <router-link :to="{name: 'DeleteNotice', params: { id: notice.id }}" class="btn btn-danger">Delete</router-link>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

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
                getValue: async function(event){
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