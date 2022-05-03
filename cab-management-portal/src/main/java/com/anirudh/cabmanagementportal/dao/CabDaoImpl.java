package com.anirudh.cabmanagementportal.dao;

import com.anirudh.cabmanagementportal.common.CabStatus;
import com.anirudh.cabmanagementportal.entity.Cab;
import com.anirudh.cabmanagementportal.helper.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class CabDaoImpl implements CabDao {
    Connection connection = null;
    PreparedStatement ptmt = null;

    public CabDaoImpl() {};

    public void registerCab(Cab cab) {
        try {
            String queryString = "INSERT INTO Cab(ownerName, status, licensePlateNumber, " +
                    "createdAt, updatedAt) VALUES(?,?,?,?,?)";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setString(1, cab.getOwnerName());
            ptmt.setString(2, cab.getStatus().name());
            ptmt.setString(3, cab.getLicensePlateNumber());
            ptmt.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            ptmt.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
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

    public void changeCabStatus(int cabId, CabStatus cabStatus) {
        try {
            String queryString = "update cab set status = ?, updatedAt = ? where cabId = ?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);

            ptmt.setString(1, cabStatus.name());
            ptmt.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            ptmt.setInt(3, cabId);

            int rowsUpdated = ptmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Cab status has been updated successfully!");
            }
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

    public void changeCurrentCity(int cabId, int newCityId) {
        try {
            String queryString = "update cab set cityId = ?, updatedAt = ? where cabId = ?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);

            ptmt.setInt(1, newCityId);
            ptmt.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            ptmt.setInt(3, cabId);

            int rowsUpdated = ptmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Cab status has been updated successfully!");
            }
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

    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = DbUtil.getInstance().getConnection();
        return conn;
    }
}
