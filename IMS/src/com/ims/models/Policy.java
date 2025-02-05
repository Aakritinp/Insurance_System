package com.ims.models;

public class Policy {
    private int policyId;
    private String policyName;
    private String category;
    private double premium;

    public Policy(int policyId, String policyName, String category, double premium) {
        this.policyId = policyId;
        this.policyName = policyName;
        this.category = category;
        this.premium = premium;
    }

    public int getPolicyId() {
        return policyId;
    }

    public String getPolicyName() {
        return policyName;
    }

    public String getCategory() {
        return category;
    }

    public double getPremium() {
        return premium;
    }

    @Override
    public String toString() {
        return "Policy ID: " + policyId + ", Name: " + policyName + ", Category: " + category + ", Premium: $" + premium;
    }
}
