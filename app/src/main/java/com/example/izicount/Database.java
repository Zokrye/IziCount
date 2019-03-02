package com.example.izicount;

import com.example.izicount.DAOs.AutreDao;
import com.example.izicount.DAOs.Hebergement_DAOs;
import com.example.izicount.DAOs.Lieux_visites_DAOs;
import com.example.izicount.DAOs.Loisir_DAOs;
import com.example.izicount.DAOs.Meteo_DAOs;
import com.example.izicount.DAOs.Restauration_DAOs;
import com.example.izicount.DAOs.Transport_DAOs;
import com.example.izicount.tables.Autre;
import com.example.izicount.tables.Hebergement;
import com.example.izicount.tables.Lieux_visites;
import com.example.izicount.tables.Loisir;
import com.example.izicount.tables.Restauration;
import com.example.izicount.tables.Transport;
import com.example.izicount.tables.Weather;

import androidx.room.RoomDatabase;

@androidx.room.Database(entities={Weather.class, Transport.class, Restauration.class, Loisir.class, Lieux_visites.class, Hebergement.class, Autre.class},
        version=1, exportSchema = false)
public abstract class Database extends RoomDatabase {
    public abstract Hebergement_DAOs hebergementDAO();
    public abstract Lieux_visites_DAOs lieuDAO();
    public abstract Loisir_DAOs loisirDAO();
    public abstract Restauration_DAOs restaurationDAO();
    public abstract Transport_DAOs transportDAO();
    public abstract Meteo_DAOs weatherDAO();
    public abstract AutreDao autreDAO();
}
