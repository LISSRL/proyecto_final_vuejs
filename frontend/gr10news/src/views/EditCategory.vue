<template>
    <div id="update-category" class="register">
        <h1>Update Category</h1>

        <p><router-link :to="{ name: 'AllCategories' }">Retornar a categorias</router-link></p>
        <notification v-bind:notifications="notifications"></notification>
    <div class="register">
    <form v-on:submit.prevent="editCategory" class="register">
         <label>Nombre:</label>
        <input type="text" placeholder="Ingrese Nombre" v-model="category.name" class="input">
        <br>
        <br>
         <label>Descripcion:</label>
        <input type="text" placeholder="Ingrese Descripcion" v-model="category.description" class="input">
        <br>
        <br>
        <button type="submit">Guardar</button>
    </form>
  </div>
    </div>
</template>

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
                  console.log(data);
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
