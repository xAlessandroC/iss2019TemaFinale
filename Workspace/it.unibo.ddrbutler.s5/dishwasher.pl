%====================================================================================
% dishwasher description   
%====================================================================================
mqttBroker("localhost", "1883").
context(ctxdishwasher, "localhost",  "MQTT", "0" ).
 qactor( dishwasher, ctxdishwasher, "it.unibo.dishwasher.Dishwasher").
  qactor( resourcemodeldishwasher, ctxdishwasher, "it.unibo.resourcemodeldishwasher.Resourcemodeldishwasher").
