package com.GraphesOrientesValues;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class GrapheMAdj implements IGraphe{
	private int[][] matrice;
	private Map<String, Integer> indices;
	private final int cst = -1;
	
	public GrapheMAdj() {
		indices = new HashMap<>();
		matrice = new int[0][0];
	}
	
	public GrapheMAdj(String graphe) {
		this();
		//int taillematrice = taille(graphe, indices);
		//System.out.println(taillematrice);
		//matrice = new int[taille][taille];
		//initialisation(taille, matrice);
		//affiche(taillematrice, matrice);
		//System.out.println("Avant");
		peupler(graphe);
		//affiche(matrice.length,matrice);
		//System.out.println("Après");
	}
	public void affiche(int taille ,int[][] m) {
		for(int i=0;i<taille;++i) {
			for(int j=0;j<taille;++j)
				System.out.print(m[i][j]+" ");
			System.out.println();
		}	
		for (Entry<String, Integer> e : indices.entrySet()) {
			System.out.print(e.getKey()+e.getValue()+" ");
		}
	}
	/*
	private void initialisation(int taille, int[][] m) {
		for(int i=0;i<taille;++i)
			for(int j=0;j<taille;++j)
				m[i][j] = cst;			
	}
	*/
	/*
	private int taille(String graphe, Map<String, Integer> som) {
	    //assert this.getSommets().isEmpty();
	    String[] arcs = graphe.split(",\\s*");
	    for (String arc : arcs) {
	        String[] elements = arc.trim().split("-");
	        String src = elements[0].replaceAll(":", "");
	        ajouterSommet(src);//
	        if (elements.length > 1 && !elements[1].isEmpty()) {
	            String[] targets = elements[1].split(",\\s*");
	            for (String target : targets) {
	                String dest = target.substring(0, target.indexOf('('));
	                int val = Integer.parseInt(target
	                		.substring(target.indexOf('(') + 1,
	                				   target.indexOf(')')));
	                ajouterSommet(dest);
	            }
	        }
	    }
		return som.size();
	}
	*/

	@Override
	public void ajouterSommet(String noeud) {
		if(!contientSommet(noeud)) {
			indices.put(noeud, matrice.length);
			redimenssion();
		}
	}
	
	private void redimenssion() {
		int t = matrice.length +1;
		int[][] matrice2 = new int[t][t];
		for (int i=0;i<t;++i)
			for (int j=0;j<t;++j) {
				if (i<t-1 && j < t-1)
					matrice2[i][j] = matrice[i][j];
				else
					matrice2[i][j] = cst;
			}
		matrice = matrice2;		
	}

	@Override
	public void ajouterArc(String source, String destination, Integer valeur) {
		if (valeur < 0) {
            throw new IllegalArgumentException();
        }
		ajouterSommet(source);
        ajouterSommet(destination);
        if(contientArc(source, destination)){
            throw new IllegalArgumentException();
        }
        matrice[indices.get(source)][indices.get(destination)]=valeur;
	}
	
	@Override
	public void oterSommet(String noeud) {
		if (indices.containsKey(noeud)) {
			int inoeud = indices.get(noeud);
			//System.out.println(noeud+" "+inoeud);
			for (Entry<String, Integer> e : indices.entrySet()) {
				if(matrice[inoeud][e.getValue()] > cst)
					oterArc(noeud,e.getKey());
				if(matrice[e.getValue()][inoeud] > cst)
					oterArc(e.getKey(),noeud);
			}
			indices.remove(noeud);
		}
	}
	
	@Override
	public void oterArc(String source, String destination){
		if (contientArc(source, destination))
			matrice[indices.get(source)][indices.get(destination)] = cst;
		else
			throw new IllegalArgumentException();
	}
	
	@Override
	public List<String> getSommets(){
		List<String> sommets = new ArrayList<>();
		for (Entry<String, Integer> e : indices.entrySet()) {
			sommets.add(e.getKey());
			//System.out.println(e.getKey());
		}
		return sommets;		
	}
	
	@Override
	public List<String> getSucc(String sommet){
		List<String> successeur = new ArrayList<>();
		int isommet = indices.get(sommet);
		for (Entry<String, Integer> e : indices.entrySet()) {
			if(matrice[isommet][e.getValue()] > cst) {
				successeur.add(e.getKey());
				//System.out.println(e.getKey()+" "+matrice[isommet][e.getValue()]);
			}
		}
		return successeur;	
	}
	
	@Override
	public int getValuation(String src, String dest) {
		return matrice[indices.get(src)][indices.get(dest)];
	}
	
	@Override
	public boolean contientSommet(String sommet) {
		if(indices.containsKey(sommet))
			return true;
		return false;		
	}
	
	@Override
	public boolean contientArc(String src, String dest) {
		try{
			if(contientSommet(src) && contientSommet(dest))
				if (matrice[indices.get(src)][indices.get(dest)] != cst)
					return true;
		return false;
		}catch(NullPointerException e) {
			System.err.println(src + "->" + dest + " = null pointer "+indices.get(src)+" "+indices.get(dest));
			return false;
		}
		
	}
	
	public String toString() {
    	return toAString();
	}
}
