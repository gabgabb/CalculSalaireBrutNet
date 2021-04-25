package com.example.calculsalairebrutnet;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.gson.Gson;
import java.util.ArrayList;



public class ParametresActivity extends AppCompatActivity {

    private Spinner Spinnerdevise;
    private Spinner Spinnerdevise2;

    private RadioGroup radioGroupSalaire;
    private RadioButton radioButtonMensuel;
    private RadioButton radioButtonAnnuel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametres);

        //Ajout des devises dans les spinners
        Spinnerdevise = findViewById(R.id.spinner);
        Spinnerdevise2 = findViewById(R.id.spinner2);

        ArrayList<Devise> listeDevise = DeviseData.getDevise();

        ArrayAdapter<Devise> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                listeDevise);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinnerdevise.setAdapter(adapter);
        Spinnerdevise2.setAdapter(adapter);

        // chargement des préférences des spinners
        Spinnerdevise.setSelection(PreferencesConfig.loadSpinnerItem(this,1));
        Spinnerdevise2.setSelection(PreferencesConfig.loadSpinnerItem(this,2));

        // radiogroup et radioButton
        radioGroupSalaire = findViewById(R.id.radioGroupSalaire);
        radioButtonMensuel = findViewById(R.id.radioButtonMensuel);
        radioButtonAnnuel = findViewById(R.id.radioButtonAnnuel);

        // chargement des préférences des radiobuttons
        radioButtonMensuel.setChecked(PreferencesConfig.loadRadioButton(this,1));
        radioButtonAnnuel.setChecked(PreferencesConfig.loadRadioButton(this,2));

        // selection d'un item du premier spinner
        Spinnerdevise.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                // devise selectionnée
                Devise deviseSelected = (Devise) parent.getSelectedItem();
                // sauvegarde de l'objet selectionné
                Devise devise= new Devise(deviseSelected.getId(),deviseSelected.getInsigne(), deviseSelected.getNom());
                PreferencesConfig.saveDevisePref(getApplicationContext(),devise,1);
                PreferencesConfig.saveSpinnerItem(getApplicationContext(),devise,1);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        // selection d'un item du second spinner
        Spinnerdevise2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // devise selectionnée
                Devise deviseSelected = (Devise) parent.getSelectedItem();
                Toast.makeText(ParametresActivity.this, "Devise : " + deviseSelected.getNom() + " " + deviseSelected.getInsigne(), Toast.LENGTH_SHORT).show();
                // sauvegarde de l'objet selectionné
                Devise devise= new Devise(deviseSelected.getId(),deviseSelected.getInsigne(), deviseSelected.getNom());
                PreferencesConfig.saveDevisePref(getApplicationContext(),devise,2);
                PreferencesConfig.saveSpinnerItem(getApplicationContext(),devise,2);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        radioGroupSalaire.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                changeSalaire(group,checkedId);
            }
        });

    }

    private void changeSalaire(RadioGroup group, int idRadioChecked){
        int checkedRadio=group.getCheckedRadioButtonId();

        if(checkedRadio==R.id.radioButtonMensuel){
            PreferencesConfig.saveRadioButton(getApplicationContext(),radioButtonMensuel,1);
        } else if(checkedRadio==R.id.radioButtonAnnuel){
            PreferencesConfig.saveRadioButton(getApplicationContext(),radioButtonAnnuel,2);
        }
    }

}
