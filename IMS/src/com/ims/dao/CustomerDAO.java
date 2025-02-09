package com.ims.dao;


import com.ims.models.Customer;

import java.util.List;

public interface CustomerDAO {
    void registerCustomer(Customer customer);
    Customer loginCustomer(String username, String password);
    List<Customer> getAllCustomers();
}

