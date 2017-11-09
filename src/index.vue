<template>
  <div class="wrapper">
  <button @click="startScanner">Start Scanner</button>
  <button @click="stopScanner">Close Scanner</button>
    <div v-if="showScanner">
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
    data: function() {
      return {
        showScanner: false
      }
    },
    methods: {
      startScanner: function() {
        this.showScanner = true
      },
      stopScanner: function() {
        this.showScanner = false
      }
    },
    components: {
      'quagga': Quagga
    }
  }
</script>