:- module(_, _, [pure, assertions]).
author_data('Ferrere', 'Hoareau', 'Anthony', '240915').
:- doc(author_data/4, "Ferrere Hoareau Anthony, 240915").

%-----------------------eje 1-------------------------------

natural(0).
natural(s(X)) :- natural(X).
:- doc(natural/1, "Definition of the natural number 0=0, s(0)=1 ...").

suma(0, X, X).
suma(s(X), Y, s(Z)) :- suma(X, Y, Z).
:- doc(suma/3, "Determine the sum of X and Y").

less(0, s(X)) :- natural(X).
less(s(X), s(Y)) :- less(X, Y).
:- doc(less/2, "Determine if X is smaller than Y").

mod(X, Y, X) :- less(X, Y).
mod(X, Y, Z) :- suma(Y, _Q, X), mod(_Q, Y, Z).
:- doc(mod/3, "Determine the remainder of the division of X by Y, using the predicates suma/3 and less/2").

par(X) :- mod(X, s(s(0)), 0).
:- doc(par/1, "Determine if a number is even").

impar(X) :- mod(X, s(s(0)), s(0)).
:- doc(impar/1, "Determine if a number is odd").


%---------------------------eje 2------------------------------------

suma_a_lista([], N, []) :- natural(N).
suma_a_lista([X|L], N, [Z|SL]) :- suma_a_lista(L, N, SL), suma(X, N, Z).
:- doc(suma_a_lista/3, "Calculate the sum of each element in a list with an element N, using the predicate suma/3").

pares_lista([], []).
pares_lista([X|L], [X|Ps]) :- par(X), pares_lista(L, Ps).
pares_lista([X|L], Ps) :- impar(X), pares_lista(L, Ps).
:- doc(pares_lista/2, "Returns a list containing all the even elements of another, using the predicates par/1 and impar/1").


%---------------------------eje 3------------------------------------

extrae_elemento(0, [E|L], E, L).
extrae_elemento(s(I), [X|L], E, [X|NL]) :- extrae_elemento(I, L, E, NL).
:- doc(extrae_elemento/4, "Determine and remove an element E located at index I in a list").