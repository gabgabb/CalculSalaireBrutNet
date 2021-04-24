package com.example.calculsalairebrutnet;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.gson.Gson;
import java.util.ArrayList;



public class ParametresActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametres);

        //sharedpreferences



        //Ajout des devises dans le spinner
        Spinner devise = (Spinner) findViewById(R.id.spinner);

        ArrayList<Devise> listeDevise = DeviseData.getDevise();

        ArrayAdapter<Devise> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                listeDevise);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        devise.setAdapter(adapter);

        // selection d'un item du spinner
        devise.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                // devise selectionnée
                Devise deviseSelected = (Devise) parent.getSelectedItem();
                Toast.makeText(ParametresActivity.this, "Devise : " + deviseSelected.getInsigne() + " - taux : " + deviseSelected.getTaux(), Toast.LENGTH_SHORT).show();
                // sauvegarde de l'objet selectionné
                Devise devise= new Devise(deviseSelected.getInsigne(), deviseSelected.getNom(),deviseSelected.getTaux());
                saveDevise(devise);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void saveDevise(Devise devise){

        SharedPreferences parametres = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor pref = parametres.edit();
        Gson gson = new Gson();
        String json = gson.toJson(devise);
        pref.putString("devise",json);
        pref.apply();
        Log.i("JSON",""+json);
    }
}
