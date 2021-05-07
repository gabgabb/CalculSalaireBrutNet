package com.example.calculsalairebrutnet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public Button calcul;
    public Button parametres;
    public RelativeLayout background;
    public boolean switchOn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calcul = (Button) findViewById(R.id.buttonSalaire);
        parametres = (Button) findViewById(R.id.buttonParametre);
        background = findViewById(R.id.pagePrincipal);

        // couleur du fond
        switchOn=PreferencesConfig.loadSwitchColor(this);
        ParametresActivity.changeColor(background,switchOn);

        // changement d'activity
        calcul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent calculActivity = new Intent(MainActivity.this, CalculerSalaireActivity.class);
                startActivity(calculActivity);
            }
        });

        // changement d'activity
        parametres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent parametresActivity = new Intent(MainActivity.this, ParametresActivity.class);
                startActivity(parametresActivity);
            }
        });
    }

    // changement de couleur au retour de l'activity
    protected void onResume(){
        super.onResume();
        background = findViewById(R.id.pagePrincipal);

        // couleur du fond
        switchOn=PreferencesConfig.loadSwitchColor(this);
        ParametresActivity.changeColor(background,switchOn);

    }

}
