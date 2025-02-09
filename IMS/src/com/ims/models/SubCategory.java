package com.ims.models;

public class SubCategory {
    private int subCategoryId;
    private String subCategoryName;
    private int categoryId;

    public SubCategory(int subCategoryId, String subCategoryName, int categoryId) {
        this.subCategoryId = subCategoryId;
        this.subCategoryName = subCategoryName;
        this.categoryId = categoryId;
    }

    public int getSubCategoryId() {
        return subCategoryId;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }
    public void setSubCategoryName(String subCategoryName)
    {
        this.subCategoryName = subCategoryName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    @Override
    public String toString() {
        return "SubCategory ID: " + subCategoryId + ", Name: " + subCategoryName + ", Category ID: " + categoryId;
    }
}
