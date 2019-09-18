%%STATE OF THE MAITRE

state(maitre, unknown). %%initial state

action(maitre, X) :- changeModelMaitre(maitre, X).

changeModelMaitre(NAME, VALUE) :- 
	replaceRule(state(NAME, _), state(NAME, VALUE)).