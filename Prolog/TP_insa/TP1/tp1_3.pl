	% Premiere definition de factorielle(N)
	% 2 clauses : cas trivial et clause recursive
	% factorielle(0) = 1 
	% factorielle(N) = N*factorielle(N-1)

% le point d'exclamation sert à dire au programme de s'arrêter une fois tombé sur sur cette règle et on n'exécute pas la partie recursive	
fact(0,1) :- !.				

fact(N,F) :-				
	N1 is N-1,
	fact(N1,F1),
	F is N*F1.


factorielle(0, 1).
factorielle(N, F) :-
	N > 0,
	N1 is N-1,
	factorielle(N1, F1),
	F is N*F1.
% l'avantage ici est que notre fonction peut être exécuter avec des nombre négatif sans craché et permet égalament une résolution sans l'utilisation d'un coup choix 

% version sans accumulateur
somme([], 0).
somme([X|L], S) :-
	somme(L, _s1),
	S is (X + _s1).


% Version avec accumulateur
somme2([], S, S).
somme2([X|L], I, S) :-
	J is X+I,
	somme2(L, J, S).

% l'avantage de la version avec accumulateur est la possiblité de faire une reccursivité plus importante avant de saturé les buffer.


fibo(0, 0).
fibo(1, 1).
fibo(N, F) :-
	N > 1,
	N1 is N-1, N2 is N-2,
	fibo(N1, F1), fibo(N2, F2),
	F is (F1+F2).


fiboplus(0, 0, 1).
fiboplus(N, Fn, Fn1) :- % n f(n) f(n+1)
	N > 0,
	N1 is N-1,
	fiboplus(N1, Fn2, Fn3), % n-1 f(n-1) f(n-1+1)
	Fn is Fn3,
	Fn1 is (Fn2+Fn3).
