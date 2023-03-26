package com.GraphesOrientesValues;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class GrapheLArcs implements IGraphe  {
    private ArrayList<Arc> arcs;
    private ArrayList<String>sommets;
    public GrapheLArcs(){
        arcs = new ArrayList<>();
        sommets = new ArrayList<>();
    }
    public GrapheLArcs(String graphe){
        this();
        peupler(graphe);
    }

    /**
     * Vérifie si le graphe contient un sommet donné.
     *
     * @param sommet le sommet à rechercher dans le graphe.
     * @return true si le graphe contient le sommet, false sinon.
     */
    @Override
    public boolean contientSommet(String sommet) {
        return sommets.contains(sommet);
    }
    /**
     * Ajoute un sommet au graphe s'il n'est pas déjà présent.
     *
     * @param noeud le nom du sommet à ajouter.
     */
    @Override
    public void ajouterSommet(String noeud){
        if(!contientSommet(noeud)){
            sommets.add(noeud);
        }
    }
    /**
     * Ajoute un arc entre deux sommets du graphe, avec une valuation donnée.
     * Si les sommets ne sont pas déjà présents dans le graphe, ils sont ajoutés.
     *
     * @param source le nom du sommet source de l'arc.
     * @param destination le nom du sommet destination de l'arc.
     * @param valeur la valuation de l'arc.
     */
    @Override
    public void ajouterArc(String source, String destination, Integer valeur){
        if(!contientSommet(source) && !contientSommet(destination)) {
            sommets.add(source);
            sommets.add(destination);
        }
        arcs.add(new Arc(source, destination, valeur));
    }

    /**
     * Retourne une liste contenant tous les sommets du graphe.
     *
     * @return une nouvelle liste contenant tous les sommets du graphe.
     */
    @Override
    public List<String> getSommets() {
        //On retourne une nouvelle liste qui contient tous les sommets car elle est en private
        return new ArrayList<>(sommets);
    }

    /**
     * Supprime le sommet donné ainsi que tous les arcs associés à ce sommet.
     *
     * @param noeud le sommet à supprimer du graphe.
     */
    @Override
    public void oterSommet(String noeud){
        if(contientSommet(noeud)){
            //On parcourt les arcs pour supprimer tous les arcs qui ont le noeud en source ou destination
            for (int i=0; i<arcs.size();++i){
                Arc arc = arcs.get(i);
                if(arc.getNoeudSource().equals(noeud) || arc.getNoeudDest().equals(noeud)){
                    arcs.remove(arc);
                    sommets.remove(noeud);
                }
            }
        }
    }

    /**
     * Supprime l'arc qui relie le sommet source donné au sommet destination donné.
     *
     * @param source le sommet source de l'arc à supprimer.
     * @param destination le sommet destination de l'arc à supprimer.
     */
    @Override
    public void oterArc(String source, String destination){
        for(int i =0; i<arcs.size();++i){
            if(arcs.get(i).equals(source) && arcs.get(i).equals(destination)){
                arcs.remove(i);
            }
        }
    }

    /**
     * Renvoie une liste de tous les successeurs du sommet donné dans le graphe.
     *
     * @param sommet le sommet pour lequel récupérer les successeurs.
     * @return une liste de chaînes de caractères représentant les successeurs du sommet.
     */
    @Override
    public List<String> getSucc(String sommet) {
        List<String> successeurs = new ArrayList<>();
        for (Arc arc : arcs)
            if (arc.getNoeudSource().equals(sommet)) {
                successeurs.add(arc.getNoeudDest());
            }
        return successeurs;
    }

    /**
     * Récupère la valuation de l'arc entre le sommet source et le sommet destination du graphe.
     *
     * @param src la chaîne de caractère représentant le sommet source de l'arc.
     * @param dest la chaîne de caractère représentant le sommet destination de l'arc.
     * @return un int qui est la valuation sinon si l'arc n'existe pas, retourne 0.
     */
    @Override
    public int getValuation(String src, String dest) {
        for (Arc arc:arcs){
            if(arc.getNoeudSource().equals(src) && arc.getNoeudDest().equals(dest)){
                return arc.getValuation();
            }
        }
        return 0;
    }
    /**
     * Vérifie si l'arc partant du sommet source au sommet destination choisi est présent dans le graphe.
     *
     * @param src String représentant le sommet source de l'arc.
     * @param dest String représentant le sommet destination de l'arc.
     * @return true si l'arc est présent dans le graphe, false sinon.
     */
    @Override
    public boolean contientArc(String src, String dest) {
        for(Arc arc: arcs) {
            if(arc.getNoeudSource().equals(src) && arc.getNoeudDest().equals(dest)){
                return true;
            }
        }
        return false;
    }

    /**

     Retourne une chaîne de caractères représentant le graphe sous forme de liste d'adjacence.
     Les sommets sont représentés par leur nom et les arcs sont représentés par une flèche reliant le sommet source
     au sommet destination, suivie de la valuation de l'arc entre parenthèses. Les sommets qui n'ont pas de successeurs
     sont représentés seuls sur leur ligne.
     (ex: "A-B(3), C-D(1), E-F(2)").
     @return une chaîne de caractères représentant le graphe sous forme de liste d'adjacence.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (String noeud : getSommets()) {

            List<String> successeurs = getSucc(noeud);
            if (!successeurs.isEmpty()) {
                for (String succ : successeurs) {
                    int valuation = getValuation(noeud, succ);
                    sb.append(noeud).append("-");
                    sb.append(succ).append("(").append(valuation).append(")");
                    sb.append(", ");
                }
            }else{
                sb.append(noeud);
                sb.append(":");
                sb.append(", ");
            }
        }
        return sb.substring(0, sb.length()-2);//Permet de retirer les deux derniers caractères de la chaîne sb
    }


}
