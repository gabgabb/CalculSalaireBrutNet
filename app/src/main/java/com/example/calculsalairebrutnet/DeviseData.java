package com.example.calculsalairebrutnet;

import java.util.ArrayList;
import java.util.List;

public class DeviseData {

    public static ArrayList<Devise> getDevise(){
        Devise Dollar = new Devise('$', "Dollar", 1);
        Devise Euro = new Devise('€', "Euro", 0.8);
        Devise Livre = new Devise('£', "Livre", 0.25);
        Devise BitCoin = new Devise('₿', "Bitcoin", 0.00002);

        ArrayList<Devise> listDevise = new ArrayList<Devise>();
        listDevise.add(Dollar);
        listDevise.add(Euro);
        listDevise.add(Livre);
        listDevise.add(BitCoin);

        return listDevise;
    }
}
