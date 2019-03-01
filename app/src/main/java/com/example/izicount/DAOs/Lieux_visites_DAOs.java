package com.example.izicount.DAOs;

import com.example.izicount.tables.Lieux_visites;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Query;

@Dao
public interface Lieux_visites_DAOs {
    @Query("SELECT * FROM `Lieux visités` ") //Choppe toutes les infos de Lieux_visités
    List<Lieux_visites> getAll();

    //Renvoie une liste de autre correspondant à la liste d'id en para
    @Query("SELECT * FROM `Lieux visités` WHERE type_lieux IN (:autreIds)")
    List<Lieux_visites> loadsByIds(int[] autreIds);

    //Renvoie une instance de Lieux_visités correspondant au type de lieu passé en paramètre
    @Query("SELECT * FROM `Lieux visités` WHERE type_lieux LIKE:type LIMIT 1")
    Lieux_visites findByType(String type);

    //Renvoie une instance de Lieux_visités correspondant à l'id passé en paramètre
    @Query("SELECT * FROM `Lieux visités`WHERE lieux_id LIKE:id LIMIT 1")
    Lieux_visites findById(int id);

    @Delete
    void delete(Lieux_visites lieux_visites);
}
