package com.example.calculsalairebrutnet;

public class Devise {

    private final int id;
    private char insigne;
    private String nomDevise;


    public Devise(int id,char insigne, String nomDevise){
            this.id=id;
            this.insigne=insigne;
            this.nomDevise=nomDevise;

    }

    public int getId(){ return this.id;}

    public char getInsigne(){
        return this.insigne;
    }

    public String getNom(){
        return nomDevise;
    }

    public void setInsigne(char c){
        this.insigne=c;
    }

    public void setNomDevise(String n){
        this.nomDevise=n;
    }

    @Override
    public String toString(){ return nomDevise + " " + insigne ; }
}
