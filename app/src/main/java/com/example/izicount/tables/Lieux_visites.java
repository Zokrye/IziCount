package com.example.ezcount.tables;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Lieux visités")
public class Lieux_visites {
    @PrimaryKey
    private int lieux_id;

    @ColumnInfo(name = "type_lieux")
    private String type_lieux;

    @ColumnInfo(name = "lieux_occurence")
    private int lieux_occurence;

    public int getLieux_id() {
        return lieux_id;
    }

    public void setLieux_id(int lieux_id) {
        this.lieux_id = lieux_id;
    }

    public String getType_lieux() {
        return type_lieux;
    }

    public void setType_lieux(String type_lieux) {
        this.type_lieux = type_lieux;
    }

    public int getLieux_occurence() {
        return lieux_occurence;
    }

    public void setLieux_occurence(int lieux_occurence) {
        this.lieux_occurence = lieux_occurence;
    }

    public void inc_lieux_visites_occurences(){
        this.lieux_occurence++;
    }

    public void dec_lieux_visites_occurences(){
        this.lieux_occurence--;
    }
}
