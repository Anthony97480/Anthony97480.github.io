	% Description de graphes
	% ----------------------
	% Chaque clause donne le nom du graphes, la liste des sommets et la liste des arcs.
	% g1 est un graphe acyclique
	% g2 contient des cycles
	

graphe(g1, [1,2,3,4,5,6], [[1,2],[1,3],[2,4],[3,4],[4,5],[4,6]]).

graphe(g2, [1,2,3,4,5,6], [[1,2],[1,3],[2,3],[2,4],[3,4],[4,1],[4,5],[4,6]]).

%-------------------------P1--------------------------

last_element([X], X).
last_element([], []).
last_element([_X|L], S) :-
	last_element(L, S).


add_element(L, X, [X|L]).

add_element_last([], X, [X]).
add_element_last([X|L], Y, [X|S]) :-
	add_element_last(L, Y, S).

delete_last([_X], []).
delete_last([X|L], [X|S]) :-
	delete_last(L, S).

delete_first([_X|L], L).

inv([], []).
inv([X|Xs], Zs) :- 
	inv(Xs, Ys),
	add_element_last(Ys, X, Zs).

equals(X, X).

palindrome_rec([]).
palindrome_rec([_]).
palindrome_rec([X|L]) :-
	last_element([X|L], Y),
	equals(X, Y),
	delete_last(L, NL),
	palindrome_rec(NL).

palindrome_iter(L) :-
	inv(L, S),
	equals(L, S).

%-------------------------P2--------------------------

dispatch(_, [], [], []).
dispatch(X, [E|L], Infeg, Sup) :-
	dispatch(X, L, Infeg_Tmp, Sup),
	(E =< X),
	add_element(Infeg_Tmp, E, Infeg).

dispatch(X, [E|L], Infeg, Sup) :-
	dispatch(X, L, Infeg, Sup_Tmp),
	(E > X),
	add_element(Sup_Tmp, E, Sup).

quicksort([], []).
quicksort([X|L], Result) :-
	dispatch(X, L, [I|Inf], [S|Sup]),

