package com.example.ezcount.tables;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Restauration")
public class Restauration {
    @PrimaryKey
    private int restau_id;

    @ColumnInfo(name = "type_restau")
    private String type_restau;

    @ColumnInfo(name = "restau_occurence")
    private int restau_occurence;


    public int getRestau_id() {
        return restau_id;
    }

    public void setRestau_id(int restau_id) {
        this.restau_id = restau_id;
    }

    public String getType_restau() {
        return type_restau;
    }

    public void setType_restau(String type_restau) {
        this.type_restau = type_restau;
    }

    public int getRestau_occurence() {
        return restau_occurence;
    }

    public void setRestau_occurence(int restau_occurence) {
        this.restau_occurence = restau_occurence;
    }

    public void inc_restauration_occurences(){
        this.restau_occurence++;
    }
    public void dec_restauration_occurences(){
        this.restau_occurence--;
    }

}
