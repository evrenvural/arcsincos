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
        double result  = Math.sin(derece);
        result=format(result);

        return result;
    }
    public double cosinus(){
        double result  = Math.cos(derece);
        result = format(result);

        return result;

    }
    public double tanjant(){    //Burada kaldım değerler doğru mu diye bakıyodum ve yanlış sanırım, spinner değişince de değer değissin bu arada
        double result  = Math.tan(derece);
        result=format(result);

        return result;
    }
    public double cotanjant(){
        return 1.0 / Math.tan(derece);
    }
    public double secant(){
        return 1.0 / cosinus();
    }
    public double cosecant(){
        return 1.0 / Math.sin(derece);
    }




    private double format(double value) {
        return (double)Math.round(value * 1000000) / 1000000; //you can change this to round up the value(for two position use 100...)
    }
}
