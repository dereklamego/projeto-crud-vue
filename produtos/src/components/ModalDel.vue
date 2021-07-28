<template>
  <div id="modalDel">
      
      <div class="container">
            <div class="modalHeader">
                <h4>Confirmação</h4>
                <a class="close" @click="$emit('close')">X</a>
            </div>

            <div class="modalMid">
                <font-awesome-icon :icon="['fa','exclamation-triangle']" />
                <p>   Você deseja realmente excluir <strong> {{productName}} </strong>? </p>
                
            </div>
                
            <div class="modal-footer">
                <a @click="$emit('close')">X Não</a>
                <a @click="allowDelete(id)"> <font-awesome-icon :icon="['fa', 'check']" />Sim</a> 
                <!-- <a @click=""> <font-awesome-icon :icon="['fa', 'check']" />Sim</a> -->
            </div>
            
      </div>
  </div>
</template>

<script>
//import ApiDataService from "../services/ApiDataServices"

export default {
    name:'ModalDel',
    props:[
        'delete',
        'toglle',
        'id',
        'productName',
        'deleteManyTrigger',
        'blockFlag',
        'idsToDelete'

    ],
    
    methods:{

        
        allowDelete(id){

            if(this.deleteManyTrigger==true){
                this.idsToDelete.forEach(id => {
                    this.delete(id);
                });
                this.blockFlag();
                this.toglle();
                
            }else{
                this.delete(id);
                this.toglle();
            }
            
        }
    }

}
</script>

<style scoped>

    #modalDel{
        position: fixed;
        top: 0;
        right: 0;
        bottom: 0;
        left: 0;
        background: rgba(0, 0, 0, 0.5);
        z-index: 999;
    }
    .modalHeader{
        color: rgb(58, 58, 58);
        display: flex;
        align-items: center;
        justify-content: space-between;
        height: 20px;
    }
    .container{
        display: flex;
        flex-direction: column;
        justify-content: space-around;
        height: 150px;
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

    .modalMid{
        display: flex;
        color:rgb(88, 88, 88);
        align-items: center;
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

</style>