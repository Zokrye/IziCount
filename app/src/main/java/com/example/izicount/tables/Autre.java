package com.example.izicount.tables;

import com.example.izicount.pojo.Comptable;
import com.example.izicount.pojo.Compteur;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Autre")
public class Autre extends Comptable {
    @PrimaryKey
    private int autre_id;

    public Autre(){
        super();
        this.setAutre_id(8);
    }

    public Autre(String type, Compteur compteur,int id){
        super(type,compteur);
        this.setAutre_id(id);
    }

    public Autre(String type,String nom_compteur,int id){
        super(type,nom_compteur);
        this.setAutre_id(id);
    }


    public int getAutre_id() {
        return autre_id;
    }

    public void setAutre_id(int autre_id) {
        this.autre_id = autre_id;
    }
}
