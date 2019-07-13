%====================================================================================
% ddrbutler description   
%====================================================================================
context(ctxrobotbutlerreq, "localhost",  "TCP", "3030" ).
 qactor( rbr_req, ctxrobotbutlerreq, "it.unibo.rbr_req.Rbr_req").
  qactor( maitrereq, ctxrobotbutlerreq, "it.unibo.maitrereq.Maitrereq").
  qactor( fridgereq, ctxrobotbutlerreq, "it.unibo.fridgereq.Fridgereq").
  qactor( pantryreq, ctxrobotbutlerreq, "it.unibo.pantryreq.Pantryreq").
  qactor( dishwasherreq, ctxrobotbutlerreq, "it.unibo.dishwasherreq.Dishwasherreq").
