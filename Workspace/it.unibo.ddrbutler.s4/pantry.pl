%====================================================================================
% pantry description   
%====================================================================================
mqttBroker("localhost", "1883").
context(ctxpantry, "localhost",  "MQTT", "0" ).
 qactor( pantry, ctxpantry, "it.unibo.pantry.Pantry").
  qactor( resourcemodelpantry, ctxpantry, "it.unibo.resourcemodelpantry.Resourcemodelpantry").
