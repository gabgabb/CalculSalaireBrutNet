package com.example.calculsalairebrutnet;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class CalculerSalaireActivity extends AppCompatActivity {

    private EditText salaire;
    private Button calculSalaire;
    private TextView affichage;
    private CheckBox cbHoraire;
    private CheckBox cbMensuel;
    private CheckBox cbAnnuel;
    private Devise devise;
    private Devise devise2;
    private double taux;
    private boolean formatMensuel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acticity_calcul_salaire);

        salaire = (EditText) findViewById(R.id.edittextsalaire);
        calculSalaire= (Button) findViewById(R.id.buttonSalairenet);
        affichage= (TextView) findViewById(R.id.affichageSalaire);
        cbHoraire=(CheckBox) findViewById(R.id.checkBoxHoraire) ;
        cbMensuel=(CheckBox) findViewById(R.id.checkBoxMensuel) ;
        cbAnnuel=(CheckBox) findViewById(R.id.checkBoxAnnuel) ;

        // chargement des devises de getsharedpreference
        devise = PreferencesConfig.loadDevisePref(this,1);
        devise2 = PreferencesConfig.loadDevisePref(this,2);

        // chargement des checkbox de getsharedpreference
        cbHoraire.setChecked(PreferencesConfig.loadCheckBox(this,1));
        cbMensuel.setChecked(PreferencesConfig.loadCheckBox(this,2));
        cbAnnuel.setChecked(PreferencesConfig.loadCheckBox(this,3));

        // chargement des préférences des radiobuttons
        formatMensuel = PreferencesConfig.loadRadioButton(this,1);

        // calcul du taux en fonction des devises
        taux=DeviseData.compareInsigne(devise,devise2);

        // modifification des différents text
        if (formatMensuel) {
            salaire.setHint(salaire.getHint() + " mensuel en " + devise.getInsigne());

        } else {
            salaire.setHint(salaire.getHint() + " annuel en " + devise.getInsigne());
        }

        calculSalaire.setText(calculSalaire.getText()+ " en " + devise2.getInsigne());

        calculSalaire.setOnClickListener(new View.OnClickListener(){
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {

                // insigne de la seconde devise
                String insigne = " "+ devise2.getInsigne();

                if(salaire.getText().toString().equals("") || Double.parseDouble(salaire.getText().toString())<1) {
                    affichage.setText("Veuillez entrer une valeur !");

                } else {
                    // sauvegarde des préférences des checkboxs
                    PreferencesConfig.saveCheckBox(getApplicationContext(), cbHoraire, 1 );
                    PreferencesConfig.saveCheckBox(getApplicationContext(), cbMensuel, 2 );
                    PreferencesConfig.saveCheckBox(getApplicationContext(), cbAnnuel, 3 );

                    // différents salaires
                    double salaireMensuelBrut = Double.parseDouble(salaire.getText().toString());
                    double salaireNet = (salaireMensuelBrut - (salaireMensuelBrut * 23/100))*taux;
                    double salaireNetMensuel;
                    double salaireNetAnnuel;
                    double salaireNetHoraire;

                    if(formatMensuel) {
                         salaireNetMensuel = Math.round(salaireNet * 100) / 100.0;
                         salaireNetAnnuel = Math.round(salaireNet*12*100)/100.0;
                         salaireNetHoraire = Math.round(salaireNet/31/24*100)/100.0;
                    } else
                    {
                         salaireNetMensuel = Math.round(salaireNet/12 * 100) / 100.0;
                         salaireNetAnnuel = Math.round(salaireNet*100)/100.0;
                         salaireNetHoraire = Math.round(salaireNet/12/31/24*100)/100.0;
                    }

                if (!cbHoraire.isChecked() && !cbMensuel.isChecked() && !cbAnnuel.isChecked()) {
                    affichage.setText("Veuillez cocher au moins une valeur !");
                }
                if (cbHoraire.isChecked()) {
                    affichage.setText("Salaire horaire net est équivalent à " + salaireNetHoraire + insigne);
                }
                if (cbMensuel.isChecked()) {
                    affichage.setText("Salaire mensuel net est équivalent à " + salaireNetMensuel + insigne);
                }
                if (cbAnnuel.isChecked()) {
                    affichage.setText("Salaire annuel net est équivalent à " + salaireNetAnnuel + insigne);
                }
                if (cbHoraire.isChecked() && cbMensuel.isChecked()) {
                    affichage.setText("Salaire horaire net est équivalent à " + salaireNetHoraire + insigne
                            + "\nSalaire mensuel net est équivalent à " + salaireNetMensuel + insigne);
                }
                if (cbHoraire.isChecked() && cbAnnuel.isChecked()) {
                    affichage.setText("Salaire horaire net est équivalent à " + salaireNetHoraire + insigne
                            + "\nSalaire annuel net est équivalent à " + salaireNetAnnuel + insigne);
                }
                if (cbMensuel.isChecked() && cbAnnuel.isChecked()) {
                    affichage.setText("Salaire mensuel net est équivalent à " + salaireNetMensuel + insigne
                            + "\nSalaire annuel net est équivalent à " + salaireNetAnnuel +insigne );
                }

                if (cbHoraire.isChecked() && cbMensuel.isChecked() && cbAnnuel.isChecked()) {
                    affichage.setText("Salaire horaire net est équivalent à " + salaireNetHoraire + insigne
                            + "\nSalaire mensuel net est équivalent à " + salaireNetMensuel  + insigne
                            + "\nSalaire annuel net est équivalent à " + salaireNetAnnuel + insigne);
                }
            }
            }
        });

    }
}
