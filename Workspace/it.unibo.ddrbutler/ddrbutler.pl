%====================================================================================
% ddrbutler description   
%====================================================================================
context(ctxrobot, "localhost",  "TCP", "8005" ).
 qactor( robot, ctxrobot, "it.unibo.robot.Robot").
  qactor( maitre, ctxrobot, "it.unibo.maitre.Maitre").
  qactor( fridge, ctxrobot, "it.unibo.fridge.Fridge").
