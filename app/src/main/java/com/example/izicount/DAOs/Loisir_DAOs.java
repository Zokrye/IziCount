package com.example.izicount.DAOs;

import com.example.izicount.pojo.Compteur;
import com.example.izicount.tables.Loisir;
import com.example.izicount.tables.Transport;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface Loisir_DAOs {
    @Query("SELECT * FROM Loisir ")//Renvoie une liste de Loisir (toutes les entrées dans la BDD)
    List<Loisir> getAll();

    //Renvoie une liste de autre correspondant à la liste d'id en para
    @Query("SELECT * FROM Loisir WHERE loisir_id IN (:autreIds)")
    List<Loisir> loadsByIds(int[] autreIds);

    //Renvoie une instance de lieux visités, correspondant  au type passé en paramètre
    @Query("SELECT * FROM Loisir WHERE type_comptable LIKE:type LIMIT 1")
    Loisir findByType(String type);

    @Query("SELECT * FROM Loisir WHERE loisir_id LIKE:id LIMIT 1")
    Loisir findById(int id);

    //Renvoie tous les compteurs de Loisir
    @Query("SELECT  compteur_id, nom ,nombre FROM Loisir")
    List<Compteur> getCompteurOfLoisir();

    @Query("SELECT compteur_id,nom,nombre FROM Loisir where compteur_id LIKE:id_compteur_cherche LIMIT 1")
    Compteur findCompteurDeLoisir(int id_compteur_cherche);

    @Query("SELECT COUNT(*) FROM Autre")
    boolean LoisirIsEmpty();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertLoisir(Loisir loisir);

    @Query("UPDATE Loisir SET nombre = nombre+1")
    void updateCompteurDeLoisir();

    @Query("UPDATE Loisir SET nombre = nombre-1")
    void downDateCompteurDeLoisir();

    @Delete
    void delete(Loisir loisir);
}
