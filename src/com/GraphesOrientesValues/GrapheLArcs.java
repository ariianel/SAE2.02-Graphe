package com.GraphesOrientesValues;

//import java.lang.reflect.Array;
import java.util.*;

public class GrapheLArcs implements IGraphe  {
    private List<Arc> arcs;
    public GrapheLArcs(){
        arcs = new ArrayList<>();
    }
    public GrapheLArcs(String graphe){
        this();
        peupler(graphe);
    }

    /**
     * Vérifie si le graphe contient le sommet donné en argument de la fonction
     *
     * @param sommet le nom du sommet-noeud à chercher dans le graphe
     * @return true si le graphe contient le sommet, false sinon.
     */
    @Override
    public boolean contientSommet(String sommet) {
        for(Arc arc : arcs){
            if(arc.equalsNoeud(sommet)){
                return true;
            }
        }
        return false;
    }
    /**
     * On ajoute un sommet à la liste d'arc si jamais il n'existe pas déjà
     *
     * @param noeud le nom du sommet à ajouter.
     */
    @Override
    public void ajouterSommet(String noeud){
        if(!contientSommet(noeud)){
            arcs.add(new Arc(noeud, "", 0));
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
    public void ajouterArc(String source, String destination, Integer valeur) {
        //Si valuation inférieure à zéro
        if (valeur < 0) {
            throw new IllegalArgumentException();
        }
        if(contientArc(source, destination)){
            throw new IllegalArgumentException();
        }
        ajouterSommet(source);
        ajouterSommet(destination);
        arcs.add(new Arc(source, destination, valeur));
        //Trie les sommets en trop
        trierSommets();
    }
    /**
     * Permet de savoir si les sommets de notre liste d'arcs ont des arcs qui les composent
     */
    public void trierSommets() {
        Set<String> sommetsRelies = new HashSet<>();
        for (Arc arc : arcs) {
            sommetsRelies.add(arc.getNoeudSource());
            sommetsRelies.add(arc.getNoeudDest());
        }
        List<Arc> tmp = new ArrayList<>();
        for (Arc arc : arcs) {
            if (sommetsRelies.contains(arc.getNoeudSource()) && sommetsRelies.contains(arc.getNoeudDest())) {
                tmp.add(arc);
            }
        }
        arcs = tmp;

    }

    /**
     * Retourne une liste qui contient tous les sommets du graphe
     *
     * @return une nouvelle liste contenant tous les sommets du graphe.
     */
    @Override
    public List<String> getSommets() {
        List<String> sommets = new ArrayList<>();
        for (Arc arc : arcs) {
            //Vérification de l'existence du sommet
            if (!sommets.contains(arc.getNoeudSource())) {
                sommets.add(arc.getNoeudSource());
            }
            if (!sommets.contains(arc.getNoeudDest())) {
                sommets.add(arc.getNoeudDest());
            }
        }
        sommets.remove("");
        Collections.sort(sommets);
        return sommets;

    }

    /**
     * Supprime le sommet donné ainsi que tous les arcs qui sont associés à ce sommet.
     *
     * @param noeud c'est le sommet à supprimer du graphe.
     */
    @Override
    public void oterSommet(String noeud){

        if(contientSommet(noeud)){
            //On parcourt les arcs pour supprimer tous les arcs qui ont le noeud en source ou destination
            for(Arc arc : arcs){
                if(arc.equalsNoeud(noeud)){
                    arcs.remove(arc);
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
        if(!contientArc(source, destination)){
            throw new IllegalArgumentException();
        }
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
        successeurs.remove("");
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
