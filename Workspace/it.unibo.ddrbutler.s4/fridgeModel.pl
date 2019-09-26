%%STATE OF THE MAITRE

state(fridge, idle, unknown, unknown). %%initial state

action(fridge, TASK, FC, QNT) :- changeModelFridge(NAME, TASK, FC, QNT).

changeModelFridge(NAME, TASK, FC, QNT) :- 
	replaceRule(state(NAME, _, _, _), state(NAME, TASK, FC, QNT)).