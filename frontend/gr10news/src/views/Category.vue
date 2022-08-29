<template>
    <div class="">
    <form v-on:submit.prevent="register" class="register">
         <label>Nombre:</label>
        <input type="text" placeholder="Ingrese Nombre" v-model="category.name" class="input">
        <br>
        <br>
         <label>Descripcion:</label>
        <input type="text" placeholder="Ingrese Descripcion" v-model="category.description" class="input">
        <br>
        <br>
        <button type="submit" class="btn-registrar">Registrar</button>
    </form>
  </div>
</template>
<style>
   .register{
    display: flex;
    flex-direction: column;
    width: 50%;
    justify-content: center;
    align-self: center;
    align-content: center;
    margin-left: 25%;
    margin-right: 25%;
    margin-top: 20px;
    margin-bottom: 20px;
    font-size: medium;
    background-color: #8ab59794;
   }
   .input{
    height: 30px;
   }
   .btn-registrar{
    background-color: green;
    width: 60%;
    align-self: center;
    height: 40px;
    border-radius: 10%;
   }
</style>
<script>
export default {
    name: 'Category',
    data() {
        return {
            category:  {
                name: '',
                description: '',
            }
        }
    },
      methods: {
         register: async function()
            {
                console.log(JSON.stringify(this.category))
                const response = await fetch("http://localhost:8764/api/categories", {
                method: 'POST',
                headers: {
                  'Accept': 'application/json',
                  'Content-Type': 'application/json'
                },
                body:JSON.stringify(this.category)
                });

                response.json().then(data => {
                  this.$router.push('/all-categories');
                  console.log(data);
                });
            }      
    }
}
</script>