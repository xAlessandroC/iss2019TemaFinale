%====================================================================================
% pantry description   
%====================================================================================
context(ctxbutler, "localhost",  "TCP", "3030" ).
 qactor( pantry, ctxbutler, "it.unibo.pantry.Pantry").
  qactor( resourcemodelpantry, ctxbutler, "it.unibo.resourcemodelpantry.Resourcemodelpantry").
  qactor( console, ctxbutler, "it.unibo.console.Console").
