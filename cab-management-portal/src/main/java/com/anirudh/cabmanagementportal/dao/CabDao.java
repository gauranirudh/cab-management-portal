package com.anirudh.cabmanagementportal.dao;

import com.anirudh.cabmanagementportal.common.CabStatus;
import com.anirudh.cabmanagementportal.entity.Cab;

public interface CabDao {
    public void registerCab(Cab cab);
    public void changeCabStatus(int cabId, CabStatus cabStatus);
    public void changeCurrentCity(int cabId, int newCityId);
}
