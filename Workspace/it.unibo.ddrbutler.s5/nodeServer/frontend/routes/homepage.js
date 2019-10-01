var express = require('express');
var router = express.Router();
//const coap = require("node-coap-client").CoapClient;
const coapSupport = require('../supports/coap/coapSupport')

const pic = {
  dish : 20
}
const dic = {
  dish : 0
}


/* GET home page. */
router.get('/', function(req, res, next) {
  var fic = Object()
  //get content on fridge
  coapSupport.getContent().then(response => {
    console.log(response)
    var field=response.split(";")
    field.forEach((e)=>{
      if(e!==""){
        var fc=e.split(",")[0]
        var qnt=e.split(",")[1]

        fic[fc]=qnt
      }
    })

    res.render('homepage', {
       title: 'Maitre',
       pantryInitialContent : JSON.stringify(pic),//.replace(/{|}|"/g,'').replace(":"," "),
       dishwasherInitialContent : JSON.stringify(dic),//.replace(/{|}|"/g,'').replace(":"," "),
       tableInitialContent : null,
       fridgeInitialContent : JSON.stringify(fic),
     });
  }).catch(err => { console.log(err) });
});

module.exports = router;
