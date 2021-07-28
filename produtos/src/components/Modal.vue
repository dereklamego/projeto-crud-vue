<template>
  <div class="modal">
        <div class="container">

            <div class="modalHeader">
                <h4>Detalhe do Produto</h4>
                <a class="close" @click="$emit('close')">X</a>
            </div>

                <form method="" class="modal-form" autocomplete="off">
                        <label for="name">Nome</label>

                        <input class="inputBorder" 
                            type="text" 
                            name="product-name"
                            id="name" 
                            required 
                            maxlength="50" 
                            placeholder="Nome do produto..."
                            v-model="product.name"
                            autocomplete="off"
                        >

                        <label for="description">Descrição</label>
                        <textarea id="description" 
                            name="product-description" 
                            rows="3" cols="45" 
                            maxlength="120">
                        </textarea>

                        <label for="status">Status</label>
                        <select class="inputBorder" id="status" name="product-status"  v-model="product.status" >
                            <option value="EMESTOQUE" >Em estoque</option>
                            <option value="SEMESTOQUE" >Sem estoque</option>
                            <option value="BAIXOESTOQUE" >Baixo estoque</option>
                        </select>

                        <p>Categoria</p>
            
                        <div class="categories">

                            <div class="inputCat">
                        
                                <div>
                                    <input class="inputBorder" 
                                        type="radio" 
                                        id="cat1" 
                                        name="category" 
                                        value="Categoria1"
                                        v-model="product.category"
                                    >
                                    <label for="cat1">Categoria 1</label>
                                </div>
                                    
                                <div>
                                    <input class="inputBorder" 
                                        type="radio" 
                                        id="cat2"
                                        name="category" 
                                        value="Categoria2"
                                        v-model="product.category"
                                    >
                                    <label for="cat2">Categoria 2</label>
                                </div>
                                
                            </div>
                                
                            <div class="inputCat">
                                <div>

                                    <input class="inputBorder" 
                                        type="radio" 
                                        id="cat3"
                                        name="category" 
                                        value="Categoria3"
                                        v-model="product.category"
                                    >
                                    <label for="cat3">Categoria 3</label>
                                </div>

                                <div>
                                    <input class="inputBorder" 
                                        type="radio" 
                                        id="cat4" 
                                        name="category" 
                                        value="Categoria4"
                                        v-model="product.category"
                                    >
                                    <label for="cat4">Categoria 4</label>
                                </div>
                                
                            </div>
                        </div>

                        <div class="foot-input">
                            <div>
                                <label for="price">Preço</label>
                                <input class="inputBorder" 
                                    type="number" 
                                    name="product-price" 
                                    id="price" 
                                    required 
                                    placeholder="0,00"
                                    v-model="product.price"
                                >
                            </div>
                            <div>
                                <label for="total">Quantidade</label>
                                <input class="inputBorder" 
                                    type="number" 
                                    name="total" 
                                    v-model="product.total"
                                    id="total" 
                                    placeholder="0"
                                    
                                    >
                            </div>
                            
                        </div>

                        <div class="modal-footer">
                            <a @click="$emit('close')">X Cancelar</a>
                            <a @click="createProduct" > <font-awesome-icon :icon="['fa', 'check']" />Salvar</a>
                        </div>

                </form>
            
        </div>
  </div>
</template>

<script>

import ApiDataService from "../services/ApiDataServices"

