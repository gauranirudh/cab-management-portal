package com.anirudh.cabmanagementportal.dao;

import com.anirudh.cabmanagementportal.entity.City;

public interface CityDao {
    public void addCity(City city);
    public City getCityByName(String cityName);
    public City getCityById(int cityId);
}
