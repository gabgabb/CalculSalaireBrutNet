package com.example.calculsalairebrutnet;

import java.util.ArrayList;
import java.util.List;

public class DeviseData {

    public static ArrayList<Devise> getDevise(){
        // création des devises
        Devise Euro = new Devise(0,'€', "Euro");
        Devise Dollar = new Devise(1,'$', "Dollar");
        Devise Livre = new Devise(2,'£', "Livre");
        Devise BitCoin = new Devise(3,'₿', "Bitcoin");

        // ajout des devises dans la liste
        ArrayList<Devise> listDevise = new ArrayList<Devise>();
        listDevise.add(Euro);
        listDevise.add(Dollar);
        listDevise.add(Livre);
        listDevise.add(BitCoin);

        return listDevise;
    }
    // changement de taux en fonction des différentes spinners
    public static double compareInsigne(Devise devise, Devise devise2){
        double taux = 1;
        if(devise.getInsigne()==devise2.getInsigne()) { taux = 1; }
        else if (devise.getInsigne() == '€' && devise2.getInsigne() == '$') { taux = 1.21; }
        else if (devise.getInsigne() == '€' && devise2.getInsigne() == '£') { taux=0.87; }
        else if(devise.getInsigne() == '€' && devise2.getInsigne() == '₿') { taux = 0.00002; }
        else if(devise.getInsigne() == '$' && devise2.getInsigne() == '€'){ taux=0.82; }
        else if(devise.getInsigne() == '$' && devise2.getInsigne() == '£'){ taux=0.72; }
        else if(devise.getInsigne() == '$' && devise2.getInsigne() == '₿'){ taux=0.00002; }
        else if(devise.getInsigne() == '£' && devise2.getInsigne() == '€'){ taux=1.14; }
        else if(devise.getInsigne() == '£' && devise2.getInsigne() == '$'){ taux=1.38; }
        else if(devise.getInsigne() == '£' && devise2.getInsigne() == '₿'){ taux=0.00002; }
        else if(devise.getInsigne() == '₿' && devise2.getInsigne() == '€'){ taux=41751; }
        else if(devise.getInsigne() == '₿' && devise2.getInsigne() == '$'){ taux=50507; }
        else if(devise.getInsigne() == '₿' && devise2.getInsigne() == '£'){ taux=36389; }

        return taux;
    }
}
