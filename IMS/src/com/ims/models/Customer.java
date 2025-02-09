package com.ims.models;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User {
    private String fullName;
    private List<Policy> policies = new ArrayList<>();

    public Customer(int userId, String username, String password, String fullName) {
        super(userId, username, password);
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public List<Policy> getPolicies() {
        return policies;
    }

    public void applyPolicy(Policy policy) {
        this.policies.add(policy);
    }

    @Override
    public String toString() {
        return "Customer -> " + super.toString() + ", Full Name: " + fullName;
    }
}