export default {
    name:'Modal',
    props:[
        'func',
        'productData',
        'trigger'
    ],
    data() {
        return {
            product: {
                id: null,
                name: null,
                price: null,
                code:null,
                status:null,
                category:null,
                total:null
            }
        }
    },

    methods:{
        createProduct(){
            console.log(this.trigger)
            if(!this.trigger){
                this.newProduct();

            }else{
                this.editProduct();
                this.trigger=false;
            }       
        },

        newProduct(){
                this.randomAlphanumeric();
                var data = {
                    name: this.product.name,
                    price: this.product.price,
                    code: this.product.code,
                    status: this.product.status,
                    category: this.product.category,
                    
                };

                if(this.allowChanges()){
                    alert("Por favor! Preencha os campos necessarios!")
                }else{
                    ApiDataService.create(data)
                    .then(response => {

                        this.product.id = response.data.id;
                        console.log(response.data);
                        
                    })
                    .catch(e => {
                        console.log(e);
                    });

                    alert("Produto salvo com sucesso! ")
                    location.reload();
                }
        },
        
        loadDataModal(product){

            this.product.name = product.name,
            this.product.price = product.price;
            this.product.status = product.status;
            this.product.category = product.category;
            this.product.total = product.total;
        },

        allowChanges(){
            if(this.product.name==null || this.product.price==null || this.product.category==null|| this.product.total==null || this.product.status==null){
                return true
            }else{
                return false
            }
        },

        editProduct(){
            console.log("entrou pra editar")
            var data = {
                name: this.product.name,
                price: this.product.price,
                status: this.product.status,
                category: this.product.category,
                code: this.productData.code,
                id: this.productData.id
            };

            if(this.allowChanges()){
                alert("Por favor! Preencha os campos necessarios!")
            }else{
                ApiDataService.update(this.productData.id, data)
                .then(response => {
                    console.log(response.data);
                    alert("Produto alterado com sucesso! ")
                })
                .catch(e => {
                    console.log(e);
                });

                location.reload();
            }
        },

        //Metodo para gerar codigo alpha numerico do produto
        randomAlphanumeric() {
            var code = '',
                possible = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789',
                numberOfLetters = 10;
            
            for (var i = 0; i < numberOfLetters; i++) {
                code += possible.charAt(Math.floor(Math.random() * possible.length));
            }
            this.product.code = code;
        }
    }
 
}
</script>

<style scoped>

    .modal{
        position: fixed;
        top: 0;
        right: 0;
        bottom: 0;
        left: 0;
        background: rgba(0, 0, 0, 0.5);
        z-index: 999;
    }
    .modal .container{
        display: flex;
        flex-direction: column;
        justify-content: space-around;
        height: 550px;
        max-width: 350px;
        top:4vh;
        bottom: 4vh;
        left: 0;
        right: 0;
        margin: 5vh auto;
        padding: 2vh 4vh;
        border-radius:5px ;
        background-color: white;
        box-shadow: 0 0 20px rgba(rgba(0, 0, 0, 0.8));
        overflow: auto;
    }

    .close{
        
        cursor: pointer;
        color: rgb(112, 112, 112);
        
    }

    .close:hover{
        color: red;
        transform: scale(1.3,1.3);
        transition: 0.2s;
    }
    
    .modalHeader{
        color: rgb(112, 112, 112);
        display: flex;
        align-items: center;
        justify-content: space-between;
        height: 20px;
    }
    
    .modal-form{
        display: flex;
        flex-direction: column;
        height: 90%;
        justify-content: space-around;
        font-size: 10pt;
        
    }

    .foot-input{
        display: flex;
        flex-direction: row;
        justify-content: space-around;
    }

    .foot-input input{
        width: 140px;
    }
    .foot-input div{
       margin: 0 15px;
    }

    .categories{
        display: flex;
        flex-direction: row;
        margin-top: -35px;
    }

    .inputCat{
        display: flex;
        flex-direction: column;
        margin-right: 12vh;
    }

    .inputCat div{
        margin-top: 2vh;
    }

    textarea{
        border: 1px solid #ccc;
        resize: none;
    }

    .inputBorder{
        padding: 7px;
        border: 1px solid #ccc;
    }

    .modal-footer{
        font-weight: bold;
        display: flex;
        color: #4aa8f5;
        justify-content: flex-end;
    }

    .modal-footer a{
        cursor: pointer;
        margin: 0 10px;
    }

    .modal-footer a:hover{
        color: #1a3e5c;
        transition: 0.4s;
    }
</style>