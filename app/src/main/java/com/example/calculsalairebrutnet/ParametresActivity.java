package com.example.calculsalairebrutnet;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class ParametresActivity extends AppCompatActivity {

    private Spinner Spinnerdevise;
    private Spinner Spinnerdevise2;
    private RelativeLayout background;
    private RadioGroup radioGroupSalaire;
    private RadioButton radioButtonMensuel;
    private RadioButton radioButtonAnnuel;
    private Switch modeNuit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametres);

        background = findViewById(R.id.pageParametres);

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

        modeNuit = findViewById(R.id.switchModeNuit);
        modeNuit.setChecked(PreferencesConfig.loadSwitchColor(this));
        changeColor(background,modeNuit.isChecked());

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
                Toast.makeText(ParametresActivity.this, "Devise : " + Spinnerdevise2.getSelectedItem() + " - taux " + DeviseData.compareInsigne(deviseSelected, (Devise)Spinnerdevise2.getSelectedItem()) , Toast.LENGTH_SHORT).show();
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
                Toast.makeText(ParametresActivity.this, "Devise : " + deviseSelected.getNom() + " " + deviseSelected.getInsigne() + " - taux " + DeviseData.compareInsigne((Devise)Spinnerdevise.getSelectedItem(),deviseSelected) , Toast.LENGTH_SHORT).show();
                // sauvegarde de l'objet selectionné
                Devise devise= new Devise(deviseSelected.getId(),deviseSelected.getInsigne(), deviseSelected.getNom());
                PreferencesConfig.saveDevisePref(getApplicationContext(),devise,2);
                PreferencesConfig.saveSpinnerItem(getApplicationContext(),devise,2);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        // changement de radioButton
        radioGroupSalaire.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                changeSalaire(group,checkedId);
            }
        });
        // changement de thème
        modeNuit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isChecked=buttonView.isChecked();
                if(isChecked){
                    changeColor(background,modeNuit.isChecked());
                    PreferencesConfig.saveSwitchColor(getApplicationContext(),buttonView);
                } else {
                    changeColor(background,modeNuit.isChecked());
                    PreferencesConfig.saveSwitchColor(getApplicationContext(),buttonView);
                }
            }
        });

    }

    // méthode pour changer de type de salaire
    public void changeSalaire(RadioGroup group, int idRadioChecked){
        int checkedRadio=group.getCheckedRadioButtonId();

        if(checkedRadio==R.id.radioButtonMensuel){
            PreferencesConfig.saveRadioButton(getApplicationContext(),radioButtonMensuel,1);
        } else if(checkedRadio==R.id.radioButtonAnnuel){
            PreferencesConfig.saveRadioButton(getApplicationContext(),radioButtonAnnuel,2);
        }
    }

    // méthode pour changer de couleur de fond
    public static void changeColor(RelativeLayout r, boolean bool){
        if(bool){
            r.setBackgroundColor(Color.parseColor("#455052"));

        } else {
            r.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
    }



}
