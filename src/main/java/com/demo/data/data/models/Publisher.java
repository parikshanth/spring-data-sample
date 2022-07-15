package com.demo.data.data.models;

public class Publisher {
    private int id;
    private String name;
    private String city;
    private String state;
    private String country;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    public Publisher(int id, String name, String city, String state, String country) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.state = state;
        this.country = country;
    }
}
