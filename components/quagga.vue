<template>
  <div id="quagga-wrapper">
    <text>Barcode Scanner - ean & upc</text>
    <text v-if="results.length > 2">Barcode: {{ results[results.length-1].code }} Format: {{ results[results.length-1].format }}</text>
    <text v-if="error.length > 0">Errors: {{ error }}</text>
    <div id="interactive" class="viewport"></div>
  </div>
</template>

<style scoped>
/*Coming soon*/
</style>

<script>
var Quagga = require('quagga')
  export default {
    data: function() {
      return {
        results: [],
        error: ''
      }
    },
    mounted: function() {
      console.log('mounted')
      var resultCollector = Quagga.ResultCollector.create({
        capture: true, // keep track of the image producing this result
        capacity: 20,  // maximum number of results to store
        blacklist: [],   // list containing codes which should not be recorded
        filter: function(codeResult) {
            // only store results which match this constraint
            // returns true/false
            // e.g.: return codeResult.format === "ean_13";
            return true;
        }
      });
      Quagga.registerResultCollector(resultCollector)
      Quagga.init({
        inputStream : {
        name : "Live",
        type : "LiveStream"
          // Or '#yourElement' (optional)
        },
        decoder : {
          readers : ["ean_reader",
                        "ean_8_reader",
                        "upc_reader",
                        "upc_e_reader"]
        }
      },  function(err) {
            if (err) {
              this.error = err
                return console.log(err);
            }
            //remove canvas for now, not built into weex for mobile
            document.querySelector('#quagga-wrapper canvas').remove()
            Quagga.start();
          });
      Quagga.onDetected((res)=> {
          // this.results.push({'code': res.codeResult.code, 'format': res.codeResult.format})
        this.results = resultCollector.getResults().map(result => result.codeResult)
      })
    },
    destroyed: function() {
      console.log('destroyed')
      Quagga.stop();
    } 
  }
</script>