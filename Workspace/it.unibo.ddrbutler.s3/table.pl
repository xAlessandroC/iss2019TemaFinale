%====================================================================================
% table description   
%====================================================================================
mqttBroker("localhost", "1883").
context(ctxtable, "localhost",  "MQTT", "0" ).
 qactor( table, ctxtable, "it.unibo.table.Table").
  qactor( resourcemodeltable, ctxtable, "it.unibo.resourcemodeltable.Resourcemodeltable").
