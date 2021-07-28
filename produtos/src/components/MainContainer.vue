<template>

  <article id="main-container" class="border-div">

      <section class="topButtons gray-bg border-div">

        <button class="btn btn-green" @click="toggleModal">
             <font-awesome-icon :icon="['fa', 'plus']" /> Novo
        </button>

        <button class="btn btn-red"  @click="deleteAll">
            <font-awesome-icon :icon="['fa', 'trash']"/> Excluir
        </button>

      </section>

      <section class="topTable gray-bg border-div">
          <h4>Gerenciar Produto</h4>
          <div class="searchContainer">
              <input class="searchInput" type="text" placeholder="Nome do produto..." v-model="productName">
              <button class="btn btn-green" @click="findByName" >
                    <font-awesome-icon :icon="['fa', 'search']" /> 
                    Pesquisar
            </button>
          </div>
      </section>

      <Table :data="showModal" 
        :func="toggleModal" 
        :searchByName="findByName" 
        :productName="productName" 
       
        ref="tableChild"

    /> 
    <!-- Meu componente de tabela -->

      <Modal v-if="showModal" 
        @close="toggleModal" 
        :func="toggleModal"
    />

      
  </article>
</template>

<script>
//import api from '../services/api'
import Table from './Table'

import Modal from './Modal'

export default {
    components:{
        Table,
        Modal
    },
    data(){
        return{
            showModal: false,
            productName:null,
            
        }
    },


    methods:{
        toggleModal(){
            this.showModal = !this.showModal;
        },
        
        findByName(){
            this.$refs.tableChild.searchName(this.productName);
        },

        deleteAll(){
            
            if(this.$refs.tableChild.productsIds.length>0){ 
                this.$refs.tableChild.deleteManyFlag =true;
                this.$refs.tableChild.deleteMany();
            }
        }

    }
     
}
</script>

<style scoped>
    #main-container{
        background-color: white;
        width: 90%;
        
        padding: 10px;
    }

    .gray-bg{
        background-color: #f8f9fa;
    }

    .border-div{
        border-radius: 5px;
        border: solid 1px #edeff1;
    }

    .topButtons{
        padding: 5px;
        display: flex;
        align-items: center;
        height: 8vh;

    }

    .topButtons button span{
        font-size: 25px;
    }

    .btn{
        height: 30px;
        width: 75px;
        margin-left: 5px;
        outline: none;
        border:0;
        color:white;
        cursor: pointer;
        /* display: flex; */
    
    }
    .btn-green{
        background-color:#689f38;
    }

    .btn-green:hover{
        background-color:#3f6122;
        transition: 0.4s;
    }

    .btn-red{
        background-color:#e17f7f;
    }

    .btn-red:hover{
        background-color:#8f4e4e;
        transition: 0.4s;
    }

    .topTable{
        display: flex;
        align-items: center;
        justify-content: space-between;
        color:#71787c;
        margin-top: 15px;
    }

    .topTable h4{
        margin-left: 10px;
    }

    .searchContainer button{
        width: 100px;
        margin-left: 0;
        margin-right: 5px; 
        flex-direction: row;
    }

    .searchInput{
        padding: 7px;
        border: 1px solid #ccc;
        margin-right: 0;
    }

</style>