package com.GraphesOrientesValues;

public interface IGraphe extends IGrapheConst {
	void ajouterSommet(String noeud);
	void ajouterArc(String source, String destination, Integer valeur);
	void oterSommet(String noeud);
	void oterArc(String source, String destination);
	
	// construit un graphe vide a partir d'une chaine
	// au format "A-B(5), A-C(10), B-C(3), C-D(8), E:";
	default void peupler(String str) {
		//Vérifie si le graphe est vide sinon assertion
	    assert this.getSommets().isEmpty();
		//Découpe chaine de caractère entré en tab de chaine arcs qui
		//use de ", " comme délimiteur
	    String[] arcs = str.split(",\\s*");
	    for (String arc : arcs) {
			//Pour chaque arc elle extrait le nom du noeud
			//ajoute comme sommet du graphe
	        String[] elements = arc.trim().split("-");

	        // extrait le noeud source et ote ":" si nécessaire dans le nom
	        String src = elements[0].replaceAll(":", "");
	        ajouterSommet(src);

			//Si jamais il y a un noeud de destination
	        if (elements.length > 1 && !elements[1].isEmpty()) {
	            String[] targets = elements[1].split(",\\s*");
	            for (String target : targets) {
					//On extrait le nom du noeud de destination avant la paranthèse
					//qui va être stock dans dest
	                String dest = target.substring(0, target.indexOf('('));
	                int val = Integer.parseInt(target
	                		.substring(target.indexOf('(') + 1,
	                				   target.indexOf(')')));
	                ajouterArc(src, dest, val);
	            }
	        }
	    }
	}

}
