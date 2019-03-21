package com.example.mohammadabdolla.s309856mappe3.model;

public class Rom {
    private String romNr;
    private String beskrivelse;
    private String latitude;
    private String longitude;


    public Rom(String romNr, String beskrivelse, String latitude, String longitude) {
        this.romNr = romNr;
        this.beskrivelse = beskrivelse;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Rom(){}

    public void setRomNr(String romNr) {
        this.romNr = romNr;
    }


    public void setBeskrivelse(String beskrivelse) {
        this.beskrivelse = beskrivelse;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getRomNr() {
        return romNr;
    }

    public String getBeskrivelse() {
        return beskrivelse;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }
}