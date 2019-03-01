package com.example.izicount.tables;

import com.example.izicount.pojo.Comptable;
import com.example.izicount.pojo.Compteur;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Hebergement")
public class Hebergement extends Comptable {
    @PrimaryKey
    @ColumnInfo(name = "hebergement_id")
    private int hebergement_id;//Id différent pour chaque type d'hébergement

    public Hebergement(){
        super();
        this.setHebergement_id(1);
    }

    public Hebergement(String type_hebergement,String nom_compteur,int id){
        super(type_hebergement,nom_compteur);
        this.setHebergement_id(id);
    }

    public Hebergement(String type,Compteur compteur,int id){
        super(type,compteur);
        this.setHebergement_id(id);
    }

    public int getHebergement_id() {
        return hebergement_id;
    }

    public void setHebergement_id(int hebergement_id) {
        this.hebergement_id = hebergement_id;
    }


}
