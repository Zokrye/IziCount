package com.example.izicount.DAOs;

import com.example.izicount.tables.Lieux_visites;
import com.example.izicount.tables.Restauration;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface Restauration_DAOs {

    @Insert//Insère un objet restau dans la bdd Restauration
    public void insertRestau(Restauration restauration);

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

    @Update(onConflict = OnConflictStrategy.IGNORE)
    void update(Restauration restauration);
    @Delete
    void delete(Restauration restauration);
}
