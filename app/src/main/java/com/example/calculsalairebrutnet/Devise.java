package com.example.calculsalairebrutnet;

public class Devise {

    private char insigne;
    private String nomDevise;
    private double taux;

    public Devise(char insigne, String nomDevise, double taux){
            this.insigne=insigne;
            this.nomDevise=nomDevise;
            this.taux=taux;
    }

    public char getInsigne(){
        return this.insigne;
    }

    public String getNom(){
        return nomDevise;
    }

    public double getTaux(){
        return taux;
    }

    public void setInsigne(char c){
        this.insigne=c;
    }

    public void setNomDevise(String n){
        this.nomDevise=n;
    }

    public void setTaux(float t){
        this.taux=t;
    }

    @Override
    public String toString(){

        return nomDevise + " " + insigne ;
    }
}
