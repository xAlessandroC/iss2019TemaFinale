%%STATE OF THE MAITRE

state(maitre, idle). %%initial state

action(maitre, X) :- changeModelMaitre(maitre, X).

changeModelPantry(NAME, VALUE) :- 
	replaceRule(state(NAME, _), state(NAME, VALUE)).