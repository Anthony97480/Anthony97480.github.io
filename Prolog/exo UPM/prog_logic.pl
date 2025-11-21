%Pour lancé l'interpréteur Ciao juste marquer ciao dans un prompte linux
%Pour lancée dans ciao le faire directement depuis le code

/*
    Variable: commence par une majuscule ou "_" et peu inclure des nombre
    Constante: par une minuscule et peut inclure "_" et des nombre ou des caractère spéciaux tel que entre '' ou []
    structure: "functor" est comme le nom d'une constante suivit d'un nombre fix d'argument (représenté les fonction et les struture de donnée complexe)
    Arity: nombre d'argument d'une structure (une constante peut être vue comme une structure avec un arity de zero)
        les fonction sont représenté par des nom/arity
*/



/*
    functor/dad: functor
    dad/0: constante
    time(min, sec) = time/2: structure
    Tree(Alf, rob): illégale car une structure à les même règle de sintax qu'un constante
*/



/*
    a + b = +(a,b) if +/2 declared inflix

    description des élément d'une structure dans d'autre structure:
        meal(soup, beef, coffee).
        meal(First, Second, Third) :-
            appetizer(First),
            main_dish(Second),
            dessert(Third).

    le point permet de finir une ligne (:- permet de faire une définition)
    "," opérateur "and"
    il n'y a pas de "or"

    les règles sont générique et nous permettent de définir les structure (leur propriété)
    les règles et fait sont appeler les clauses
    une règles est équivalante à une implication: p, q, r, s --> z
*/



/*
    programme logic est un ensemble de prédicat
    a Query represente une question pour le programme et est une clause sans tête commençant par "?-"
    un fait est quelque chose qui est toujours vrai: animal(chat). --> le chat est un animal
    rules structure de fait
    si plusieurs règles sont défini pour une même structure, cela permet de faire en sorte qu'il y ait différente altetrnative pour que la struture soit vrai (s'il l'une des règles de la structure est vrai alors la structure est vrai)

*/



/*
    use_module pour utiliser un programme (à écrire dans un prompte)
    quand on pose une question de l'existance d'un élément si on met ?; à la fin on demande au programe de nous donnée une autre solution si celle-ci exite
    les élément comparer dans un égale doivent avoir la même longueur  
*/

/*
    exemple de petite implémentation en ciao
    la recherche de la solution se fait sous forme d'arbre en testant toute les possibilité une part une
    cependant parfois dans certain programme il peut y avoir une ramification infinie (qui mène à une infinité d'erreur, récusirvement)
    On peut utiliser certain module afin de pouvoir modifier la manière de traverser l'arbre pour trouver la solution (résoud le problème de branche infini puisque trouve toute les solution avant d'entré dans la branche infini)
*/
:- module(_, _, [sr/bfall]).

pet(X) :- animal(X), barks(X).
pet(X) :- animal(X), meows(X).

animal(tim).
animal(spot).
animal(hobbes).

barks(spot).
meows(tim).
roars(hobbes).

/*
    pour sélectionnée la méthode de recherche dans l'arbre on commence par:
        :- module(_, _, [X]),
            X = [] pour le standart (branche par branche en allant le plus profondément possible en faisant celle de gauche toujours en premier)
            X = [sr/bfall] pour la recherche ligne part ligne
*/

/*
    Il est possible de créer des variable local dans la définition des fonction
*/

father_of(john ,peter).
father_of(john ,mary).
father_of(peter ,michael).

mother_of(mary , david).

grandfather_of(L,M) :- father_of(L,N), father_of(N,M). %variable local N
grandfather_of(X,Y) :- father_of(X,Z), mother_of(Z,Y).

grandmother_of(L,M) :- mother_of(L, N), father_of(N, M).
grandmother_of(X,Y) :- mother_of(X, Z), mother_of(Z, Y).

parent(X, Y) :- father_of(X, Y).
parent(X, Y) :- mother_of(X, Y).

brother(X, Y) :- father_of(_Z, X), father_of(_Z, Y). % X non uni à Y, X \== Y
brother(X, Y) :- mother_of(_Z, X), mother_of(_Z, Y). % l'inverse de l'union ne fonctionne pas mais je ne sais pas pourquoi

ancestor(X, Y) :- parent(X, Y).
ancestor(X, Y) :- parent(X, _Z), ancestor(_Z, Y).

/*
    _ variable anonyme, quand on pose une question, la question
    ne prend pas en compte ces variable et renvoir uniquement la réponse
    au variable non anonyme.

    Pour une variable que commence par "_" le programme va faire les calcule et prendre en compte la variable mais il ne va pas renvoyer le résultat présent dans cette variable
    elles s'utilise quand on a pas besoin de toute les information d'une fonction quand on pose une question mais seulement de certain élément

*/

/*
    la programmation récursive est la même qu'en programmation procedural (ada)
    le cas de base doit être la solution (un moyen de sortir de la boucle)
    si le cas de base est vrai alors il y a une solution sinon il n'y a pas de solution

    Faire attention au boucle infini quand on rentre les nom des élément
    
*/

