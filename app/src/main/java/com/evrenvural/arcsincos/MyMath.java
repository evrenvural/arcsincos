package com.evrenvural.arcsincos;


public class MyMath{
    private double derece;
    private double tersi;

    public double getDerece() {
        return derece;
    }

    public double getTersi() {
        return tersi;
    }

    public void setTersi(double tersi) {
        this.tersi = tersi;
    }

    public void setDerece(double derece) {
        if (derece >= 360){
            derece = derece % 360;
        }
        this.derece = Math.toRadians(derece);
    }


    public double sinus(){

        return Math.sin(derece);
    }
}
