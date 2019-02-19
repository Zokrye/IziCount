package com.example.izicount.DAOs;

import com.example.izicount.tables.Hebergement;
import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Query;

@Dao
public interface Hebergement_DAOs {
    @Query("SELECT * FROM Hebergement ") //Choppe toutes les infos de Hebergement
    List<Hebergement> getAll();

    //Renvoie une liste de autre correspondant à la liste d'id en para
    @Query("SELECT * FROM Hebergement WHERE type_hebergement IN (:autreIds)")
    List<Hebergement> loadsByIds(int[] autreIds);

    //Renvoie une instance de Hebergement correspondant à l'id passé en paramètre
    @Query("SELECT * FROM Hebergement WHERE type_hebergement LIKE:type LIMIT 1")
    Hebergement findByType(String type);

    //Renvoie une instance de Hebergement correspondant à l'id passé en paramètre
    @Query("SELECT * FROM Hebergement WHERE hebergement_id LIKE:id LIMIT 1")
    Hebergement findById(int id);

    @Delete
    void delete(Hebergement hebergement);
}
