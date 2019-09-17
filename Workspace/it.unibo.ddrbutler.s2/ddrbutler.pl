%====================================================================================
% ddrbutler description   
%====================================================================================
context(ctxbutler, "localhost",  "TCP", "3030" ).
 qactor( mindbutler, ctxbutler, "it.unibo.mindbutler.Mindbutler").
  qactor( maitre, ctxbutler, "it.unibo.maitre.Maitre").
  qactor( maitremodel, ctxbutler, "it.unibo.maitremodel.Maitremodel").
  qactor( fridge, ctxbutler, "it.unibo.fridge.Fridge").
