%%STATE OF THE MAITRE

state(table, unknown, idle, unknown, unknown). %%initial state

action(table, TYPE, TASK, FC, QNT) :- changeModelMaitre(NAME, TYPE, TASK, FC, QNT).

changeModelMaitre(NAME, TYPE, TASK, FC, QNT) :- 
	replaceRule(state(NAME, _, _, _, _), state(NAME, TYPE, TASK, FC, QNT)).