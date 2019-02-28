package com.example.izicount.tables;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Autre")
public class Autre {
    @PrimaryKey
    private int autre_id;

    @ColumnInfo(name = "type_autre")
    private String type_autre;

    @ColumnInfo(name = "autre_occurence")
    private int autre_occurence;


    public int getAutre_id() {
        return autre_id;
    }

    public void setAutre_id(int autre_id) {
        this.autre_id = autre_id;
    }

    public String getType_autre() {
        return type_autre;
    }

    public void setType_autre(String type_autre) {
        this.type_autre = type_autre;
    }

    public int getAutre_occurence() {
        return autre_occurence;
    }

    public void setAutre_occurence(int autre_occurence) {
        this.autre_occurence = autre_occurence;
    }

    public void inc_autre_occurences(){
        this.autre_occurence++;
    }

    public void dec_autre_occurences(){
        this.autre_occurence--;
    }
}
