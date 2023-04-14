package com.GraphesOrientesValues;
import java.util.*;

/**
 * @author John LI Grp 111
 * @version 1.0
 * @since Semestre 2 Période D
 * * Implémentation d'une table de hachage qui représente un graphe avec des tables de hachage imbriquées
 */


public class GrapheHHAdj implements IGraphe{
    private Map<String, Map<String, Integer>> hhadj; //Map<Sommets sources, Map<Sommets destination, Valuation>>
                                                     //Map<Clé,Valeur(Map<Clé,Valeur>)>
    public GrapheHHAdj() {
        hhadj = new HashMap<>();
    }
    public GrapheHHAdj(String graphe){
        this();
        peupler(graphe);
    }
    /**
     * Vérifie l'existence de l'arc.
     * Si le sommet n'est pas déjà présent dans le graphe, il est ajouté.

     * @param sommet le nom du sommet source de l'arc.
     * @param destination le nom du sommet destination de l'arc.
     * @throws IllegalArgumentException si l'arc existe déjà entre la source et la destination
     */
    public void verifyExistenceA(String sommet, String destination) {
        ajouterSommet(sommet);

        if (hhadj.get(sommet).get(destination) != null) {

            throw new IllegalArgumentException("L'arc (" + sommet + "-" + destination + "), " + "existe déjà dans le graphe.");

        }

    }
    /**
     * Vérifie que la valuation est positive.

     * @param poids valuation de l'arc
     * @throws IllegalArgumentException si l'arc a une valuation négative
     */
    public void verifyValuation(Integer poids){
        if (poids < 0) {
            throw new IllegalArgumentException("Valuation négative.");
        }
    }
    /**
     * Vérifie l'existence du sommet source.

     * @param sommet le nom du sommet source de l'arc.
     * @throws IllegalArgumentException si le sommet n'existe pas dans le graphe
     */
    public void verifyExistenceS(String sommet){
        if (hhadj.get(sommet) == null) {
            throw new IllegalArgumentException("Le sommet (" + sommet + "), " +   "n'existe pas dans le graphe.");
        }
    }
    /**
     * On ajoute un sommet à la liste d'arc si jamais il n'existe pas déjà
     *
     * @param noeud le nom du sommet à ajouter.
     */
    @Override
    public void ajouterSommet(String noeud) {
        if(!contientSommet(noeud))
            hhadj.put(noeud, new HashMap<>());
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
        //verifyExistenceS(source);
        verifyExistenceA(source, destination);
        verifyValuation(valeur);

        hhadj.get(source).put(destination, valeur); // hhadj.get(source) == on récupère la valeur de la HashMap extérieure
                                                    // qui correspond à la clé de la HashMap intérieure et on ajoute avec
                                                    // put(destination, valeur) la valeur pour la clé



    }
    /**
     * Supprime le sommet donné ainsi que tous les arcs qui sont associés à ce sommet.
     *
     * @param noeud c'est le sommet à supprimer du graphe.
     */
    @Override
    public void oterSommet(String noeud) {
        hhadj.remove(noeud);
    }
    /**
     * Supprime l'arc qui relie le sommet source donné au sommet destination donné.
     *
     * @param source le sommet source de l'arc à supprimer.
     * @param destination le sommet destination de l'arc à supprimer.
     */
    @Override
    public void oterArc(String source, String destination) {
        verifyExistenceS(source);
        if(hhadj.get(source).get(destination) == null){
            throw new IllegalArgumentException("Pour le sommet (" + source + "), " + "l'arc (" + destination + ") n'existe pas dans le graphe.");
        }
        hhadj.get(source).remove(destination);
    }
    /**
     * Retourne une liste qui contient tous les sommets du graphe
     *
     * @return une nouvelle liste contenant tous les sommets du graphe.
     */
    @Override
    public List<String> getSommets() {
        return new ArrayList<>(hhadj.keySet()); //renvoie les clés de la HashMap extérieure
    }

    /**
     * Renvoie une liste de tous les successeurs du sommet donné dans le graphe.
     *
     * @param sommet le sommet pour lequel récupérer les successeurs.
     * @return une liste de chaînes de caractères représentant les successeurs du sommet.
     */
    @Override
    public List<String> getSucc(String sommet) {
        List<String> successeurs = new ArrayList<String>();
        if (hhadj.containsKey(sommet)) {
            for (String succDeSommet: hhadj.get(sommet).keySet()) { //on itère sur les valeurs de la HashMap extérieure qui sont les clés de la HashMap intérieure et
                                                                    // correspondent aux arcs de chaque sommet
                successeurs.add(succDeSommet);
            }
        }

        return successeurs; //renvoie les clés de la HashMap intérieure
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
        if (hhadj.get(src) != null) {
            Integer poids = hhadj.get(src).get(dest);
            if (poids != null) {
                return poids;
            }
        }
        return 0; //si l'arc n'existe pas, retourne 0.
    }
    /**
     * Vérifie si le graphe contient le sommet donné en argument de la fonction
     *
     * @param sommet le nom du sommet-nœud à chercher dans le graphe
     * @return true si le graphe contient le sommet, false sinon.
     */
    @Override
    public boolean contientSommet(String sommet) {

        return hhadj.containsKey(sommet);
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
        if (hhadj.containsKey(src)) {
            return hhadj.get(src).containsKey(dest);
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
        List<String> sommets = new ArrayList<>();
        sommets = getSommets();
        Collections.sort(sommets); //tri des sommets par ordre lexicographique ex: [1,10,2,3]
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
            }else{
                sb.append(noeud);
                sb.append(":");
                sb.append(", ");
            }
        }
        return sb.substring(0, sb.length()-2);//Permet de retirer les deux derniers caractères de la chaîne sb
    }
}

