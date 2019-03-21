package com.example.mohammadabdolla.s309856mappe3.model;

public class Reservasjon {
    public String Romnr;
    public String Dato;
    public String Tid;

    public Reservasjon() {
    }

    public Reservasjon(String romnr, String dato, String tid) {
        Romnr = romnr;
        Dato = dato;
        Tid = tid;
    }

    public String getRomnr() {
        return Romnr;
    }

    public void setRomnr(String romnr) {
        Romnr = romnr;
    }

    public String getDato() {
        return Dato;
    }

    public void setDato(String dato) {
        Dato = dato;
    }

    public String getTid() {
        return Tid;
    }

    public void setTid(String tid) {
        Tid = tid;
    }
}
