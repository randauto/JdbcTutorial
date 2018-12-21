package com.jdbc.tutorial.database;

import java.sql.*;

public class JavaConnection {
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String JDBC_URL = "jdbc:mysql://localhost/classicmodels?useSSL=false";
    private static final String JDBC_USER = "tuanlq";
    private static final String JDBC_PASS = "123456";
    private static Driver driver;

    //So there are no problems when getting the connection of
//concurrently, the word synchronized is used
    public static synchronized Connection getConnection() throws SQLException {
        if (driver == null) {
            try {
                Class jdbcDriverClass = Class.forName(JDBC_DRIVER);
                driver = (Driver) jdbcDriverClass.newInstance();
                DriverManager.registerDriver(driver);
            } catch (Exception e) {
                System.out.println("Failure to load the JDBC driver");
                e.printStackTrace(System.out);
            }
        }
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
    }

    //Close the resultSet object
    public static void close(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace(System.out);
        }
    }

    //Close the PrepareStatement object
    public static void close(PreparedStatement stmt) {
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace(System.out);
        }

    }

    //Close the connection object
    public static void close(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace(System.out);
        }
    }
}
