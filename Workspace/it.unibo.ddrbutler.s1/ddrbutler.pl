%====================================================================================
% ddrbutler description   
%====================================================================================
context(ctxbutler, "192.168.43.185",  "TCP", "3030" ).
context(ctxsupport, "localhost",  "TCP", "3031" ).
 qactor( basicrobot, ctxbutler, "it.unibo.basicrobot.Basicrobot").
  qactor( obstacledetector, ctxbutler, "it.unibo.obstacledetector.Obstacledetector").
  qactor( robotmind, ctxbutler, "it.unibo.robotmind.Robotmind").
  qactor( resourcemodel, ctxbutler, "it.unibo.resourcemodel.Resourcemodel").
  qactor( console, ctxsupport, "it.unibo.console.Console").
