package com.anirudh.cabmanagementportal.helper;

import org.springframework.beans.factory.annotation.Value;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
    String driverClassName = "com.mysql.jdbc.Driver";
    //Can put the below values in a config file
    @Value("${dbconfig.connectionUrl}")
    String connectionUrl;
    @Value("${dbconfig.username}")
    String dbUser;
    @Value("${dbconfig.password}")
    String dbPwd;

    private static DbUtil dbUtil = null;

    // private constructor to make DbUtil a singleton
    private DbUtil() {
        try {
            Class.forName(driverClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        Connection conn = null;
        conn = DriverManager.getConnection(connectionUrl, dbUser, dbPwd);
        return conn;
    }

    public static DbUtil getInstance() {
        if (dbUtil == null) {
            dbUtil = new DbUtil();
        }
        return dbUtil;
    }
}
