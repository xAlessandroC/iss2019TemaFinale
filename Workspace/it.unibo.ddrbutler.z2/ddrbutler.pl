%====================================================================================
% ddrbutler description   
%====================================================================================
context(ctxbutler, "localhost",  "TCP", "3030" ).
 qactor( sonarhandler, ctxbutler, "it.unibo.sonarhandler.Sonarhandler").
  qactor( obstacledetector, ctxbutler, "it.unibo.obstacledetector.Obstacledetector").
  qactor( butler, ctxbutler, "it.unibo.butler.Butler").
  qactor( execroute, ctxbutler, "it.unibo.execroute.Execroute").
  qactor( movementmind, ctxbutler, "it.unibo.movementmind.Movementmind").
  qactor( basicrobot, ctxbutler, "it.unibo.basicrobot.Basicrobot").
  qactor( maitre, ctxbutler, "it.unibo.maitre.Maitre").
  qactor( fridge, ctxbutler, "it.unibo.fridge.Fridge").
  qactor( pantry, ctxbutler, "it.unibo.pantry.Pantry").
  qactor( dishwasher, ctxbutler, "it.unibo.dishwasher.Dishwasher").
  qactor( table, ctxbutler, "it.unibo.table.Table").
