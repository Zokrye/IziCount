package com.example.izicount.tables;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Meteo")
public class Weather {
    @ColumnInfo(name = "temps")
    private String weather;
    @PrimaryKey @NonNull
    @ColumnInfo(name = "lieu")
    private String place;
    @ColumnInfo(name = "icone")
    private String icon;

    public Weather(String place, String weather, String icon ) {
        this.place = place;
        this.weather = weather;
        this.icon=icon;

    }

    public String getIcon() {
        return icon;
    }

    public String getWeather() {
        return weather;
    }

    public String getPlace() {
        return place;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
