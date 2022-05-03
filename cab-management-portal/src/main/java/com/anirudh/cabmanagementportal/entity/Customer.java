package com.anirudh.cabmanagementportal.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Customer {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @NotEmpty(message = "first name can not be empty")
    private String firstName;

    @NotNull
    @NotEmpty(message = "last name can not be empty")
    private String lastName;

    //Constructor
    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Customer() {

    }

    //Getters and Setters
    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
