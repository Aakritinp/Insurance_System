package com.ims.impl;

import com.ims.dao.InsuranceDAO;
import com.ims.models.Policy;
import com.ims.models.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public  class InsuranceDAOImpl implements InsuranceDAO {
    protected final List<Customer> customers = new ArrayList<>();
    public List<Category> categories = new ArrayList<>();
    public List<SubCategory> subCategories = new ArrayList<>();
    public List<Policy> policies = new ArrayList<>();
    public Map<String, List<Policy>> customerPolicies = new HashMap<>();

    @Override
    public List<Customer> viewCustomers() {
        return new ArrayList<>(customers);
    }

    @Override
    public void addCategory(Category category) {
        categories.add(category);
        System.out.println("Category added successfully.");
    }

    @Override
    public List<Category> viewCategories() {
        return new ArrayList<>(categories);
    }

    @Override
    public void updateCategory(int categoryId, String newCategoryName) {
        for (Category category : categories) {
            if (category.getCategoryId() == categoryId) {
                category.setCategoryName(newCategoryName);
                System.out.println("Category updated successfully.");
                return;
            }
        }
        System.out.println("Category not found.");
    }

    @Override
    public void deleteCategory(int categoryId) {
        categories.removeIf(category -> category.getCategoryId() == categoryId);
        System.out.println("Category deleted successfully.");
    }

    @Override
    public void addSubCategory(SubCategory subCategory) {
        subCategories.add(subCategory);
        System.out.println("Sub-Category added successfully.");
    }

    @Override
    public List<SubCategory> viewSubCategories() {
        return new ArrayList<>(subCategories);
    }

    @Override
    public void updateSubCategory(int subCategoryId, String newSubCategoryName) {
        for (SubCategory subCategory : subCategories) {
            if (subCategory.getSubCategoryId() == subCategoryId) {
                subCategory.setSubCategoryName(newSubCategoryName);
                System.out.println("Sub-Category updated successfully.");
                return;
            }
        }
        System.out.println("Sub-Category not found.");
    }

    @Override
    public void deleteSubCategory(int subCategoryId) {
        subCategories.removeIf(subCategory -> subCategory.getSubCategoryId() == subCategoryId);
        System.out.println("Sub-Category deleted successfully.");
    }

    @Override
    public void addPolicy(Policy policy) {
        policies.add(policy);
        System.out.println("Policy added successfully.");
    }

    @Override
    public List<Policy> viewPolicies() {
        return new ArrayList<>(policies);
    }

    @Override
    public void updatePolicy(int policyId, String newPolicyName) {
        for (Policy policy : policies) {
            if (policy.getPolicyId() == policyId) {
                policy.setPolicyName(newPolicyName);
                System.out.println("Policy updated successfully.");
                return;
            }
        }
        System.out.println("Policy not found.");
    }

    @Override
    public void deletePolicy(int policyId) {
        policies.removeIf(policy -> policy.getPolicyId() == policyId);
        System.out.println("Policy deleted successfully.");
    }

    @Override
    public void applyPolicy(String username, int policyId) {
        for (Policy policy : policies) {
            if (policy.getPolicyId() == policyId) {
                customerPolicies.computeIfAbsent(username, k -> new ArrayList<>()).add(policy);
                System.out.println("Policy applied successfully.");
                return;
            }
        }
        System.out.println("Policy not found.");
    }

    @Override
    public List<Policy> viewCustomerPolicies(String username) {
        return new ArrayList<>(customerPolicies.getOrDefault(username, new ArrayList<>()));
    }

    @Override
    public void managePolicyRequests() {
        System.out.println("No pending requests at the moment.");
    }


}
