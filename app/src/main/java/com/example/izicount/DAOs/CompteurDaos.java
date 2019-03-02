package com.example.izicount.DAOs;

import com.example.izicount.tables.Autre;
import com.example.izicount.pojo.Compteur;


import java.util.List;

import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

public interface CompteurDaos {

   @Insert
    void insertCompteur(Compteur compteur);

   @Query("SELECT * FROM Compteur WHERE compteur_id IN (:id)")
    List<Compteur> searchById(int[] id);

   @Query("SELECT * FROM Compteur WHERE compteur_id LIKE:id LIMIT 1")
    Compteur getById(int id);

   @Query("SELECT * FROM Compteur WHERE `nom compteur` LIKE:name LIMIT 1")
   Compteur getByName(String name);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    void update(Compteur compteur);


}
