package com.example.izicount.pojo;

public class Compteur {

    private String nom;

    private int nombre;

    public Compteur(){
        setNombre(0);
    }

    public Compteur(String name){
        setNom(name);
        setNombre(0);
    }
    public Compteur(int nombre,String name){
        this.setNombre(new Integer(nombre));
        this.setNom(new String(name));
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
}