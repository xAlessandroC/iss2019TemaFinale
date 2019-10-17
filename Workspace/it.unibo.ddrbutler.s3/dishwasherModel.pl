%%STATE OF THE MAITRE

state(dishwasher, idle, unknown). %%initial state

action(dishwasher, X, QNT) :- changeModelMaitre(dishwasher, X, QNT).

changeModelMaitre(NAME, VALUE, QNT) :- 
	replaceRule(state(NAME, _, _), state(NAME, VALUE, QNT)).