package com.example.izicount.DAOs;

import com.example.izicount.pojo.Compteur;
import com.example.izicount.tables.Lieux_visites;
import com.example.izicount.tables.Transport;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface Lieux_visites_DAOs {
    @Query("SELECT * FROM `Lieux visités` ") //Choppe toutes les infos de Lieux_visités
    List<Lieux_visites> getAll();

    //Renvoie une liste de autre correspondant à la liste d'id en para
    @Query("SELECT * FROM `Lieux visités` WHERE lieux_id IN (:autreIds)")
    List<Lieux_visites> loadsByIds(int[] autreIds);

    //Renvoie une instance de Lieux_visités correspondant au type de lieu passé en paramètre
    @Query("SELECT * FROM `Lieux visités` WHERE type_comptable LIKE:type LIMIT 1")
    Lieux_visites findByType(String type);

    //Renvoie une instance de Lieux_visités correspondant à l'id passé en paramètre
    @Query("SELECT * FROM `Lieux visités`WHERE lieux_id LIKE:id LIMIT 1")
    Lieux_visites findById(int id);

    //Renvoie tous les compteurs de Lieux visités
    @Query("SELECT  compteur_id, nom ,nombre FROM `Lieux visités`")
    List<Compteur> getCompteurOfLieuxVisites();

    @Query("SELECT compteur_id,nom,nombre FROM `Lieux visités` where compteur_id LIKE:id_compteur_cherche LIMIT 1")
    Compteur findCompteurDeLieuxVisites(int id_compteur_cherche);

    @Query("SELECT COUNT(*) FROM `Lieux visités`")
    boolean Lieux_visitesIsEmpty();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertLieux(Lieux_visites lieux_visites);

    @Query("UPDATE `Lieux visités` SET nombre = nombre+1")
    void updateCompteurDeLieuxVisites();

    @Query("UPDATE `Lieux visités` SET nombre = nombre-1")
    void downDateCompteurDeLieuxVisites();

    @Delete
    void delete(Lieux_visites lieux_visites);
}
