package com.jdbc.tutorial.data;

import com.jdbc.tutorial.database.JavaConnection;
import com.jdbc.tutorial.model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcCustomer {
    private final String SQL_TABLE = "customers";
    private final String SQL_INSERT = "INSERT INTO " + SQL_TABLE + "(customerName) VALUES(?)";
    private final String SQL_UPDATE = "UPDATE " + SQL_TABLE + " SET customerName=? WHERE customerNumber=?";
    private final String SQL_DELETE = "DELETE FROM " + SQL_TABLE + " WHERE customerNumber = ?";
    private final String SQL_SELECT = "SELECT customerNumber, customerName, contactFirstName, contactLastName FROM customers ORDER BY customerNumber";


    public int insert(String customerName) {
        Connection connection = null;
        PreparedStatement statement = null;
        int rows = 0;

        try {
            connection = JavaConnection.getConnection();
            statement = connection.prepareStatement(SQL_INSERT);
            statement.setString(1, customerName);
            System.out.println("Executing query:" + SQL_INSERT);
            rows = statement.executeUpdate();
            System.out.println("Affected records:" + rows);

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            JavaConnection.close(statement);
            JavaConnection.close(connection);
        }

        return rows;
    }

    public int update(int customerNumber, String customerName) {
        Connection connection = null;
        PreparedStatement statement = null;
        int rows = 0;
        try {
            connection = JavaConnection.getConnection();
            statement = connection.prepareStatement(SQL_UPDATE);
            statement.setString(1, customerName);
            statement.setInt(2, customerNumber);

            System.out.println("Executing query:" + SQL_UPDATE);
            rows = statement.executeUpdate();
            System.out.println("Affected records:" + rows);

        } catch (SQLException ex) {

        } finally {
            JavaConnection.close(statement);
            JavaConnection.close(connection);
        }

        return rows;

    }

    public int delete(int customerNumber) {
        Connection connection = null;
        PreparedStatement statement = null;
        int rows = 0;
        try {
            connection = JavaConnection.getConnection();
            statement = connection.prepareStatement(SQL_DELETE);
            statement.setInt(1, customerNumber);

            System.out.println("Executing query:" + SQL_DELETE);
            rows = statement.executeUpdate();
            System.out.println("Affected records:" + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            JavaConnection.close(statement);
            JavaConnection.close(connection);
        }

        return rows;
    }

    public List<Customer> getListCustomer() {
        PreparedStatement statement = null;
        ResultSet re = null;


        List<Customer> list = new ArrayList<>();
        try (Connection connection = JavaConnection.getConnection()) {
            Customer customer = null;
            statement = connection.prepareStatement(SQL_SELECT);
            re = statement.executeQuery();

            while (re.next()) {
                customer = new Customer();
                customer.setCustomerNumber(re.getInt(1));
                customer.setCustomerName(re.getString(2));
                customer.setContactFirstName(re.getString(3));
                customer.setContactLastName(re.getString(4));

                list.add(customer);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);


        } finally {
            JavaConnection.close(re);
            JavaConnection.close(statement);
        }


        return list;
    }
}
