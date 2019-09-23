%%STATE OF THE MAITRE

state(pantry, idle, unknown). %%initial state

action(pantry, X, QNT) :- changeModelMaitre(pantry, X, QNT).

changeModelMaitre(NAME, VALUE, QNT) :- 
	replaceRule(state(NAME, _, _), state(NAME, VALUE, QNT)).