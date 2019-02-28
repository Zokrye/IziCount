package com.example.izicount.interfaces;

import com.example.izicount.pojo.Compteur;

public interface CompteurListener {
    public void onPlus(Compteur compteur);
    public void onMoins(Compteur compteur);
}
