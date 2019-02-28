package com.example.izicount.async;

import android.os.AsyncTask;

import com.example.izicount.interfaces.CompteursLoad;
import com.example.izicount.pojo.Compteur;

import java.util.ArrayList;
import java.util.List;

public class RetrieveCompteursAsyncTask extends AsyncTask<String,Void, List<Compteur>> {

    private CompteursLoad compteursLoad;
    private String nomBDD;

    public RetrieveCompteursAsyncTask(CompteursLoad compteursLoad){
        this.compteursLoad=compteursLoad;
    }

    @Override
    protected List<Compteur> doInBackground(String... theme) {

        if((null!=theme) && (theme.length>0))
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
        //BDD ajout de la liste compteur
        /*int i=1;
        while(i<getSizeTable()){
            final Compteur compteur = new Compteur();
            compteur.nom="Get id pour le nom";
            compteur.nombre=1;
            i++;
        }*/
        return compteurs;
    }

    @Override
    protected void onPostExecute(List<Compteur> compteurs) {
        super.onPostExecute(compteurs);
        compteursLoad.onCompteursRetrieved(compteurs);
    }
}
