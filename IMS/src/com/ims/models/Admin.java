package com.ims.models;

public class Admin extends User {

    public Admin(int userId, String username, String password) {
        super(userId, username, password);
    }

    @Override
    public String toString() {
        return "Admin" + super.toString();
    }
}
