package com.jdbc.tutorial.test;

import com.jdbc.tutorial.data.JdbcCustomer;
import com.jdbc.tutorial.model.Customer;

import java.util.List;

public class JdbcHandlingTest {
    public static void main(String[] args) {
        JdbcCustomer jdbcCustomer = new JdbcCustomer();
        getList(jdbcCustomer);

        jdbcCustomer.update(103, "Le Quoc Tuan");

    }

    private static void getList(JdbcCustomer jdbcCustomer) {
        List<Customer> data = jdbcCustomer.getListCustomer();
        if (data != null && data.size() > 0) {
            System.out.println("List Customer");
            for (Customer item : data) {
                System.out.println("-----------------------------");
                System.out.println("Number: " + item.getCustomerNumber());
                System.out.println("Name: " + item.getCustomerName());
                System.out.println("Contact First Name: " + item.getContactFirstName());
                System.out.println("Contact Last Name: " + item.getContactLastName());
                System.out.println("-----------------------------");
            }
        }
    }
}
