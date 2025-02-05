package com.ims.models;

public class User {
    private String username;
    private String password;
    private String name;
    private int id;
    private String role;
    private String email;
    private String address;
    private String gender;
    private String dob;

    public User(String username, String password, String name, String role, String email, String address, String gender, String dob) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.role = role;
        this.email = email;
        this.address = address;
        this.gender = gender;
        this.dob = dob;
    }
}
