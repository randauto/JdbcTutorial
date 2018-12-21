package com.jdbc.tutorial.transaction;

import java.sql.*;

public class UpdateWithOutTransaction {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo-jdbc-transaction",
                    "tuanlq", "123456");
            Statement stmt = dbConnection.createStatement();
            stmt.executeUpdate("UPDATE account_banking SET amount = 40000000.0 WHERE name = 'A'");
            stmt.executeUpdate("UPDATE account_banking SET amount = 140000000.0 WHERE name = 'B'");

            stmt.close();
            dbConnection.close();
        } catch (Exception e) {
            e.printStackTrace();

        }


    }
}
