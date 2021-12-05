package com.akhil.CovidTracker.model;

public class DataBuilder {
    private String country;
    private String state;
    private int casesCount;
    private int change;

    public DataBuilder setCountry(String country) {
        this.country = country;
        return this;
    }

    public DataBuilder setState(String state) {
        this.state = state;
        return this;
    }

    public DataBuilder setCasesCount(int casesCount) {
        this.casesCount = casesCount;
        return this;
    }
    public DataBuilder setChange(int lastDayCaseCount){
        this.change = lastDayCaseCount;
        return this;
    }

    public Data build(){
        return new Data(country, state, casesCount, change);
    }
}
