package com.anirudh.cabmanagementportal.dao;

import com.anirudh.cabmanagementportal.entity.Trip;
import com.anirudh.cabmanagementportal.helper.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TripDaoImpl implements TripDao{
    Connection connection = null;
    PreparedStatement ptmt = null;

    public TripDaoImpl() {};

    public void createTrip(Trip trip) {
        try {
            String queryString = "INSERT INTO Trip(customerId, cabId, fare, " +
                    "startCityId, destinationCityId) VALUES(?,?,?,?,?)";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setInt(1, trip.getCustomerId());
            ptmt.setInt(2, trip.getCabId());
            ptmt.setInt(3, trip.getFare());
            ptmt.setInt(4, trip.getStartCityId());
            ptmt.setInt(5, trip.getDestinationCityId());
            ptmt.executeUpdate();
            System.out.println("Data Added Successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ptmt != null)
                    ptmt.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Trip> GetTripsByCab(int cabId) {
        try {
            List<Trip> trips = new ArrayList<>();
            String queryString = "Select startTime, endTime From Trip where cabId = ?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setInt(1, cabId);
            ResultSet resultSet = ptmt.executeQuery();
            while(resultSet.next()) {
                Trip trip = new Trip();
                trip.setStartTime(resultSet.getTimestamp("startTime"));
                trip.setEndTime(resultSet.getTimestamp("endTime"));
                trips.add(trip);
            }
            System.out.println("Fetched trips Successfully");
            return trips;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ptmt != null)
                    ptmt.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public List<Trip> GetAllTrips() {
        try {
            List<Trip> trips = new ArrayList<>();
            String queryString = "Select startCityId From Trip";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ResultSet resultSet = ptmt.executeQuery();
            while(resultSet.next()) {
                Trip trip = new Trip();
                trip.setStartCityId(resultSet.getInt("startCityId"));
                trips.add(trip);
            }
            System.out.println("Fetched all trips Successfully");
            return trips;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ptmt != null)
                    ptmt.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = DbUtil.getInstance().getConnection();
        return conn;
    }
}
