package com.example.izicount.pojo;


import androidx.room.Embedded;

public class Comptable {

    private String type_comptable;
    @Embedded
    private Compteur compteur_comptable;

    public Comptable(){
        this.type_comptable = new String(" ");
        this.compteur_comptable = new Compteur(0," ");
    }

    public Comptable(String type,Compteur compteur){
        this.type_comptable = new String(type);
        this.compteur_comptable.setNom(new String(compteur.getNom()));
        this.compteur_comptable.setNombre(new Integer(compteur.getNombre()));
    }

    public Comptable(String type,String nom_compteur){
        this.type_comptable = new String(type);
        this.compteur_comptable.setNombre(0);
        this.compteur_comptable.setNom(nom_compteur);
    }

    public Compteur getCompteur_comptable(){
        return compteur_comptable;
    }
}
