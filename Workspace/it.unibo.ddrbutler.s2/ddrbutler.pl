%====================================================================================
% ddrbutler description   
%====================================================================================
context(ctxbutler, "localhost",  "TCP", "3030" ).
 qactor( basicrobot, ctxbutler, "it.unibo.basicrobot.Basicrobot").
  qactor( obstacledetector, ctxbutler, "it.unibo.obstacledetector.Obstacledetector").
  qactor( robotmind, ctxbutler, "it.unibo.robotmind.Robotmind").
  qactor( resourcemodel, ctxbutler, "it.unibo.resourcemodel.Resourcemodel").
  qactor( butlermind, ctxbutler, "it.unibo.butlermind.Butlermind").
  qactor( calibration, ctxbutler, "it.unibo.calibration.Calibration").
