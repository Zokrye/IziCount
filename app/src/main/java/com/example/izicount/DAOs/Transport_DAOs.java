package com.example.izicount.DAOs;

import com.example.izicount.pojo.Compteur;
import com.example.izicount.tables.Transport;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface Transport_DAOs {
    @Query("SELECT * FROM Transport ") //Choppe toutes les infos de Transport
    List<Transport> getAll();

    //Renvoie une liste de autre correspondant à la liste d'id en para
    @Query("SELECT * FROM Transport WHERE transport_id IN (:autreIds)")
    List<Transport> loadsByIds(int[] autreIds);

    //Renvoie une instance de transport, correspondant au type passé en paramètre
    @Query("SELECT * FROM Transport WHERE type_comptable LIKE:type LIMIT 1")
    Transport findByType(String type);

    //Renvoie une instance de transport, correspondant à l'id passé en paramètre
    @Query("SELECT * FROM Transport WHERE transport_id LIKE:id LIMIT 1")
    Transport findById(int id);

    //Renvoie tous les compteurs de Transport
    @Query("SELECT  compteur_id, nom ,nombre FROM Transport")
    List<Compteur> getCompteurOfTransport();

    @Query("SELECT COUNT(*) FROM Transport")
    boolean TransportIsEmpty();

    @Query("SELECT compteur_id,nom,nombre FROM Transport where compteur_id LIKE:id_compteur_cherche LIMIT 1")
    Compteur findCompteurDeTransport(int id_compteur_cherche);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertTransport(Transport transport);

    @Query("UPDATE Transport SET nombre = nombre+1")
    void updateCompteurDeTransport();

    @Query("UPDATE Transport SET nombre = nombre-1")
    void downDateCompteurDeTransport();


    @Delete
    void delete(Transport transport);
}
