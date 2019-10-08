%====================================================================================
% ddrbutler description   
%====================================================================================
context(ctxbutler, "localhost",  "TCP", "3030" ).
 qactor( sonarhandler, ctxbutler, "it.unibo.sonarhandler.Sonarhandler").
  qactor( obstacledetector, ctxbutler, "it.unibo.obstacledetector.Obstacledetector").
  qactor( butler, ctxbutler, "it.unibo.butler.Butler").
