package main.java.graphe.core;

public class Arc {
    private String noeudSrc;
    private String noeudDest;
    private Integer valuation;

    public Arc(String noeudSource, String noeudDest, int valuation) {
        this.noeudSrc = noeudSource;
        this.noeudDest = noeudDest;
        this.valuation = valuation;

    }
    public String getNoeudSource() {
        return noeudSrc;
    }

    public String getNoeudDest(){
        return noeudDest;
    }

    public int getValuation(){
        return valuation;
    }

    public boolean equalsNoeud(String noeud) {
        return this.noeudSrc.equals(noeud) || this.noeudDest.equals(noeud);
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(noeudSrc).append("-").append(noeudDest).append("(").append(valuation).append(")");
        return sb.toString();
    }
}
