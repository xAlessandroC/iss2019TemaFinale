%%STATE OF THE MAITRE

state(pantry, idle). %%initial state

action(pantry, X) :- changeModelPantry(pantry, X).

changeModelPantry(NAME, VALUE) :- 
	replaceRule(state(NAME, _), state(NAME, VALUE)).