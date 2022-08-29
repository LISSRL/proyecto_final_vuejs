<template>
    <div id="all-categories" class="register-categoria"> 
        <h1>LISTA DE CATEGORIAS</h1>

        <p><router-link :to="{ name: 'Category' }" class="btn btn-primary"> <button class="button-crear">Crear Categoria</button></router-link></p>

        <div class="form-group">
            <input type="text" name="search" v-model="productSearch" placeholder="Search categories" class="form-control" v-on:keyup="searchCategories">
        </div>

        <table class="">
            <thead>
            <tr class="tr-header">
                <td>NOMBRE</td>
                <td>DESCRIPCION</td>
                <td>ACCION</td>
            </tr>
            </thead>
            <tbody>
                <tr v-for="category in categories" :key="category.id" class="tr-tabla">
                    <td class="tr-name">{{ category.name }}</td>
                    <td>{{ category.description }}</td>
                    <td class="tr-action">
                        <router-link :to="{name: 'EditCategory', params: { id: category.id}}" ><button class="button-editar">Editar</button></router-link>
                        <router-link :to="{name: 'delete_category', params: { id: category.id }}"><button class="button-eliminar">Eliminar</button></router-link>
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
   .register-categoria{
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
</style>
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
