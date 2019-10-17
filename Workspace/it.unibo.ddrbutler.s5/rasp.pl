%====================================================================================
% rasp description   
%====================================================================================
mqttBroker("localhost", "1883").
context(ctxrasp, "localhost",  "MQTT", "0" ).
 qactor( basicrobot, ctxrasp, "it.unibo.basicrobot.Basicrobot").
  qactor( obstacledetector, ctxrasp, "it.unibo.obstacledetector.Obstacledetector").
  qactor( resourcemodelbutler, ctxrasp, "it.unibo.resourcemodelbutler.Resourcemodelbutler").
  qactor( movementhandler, ctxrasp, "it.unibo.movementhandler.Movementhandler").
  qactor( robotmind, ctxrasp, "it.unibo.robotmind.Robotmind").
  qactor( butlermind, ctxrasp, "it.unibo.butlermind.Butlermind").
  qactor( calibration, ctxrasp, "it.unibo.calibration.Calibration").
  qactor( planner, ctxrasp, "it.unibo.planner.Planner").
  qactor( preparehandler, ctxrasp, "it.unibo.preparehandler.Preparehandler").
  qactor( addfoodhandler, ctxrasp, "it.unibo.addfoodhandler.Addfoodhandler").
  qactor( clearhandler, ctxrasp, "it.unibo.clearhandler.Clearhandler").
  qactor( contentontable, ctxrasp, "it.unibo.contentontable.Contentontable").
  qactor( dishwasher, ctxrasp, "it.unibo.dishwasher.Dishwasher").
  qactor( resourcemodeldishwasher, ctxrasp, "it.unibo.resourcemodeldishwasher.Resourcemodeldishwasher").
  qactor( pantry, ctxrasp, "it.unibo.pantry.Pantry").
  qactor( resourcemodelpantry, ctxrasp, "it.unibo.resourcemodelpantry.Resourcemodelpantry").
  qactor( table, ctxrasp, "it.unibo.table.Table").
  qactor( resourcemodeltable, ctxrasp, "it.unibo.resourcemodeltable.Resourcemodeltable").
