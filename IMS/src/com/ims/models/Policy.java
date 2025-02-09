package com.ims.models;

public class Policy {
    private int policyId;
    private String policyName;
    private int subcategory;
    private double premium;
    private boolean isActive;


    public Policy(int policyId, String policyName, int subcategory, double premium) {
        this.policyId = policyId;
        this.policyName = policyName;
        this.subcategory = subcategory;
        this.premium = premium;
        this.isActive = false;
    }


    public int getPolicyId() {
        return policyId;
    }
    public int setPolicyId(int policyId) {
        this.policyId = policyId;
        return policyId;
    }

    public String getPolicyName() {
        return policyName;
    }
public String setPolicyName(String policyName) {
        this.policyName = policyName;
        return policyName;
}
    public int getsubCategory() {
        return subcategory;
    }

    public void setSubcategory(int subcategory) {
        this.subcategory = subcategory;
    }

    public double getPremium() {
        return premium;
    }

    public boolean isActive() {
        return isActive;
    }

    public void activate() {
        this.isActive = true;
    }

    public void cancel() {
        this.isActive = false;
    }

    @Override
    public String toString() {
        return "Policy ID: " + policyId + ", Name: " + policyName + ", SubCategory: " + subcategory + ", Premium: " + premium + ", Active: " + isActive;
    }
}
