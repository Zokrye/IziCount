package com.example.izicount;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ThemesCompteur extends Activity {

    FragmentManager ft =getFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_themes_compteurs);
        Button lieux = findViewById(R.id.lieux_visited);
        Button hebergement = findViewById(R.id.hebergement);
        Button restauration = findViewById(R.id.restauration);
        final Intent intent = new Intent(ThemesCompteur.this,CompteursActivity.class);
        lieux.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("theme","lieux");
                startActivity(intent);
            }
        });
        hebergement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("theme","hebergement");
                startActivity(intent);
            }
        });
        restauration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("theme","restauration");
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(ThemesCompteur.this,MainActivity.class);
        startActivity(intent);
    }
}
