package com.example.izicount.DAOs;

import com.example.izicount.pojo.Compteur;
import com.example.izicount.tables.Restauration;
import com.example.izicount.tables.Transport;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface Restauration_DAOs {

    //Renvoie tout le contenu de la bdd Restauration
    @Query("SELECT * FROM Restauration ")
    List<Restauration> getAll();

    //Renvoie une liste de Restauration correspondant à la liste d'id en para
    @Query("SELECT * FROM Restauration WHERE restau_id  IN (:autreIds)")
    List<Restauration> loadsByIds(int[] autreIds);

    //Renvoie une instance de Restauration, correspondant au type de restau passé en paramètre
    @Query("SELECT * FROM Restauration WHERE type_comptable LIKE:type LIMIT 1")
    Restauration findByType(String type);

    @Query("SELECT * FROM Restauration WHERE restau_id LIKE:id LIMIT 1")
    Restauration findById(int id);

    //Renvoie tous les compteurs de Restauration
    @Query("SELECT  compteur_id, nom ,nombre FROM Restauration")
    List<Compteur> getCompteurOfRestauration();

    @Query("SELECT COUNT(*) FROM Restauration")
    boolean RestaurationIsEmpty();

    @Query("SELECT compteur_id,nom,nombre FROM Restauration where compteur_id LIKE:id_compteur_cherche LIMIT 1")
    Compteur findCompteurDeRestauration(int id_compteur_cherche);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertRestauration(Restauration restauration);


    @Query("UPDATE Restauration SET nombre = nombre+1")
    void updateCompteurDeRestauration();

    @Query("UPDATE Restauration SET nombre = nombre-1")
    void downDateCompteurDeRestauration();

    @Delete
    void delete(Restauration restauration);
}
