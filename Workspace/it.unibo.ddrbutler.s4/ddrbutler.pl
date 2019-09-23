%====================================================================================
% ddrbutler description   
%====================================================================================
mqttBroker("localhost", "1883").
context(ctxbutler, "localhost",  "MQTT", "0" ).
 qactor( basicrobot, ctxbutler, "it.unibo.basicrobot.Basicrobot").
  qactor( obstacledetector, ctxbutler, "it.unibo.obstacledetector.Obstacledetector").
  qactor( resourcemodelbutler, ctxbutler, "it.unibo.resourcemodelbutler.Resourcemodelbutler").
  qactor( movementhandler, ctxbutler, "it.unibo.movementhandler.Movementhandler").
  qactor( robotmind, ctxbutler, "it.unibo.robotmind.Robotmind").
  qactor( butlermind, ctxbutler, "it.unibo.butlermind.Butlermind").
  qactor( calibration, ctxbutler, "it.unibo.calibration.Calibration").
  qactor( planner, ctxbutler, "it.unibo.planner.Planner").
  qactor( preparehandler, ctxbutler, "it.unibo.preparehandler.Preparehandler").
  qactor( addfoodhandler, ctxbutler, "it.unibo.addfoodhandler.Addfoodhandler").
  qactor( clearhandler, ctxbutler, "it.unibo.clearhandler.Clearhandler").
  qactor( contentontable, ctxbutler, "it.unibo.contentontable.Contentontable").
  qactor( console, ctxbutler, "it.unibo.console.Console").
