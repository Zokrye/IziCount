package com.example.izicount;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.izicount.interfaces.CompteurListener;
import com.example.izicount.pojo.Compteur;
import com.example.izicount.ui.fragments.CompteursFragment;

import androidx.annotation.Nullable;
import android.app.FragmentManager;
import android.telephony.mbms.MbmsErrors;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class CompteursActivity extends Activity implements CompteurListener {

    FragmentManager ft = getFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_compteurs);

        Intent intent =getIntent();
        String theme = intent.getStringExtra("theme");
        TextView pageTheme = findViewById(R.id.textViewPageCompteurs);
        pageTheme.setText(theme);
        Toast.makeText(this,theme,Toast.LENGTH_LONG).show();
        final CompteursFragment fragment = CompteursFragment.newinstance(theme);
        ft.beginTransaction().add(R.id.container, fragment,"Theme")
                .addToBackStack(null).commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(CompteursActivity.this, ThemesCompteur.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onPlus(Compteur compteur) {
        Log.d("Activity compteur","Plus");
        compteur.nombre++;
        Log.d("Activity compteur",String.valueOf(compteur.nombre));
    }

    @Override
    public void onMoins(Compteur compteur) {
        Log.d("Activity compteur","Moins");
        if(compteur.nombre>0)
            compteur.nombre--;
        else
            Toast.makeText(this,"Can't go under 0",Toast.LENGTH_LONG).show();
        Log.d("Activity compteur",String.valueOf(compteur.nombre));
    }
}
