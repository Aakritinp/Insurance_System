package com.ims.models;

public class Policy {
    private int id;
    private String name;
    private String category_id;
    private String subcategory_id;
    private double premium;
    private String description;
    private String pay_type;

    public Policy(int id, String name, String category_id, String subcategory_id, double premium, String description, String pay_type) {
        this.id = id;
        this.name = name;
        this.category_id = category_id;
        this.subcategory_id = subcategory_id;
        this.premium = premium;
        this.description = description;
        this.pay_type = pay_type;
    }
}
