package com.example.izicount;

import android.app.Application;

import com.example.izicount.DAOs.AutreDao;
import com.example.izicount.DAOs.Hebergement_DAOs;
import com.example.izicount.DAOs.Lieux_visites_DAOs;
import com.example.izicount.DAOs.Loisir_DAOs;
import com.example.izicount.DAOs.Meteo_DAOs;
import com.example.izicount.DAOs.Restauration_DAOs;
import com.example.izicount.DAOs.Transport_DAOs;
import com.example.izicount.tables.Autre;
import com.example.izicount.tables.Hebergement;
import com.example.izicount.tables.Loisir;
import com.example.izicount.tables.Restauration;

import androidx.room.Room;

public class DatabaseHelper {
    static DatabaseHelper instance=null;
    private final Database db;
    public static DatabaseHelper getInstance() {
        if(instance==null) {
            instance=new DatabaseHelper();
        }
        return instance;
    }
    public AutreDao getAutreDAO() {
        return db.autreDAO();
    }
    public Hebergement_DAOs getHebergementDAO() {
        return db.hebergementDAO();
    }
    public Lieux_visites_DAOs getLieuxDAO() {
        return db.lieuDAO();
    }
    public Loisir_DAOs getLoisirDAO() {
        return db.loisirDAO();
    }
    public Restauration_DAOs getRestaurationDAO() {
        return db.restaurationDAO();
    }
    public Transport_DAOs getTransportDAO() {
        return db.transportDAO();
    }
    public Meteo_DAOs getMeteoDAO() {
        return db.weatherDAO();
    }
    public DatabaseHelper() {
        db= Room.databaseBuilder(IziCountApplication.getContext(),Database.class,"meteo.db").build();
    }
}
