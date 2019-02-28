package com.example.izicount;

public class Weather {
    private String weather;
    private String place;
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
}
