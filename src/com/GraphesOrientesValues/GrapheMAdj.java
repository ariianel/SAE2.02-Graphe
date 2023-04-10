package com.GraphesOrientesValues;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class GrapheMAdj implements IGraphe{
	private int[][] matrice;
	private Map<String, Integer> indices;
	
	public GrapheMAdj() {
		indices = new HashMap<>();
		matrice = new int[3][3];
	}
	public static void main(String[] args) {
		GrapheMAdj g = new GrapheMAdj();
		System.out.println(g.matrice[0][0]);
	}
	
	@Override
	public void ajouterSommet(String noeud) {
		if(!contientSommet(noeud))
			indices.put(noeud, indices.size());
	}
	
	@Override
	public void ajouterArc(String source, String destination, Integer valeur) {
		if (valeur < 0) {
            throw new IllegalArgumentException();
        }
        if(contientArc(source, destination)){
            throw new IllegalArgumentException();
        }
        ajouterSommet(source);
        ajouterSommet(destination);
        matrice[indices.get(source)][indices.get(destination)]=valeur;
        trierSommets();
	}
	
	@Override
	public void oterSommet(String noeud) {
		int inoeud = indices.get(noeud);
		for (Entry<String, Integer> e : indices.entrySet()) {
			if(matrice[inoeud][e.getValue()] > -1)
				oterArc(noeud,e.getKey());
			if(matrice[e.getValue()][inoeud] > -1)
				oterArc(e.getKey(),noeud);
		}
		indices.remove(noeud);
	}
	
	@Override
	public void oterArc(String source, String destination){
		matrice[indices.get(source)][indices.get(destination)] = -1;
	}
	
	@Override
	public List<String> getSommets(){
		List<String> sommets = new ArrayList<>();
		for (Entry<String, Integer> e : indices.entrySet())
			sommets.add(e.getKey());
		return sommets;		
	}
	
	@Override
	public List<String> getSucc(String sommet){
		List<String> successeur = new ArrayList<>();
		int isommet = indices.get(sommet);
		for (Entry<String, Integer> e : indices.entrySet())
			if(matrice[isommet][e.getValue()] > -1)
				successeur.add(e.getKey());
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
		if(matrice[indices.get(src)][indices.get(dest)] > -1)
			return true;
		return false;		
	}
	
	//plus
	public void trierSommets() {
	}
	
	public String toString() {
		return null;
	}
}
