%====================================================================================
% ddrbutler description   
%====================================================================================
context(ctxbutler, "localhost",  "TCP", "3030" ).
 qactor( butler, ctxbutler, "it.unibo.butler.Butler").
  qactor( maitre, ctxbutler, "it.unibo.maitre.Maitre").
  qactor( fridge, ctxbutler, "it.unibo.fridge.Fridge").
  qactor( pantry, ctxbutler, "it.unibo.pantry.Pantry").
  qactor( dishwasher, ctxbutler, "it.unibo.dishwasher.Dishwasher").
  qactor( table, ctxbutler, "it.unibo.table.Table").
