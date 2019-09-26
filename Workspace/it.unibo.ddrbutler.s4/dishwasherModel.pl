%%STATE OF THE MAITRE

state(dishwasher, idle, unknown). %%initial state

action(dishwasher, X, QNT) :- changeModelDishwasher(dishwasher, X, QNT).

changeModelDishwasher(NAME, VALUE, QNT) :- 
	replaceRule(state(NAME, _, _), state(NAME, VALUE, QNT)).