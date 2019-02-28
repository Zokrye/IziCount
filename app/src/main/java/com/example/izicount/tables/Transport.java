package com.example.izicount.tables;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Transport")
public class Transport {
    @PrimaryKey
    private int transport_id;

    @ColumnInfo(name = "type_transport")
    private String type_transport;

    @ColumnInfo(name = "transport_occurence")
    private int transport_occurence;


    public int getTransport_id() {
        return transport_id;
    }

    public void setTransport_id(int transport_id) {
        this.transport_id = transport_id;
    }

    public String getType_transport() {
        return type_transport;
    }

    public void setType_transport(String type_transport) {
        this.type_transport = type_transport;
    }

    public int getTransport_occurence() {
        return transport_occurence;
    }

    public void setTransport_occurence(int transport_occurence) {
        this.transport_occurence = transport_occurence;
    }

    public void inc_transport_occurences(){
        this.transport_occurence++;
    }

    public void dec_transport_occurences(){
        this.transport_occurence--;
    }
}
