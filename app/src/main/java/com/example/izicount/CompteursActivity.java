package com.example.izicount;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;

import com.example.izicount.interfaces.CompteurListener;
import com.example.izicount.pojo.Compteur;
import com.example.izicount.ui.fragments.CompteursFragment;

import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class CompteursActivity extends AppCompatActivity implements CompteurListener {

    FragmentManager ft = getSupportFragmentManager();
    String theme;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_compteurs);

        //On récupère le thème choisi des compteurs
        Intent intent =getIntent();
        theme = intent.getStringExtra("theme");

        TextView pageTheme = findViewById(R.id.textViewPageCompteurs);
        pageTheme.setText(theme);
        Toast.makeText(this,theme,Toast.LENGTH_LONG).show();

        final CompteursFragment fragment = CompteursFragment.newinstance(theme);//On envoie au fragment le thème choisi
        ft.beginTransaction().add(R.id.container, fragment,"Theme")
                .addToBackStack(null).commit();
    }

    @Override
    public void onBackPressed() { //lors de l'appui sur le bouton retour on repasse sur l'activité pour choisir le thème
        super.onBackPressed();
        Intent intent = new Intent(CompteursActivity.this, ThemesCompteur.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onPlus(Compteur compteur) { //lors de l'appui d'un bouton plus, on incrémente le compteur correspondant
        Log.d("Activity compteur","Plus");
        compteur.increment();
        Log.d("Activity compteur",String.valueOf(compteur.getNombre()));
    }

    @Override
    public void onMoins(Compteur compteur) { //lors de l'appui d'un bouton moins, on décrémente le compteur correspondant
        Log.d("Activity compteur","Moins");
        if(compteur.getNombre()>0){
            compteur.decrement();
        }
        else{
            //Si le compteur est déjà à 0, il ne peut aller en dessous
            Toast.makeText(this,"Can't go under 0",Toast.LENGTH_LONG).show();
        }
        Log.d("Activity compteur",String.valueOf(compteur.getNombre()));
    }
}
