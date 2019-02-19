package com.example.izicount.tables;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Loisir")
public class Loisir {
    @PrimaryKey
    private int loisir_id;

    @ColumnInfo(name = "type_loisir")
    private String type_loisir;

    @ColumnInfo(name = "loisir_occurence")
    private int loisir_occurence;


    public int getLoisir_id() {
        return loisir_id;
    }

    public void setLoisir_id(int loisir_id) {
        this.loisir_id = loisir_id;
    }

    public String getType_loisir() {
        return type_loisir;
    }

    public void setType_loisir(String type_loisir) {
        this.type_loisir = type_loisir;
    }

    public int getLoisir_occurence() {
        return loisir_occurence;
    }

    public void setLoisir_occurence(int loisir_occurence) {
        this.loisir_occurence = loisir_occurence;
    }

    public void inc_loisir_occurences(){
        this.loisir_occurence++;
    }

    public void dec_loisir_occurences(){
        this.loisir_occurence--;
    }
}
