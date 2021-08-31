package com.example.Coronavirustracker.models;

public class Locationstats {

    private String state;
    private String country;
    private int Latesttotalcases;
    private int diffrompreviousday;

    public int getDiffrompreviousday() {
        return diffrompreviousday;
    }

    public void setDiffrompreviousday(int diffrompreviousday) {
        this.diffrompreviousday = diffrompreviousday;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getLatesttotalcases() {
        return Latesttotalcases;
    }

    public void setLatesttotalcases(int latesttotalcases) {
        Latesttotalcases = latesttotalcases;
    }

    @Override
    public String toString() {
        return "Locationstats{" +
                "state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", Latesttotalcases=" + Latesttotalcases +
                '}';
    }
}