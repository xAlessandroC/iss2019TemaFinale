%%STATE OF THE ROBOT FOR TESTING PURPOSE

state( robot, state(stopped) ).   %% initial state
state( sonar, unknown, unknown ).   %% initial state
state( butler, unknown, unknown ).	%% initial state
position(robot, 0, 0).

action(robot, move(w)) :- changeModelRobot( robot, movingForward  ).
action(robot, move(s)) :- changeModelRobot( robot, movingBackward ).
action(robot, move(a)) :- changeModelRobot( robot, rotateLeft     ).
action(robot, move(d)) :- changeModelRobot( robot, rotateRight    ).
action(robot, move(h)) :- changeModelRobot( robot, stopped        ).

action(sonar, V, OBSTACLE)  :- changeModelSonar( sonar, V, OBSTACLE  ).

action(position, X, Y) :- changePositionRobot( robot, X, Y ).
 
action(butler, prepare) :- changeModelButler( butler, prepare, ongoing ).
action(butler, add_food) :- changeModelButler( butler, add_food, ongoing ).
action(butler, clear) :- changeModelButler( butler, clear, ongoing ).
action(butler, stop) :- state(butler,X,_), changeModelButler( butler, X,  stopped).
action(butler, reactivate) :- state(butler,X,_), changeModelButler( butler, X, ongoing).

changeModelRobot( NAME, VALUE ) :-
   replaceRule( state(NAME,_),  state(NAME,state(VALUE)) ).
   %%showResourceModel.	%% at each change, show the model
   
changeModelSonar( NAME, VALUE, OBSTACLE ) :-
   replaceRule( state(NAME,_,_),  state(NAME, VALUE, OBSTACLE) ).
   %%showResourceModel.	%% at each change, show the model
   
changeModelButler( NAME, TASK, STATE ) :-
   replaceRule( state(NAME,_,_),  state(NAME, TASK, STATE) ).
   
changePositionRobot( NAME, X, Y ) :-
   replaceRule( position(NAME,_,_),  position(NAME, X, Y) ).