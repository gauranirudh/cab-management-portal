package com.anirudh.cabmanagementportal.service;

import com.anirudh.cabmanagementportal.dao.CityDao;
import com.anirudh.cabmanagementportal.dao.CityDaoImpl;
import com.anirudh.cabmanagementportal.dao.TripDao;
import com.anirudh.cabmanagementportal.dao.TripDaoImpl;
import com.anirudh.cabmanagementportal.entity.City;
import com.anirudh.cabmanagementportal.entity.Trip;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class cityService {
    CityDao cityDao = new CityDaoImpl();
    TripDao tripDao = new TripDaoImpl();
    public void onBoardCity(City city) {
        try {
            cityDao.addCity(city);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Bonus - Part 3
    public List<City> getHighDemandCities() {
        List<City> cities = new ArrayList<>();
        try {
            List<Trip> trips = tripDao.GetAllTrips();
            //Assuming demand threshold to be 3
            int threshold = 3;
            HashMap<Integer, Integer> hmap = new HashMap<>();
            for(Trip trip: trips) {
                int cityId = trip.getStartCityId();
                if(hmap.containsKey(cityId)) {
                    hmap.put(cityId, hmap.get(trip.getStartCityId()) + 1);
                }
                else {
                    hmap.put(cityId, 1);
                }
                if(hmap.get(cityId) > threshold) {
                    cities.add(cityDao.getCityById(cityId));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cities;
    }
}
