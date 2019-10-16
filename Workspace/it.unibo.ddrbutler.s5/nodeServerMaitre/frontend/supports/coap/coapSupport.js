const coap = require("node-coap-client").CoapClient;

var io;

exports.setIoSocket = function ( iosock ) {
 	io    = iosock;
	console.log("mqtt SETIOSOCKET io=" + io);
}

module.exports.enableObserving=function(){
  coap
      .observe(
        "coap://localhost:5863/content",
        "get",
        function(message){
          console.log(message.payload.toString())
          io.sockets.send( `coapUpdate(${message.payload.toString()})` );
        }
      )
      .then(() => { console.log("Observer enabling success")})
      .catch(err => { console.log("Observer enabling fail") })
      ;
}

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
