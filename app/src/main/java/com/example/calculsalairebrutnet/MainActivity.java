package com.example.calculsalairebrutnet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    public Button calcul;
    public Button parametres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calcul = (Button) findViewById(R.id.buttonSalaire);
        parametres = (Button) findViewById(R.id.buttonParametre);

        calcul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent calculActivity = new Intent(MainActivity.this, CalculerSalaireActivity.class);
                startActivity(calculActivity);
            }
        });

        parametres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent parametresActivity = new Intent(MainActivity.this, ParametresActivity.class);
                startActivity(parametresActivity);
            }
        });
    }
}
