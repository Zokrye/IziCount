package com.example.izicount.interfaces;

import com.example.izicount.pojo.Compteur;

public interface CompteurListener {
    public void onPlus(Compteur compteur);//permet d'incrémenter un compteur
    public void onMoins(Compteur compteur);//permet de décrémenter un compteur
}
