package com.example.izicount.pojo;


import androidx.room.Embedded;

public class Comptable {

    private String type_comptable;
    @Embedded
    private Compteur compteur_comptable;

    public String getType_comptable() {
        return type_comptable;
    }

    public void setType_comptable(String type_comptable) {
        this.type_comptable = type_comptable;
    }

    public void setCompteur_comptable(Compteur compteur_comptable) {
        this.compteur_comptable = compteur_comptable;
    }

    public Comptable(){
        this.type_comptable = new String(" ");
        this.compteur_comptable = new Compteur(" ",1);
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
