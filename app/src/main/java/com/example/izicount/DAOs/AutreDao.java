package com.example.izicount.DAOs;

import com.example.izicount.tables.Autre;
import com.example.izicount.pojo.Compteur;

import java.util.List;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface AutreDao {
    @Query("SELECT * FROM Autre ") //Choppe toutes les infos de Autre
    List<Autre> getAll();

    //Renvoie une liste de autre correspondant à la liste d'id en para
    @Query("SELECT * FROM autre WHERE autre_id IN (:autreIds)")
    List<Autre> loadsByIds(int[] autreIds);

    //Renvoie une instance de Autre, correspondant au type passé en paramètre
    @Query("SELECT * FROM Autre WHERE type_comptable LIKE:type LIMIT 1")
    Autre findByType(String type);

    @Query("SELECT * FROM Autre WHERE autre_id LIKE:id LIMIT 1")
    Autre findById(int id);

    @Query("SELECT COUNT(*) FROM Autre")
    boolean AutreIsEmpty();

    @Query("SELECT compteur_id,nom,nombre FROM Autre where compteur_id LIKE:id_compteur_cherche LIMIT 1")
    Compteur findCompteurDeAutre(int id_compteur_cherche);

    //Renvoie tous les compteurs de Autre
    @Query("SELECT  compteur_id, nom ,nombre FROM Autre")
    List<Compteur> getCompteurOfAutre();

    //Update que le compteur, pas le reste
    @Query("UPDATE Autre SET nombre = nombre+1")
    void updateCompteurDeAutre();

    @Query("UPDATE Autre SET nombre = nombre-1")
    void downDateCompteurDeAutre();

    @Delete
    void delete(Autre autre);
}
