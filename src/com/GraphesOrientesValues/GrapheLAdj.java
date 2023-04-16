package com.GraphesOrientesValues;

import java.util.*;

public class GrapheLAdj implements IGraphe {
    private Map<String, List<Arc>> ladj;

    public GrapheLAdj() {
        ladj = new HashMap<>();
    }

    public GrapheLAdj(String graphe) {
        this();
        peupler(graphe);
    }

    @Override
    public void ajouterSommet(String noeud) {
        if (!ladj.containsKey(noeud)) {
            ladj.put(noeud, new ArrayList<Arc>());
        }
    }

    @Override
    public boolean contientSommet(String sommet) {
        return ladj.containsKey(sommet);
    }

    @Override
    public boolean contientArc(String src, String dest) {
        if (!contientSommet(src)) {
            return false;
        }
        List<Arc> arcs = ladj.get(src);
        for (Arc arc : arcs) {
            if (arc.getNoeudDest().equals(dest)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<String> getSommets() {
        return new ArrayList<>(ladj.keySet());
    }

    @Override
    public List<String> getSucc(String sommet) {
        List<Arc> arcs = ladj.get(sommet);
        List<String> succs = new ArrayList<>();
        for (Arc arc : arcs) {
            succs.add(arc.getNoeudDest());
        }
        return succs;
    }

    @Override
    public int getValuation(String src, String dest) {
        List<Arc> arcs = ladj.get(src);
        for (Arc arc : arcs) {
            if (arc.getNoeudDest().equals(dest)) {
                return arc.getValuation();
            }
        }
        return -1;
    }

    @Override
    public void ajouterArc(String source, String destination, Integer valeur) {
        if (contientArc(source, destination)) {
            throw new IllegalArgumentException();
        }
        if (valeur < 0) {
            throw new IllegalArgumentException();
        }
        Arc arc = new Arc(source, destination, valeur);

        if (ladj.containsKey(source)) {
            ladj.get(source).add(arc);
        } else {
            List<Arc> successeur = new ArrayList<>();
            successeur.add(arc);
            ladj.put(source, successeur);
        }
    }

    @Override
    public void oterSommet(String noeud) {
        ladj.remove(noeud);

        for (String source : ladj.keySet()) {
            List<Arc> successeur = ladj.get(source);
            successeur.removeIf(arc -> arc.getNoeudDest().equals(noeud));
        }
    }

    @Override
    public void oterArc(String source, String destination) {
        if (!contientArc(source, destination)) {
            throw new IllegalArgumentException();
        }
        if (ladj.containsKey(source) && ladj.containsKey(destination)) {
            List<Arc> arcs = ladj.get(source);
            if (arcs != null) {
                arcs.removeIf(arc -> arc.getNoeudDest().equals(destination));
            }
        }
    }

    public String toString() {

        StringBuilder sb = new StringBuilder();
        List<String> sommets = new ArrayList<>();
        sommets = getSommets();
        Collections.sort(sommets);

        for (String noeud : sommets) {

            List<String> successeurs = getSucc(noeud);
            Collections.sort(successeurs);
            if (!successeurs.isEmpty()) {
                for (String succ : successeurs) {
                    int valuation = getValuation(noeud, succ);
                    sb.append(noeud).append("-");
                    sb.append(succ).append("(").append(valuation).append(")");
                    sb.append(", ");
                }
            } else {
                sb.append(noeud);
                sb.append(":");
                sb.append(", ");
            }
        }
        return sb.substring(0, sb.length() - 2);//Permet de retirer les deux derniers caractères de la chaîne sb
    }
}
