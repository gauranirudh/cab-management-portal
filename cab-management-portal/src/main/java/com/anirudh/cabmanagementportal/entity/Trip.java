package com.anirudh.cabmanagementportal.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.sql.Timestamp;

public class Trip {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @NotEmpty(message = "Invalid cab Id")
    private int cabId;

    @NotNull
    @NotEmpty(message = "Invalid customer Id")
    private int customerId;

    @NotNull
    @Positive(message = "Fare can not be negative")
    private int fare;

    @NotNull
    @NotEmpty(message = "Invalid start city")
    private int startCityId;

    @NotNull
    @NotEmpty(message = "Invalid destination city")
    private int destinationCityId;

    //end time will be null until trip is completed
    private Timestamp startTime;
    private Timestamp endTime;

    //Constructor
    public Trip(int cabId, int customerId, int tripAmount, int startCityId,
                int destinationCityId) {
        this.cabId = cabId;
        this.customerId = customerId;
        this.fare = tripAmount;
        this.startCityId = startCityId;
        this.destinationCityId = destinationCityId;
    }

    public Trip() {

    }

    //Getters and Setters
    public int getId() {
        return id;
    }

    public int getCabId() {
        return cabId;
    }

    public void setCabId(int cabId) {
        this.cabId = cabId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getFare() {
        return fare;
    }

    public void setFare(int fare) {
        this.fare = fare;
    }

    public int getStartCityId() {
        return startCityId;
    }

    public void setStartCityId(int startCityId) {
        this.startCityId = startCityId;
    }

    public int getDestinationCityId() {
        return destinationCityId;
    }

    public void setDestinationCityId(int destinationCityId) {
        this.destinationCityId = destinationCityId;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }
}
