%%STATE OF THE MAITRE

state(table, idle, unknown, unknown). %%initial state

action(table, TASK, FC, QNT) :- changeModelMaitre(NAME, TASK, FC, QNT).

changeModelMaitre(NAME, TASK, FC, QNT) :- 
	replaceRule(state(NAME, _, _, _), state(NAME, TASK, FC, QNT)).