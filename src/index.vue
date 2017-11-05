<template>
  <div class="wrapper">
    <div>
      <quagga></quagga>
    </div>
  </div>
</template>

<style>
/*Coming Soon*/
</style>

<script>
// import Nat from 'natjs'
var Quagga = require('../components/quagga.vue')
  export default {
    components: {
      'quagga': Quagga
    }
  }
</script>