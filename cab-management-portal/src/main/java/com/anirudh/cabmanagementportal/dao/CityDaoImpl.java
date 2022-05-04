package com.anirudh.cabmanagementportal.dao;

import com.anirudh.cabmanagementportal.entity.City;
import com.anirudh.cabmanagementportal.helper.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CityDaoImpl implements CityDao{
    Connection connection = null;
    PreparedStatement ptmt = null;

    public CityDaoImpl() {};

    public void addCity(City city) {
        try {
            String queryString = "INSERT INTO City(name, state) VALUES(?,?)";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setString(1, city.getName());
            ptmt.setString(2, city.getState());
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

    public City getCityByName(String cityName) {
        try {
            City city = new City();
            String queryString = "Select id, name, state From City where name = ?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setString(1, cityName);
            ResultSet resultSet = ptmt.executeQuery();
            if(resultSet.next()) {
                city.setId(resultSet.getInt("id"));
                city.setName(resultSet.getString("name"));
                city.setName(resultSet.getString("state"));
            }
            System.out.println("Fetched city Successfully");

            return city;
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

    public City getCityById(int cityId) {
        try {
            City city = new City();
            String queryString = "Select id, name, state From City where id = ?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setInt(1, cityId);
            ResultSet resultSet = ptmt.executeQuery();
            if(resultSet.next()) {
                city.setId(resultSet.getInt("id"));
                city.setName(resultSet.getString("name"));
                city.setName(resultSet.getString("state"));
            }
            System.out.println("Fetched city Successfully");
            return city;
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
