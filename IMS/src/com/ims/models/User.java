package com.ims.models;

public class User {
    protected int userId;
    protected String username;
    protected String password;

    public User(int userId, String username, String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User ID: " + userId + ", Username: " + username;
    }
}
