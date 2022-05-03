package com.anirudh.cabmanagementportal.dao;

import com.anirudh.cabmanagementportal.entity.Trip;

import java.util.List;

public interface TripDao {
    public void createTrip(Trip trip);
    public List<Trip> GetTripsByCab(int cabId);
    public List<Trip> GetAllTrips();
}
