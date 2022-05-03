package com.anirudh.cabmanagementportal.service;

import com.anirudh.cabmanagementportal.common.CabStatus;
import com.anirudh.cabmanagementportal.dao.CityDao;
import com.anirudh.cabmanagementportal.dao.CityDaoImpl;
import com.anirudh.cabmanagementportal.dao.TripDao;
import com.anirudh.cabmanagementportal.dao.TripDaoImpl;
import com.anirudh.cabmanagementportal.entity.Cab;
import com.anirudh.cabmanagementportal.entity.City;
import com.anirudh.cabmanagementportal.entity.Trip;

import java.sql.Timestamp;
import java.util.List;

public class BookingService {
    TripDao tripDao = new TripDaoImpl();
    CityDao cityDao = new CityDaoImpl();
    CabService cabService = new CabService();

    public Trip bookCab(List<Cab> cabs, String sourceCityName, String targetCityName, int customerId) {
        City sourceCity = cityDao.getCityByName(sourceCityName);
        City targetCity = cityDao.getCityByName(targetCityName);

        Cab cab = searchCab(cabs, sourceCity);
        int fare = calculateFare(sourceCity, targetCity);
        Trip trip = new Trip(cab.getId(), customerId, fare, sourceCity.getId(), targetCity.getId());
        tripDao.createTrip(trip);
        return trip;
    }

    public void startTrip(Trip trip, Cab cab) {
        trip.setStartTime(new Timestamp(System.currentTimeMillis()));
        cabService.updateStatus(cab.getId(), CabStatus.ON_TRIP);
        //cityId = -1 denotes indeterminate city
        cab.setCurrentCityId(-1);
        cab.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
    }

    public void endTrip(Trip trip, Cab cab) {
        try {
            trip.setEndTime(new Timestamp(System.currentTimeMillis()));
            cabService.updateStatus(cab.getId(), CabStatus.IDLE);
            cab.setCurrentCityId(trip.getDestinationCityId());
            cab.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Cab searchCab(List<Cab> cabs, City sourceCity) {
        return cabService.searchCab(cabs, sourceCity);
    }

    private int calculateFare(City sourceCity, City targetCity) {
        //Calculate fare based on the distance between two cities
        return 100;
    }
}
