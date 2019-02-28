package com.example.izicount.tables;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Hebergement")
public class Hebergement {
    @PrimaryKey
    private int hebergement_id;//Id différent pour chaque type d'hébergement

    @ColumnInfo(name = "type_hebergement")
    private String type;

    @ColumnInfo(name = "hebergement_occurence")
    private int herbergement_occurence;


    public int getHebergement_id() {
        return hebergement_id;
    }

    public void setHebergement_id(int hebergement_id) {
        this.hebergement_id = hebergement_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getHerbergement_occurence() {
        return herbergement_occurence;
    }

    public void setHerbergement_occurence(int herbergement_occurence) {
        this.herbergement_occurence = herbergement_occurence;
    }

    public void inc_hebergement_occurences(){
        this.herbergement_occurence++;
    }

    public void dec_hebergement_occurences(){
        this.herbergement_occurence--;
    }
}
