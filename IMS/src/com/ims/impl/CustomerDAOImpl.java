package com.ims.impl;


import com.ims.dao.CustomerDAO;
import com.ims.models.Customer;

import java.util.ArrayList;
import java.util.List;


public class CustomerDAOImpl implements CustomerDAO {
    private final List<Customer> customers = new ArrayList<>();

    @Override
    public void registerCustomer(Customer customer) {
        customers.add(customer);
        System.out.println("Customer registered successfully.");
    }

    @Override
    public Customer loginCustomer(String username, String password) {
        for (Customer customer : customers) {
            if (customer.getUsername().equals(username) && customer.getPassword().equals(password)) {
                System.out.println("Login successful.");
                return customer;
            }
        }
        System.out.println("Invalid credentials.");
        return null;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customers);
    }
}
