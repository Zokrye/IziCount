package com.example.izicount.DAOs;

import com.example.izicount.tables.Weather;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;


@Dao
public interface Meteo_DAOs {
    @Query("SELECT * FROM Meteo")
    List<Weather> loadWeather();

    @Query("UPDATE Meteo SET temps = :temps, icone=:icon WHERE lieu=:place")
    void UpdateWeather(String place,String temps, String icon);

    @Query("DELETE FROM Meteo WHERE lieu=(:lieu)")
    void DeleteWeather(String lieu);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void AddWeather(Weather meteo);
}
