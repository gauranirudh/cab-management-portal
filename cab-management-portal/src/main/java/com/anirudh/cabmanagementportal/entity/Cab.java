package com.anirudh.cabmanagementportal.entity;

import com.anirudh.cabmanagementportal.common.CabStatus;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Objects;

public class Cab {

    @Id
    @GeneratedValue
    @NotNull
    private int id;

    @NotNull
    @NotEmpty(message = "Driver Name can not be empty")
    private String OwnerName;

    @NotNull
    private CabStatus status;

    @NotNull
    @NotEmpty(message = "License Plate number can not be empty")
    private String licensePlateNumber;

    private int currentCityId;

    @NotNull
    private Timestamp createdAt;

    @NotNull
    private Timestamp updatedAt;

    //Constructor
    public Cab(int id, String ownerName, CabStatus status, String licensePlateNumber,
               int currentCityId, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.OwnerName = ownerName;
        this.status = status;
        this.licensePlateNumber = licensePlateNumber;
        this.currentCityId = currentCityId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Cab(){

    }

    //Getters and Setters
    public int getId() {
        return id;
    }

    public String getOwnerName() {
        return OwnerName;
    }

    public void setOwnerName(String ownerName) {
        this.OwnerName = ownerName;
    }

    public CabStatus getStatus() {
        return status;
    }

    public void setStatus(CabStatus cabStatus) {
        this.status = cabStatus;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public void setLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }

    public int getCurrentCityId() {
        return currentCityId;
    }

    public void setCurrentCityId(int currentCityId) {
        this.currentCityId = currentCityId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cab cab = (Cab) o;
        return id == cab.id && currentCityId == cab.currentCityId && OwnerName.equals(cab.OwnerName)
                && status == cab.status && licensePlateNumber.equals(cab.licensePlateNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, OwnerName, status, licensePlateNumber, currentCityId, createdAt, updatedAt);
    }
}