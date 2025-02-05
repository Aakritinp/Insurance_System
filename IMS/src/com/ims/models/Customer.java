package com.ims.models;


public class Customer extends User {
    private String fullName;

    public Customer(int userId, String username, String password, String fullName) {
        super(userId, username, password);
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    @Override
    public String toString() {
        return "Customer -> " + super.toString() + ", Full Name: " + fullName;
    }
}

