package com.example.izicount;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button meteo = findViewById(R.id.meteo);
        Button compteur = findViewById(R.id.compteur);
        compteur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent direction = new Intent(v.getContext(),ThemesCompteur.class);
                startActivity(direction);
            }
        });
        meteo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent direction = new Intent(v.getContext(),Meteo.class);
                startActivity(direction);
            }
        });
    }

}
