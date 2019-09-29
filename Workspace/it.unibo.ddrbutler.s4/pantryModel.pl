%%STATE OF THE MAITRE

state(pantry, idle, unknown). %%initial state

action(pantry, put, QNT) :- changeModelPantry(pantry, put, QNT).
action(pantry, take, QNT) :- changeModelPantry(pantry, take, QNT).
action(pantry, idle, QNT) :- changeModelPantry(pantry, idle, QNT).

changeModelPantry(NAME, VALUE, QNT) :- 
	replaceRule(state(NAME, _, _), state(NAME, VALUE, QNT)).