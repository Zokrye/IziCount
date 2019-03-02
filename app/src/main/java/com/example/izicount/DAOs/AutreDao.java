package com.example.izicount.DAOs;

import com.example.izicount.tables.Autre;

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

    @Update(onConflict = OnConflictStrategy.IGNORE)
    void update(Autre autre);

    @Delete
    void delete(Autre autre);
}
