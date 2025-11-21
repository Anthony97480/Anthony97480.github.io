	%***************
	% Question
	%***************

/* 
	2.1) Le problème avec pere est que les variable Age, Profession et Ville ne sont pas utiliser par la fonction pere et donc n'ont aucune utilité ici et sont des varible non existante dans la fonction et juste créer pour celle-ci alors qu'elle n'ont ici pour ce qu'on cherche aucune utilité

	2.2) La solution mise en place est de mettre un '_' qui correspond à une variable muette non interpréter par prolog pour la solution sortante du programme

	2.3) personne domocilier à nice: personne(X, _, _, _, nice).

	2.4) personne habitant dans la même vile que leur deux parent: personne(X, _, _, _, Y), parent(X, Z), personne(Z, _, _, _, Y).

	4) On cherche ici à connaitre les parent ayant des enfant ecolier: parent(P,E), personne(E,_,_,ecolier,_).
*/
	%***************
	% Les personnes
	%***************
	
personne(edouard,   homme,  72, retraite(commandant(armee_de_terre)), nice).
personne(agathe,    femme,  58, musicienne(blues),                    nice).
personne(gaston,    homme,  65, retraite(chauffeur(semvat)),          balma).
personne(simone,    femme,  65, retraite(cableur(airbus)),            balma).
personne(georges,   homme,  28, animateur(club_med),                  toulouse).
personne(marie_cecile,femme,35, clerc_de_notaire,                     toulouse).
personne(octave,    homme,  40, huissier,                             nice).
personne(elton,     homme,  10, ecolier,                              toulouse).
personne(vanessa,   femme,  5,  ecolier,                              toulouse).

	%*************************
	% Les relations de parente
	%*************************
	
parent(edouard, marie_cecile).
parent(agathe,  marie_cecile).
parent(edouard, octave).
parent(agathe,  octave).
parent(gaston,  georges).
parent(simone,  georges).
parent(georges, elton).
parent(georges, vanessa).
parent(marie_cecile, elton).
parent(marie_cecile, vanessa).

	%********
	% Regles
	%********
pere(Pere, Enfant) :-
   personne(Pere, homme, _, _, _),
   parent(Pere, Enfant).
	

fille(Fille, Parent) :-
   personne(Fille, femme, _, _, _),
   parent(Parent, Fille).
   
%-------------------------------
% Ajouter vos nouvelles regles a partir d'ici
%------------------------------- 
 
mere(Mere, Enfant) :-
	personne(Mere, femme, _, _, _),
	parent(Mere, Enfant).

fils(Fils, Parent) :-
	personne(Fils, homme, _, _, _),
	parent(Parent, Fils).

parent_ecolier(Parent) :-
	once(personne(E, _, _, ecolier, _)),
	parent(Parent, E).
 
 
 