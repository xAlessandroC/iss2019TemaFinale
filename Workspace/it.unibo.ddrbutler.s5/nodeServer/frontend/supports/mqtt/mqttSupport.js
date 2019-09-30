const mqtt   = require ('mqtt');  //npm install --save mqtt
const topic  = ["unibo/qak/events","unibo/qak/maitremodel"];

var mqttAddr = 'mqtt://localhost'

var client   = mqtt.connect(mqttAddr);
var io  ; 	//Upgrade for socketIo;

console.log("mqtt client= " + client );

exports.setIoSocket = function ( iosock ) {
 	io    = iosock;
	console.log("mqtt SETIOSOCKET io=" + io);
}


client.on('connect', function () {
    for(s in topic){
      client.subscribe( topic[s] );
      console.log(`client has subscribed to ${topic[s]}`)
    }

	  console.log('client has connected successfully with ' + mqttAddr);
});

//The message usually arrives as buffer, so I had to convert it to string data type;
client.on('message', function (topic, message){
  console.log("mqtt RECEIVES:"+ message.toString() + " by " + topic);

  var msg = ""
  switch(topic){
    case "unibo/qak/maitremodel" :
      var p1=message.toString().indexOf(',modelChangeMaitre')
      var p2=message.toString().indexOf('),')
      msg=message.toString().substring(p1+1,p2+1)
      break;
    case "unibo/qak/events" :
      var p1=message.toString().indexOf(',updateContent')
      var p2=message.toString().indexOf('),')
      msg=message.toString().substring(p1+1,p2+1)
      break;
  }

  console.log("mqtt send on io.sockets| "+ msg);
  io.sockets.send( msg );
});

exports.publish = function( msg, topic ){
	console.log('mqtt publish ' + msg + 'on ' + topic);
	client.publish(topic, msg);
}
