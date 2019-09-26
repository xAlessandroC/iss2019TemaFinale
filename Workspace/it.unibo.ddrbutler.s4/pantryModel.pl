%%STATE OF THE MAITRE

state(pantry, idle, unknown). %%initial state

action(pantry, X, QNT) :- changeModelPantry(pantry, X, QNT).

changeModelPantry(NAME, VALUE, QNT) :- 
	replaceRule(state(NAME, _, _), state(NAME, VALUE, QNT)).