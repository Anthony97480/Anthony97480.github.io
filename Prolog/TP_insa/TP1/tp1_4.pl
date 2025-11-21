	% Description de graphes
	% ----------------------
	% Chaque clause donne le nom du graphes, la liste des sommets et la liste des arcs.
	% g1 est un graphe acyclique
	% g2 contient des cycles
	

graphe(g1, [1,2,3,4,5,6], [[1,2],[1,3],[2,4],[3,4],[4,5],[4,6]]).

graphe(g2, [1,2,3,4,5,6], [[1,2],[1,3],[2,3],[2,4],[3,4],[4,1],[4,5],[4,6]]).