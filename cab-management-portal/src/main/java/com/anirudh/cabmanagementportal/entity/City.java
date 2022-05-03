package com.anirudh.cabmanagementportal.entity;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class City {
    @NotNull
    @NotEmpty(message = "Invalid city id")
    private int id;

    @NotNull
    @NotEmpty(message = "Invalid city name")
    @Column(unique = true)
    private String name;

    @NotNull
    @NotEmpty(message = "Invalid state name")
    private String state;

    //Constructor
    public City(String name, String state) {
        this.name = name;
        this.state = state;
    }

    public City() {

    }

    //Getters and Setters
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
