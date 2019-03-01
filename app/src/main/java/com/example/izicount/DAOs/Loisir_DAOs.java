package com.example.izicount.DAOs;

import com.example.izicount.tables.Loisir;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Query;
@Dao
public interface Loisir_DAOs {
    @Query("SELECT * FROM Loisir ")//Renvoie une liste de Loisir (toutes les entrées dans la BDD)
    List<Loisir> getAll();

    //Renvoie une liste de autre correspondant à la liste d'id en para
    @Query("SELECT * FROM Loisir WHERE loisir_id IN (:autreIds)")
    List<Loisir> loadsByIds(int[] autreIds);

    //Renvoie une instance de lieux visités, correspondant  au type passé en paramètre
    @Query("SELECT * FROM Loisir WHERE type_loisir LIKE:type LIMIT 1")
    Loisir findByType(String type);

    @Query("SELECT * FROM Loisir WHERE loisir_id LIKE:id LIMIT 1")
    Loisir findById(int id);

    @Delete
    void delete(Loisir loisir);
}
