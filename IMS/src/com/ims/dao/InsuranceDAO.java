package com.ims.dao;

import com.ims.models.Category;
import com.ims.models.Policy;
import java.util.List;

public interface InsuranceDAO {
    void addCategory(Category category);
    List<Category> viewCategories();

    void addPolicy(Policy policy);
    List<Policy> viewPolicies();
}

