package com.example.izicount;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button meteo = findViewById(R.id.meteo);//Bouton météo
        Button compteur = findViewById(R.id.compteur);//Bouton compteur
        //Bruit de click
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.click);
        //Action bouton Compteur au clic
        compteur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();//Activation du bruit de click
                final Intent direction = new Intent(v.getContext(),ThemesCompteur.class);
                startActivity(direction);
            }
        });
        meteo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                final Intent direction = new Intent(v.getContext(),Meteo.class);
                startActivity(direction);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //onDestroy();
    }

}
