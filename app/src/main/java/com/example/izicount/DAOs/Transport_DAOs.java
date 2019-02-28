package com.example.izicount.DAOs;

import com.example.izicount.tables.Transport;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Query;

@Dao
public interface Transport_DAOs {
    @Query("SELECT * FROM Transport ") //Choppe toutes les infos de Transport
    List<Transport> getAll();

    //Renvoie une liste de autre correspondant à la liste d'id en para
    @Query("SELECT * FROM Transport WHERE type_transport IN (:autreIds)")
    List<Transport> loadsByIds(int[] autreIds);

    //Renvoie une instance de transport, correspondant au type passé en paramètre
    @Query("SELECT * FROM Transport WHERE type_transport LIKE:type LIMIT 1")
    Transport findByType(String type);

    //Renvoie une instance de transport, correspondant à l'id passé en paramètre
    @Query("SELECT * FROM Transport WHERE transport_id LIKE:id LIMIT 1")
    Transport findById(int id);

    @Delete
    void delete(Transport transport);
}
