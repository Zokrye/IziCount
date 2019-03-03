package com.example.izicount.DAOs;

import com.example.izicount.pojo.Comptable;
import com.example.izicount.pojo.Compteur;
import com.example.izicount.tables.Hebergement;
import com.example.izicount.tables.Transport;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface Hebergement_DAOs {
    @Query("SELECT * FROM Hebergement ") //Choppe toutes les infos de Hebergement
    List<Hebergement> getAll();

    //Renvoie une liste de autre correspondant à la liste d'id en para
    @Query("SELECT * FROM Hebergement WHERE hebergement_id IN (:autreIds)")
    List<Hebergement> loadsByIds(int[] autreIds);

    //Renvoie une instance de Hebergement correspondant à l'id passé en paramètre
    @Query("SELECT * FROM Hebergement WHERE type_comptable LIKE:type LIMIT 1")
    Hebergement findByType(String type);

    //Choper tous les compteurs de Hebergement
    @Query("SELECT compteur_id, nom ,nombre FROM Hebergement")
    List<Compteur> getCompteurOfHebergement();

    @Query("SELECT COUNT(*) FROM Hebergement")
    boolean HebergementIsEmpty();

    //Renvoie une instance de Hebergement correspondant à l'id passé en paramètre
    @Query("SELECT * FROM Hebergement WHERE hebergement_id LIKE:id LIMIT 1")
    Hebergement findById(int id);

    @Query("SELECT compteur_id,nom,nombre FROM Hebergement where compteur_id LIKE:id_compteur_cherche LIMIT 1")
    Compteur findCompteurDeHebergement(int id_compteur_cherche);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertHebergement(Hebergement hebergement);

    @Query("UPDATE Hebergement SET nombre = nombre+1")
    void updateCompteurDeHebergement();


    @Query("UPDATE Hebergement SET nombre = nombre-1")
    void downDateCompteurDeHebergement();

    @Delete
    void delete(Hebergement hebergement);
}
