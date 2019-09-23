%====================================================================================
% maitre description   
%====================================================================================
context(ctxmaitre, "localhost",  "TCP", "3031" ).
context(ctxbutler, "localhost",  "TCP", "3030" ).
 qactor( resourcemodel, ctxbutler, "external").
  qactor( maitre, ctxmaitre, "it.unibo.maitre.Maitre").
  qactor( maitremodel, ctxmaitre, "it.unibo.maitremodel.Maitremodel").