/*
    revoir les diapo sur les oppération (page 43-55)
*/
nat(0).
nat(s(X)) :- nat(X). %0=0 / s(0)=1 / s(s(0))=2 / ...
%définition des nombre
integer(X) :- nat(X).
integer(-X) :- nat(X).

less_or_equal(0,X) :- nat(X).
less_or_equal(s(X), s(Y)) :- less_or_equal(X,Y).
%fonctionne uniquement avec la définition des nombres.

add(0, X, X). % 0+X=X (cas simple)
add(s(X), Y, s(R)) :- add(X,Y,R). %cas récursif
/*
    add(s(s(0)), s(s(0)), R) --> add(s(0), s(s(0)), s-1(R)) --> add(0, s(s(0)), s-2(R)) --> s-2(R)=s(s(0)) --> s-1(R)=s(s(s(0))) --> R=s(s(s(s(0))))
*/

%faire la soustraction soit même pour être sure d'avoir bien compris
sous(X, 0, X). % X-0=X (cas simple)
sous(X, s(Y), R) :- sous(X, Y, s(R)).
%sous(s(s(0)), s(0), R) --> sous(s(s(0)), 0, s(R)) --> s(R)=s(s(0))
%la soustraction fonctionne (ouiiii)


/*
    Pour les listes on utilise le constructeur ./2 le premier argument est le premier élément de la liste
    et le segond argument est le reste de la liste
    .(1, .(2, [])) = [1, 2]
    
    les liste sont défini de forme récusive
    list([]).
    list(.(_, B)) :- list(B). ou list([A|Xs]) :- list(Xs).


    définition de liste:
    [X|Xs] est une liste par exemple [2,3] correspond a X=2 et Xs=[3]
    c'est une liste qui contiens au minimum 1 élement

    [X, X|Xs] est une liste qui contiens au minimum 2 élement et qui commence par les deux même élément
    Ce n'est pas une opération de contaténation de liste mais uniquement de concaténation d'un seul élément au début de la liste

    member/2 => member(X, L) est un élément X d'une liste L
    élément universel et le plus simple:
    - member(X, [X]). (le cas de base)
    - member(X, [Y|Xs]) :- member(X, Xs) (le cas général)


    fonction dup/2 pour dupliquer les élement d'une liste [1,2] --> [1,1,2,2]
    dup([], []). pour dire que ca contiens des liste (avec la récursivité permet de faire en sorte que Ys soit égale à [])
    dup([X|Xs], [X,X|Ys]) :- dup(Xs, Ys).
*/

%concatener deux liste:

append([], Ys, Ys). %cas simple, Ys+[]=Ys
append([X|Xs], Ys, [X|Zs]) :- append(Xs, Ys, Zs). 
%cas récursif, on ajoute le reste de la premier liste Xs à Ys en retirant le premier élément à chaque fois et ainsi de suite
%de tel manière à pouvoir ajouter chaque élément de la liste Xs à Ys en ajoutant les dernier élément de Xs en premier
%le tous retourné dans un liste Zs


prefix([], Ys). %on regarde si les on rencontre la premier liste au début de la dexième ou non (ou du moins une partie de cette première liste)
prefix([X|Xs], [X|Ys]) :- prefix(Xs, Ys).

%même chose que le prefix mais pour la fin de la liste
suffix(Xs, Xs).
suffix(Xs, [Y|Ys]) :- suffix(Xs, Ys).


%si Xs est une sous liste de Ys (c'est à dire que si Xs appartiens à Ys peut importe ou elle est dans Ys)
sublist_rec(Xs, Ys) :- prefix(Xs, Ys).
sublist_rec(Xs, [Y|Ys]) :- sublist(Xs, Ys).

sublist_suf_pref(Xs, Ys):- prefix(Ps, Ys), suffix(Xs, Ps).


%Inverser l'ordre d'une liste:
naive_reverse([], []). %cas simple
naive_reverse([X|Xs], Zs) :- naive_reverse(Xs, Ys), append(Ys, [X], Zs).
%on retire un par un les élément de Xs jusqu'à arriver au dernier, puis on les ajoute à la fin d'une autre liste vide 


reverse_accumulate([], Ys, Ys).
reverse_accumulate([X|Xs], Acc, Ys) :- reverse_accumulate(Xs, [X|Acc], Ys).
%on ajoute à l'avant d'une liste vide tous les élément d'une autre liste un part un

%taille d'une liste
length([], 0).
length([X|Xs], s(N)) :- length(Xs, N).

%delete
delete([], X, []). %cas simple X est l'élément à éliminer d'une liste, si la liste est vide et qu'on retire un élément (pas d'erreur)
delete([X|Xs], X, Ys) :- delete(Xs, X, Ys). %il va chercher dans la liste Xs l'élément X et le retirer
delete([X|Xs], Z, [X|Ys]) :- X\==Z, delete(Xs, Z, Ys).