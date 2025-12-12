%-------------------------P1--------------------------

last_element([X], X).
last_element([], []).
last_element([_X|L], S) :-
	last_element(L, S).


add_element(X, L, [X|L]).

%ajoute la liste Xs à la fin de la liste Ys
add_list(Xs, [], Xs).
add_list(Xs, [Y|Ys], [Y|Zs]) :-
	add_list(Xs, Ys, Zs).

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
dispatch(X, [E|L], [E|Infeg], Sup) :-
	(E =< X),
	dispatch(X, L, Infeg, Sup).

dispatch(X, [E|L], Infeg, [E|Sup]) :-
	(E > X),
	dispatch(X, L, Infeg, Sup).


min_element([X], X).
min_element([X, Y|L], S) :-
	(X >= Y),
	min_element([Y|L], S).
min_element([X, Y|L], S) :-
	(X =< Y),
	min_element([X|L], S).

quicksort([], []).
quicksort([X|L], R) :-
	dispatch(X, L, Inf, Sup),
	quicksort(Inf, Ri),
	quicksort(Sup, Rs),
	append(Ri, [X|Rs], R).


quicksort2([], Acu, Acu).
quicksort2([X|L], Acu, R) :-
	dispatch(X, L, Inf, Sup),
	quicksort2(Sup, Acu, Rs), % on considère que Rs est la liste trier des élément supérieur à X
	quicksort2(Inf, [X|Rs], R).
%l'intérêt de ne pas appaler append/3 est de réduire le nombre d'appel récursif

%-------------------------P3--------------------------

a_droite(X, [Y|L], L) :-
	equals(X, Y).
a_droite(X, [_Y|L], Z) :-
	a_droite(X, L, Z).

a_gauche(X, [Y|_L], []) :-
	equals(X, Y).
a_gauche(X, [Y|L], [Y|Z]) :-
	a_gauche(X, L, Z).

separer(X, [Y|L], [], L) :-
	equals(X, Y).
separer(X, [Y|L], [Y|G], D) :-
	separer(X, L, G, D).


%-------------------------P4--------------------------

	% Description de graphes
	% ----------------------
	% Chaque clause donne le nom du graphes, la liste des sommets et la liste des arcs.
	% g1 est un graphe acyclique
	% g2 contient des cycles
	
% member/2 prend en argument en element X et une liste L d'élement et renvoie vrai si X appartie à la liste L

graphe(g1, [1,2,3,4,5,6], [[1,2],[1,3],[2,4],[3,4],[4,5],[4,6]]).

graphe(g2, [1,2,3,4,5,6], [[1,2],[1,3],[2,3],[2,4],[3,4],[4,1],[4,5],[4,6]]).


arc(G, O, E) :-
	graphe(G, _, Arc),
	member([O, E], Arc).

arc_existe_chemin(_, X, X).
arc_existe_chemin(G, O, E) :-
	graphe(G, _, Arc),
	member([O, X], Arc),
	arc_existe_chemin(G, X, E).

existe_chemin(_, X, X).
existe_chemin(G, O, E) :-
	graphe(G, _, _),
	arc(G, O, X),
	existe_chemin(G, X, E).

chemin(_, X, X, []).
chemin(G, O, E, Ch) :-
	graphe(G, _, _),
	arc(G, O, X),
	chemin(G, X, E, Tmp),
	add_element([O, X], Tmp, Ch).
%les prédicat précédent peuvent être utiliser pour réaliser de la recherche mais également pour vérifier une hypothèse

%quand on exécute existe_chemin ou chemin sur g2, il se passe qu'il y a un dépassement de la limite du stack, c'est à dire que l'algorithme tourne à l'infinie sans jamais s'arrêté étant donnée qu'il y a un circuit (une boucle) dans le graphe2



chemin_sans_circuit(_, X, X, [], _).
chemin_sans_circuit(G, O, E, Ch, Memo) :-
	graphe(G, _, _Arc),
	arc(G, O, X),
	member(X, Memo),
	arc(G, O, Y),
	X =\= Y,
	chemin_sans_circuit(G, Y, E, Tmp, [O|Memo]),
	add_element([O, Y], Tmp, Ch), !.
chemin_sans_circuit(G, O, E, Ch, Memo) :-
	graphe(G, _, _),
	arc(G, O, X),
	chemin_sans_circuit(G, X, E, Tmp, [O|Memo]),
	add_element([O, X], Tmp, Ch).