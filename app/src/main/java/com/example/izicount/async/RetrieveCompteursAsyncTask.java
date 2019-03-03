package com.example.izicount.async;

import android.os.AsyncTask;

import com.example.izicount.interfaces.CompteursLoad;
import com.example.izicount.pojo.Comptable;
import com.example.izicount.pojo.Compteur;

import java.util.ArrayList;
import java.util.List;
import java.lang.String;

import androidx.room.Database;

public class RetrieveCompteursAsyncTask extends AsyncTask<String,Void, List<Compteur>> {

    private CompteursLoad compteursLoad;
    private String nomBDD;

    public RetrieveCompteursAsyncTask(CompteursLoad compteursLoad){
        this.compteursLoad=compteursLoad;
    }

    @Override
    protected List<Compteur> doInBackground(String... theme) {


        if((null!=theme) && (theme.length>0))
            //Sans BDD on crée une liste de compteurs avec la fonction getFakeCompteurs
            return getFakeCompteurs();

        return null;
    }

    public static List<Compteur> getFakeCompteurs(){
        ArrayList<Compteur> compteurs = new ArrayList<Compteur>();
        for (int i=1;i<20;i++){
            final Compteur compteur = new Compteur();
            compteur.setNom("Numero "+ i);
            compteurs.add(compteur);
        }
        return compteurs;
    }

    @Override
    protected void onPostExecute(List<Compteur> compteurs) {
        super.onPostExecute(compteurs);
        compteursLoad.onCompteursRetrieved(compteurs);
    }
}
