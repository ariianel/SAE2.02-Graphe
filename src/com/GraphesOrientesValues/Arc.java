package com.GraphesOrientesValues;

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

}
