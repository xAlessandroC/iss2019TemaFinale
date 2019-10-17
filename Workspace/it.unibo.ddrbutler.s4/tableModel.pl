%%STATE OF THE MAITRE

state(table, unknown, idle, unknown, unknown). %%initial state

action(table, food, put, FC, QNT) :- changeModelTable(table, food, put, FC, QNT).
action(table, food, take, FC, QNT) :- changeModelTable(table, food, take, FC, QNT).
action(table, dish, put, FC, QNT) :- changeModelTable(table, dish, put, null, QNT).
action(table, dish, take, FC, QNT) :- changeModelTable(table, dish, take, null, QNT).
action(table, TYPE, idle, FC, QNT) :- changeModelTable(table, null, idle, null, null).

changeModelTable(NAME, TYPE, TASK, FC, QNT) :- 
	replaceRule(state(NAME, _, _, _, _), state(NAME, TYPE, TASK, FC, QNT)).