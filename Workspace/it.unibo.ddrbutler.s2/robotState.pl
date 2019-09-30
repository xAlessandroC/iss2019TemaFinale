%%STATE OF THE ROBOT FOR TESTING PURPOSE

state( robot, state(stopped) ).   %% initial state
state( sonar, unknown, unknown ).   %% initial state
state( butler, unknown ).	%% initial state
position(robot, 0, 0).

action(robot, move(w)) :- changeModelRobot( robot, movingForward  ).
action(robot, move(s)) :- changeModelRobot( robot, movingBackward ).
action(robot, move(a)) :- changeModelRobot( robot, rotateLeft     ).
action(robot, move(d)) :- changeModelRobot( robot, rotateRight    ).
action(robot, move(h)) :- changeModelRobot( robot, stopped        ).

action(position, X, Y) :- changePositionRobot( robot, X, Y ).

action(sonar, V, OBSTACLE)  :- changeModelSonar( sonar, V, OBSTACLE  ).
 
action(butler, TASK) :- changeModelButler( butler, TASK ).

changeModelRobot( NAME, VALUE ) :-
   replaceRule( state(NAME,_),  state(NAME,state(VALUE)) ).
   %%showResourceModel.	%% at each change, show the model
   
changeModelSonar( NAME, VALUE, OBSTACLE ) :-
   replaceRule( state(NAME,_,_),  state(NAME, VALUE, OBSTACLE) ).
   %%showResourceModel.	%% at each change, show the model
   
changeModelButler( NAME, TASK ) :-
   replaceRule( state(NAME,_),  state(NAME, TASK) ).
   
changePositionRobot( NAME, X, Y ) :-
   replaceRule( position(NAME,_,_),  position(NAME, X, Y) ).