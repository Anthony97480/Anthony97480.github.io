:- module(_, _, [assertions, pure]).
author_data('Ferrere', 'Hoareau', 'Anthony', '240915').
:- doc(author_data/4, "Ferrere Hoareau Anthony, 240915").

%-----------------------exo 1-------------------------------

elimina(corleone, solozzo) :- controlar(corleone, manhattan), controlar(corleone, brooklyn).
elimina(solozzo, corleone) :- controlar(solozzo, drogs), apoyar(roth, solozzo).
:- doc(elimina/2, "Determine with the predicates controlar/2, impunidad/2 and apoyar/2 if X should kill Y").

controlar(solozzo, bronx) :- apoyar(roth, solozzo).
controlar(solozzo, harlem) :- apoyar(roth, solozzo).
controlar(corleone, manhattan) :- apoyar(roth, corleone).
controlar(corleone, brooklyn) :- apoyar(roth, corleone).

controlar(corleone, apuesta).
controlar(solozzo, drogas).

controlar(X, policia) :- controlar(X, apuesta).
:- doc(controlar/2, "Determine if X control a place and/or an element Y, using for some element the predicate apoyar/2").


impunidad(X, Y) :- controlar(X, policia).
:- doc(impunidad/2, "Determine with the predicate controlar/2 if X gave the impunity to Y. In this case any X who control the police will give the impunity to all Y").


apoyar(roth, X) :- impunidad(X, roth).
:- doc(apoyar/2, "Determine if Roth gave his support to X, using the predicate impunidad/2").

%---------------------------exo 2------------------------------------

hermano(X, Y) :- padres(_Z, X), padres(_Z, Y).
:- doc(hermano/2, "Determine with the predicate padres/2 if X is the brother of Y").


hijo(X, Y) :- padres(Y, X).
:- doc(hijo/2, "Determine if X is the son of Y using the predicate padres/2").


abuela(X, Y) :- padres(X, _Z), padres(_Z, Y).
:- doc(abuela/2, "Determine if X is the grandmother of Y, using the predicate padres/2").


casado(homer, morticia).
:- doc(casado/2, "Determine if X is marry to Y").


padres(X, Y) :- padre(X, Y).
padres(X, Y) :- madre(X, Y).
:- doc(padres/2, "Determine with the predicates padre/2 and madre/2 if X is the parent of Y").



madre(morticia, pugsley).
madre(morticia, wednesday).

madre(abuela_addams, homer).
madre(abuela_addams, tio_fester).
madre(abuela_addams, tio_cosa).
:- doc(madre/2, "Determine if X is the mother of Y").


padre(homer, pugsley).
padre(homer, wednesday).
:- doc(padre/2, "Determine if X is the father of Y").


tio(X, Y) :- hermano(X, _Z), padres(_Z, Y).
:- doc(tio/2, "Determine with the predicates hermano/2 and padres/2 if X is the uncle Y").


sobrino(X, Y) :- tio(Y, X).
:- doc(sobrino/2, "Determine with the predicate tio/2 if X is the nephew/niece of Y").


cunyado(X, Y) :- hermano(X, _Z), casado(_Z, Y).
:- doc(cunyado/2, "Determine with the predicates hermano/2 and casado/2 if X is the stepbrother/stepsister of Y").
