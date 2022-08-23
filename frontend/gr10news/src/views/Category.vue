<template>
    <div class="register">
    <form v-on:submit.prevent="register" class="register">
         <label>Nombre:</label>
        <input type="text" placeholder="Ingrese Nombre" v-model="category.name" class="input">
        <br>
        <br>
         <label>Descripcion:</label>
        <input type="text" placeholder="Ingrese Descripcion" v-model="category.description" class="input">
        <br>
        <br>
        <button type="submit">Registrar</button>
    </form>
  </div>
</template>
<style>
   .register{
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
    background-color: antiquewhite;
   }
   .input{
    height: 30px;
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
                  console.log(data);
                });
            }      
    }
}
</script>