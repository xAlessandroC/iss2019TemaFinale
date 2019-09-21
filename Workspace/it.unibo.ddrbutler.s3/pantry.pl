%====================================================================================
% pantry description   
%====================================================================================
context(ctxbutler, "localhost",  "TCP", "3030" ).
context(ctxmaitre, "localhost",  "TCP", "3031" ).
 qactor( maitremodel, ctxmaitre, "external").
  qactor( pantry, ctxbutler, "it.unibo.pantry.Pantry").
  qactor( resourcemodelpantry, ctxbutler, "it.unibo.resourcemodelpantry.Resourcemodelpantry").
  qactor( console, ctxbutler, "it.unibo.console.Console").
