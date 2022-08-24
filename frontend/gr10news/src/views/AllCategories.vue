<template>
    <div id="all-categories" class="register"> 
        <h1>LISTA DE CATEGORIAS</h1>

        <p><router-link :to="{ name: 'Category' }" class="btn btn-primary">Crear Categoria</router-link></p>

        <div class="form-group">
            <input type="text" name="search" v-model="productSearch" placeholder="Search categories" class="form-control" v-on:keyup="searchCategories">
        </div>

        <table class="table table-hover">
            <thead>
            <tr>
                <td>NOMBRE</td>
                <td>DESCRIPCION</td>
                <td>ACCION</td>
            </tr>
            </thead>
            <tbody>
                <tr v-for="category in categories" :key="category.id">
                    <td>{{ category.name }}</td>
                    <td>{{ category.description }}</td>
                    <td>
                        <router-link :to="{name: 'EditCategory', params: { id: category.id}}" class="btn btn-primary">Edit</router-link>
                        <router-link :to="{name: 'delete_category', params: { id: category.id }}" class="btn btn-danger">Delete</router-link>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script>

    export default{
        name: 'AllCategories',
        data(){
            return{
                categories: [],
                originalCategories: [],
                categorySearch: ''
            }
        },

        created: function()
        {
            this.fetchCategoryData();
        },

        methods: {
            fetchCategoryData: async function()
            {
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
                });            },

            searchCategories: function()
            {
                if(this.categorySearch == '')
                {
                    this.categories = this.originalCategories;
                    return;
                }

                var searchedCategories = [];
                for(var i = 0; i < this.originalCategories.length; i++)
                {
                    var categoryName = this.originalCategories[i]['name'].toLowerCase();
                    if(categoryName.indexOf(this.categorySearch.toLowerCase()) >= 0)
                    {
                        searchedCategories.push(this.originalCategories[i]);
                    }
                }

                this.categories = searchedCategories;
            }
        }
    }
</script>
