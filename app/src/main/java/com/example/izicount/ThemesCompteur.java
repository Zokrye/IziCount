package com.example.izicount;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ThemesCompteur extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_themes_compteurs);
        Button lieux = findViewById(R.id.lieux_visited);
        Button hebergement = findViewById(R.id.hebergement);
        Button restauration = findViewById(R.id.restauration);
        //Préparation démarrage activité
        final Intent intent = new Intent(ThemesCompteur.this,CompteursActivity.class);
        //Choix du thème de compteurs à afficher
        lieux.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("theme","Lieux visités");
                startActivity(intent);
            }
        });
        hebergement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("theme","Hébergement");
                startActivity(intent);
            }
        });
        restauration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("theme","Restauration");
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {//Lors de l'appui sur retour on repasse sur la première activité
        super.onBackPressed();
        Intent intent = new Intent(ThemesCompteur.this,MainActivity.class);
        startActivity(intent);
    }
}
