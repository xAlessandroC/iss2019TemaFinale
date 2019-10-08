%%STATE OF THE MAITRE

state(fridge, idle, unknown, unknown). %%initial state

action(fridge, put, FC, QNT) :- changeModelFridge(fridge, put, FC, QNT).
action(fridge, take, FC, QNT) :- changeModelFridge(fridge, take, FC, QNT).
action(fridge, idle, FC, QNT) :- changeModelFridge(fridge, idle, null, null).

changeModelFridge(NAME, TASK, FC, QNT) :- 
	replaceRule(state(NAME, _, _, _), state(NAME, TASK, FC, QNT)).