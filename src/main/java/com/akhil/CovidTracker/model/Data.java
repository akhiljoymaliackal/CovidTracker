package com.akhil.CovidTracker.model;

public class Data {
    private String country;
    private String state;
    private int casesCount;
    private int change;

    public Data(String country, String state, int casesCount, int lastDayCaseCount) {
        this.country = country;
        this.state = state;
        this.casesCount = casesCount;
        this.change = lastDayCaseCount;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getCasesCount() {
        return casesCount;
    }

    public void setCasesCount(int casesCount) {
        this.casesCount = casesCount;
    }

    public int getChange() {
        return change;
    }

    public void setChange(int change) {
        this.change = change;
    }

    @Override
    public String toString() {
        return "[" +
                "country='" + country + '\'' +
                ", state='" + state + '\'' +
                ", casesCount=" + casesCount +
                ", change=" + change +
                ']';
    }
}
