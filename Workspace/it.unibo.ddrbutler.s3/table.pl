%====================================================================================
% table description   
%====================================================================================
context(ctxbutler, "localhost",  "TCP", "3030" ).
 qactor( table, ctxbutler, "it.unibo.table.Table").
  qactor( resourcemodeltable, ctxbutler, "it.unibo.resourcemodeltable.Resourcemodeltable").
  qactor( console, ctxbutler, "it.unibo.console.Console").
