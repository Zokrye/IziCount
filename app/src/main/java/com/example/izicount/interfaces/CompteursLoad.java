package com.example.izicount.interfaces;

import com.example.izicount.pojo.Compteur;

import java.util.List;

public interface CompteursLoad {
    public void onCompteursRetrieved(List<Compteur> compteurs);
}
