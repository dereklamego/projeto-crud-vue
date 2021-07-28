<template>
  <div id="table">
      
        <table >
            <!-- row dos titulos -->
            <tr>
                <th>
                    <form>
                        <input type="checkbox" v-model="allSelected"  @click="selectAll">
                    </form>
                </th>

                <th>
                     
                   Código <font-awesome-icon :icon="['fa', 'sort']"  class="sortIcon" @click="sortType('code')"/>
                </th>

                <th>
                     
                   Nome <font-awesome-icon :icon="['fa', 'sort']" class="sortIcon" @click="sortType('name')"/>
                </th>

                <th>
                    Preço <font-awesome-icon :icon="['fa', 'sort']" class="sortIcon" @click="sortType('price')"/>
                </th>

                <th>
                    Categoria <font-awesome-icon :icon="['fa', 'sort']"  class="sortIcon" @click="sortType('category')"/>
                </th>

                <th>
                    Status <font-awesome-icon :icon="['fa', 'sort']"  class="sortIcon" @click="sortType('status')"/>
                </th>

                <th></th>
            </tr>

            <!-- row dos produtos -->
            <tr v-for="product in products " :key="product.id">
                
                <td>
                    <form>
                        <input type="checkbox" @click="selectOne" :value="product.id" v-model="productsIds">
                    </form>
                </td>

                <td>{{product.code}}</td>
                <td>{{product.name}}</td>
                <td>{{formatCurrency(product.price)}}</td>
                <td>{{product.category}}</td>
                <td><strong>{{product.status}}</strong></td>
                
                
                <td>
                    <button class="editBtn" @click="modalGetProduct(product)">
                        <font-awesome-icon :icon="['fa', 'pen']" />
                    </button>

                    <button class="deleteBtn" @click="modalGetIdName(product.id,product.name)" >
                        <font-awesome-icon :icon="['fa', 'trash']" />
                    </button>
                </td>
            </tr>
    
            
        </table>

        <div>
            
        </div>
        <!-- usando o modal para passar função por parametro -->
        <Modal v-if="showModalUpdate"
            @close="toggleModalEdit" 
            :productData="currentProduct" 
            :trigger="updateTrigger" 
            ref="modalChild"
        />

        <ModalDel v-if="showModalDel" 
            @close="toggleModalDel" 
            :delete="deleteProduct" 
            :toglle="toggleModalDel" 
            :id="productId" 
            :productName="productName"
            :deleteManyTrigger="deleteManyFlag"
            :blockFlag="blockFlagDelete"
            :idsToDelete="productsIds"
        />
        
  </div>
</template>

<script>
import api from '../services/api'
import Modal from '../components/Modal'
import ModalDel from '../components/ModalDel'
import ApiDataService from "../services/ApiDataServices"

export default {
    components:{
        ModalDel,
        Modal
    },
    props:['func','id'],
    data(){
        return{
            products:[],
            Modal,
            showModalDel: false,
            showModalUpdate: false,
            productId:null,
            productName:null,
            currentProduct:{},
            updateTrigger:false,
            nameFlag:false,
            codeFlag:false,
            statusFlag:false,
            priceFlag:false,
            categoryFlag:false,
            selected: [],
            allSelected: false,
            deleteManyFlag:false,
            productsIds: [],

        }
    },

    methods:{

        modalGetIdName(id,name){
            this.productName = name;
            this.productId =id;
            this.toggleModalDel();

        },

        test(param){
            alert(param)
        },

        modalGetProduct(product){ 
            this.toggleModalEdit();
            this.currentProduct = product;
            setTimeout(()=>{ 
                this.$refs.modalChild.loadDataModal(product);
            }, 10);
            this.updateTrigger=true;
        },

        toggleModalDel(){
            this.showModalDel = !this.showModalDel;
        },

        toggleModalEdit(){
            this.showModalUpdate =! this.showModalUpdate;
            
           
        },

        deleteProduct(id){
                ApiDataService.delete(id)
                let productFilter = this.products.filter((product) => {
                    return (product.id !== id);  
                });
                return this.products = productFilter;
        },

       searchName(name) {
          ApiDataService.findByName(name)
            .then(response => {
                this.products = response.data;
                console.log(response.data);
            })
            .catch(e => {
                console.log(e);
            });
        },

        sortType(type){
            
            if(type=="price"){
                if(this.priceFlag==false){
                    this.products.sort((a, b) => a.price - b.price);
                    this.priceFlag= true
                }else{
                    this.products.sort((a, b) => b.price - a.price);
                    this.priceFlag= false
                }
            }else{
                if(eval(`this.${type}Flag==false`)){
                    this.products.sort((a, b) => a[type].localeCompare(b[type]));
                    eval(`this.${type}Flag = true`)
                }else{
                    this.products.sort((a, b) => b[type].localeCompare(a[type]))
                    eval(`this.${type}Flag = false`)
                }
            }
        },

        formatCurrency(value) {
            return value.toLocaleString('pt-BR', {style: 'currency', currency: 'BRL'});
        },

        selectAll(){

            if(this.allSelected!=false){ //so entra se for true

                this.productsIds = [];
                this.allSelected=false;
            }else{                      //so entra se for false

                this.allSelected=true;
                this.productsIds = [];

                for(this.p in this.products) {
                    this.productsIds.push(this.products[this.p].id);
                }
   
            }
            
        },

        blockFlagDelete(){
            this.deleteManyFlag=false;
        },

        deleteMany(){
                if(this.deleteManyFlag == true){  
                    this.productName=" varios produtos selecionados?"
                    this.toggleModalDel();
                }
        },

        selectOne() {
            setTimeout(()=>{ 

               if(this.products.length>this.productsIds.length){

                   this.allSelected = false;

               }else{
                   this.allSelected = true;
               }

            }, 10);
            
        },

    },



    async created(){
        const response = await api.get('/');
        this.products = response.data;   
    },

    watch:{
        async products(){
            this.products = this.response.data;
        } ,
        deep:true,
    },

    
}
</script>

<style scoped >
    table {
        border-collapse: collapse;
        width: 100%; 
    }
    td, th {
        border-top: 1px solid #dddddd;
        text-align: left;
        padding: 8px;
    }

    button{
        border: 0;
        width: 30px;
        height: 30px;
        border-radius: 50%;
        margin: 5px;
        cursor: pointer;
    }
    .editBtn{
        color:white;
        background-color: #689f38;
    }

    .editBtn:hover{
        background-color: #3f6122;
        transition: 0.4s;
    }

    .deleteBtn{
        color:black;
        background-color: #fbc02d;
    }

    .deleteBtn:hover{
        background-color: #99761c;
        transition: 0.4s;
        color: white;
    }

    .sortIcon{
        font-size: 14pt;
    }

    .sortIcon:hover{
        cursor: pointer;
        color:dodgerblue;
        
    }

    .active {
        border-bottom: solid 3px #25A26B
    }

    th{
       background-color: #f8f9fa!important;
       height: 30px;
       
    }

</style>