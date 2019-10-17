var express = require('express');
var router = express.Router();
var mqtt = require('../supports/mqtt/mqttSupport')

/* POST task handler */
router.post('/task', function(req, res, next) {
  /*res.render('homepage', {
     title: 'Maitre'
  });*/
  res.send("ok")
//console.log(req.body)
 var task = req.body.task
 var message = ""
 if(task !== "add_food")
  var message = `msg(taskChange,dispatch,js,butlermind,taskChange(butler,${task},null,null),1)`
 else
  var message = `msg(taskChange,dispatch,js,butlermind,taskChange(butler,${task},${req.body.foodcode},${req.body.quantity}),1)`
 mqtt.publish(message,"unibo/qak/resourcemodelbutler")
});

module.exports = router;
