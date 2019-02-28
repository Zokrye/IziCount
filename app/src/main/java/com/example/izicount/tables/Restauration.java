package com.example.izicount.tables;

import com.example.izicount.pojo.Comptable;
import com.example.izicount.pojo.Compteur;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Restauration")
public class Restauration extends Comptable {
    @PrimaryKey
    private int restau_id;


    public Restauration(){
        super();
    }

    public Restauration(String type,String nom_compteur,int id){
        super(type,nom_compteur);
        this.setRestau_id(id);
    }

    public Restauration(String type, Compteur compteur,int id){
        super(type,compteur);
        this.setRestau_id(id);
    }


    public int getRestau_id() {
        return restau_id;
    }

    public void setRestau_id(int restau_id) {
        this.restau_id = restau_id;
    }
}
