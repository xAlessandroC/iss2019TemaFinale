const coap = require("node-coap-client").CoapClient;

module.exports.getContent = function(){
  return new Promise(function(resolve, reject) {
    coap.request(
        "coap://localhost:5863/content",
        "get"
    )
    .then(response => {
       resolve(response.payload.toString());
     })
    .catch(err => { reject(null)});
  })
}
