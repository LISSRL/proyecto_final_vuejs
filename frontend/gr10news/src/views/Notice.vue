<template>
    <div class="register">
    <form v-on:submit.prevent="register" class="register">
         <label>Titulo:</label>
        <input type="text" placeholder="Ingrese Titulo" v-model="notice.title" class="input">
        <br>
        <br>
         <label>Subtitulo:</label>
        <input type="text" placeholder="Ingrese Subtitulo" v-model="notice.subtitle" class="input">
        <br>
        <br>
        <label>Contenido:</label>
        <textarea placeholder="Ingrese contenido" v-model="notice.content" class="input-content"/>
        <br>
        <br>
        <label>Autor:</label>
        <input type="text" placeholder="Ingrese Autor" v-model="notice.author" class="input">
        <br>
        <br>
        <label>Categoria:</label>
        <select class="categoryselect" v-on:change="getValue($event)" >
        <option v-for="category in categories" :key="category.id" v-bind:value="category.id">{{category.name}}</option>
    </select>
        <br>
        <br>
        <label>Imagen:</label>
        <input type="file" v-on:change="onFile($event)" accept="image/*" />
        <img :src="imgSrc" v-if="imgSrc" />
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
    background-color: #8ab5976b;
   }
   .input{
    height: 30px;
   }
   .input-content{
    height: 200px;
   }
</style>
<script>
export default {
    name: 'Notice',
    data() {
        return {
            notice:  {
                title: '',
                subtitle: '',
                password: '',
                category:{},
                imgSrc: ''
            },
            categories:[]
        }
    },
      methods: {
         register: async function()
            {
                console.log(JSON.stringify(this.notice))
                const response = await fetch("http://localhost:8764/api/notices", {
                method: 'POST',
                headers: {
                  'Accept': 'application/json',
                  'Content-Type': 'application/json'
                },
                body:JSON.stringify(this.notice)
                });

                response.json().then(data => {
                  console.log(data);
                });
            },   
        getValue: function(event){
      console.log(event.target.value)
          this.notice.category.id = event.target.value
          console.log(JSON.stringify(this.notice))
         },
         onFile: async function(e) {
            console.log(e);
            const files = e.target.files
            if (!files.length) return
            const reader = new FileReader()
            reader.readAsDataURL(files[0])
           
            reader.onload = (event) => {this.imgSrc = files[0].name;console.log(files[0].name,'name'); console.log(event.target.result,"sss")}
            console.log(reader.readAsDataURL(files[0]),'ss')
            console.log("file",this.imgSrc)
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