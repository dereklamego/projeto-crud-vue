<template>
  <div id="app">
   <p>Controle de estoque</p>
   <MainContainer/>
  </div>
</template>

<script>
import MainContainer from './components/MainContainer'


export default {
  name: 'App',
  components:{
    MainContainer
    
  }
}
</script>

<style scoped>
  #app{
    display:flex;
    width:100%;
    justify-content: center;
    align-items: center;
    flex-direction: column;
  }
</style>
