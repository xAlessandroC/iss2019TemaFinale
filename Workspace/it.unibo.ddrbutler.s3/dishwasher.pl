%====================================================================================
% dishwasher description   
%====================================================================================
context(ctxbutler, "localhost",  "TCP", "3030" ).
 qactor( dishwasher, ctxbutler, "it.unibo.dishwasher.Dishwasher").
  qactor( resourcemodeldishwasher, ctxbutler, "it.unibo.resourcemodeldishwasher.Resourcemodeldishwasher").
  qactor( console, ctxbutler, "it.unibo.console.Console").
