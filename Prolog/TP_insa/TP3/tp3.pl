last_element([X], X).
last_element([_X|L], S) :-
	last_element(L, S).

delete_last([_X], []).
delete_last([X|L], [X|S]) :-
	delete_last(L, S).

add_element(X, L, [X|L]).

%---------------------------------------------------------

disque(a, [-1, 0, 0, 0, 0, 0]).
disque(b, [-1, -1, 0, 0, 0, 0]).
disque(c, [-1, 0, -1, 0, 0, 0]).
disque(d, [-1, 0, 0, -1, 0, 0]).
disque(e, [-1, -1, -1, 0, 0, 0]).
disque(f, [-1, -1, 0, -1, 0, 0]).
disque(g, [-1, 0, -1, 0, -1, 0]).
disque(h, [-1, -1, -1, -1, 0, 0]).
disque(i, [-1, -1, -1, 0, -1, 0]).
disque(j, [-1, -1, 0, -1, -1, 0]).
disque(k, [-1, -1, -1, -1, -1, 0]).
disque(l, [-1, -1, -1, -1, -1, -1]).

liste_des_disques(L) :-
    findall(X, disque(X, _), L).


rotation_droite(M, R) :-
    last_element(M, Y),
    delete_last(M, TmpM),
    add_element(Y, [], TmpR),
    append(TmpR, TmpM, R).

orienter(M, M, 0).
orienter(M, M1, N) :-
    N1 is N-1,
    rotation_droite(M, M1),
    orienter(M1, M2, N1).