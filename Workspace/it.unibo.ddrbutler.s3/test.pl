%====================================================================================
% test description   
%====================================================================================
context(ctxtest, "localhost",  "TCP", "3031" ).
 qactor( basicrobot, ctxtest, "it.unibo.basicrobot.Basicrobot").
  qactor( obstacledetector, ctxtest, "it.unibo.obstacledetector.Obstacledetector").
  qactor( resourcemodelbutler, ctxtest, "it.unibo.resourcemodelbutler.Resourcemodelbutler").
  qactor( movementhandler, ctxtest, "it.unibo.movementhandler.Movementhandler").
  qactor( robotmind, ctxtest, "it.unibo.robotmind.Robotmind").
  qactor( butlermind, ctxtest, "it.unibo.butlermind.Butlermind").
  qactor( calibration, ctxtest, "it.unibo.calibration.Calibration").
  qactor( planner, ctxtest, "it.unibo.planner.Planner").
  qactor( preparehandler, ctxtest, "it.unibo.preparehandler.Preparehandler").
  qactor( addfoodhandler, ctxtest, "it.unibo.addfoodhandler.Addfoodhandler").
  qactor( clearhandler, ctxtest, "it.unibo.clearhandler.Clearhandler").
  qactor( stuffontable, ctxtest, "it.unibo.stuffontable.Stuffontable").
  qactor( console, ctxtest, "it.unibo.console.Console").
  qactor( maitre, ctxtest, "it.unibo.maitre.Maitre").
  qactor( maitremodel, ctxtest, "it.unibo.maitremodel.Maitremodel").
  qactor( dishwasher, ctxtest, "it.unibo.dishwasher.Dishwasher").
  qactor( resourcemodeldishwasher, ctxtest, "it.unibo.resourcemodeldishwasher.Resourcemodeldishwasher").
  qactor( pantry, ctxtest, "it.unibo.pantry.Pantry").
  qactor( resourcemodelpantry, ctxtest, "it.unibo.resourcemodelpantry.Resourcemodelpantry").
  qactor( table, ctxtest, "it.unibo.table.Table").
  qactor( resourcemodeltable, ctxtest, "it.unibo.resourcemodeltable.Resourcemodeltable").
