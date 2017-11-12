<template>
  <div class="wrapper">
  <template v-if="device === 'Web'">
  <button class="button" @click="startScanner" v-if="!showScanner">Start Scanner</button>
  <button class="button" @click="stopScanner" v-else>Close Scanner</button>
    <div v-if="showScanner">
      <quagga></quagga>
    </div>
  </template>
  <template v-else>
  <text class="button" @click="scanner">Start Scanner</text>
  <text v-if="androidScanResults">Code: {{ androidScanResults }}</text>
  </template>
  </div>
</template>

<style>
/*Coming Soon*/
.button {
    font-size: 36;
    width: 200;
    color: white;
    text-align: center;
    padding-top: 10;
    padding-bottom: 10;
    border-width: 2;
    border-style: solid;
    margin-right: 20;
    background-color: #66b3ff;
    border-color: #66b3ff;
  }
</style>

<script>
var Nat = require('natjs')
var Quagga = require('../components/quagga.vue')
var scannernew = weex.requireModule('scan')
// console.log(weex)
  export default {
    data: function() {
      return {
        showScanner: false,
        device: weex.config.env.platform,
        androidScanResults: ''
      }
    },
    methods: {
      scanner: function() {
        scannernew.scanCapture((results) => {
          this.androidScanResults = results.result
        });
      },
      scannertwo: function() {
        scannernew.scanBarcode();
      },
      startScanner: function() {
        this.showScanner = true
      },
      stopScanner: function() {
        this.showScanner = false
      },
      captureImage() {
        Nat.camera.captureImage({}, (err, ret) => {
          if (err) {
            Nat.toast('[ERROR] ' + JSON.stringify(err))
            return
          }
          Nat.toast(JSON.stringify(ret, null, 2))
            this.path = ret.path
          })
      },
      preview() {
        if (!this.path) {
          Nat.toast('Please take your photo first :)')
          return
        }
        Nat.image.preview(this.path)
      }
    },
    components: {
      'quagga': Quagga
    }
  }
</script>