%====================================================================================
% fridge description   
%====================================================================================
context(ctxfridge, "localhost",  "TCP", "3035" ).
 qactor( fridge, ctxfridge, "it.unibo.fridge.Fridge").
  qactor( resourcemodelfridge, ctxfridge, "it.unibo.resourcemodelfridge.Resourcemodelfridge").
