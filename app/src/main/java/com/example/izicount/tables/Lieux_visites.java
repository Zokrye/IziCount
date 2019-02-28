package com.example.izicount.tables;

import com.example.izicount.pojo.Comptable;
import com.example.izicount.pojo.Compteur;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Lieux visités")
public class Lieux_visites extends Comptable {
    @PrimaryKey
    private int lieux_id;

    public Lieux_visites(){
        super();
        this.setLieux_id(2);
    }

    public Lieux_visites(String type_hebergement,String nom_compteur,int id){
        super(type_hebergement,nom_compteur);
        this.setLieux_id(id);
    }

    public Lieux_visites(String type, Compteur compteur, int id){
        super(type,compteur);
        this.setLieux_id(id);
    }


    public int getLieux_id() {
        return lieux_id;
    }

    public void setLieux_id(int lieux_id) {
        this.lieux_id = lieux_id;
    }
}
