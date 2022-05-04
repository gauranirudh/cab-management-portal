package com.anirudh.cabmanagementportal.service;

import com.anirudh.cabmanagementportal.common.CabStatus;
import com.anirudh.cabmanagementportal.dao.*;
import com.anirudh.cabmanagementportal.entity.Cab;
import com.anirudh.cabmanagementportal.entity.City;
import com.anirudh.cabmanagementportal.entity.Trip;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CabService {
    CabDao cabDao = new CabDaoImpl();
    TripDao tripDao = new TripDaoImpl();
    CityDao cityDao = new CityDaoImpl();
    public Cab searchCab(List<Cab> cabs, City sourceCity) {
        Cab mostIdleCab = new Cab();
        try {
            if (cabs.size() == 0) {
                return null;
            }
            //search cab which is IDLE and was updated earliest
            mostIdleCab = cabs.stream()
                    .filter(c -> c.getStatus() == CabStatus.IDLE &&
                            c.getCurrentCityId() == sourceCity.getId())
                    .min(Comparator.comparing(Cab::getUpdatedAt))
                    .orElseGet(() -> null);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return mostIdleCab;
        }
    }

    public void updateStatus(int cabId, CabStatus status) {
        try {
            cabDao.changeCabStatus(cabId, status);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void changeCurrentCity(Cab cab, City city) {
        cabDao.changeCurrentCity(cab.getId(), city.getId());
    }

    //Bonus - Part 1
    public long calculateIdleDuration(Cab cab, Timestamp start, Timestamp end) {
        try {
            List<Trip> trips = tripDao.GetTripsByCab(cab.getId());
            long totalTripTime = 0L;
            for (Trip trip : trips) {
                if (trip.getStartTime().after(start) && trip.getEndTime().before(end)) {
                    totalTripTime += trip.getEndTime().getTime() - trip.getStartTime().getTime();
                }
            }
            //Calculate IDLE time in milliseconds
            long totalIdleTime = end.getTime() - start.getTime() - totalTripTime;
            return totalIdleTime;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0L;
    }

    //Bonus - Part 2
    public List<String> generateCabHistory(Cab cab) {
        List<String> states = new ArrayList<>();
        try {
            List<Trip> trips = tripDao.GetTripsByCab(cab.getId());
            for (Trip trip : trips) {
                City city =  cityDao.getCityById(trip.getStartCityId());
                if(!states.contains(city.getState())) {
                    states.add(city.getState());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return states;
    }
}
