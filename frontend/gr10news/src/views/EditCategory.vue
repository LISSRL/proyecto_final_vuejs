<template>
    <div id="update-category" class="register2">
        <h1>Update Category</h1>
        <p><router-link :to="{ name: 'AllCategories' }"><button class="button-volver"> volver a categorias</button></router-link></p>
        <notification v-bind:notifications="notifications"></notification>
    <form v-on:submit.prevent="editCategory" class="register">
         <label>Nombre:</label>
        <input type="text" placeholder="Ingrese Nombre" v-model="category.name" class="input">
        <br>
        <br>
         <label>Descripcion:</label>
        <input type="text" placeholder="Ingrese Descripcion" v-model="category.description" class="input">
        <br>
        <br>
        <button type="submit" class="btn-guardar">Guardar</button>
    </form>
  </div>
</template>
<style>
    .register2{
        background-color: #8ab5975d;
        width: 60%;
        align-self: center;
        margin-left: 20%;
        margin-right: 20%;
        margin-bottom: 20px;
    }
     .register{
    display: flex;
    flex-direction: column;
    width: 90%;
    justify-content: center;
    align-self: center;
    align-content: center;
    margin-left: 3%;
    margin-right: 5%;
    margin-top: 20px;
    margin-bottom: 20px;
    font-size: medium;
    background-color: #8ab59794;
   }
   .input{
    height: 30px;
   }
   .btn-guardar{
    background-color:green;
    height: 40px;
    width: 60%;
    align-self: center;
    border-radius: 10%;
    font-size: large;
   }
   .button-volver{
    background-color: gainsboro;
    height: 40px;
    width: 50%;
    align-self: center;
    border-radius: 10%;
    font-size: large;
   }
</style>
<script>
    import Notification from './notifications.vue';

    export default{
        name: 'EditCategory',
        data(){
            return{
                category:{},
                notifications:[]
            }
        },

        created: function(){
            this.getCategory();
        },

        methods: {
            getCategory: async function()
            {
                const response = await fetch("http://localhost:8764/api/categories/"+this.$route.params.id, {
                method: 'GET',
                headers: {
                  'Accept': 'application/json',
                  'Content-Type': 'application/json'
                },
                });
                response.json().then(data => {
                  this.category = data;
                  console.log(data);
                }); 
            },

            editCategory: async function()
            {
                const response = await fetch("http://localhost:8764/api/categories/"+this.$route.params.id, {
                method: 'PUT',
                headers: {
                  'Accept': 'application/json',
                  'Content-Type': 'application/json'
                },
                body:JSON.stringify(this.category)
                });
                response.json().then(data => {
                  this.category = data;
                  this.$router.push('all-Categories');
                }); 
                //this.$http.post('http://localhost:8764/api/categories' + this.$route.params.id, this.category, {
                //    headers : {
                //        'Content-Type' : 'application/json'
                //    }
                //}).then((response) => {
                //    this.notifications.push({
                //        type: 'success',
                //        message: 'Product updated successfully'
                //    });
                //}, (response) => {
                //    this.notifications.push({
                //        type: 'error',
                //        message: 'Product not updated'
                //    });
                //});
            }
        },

        components: {
            'notification' : Notification
        }
    }
</script>
