%====================================================================================
% ddrbutler description   
%====================================================================================
context(ctxbutler, "localhost",  "TCP", "3030" ).
 qactor( basicrobot, ctxbutler, "it.unibo.basicrobot.Basicrobot").
  qactor( obstacledetector, ctxbutler, "it.unibo.obstacledetector.Obstacledetector").
  qactor( resourcemodelbutler, ctxbutler, "it.unibo.resourcemodelbutler.Resourcemodelbutler").
  qactor( movementhandler, ctxbutler, "it.unibo.movementhandler.Movementhandler").
  qactor( robotmind, ctxbutler, "it.unibo.robotmind.Robotmind").
  qactor( butlermind, ctxbutler, "it.unibo.butlermind.Butlermind").
  qactor( calibration, ctxbutler, "it.unibo.calibration.Calibration").
  qactor( planner, ctxbutler, "it.unibo.planner.Planner").
  qactor( console, ctxbutler, "it.unibo.console.Console").
