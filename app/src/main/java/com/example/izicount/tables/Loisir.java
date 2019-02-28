package com.example.izicount.tables;

import com.example.izicount.pojo.Comptable;
import com.example.izicount.pojo.Compteur;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Loisir")
public class Loisir extends Comptable {
    @PrimaryKey
    private int loisir_id;


    public Loisir(){
        super();
        this.loisir_id = 3;
    }

    public Loisir(String type,String nom_compteur,int id){
        super(type,nom_compteur);
        this.loisir_id=id;
    }
    public Loisir(String type, Compteur compteur,int id){
        super(type,compteur);
        this.loisir_id=id;
    }

    public int getLoisir_id() {
        return loisir_id;
    }

    public void setLoisir_id(int loisir_id) {
        this.loisir_id = loisir_id;
    }
}
