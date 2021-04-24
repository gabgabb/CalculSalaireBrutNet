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

    public EditText salaire;
    public Button calculSalaire;
    public TextView affichage;
    public CheckBox cbHoraire;
    public CheckBox cbMensuel;
    public CheckBox cbAnnuel;

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

        SharedPreferences parametres = getPreferences(MODE_PRIVATE);
        Gson gson = new Gson();
        String json = parametres.getString("devise","");
        Devise devise = gson.fromJson(json,Devise.class);

       Log.d("AAAAAAA","devise : "+ json);

        calculSalaire.setOnClickListener(new View.OnClickListener(){
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {

                String insigne = ""+ devise.getInsigne();
                double taux= devise.getTaux();

                if(salaire.getText().toString().equals("") || Integer.parseInt(salaire.getText().toString())<1) {
                    affichage.setText("Veuillez entrer une valeur supérieur à 0 !");

                } else {
                int salaireMensuelBrut = Integer.parseInt(salaire.getText().toString());
                int salaireMensuelNet = salaireMensuelBrut - (salaireMensuelBrut * 23 / 100);

                if (!cbHoraire.isChecked() && !cbMensuel.isChecked() && !cbAnnuel.isChecked()) {
                    affichage.setText("Veuillez cocher au moins une valeur !");
                }
                if (cbHoraire.isChecked()) {
                    affichage.setText("Salaire horaire net est équivalent à " + salaireMensuelNet / 31 / 24);
                }
                if (cbMensuel.isChecked()) {
                    affichage.setText("Salaire mensuel net est équivalent à " + salaireMensuelNet);
                }
                if (cbAnnuel.isChecked()) {
                    affichage.setText("Salaire annuel net est équivalent à " + salaireMensuelNet * 12 + " " + devise.getInsigne());
                }
                if (cbHoraire.isChecked() && cbMensuel.isChecked()) {
                    affichage.setText("Salaire horaire net est équivalent à " + salaireMensuelNet / 31 / 24
                            + "\nSalaire mensuel net est équivalent à " + salaireMensuelNet);
                }
                if (cbHoraire.isChecked() && cbAnnuel.isChecked()) {
                    affichage.setText("Salaire horaire net est équivalent à " + salaireMensuelNet / 31 / 24
                            + "\nSalaire annuel net est équivalent à " + salaireMensuelNet * 12);
                }
                if (cbMensuel.isChecked() && cbAnnuel.isChecked()) {
                    affichage.setText("Salaire mensuel net est équivalent à " + salaireMensuelNet
                            + "\nSalaire annuel net est équivalent à " + salaireMensuelNet * 12);
                }

                if (cbHoraire.isChecked() && cbMensuel.isChecked() && cbAnnuel.isChecked()) {
                    affichage.setText("Salaire horaire net est équivalent à " + salaireMensuelNet / 31 / 24
                            + "\nSalaire mensuel net est équivalent à " + salaireMensuelNet
                            + "\nSalaire annuel net est équivalent à " + salaireMensuelNet * 12);
                }
            }
            }
        });

    }
}
