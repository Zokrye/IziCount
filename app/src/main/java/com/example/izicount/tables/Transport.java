package com.example.izicount.tables;

import com.example.izicount.pojo.Comptable;
import com.example.izicount.pojo.Compteur;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Transport")
public class Transport extends Comptable {
    @PrimaryKey
    private int transport_id;


    public Transport(){
        super();
    }

    public Transport(String type, Compteur compteur,int id){
        super(type,compteur);
        this.transport_id=id;
    }

    public Transport(String type,String nom_compteur,int id){
        super(type,nom_compteur);
        this.transport_id =id;
    }

    public int getTransport_id() {
        return transport_id;
    }

    public void setTransport_id(int transport_id) {
        this.transport_id = transport_id;
    }
}
