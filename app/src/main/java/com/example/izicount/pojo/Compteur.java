package com.example.izicount.pojo;

public class Compteur {
    public String nom;
    public int nombre;
    public Compteur(){
        nombre=0;
    }
    public Compteur(String name){
        nom=name;
        nombre=0;
    }
    public int getNombre(){
        return nombre;
    }
}
