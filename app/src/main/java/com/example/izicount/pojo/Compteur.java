package com.example.izicount.pojo;


public class Compteur  {

    private int compteur_id; //sert pour la BDD

    private String nom;

    private int nombre;

    public Compteur(){
        this.setNombre(new Integer(0));
        this.setNom(new String("Compteur sans nom"));
        this.setCompteur_id(new Integer(0));
    }
    public Compteur(String name){
        setNom(new String(name));
        setNombre(new Integer(0));
        this.setCompteur_id(0);
    }

    public Compteur(String name,int id){
        this.setNombre(new Integer(0));
        this.setCompteur_id(new Integer(id));
        this.setNom(new String(name));
    }

    public Compteur(int id, String name, int nombre){
        this.setNombre(new Integer(nombre));
        this.setNom(new String(name));
        this.setCompteur_id(new Integer(id));
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }

    public void increment(){
        this.nombre++;
    }

    public void decrement(){
        this.nombre--;
    }

    public int getCompteur_id() {
        return compteur_id;
    }

    public void setCompteur_id(int compteur_id) {
        this.compteur_id = compteur_id;
    }
}
