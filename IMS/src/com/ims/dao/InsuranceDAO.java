package com.ims.dao;

import com.ims.models.Category;
import com.ims.models.Customer;
import com.ims.models.SubCategory;
import com.ims.models.Policy;

import java.util.List;

public interface InsuranceDAO {
    List<Customer> viewCustomers();
    void addCategory(Category category);
    List<Category> viewCategories();
    void updateCategory(int categoryId, String newCategoryName);
    void deleteCategory(int categoryId);
    void addSubCategory(SubCategory subCategory);
    List<SubCategory> viewSubCategories();
    void updateSubCategory(int subCategoryId, String newSubCategoryName);
    void deleteSubCategory(int subCategoryId);
    void addPolicy(Policy policy);
    List<Policy> viewPolicies();
    void updatePolicy(int policyId, String newPolicyName);
    void deletePolicy(int policyId);
    void applyPolicy(String username, int policyId);
    List<Policy> viewCustomerPolicies(String username);
    void managePolicyRequests();

}