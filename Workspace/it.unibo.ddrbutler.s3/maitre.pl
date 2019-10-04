%====================================================================================
% maitre description   
%====================================================================================
mqttBroker("localhost", "1883").
context(ctxmaitre, "localhost",  "MQTT", "0" ).
 qactor( maitre, ctxmaitre, "it.unibo.maitre.Maitre").
  qactor( maitremodel, ctxmaitre, "it.unibo.maitremodel.Maitremodel").
