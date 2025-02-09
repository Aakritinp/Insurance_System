package com.ims.client;

import com.ims.dao.CustomerDAO;
import com.ims.dao.InsuranceDAO;
import com.ims.impl.CustomerDAOImpl;
import com.ims.impl.InsuranceDAOImpl;
import com.ims.models.Category;
import com.ims.models.Customer;
import com.ims.models.Policy;
import com.ims.models.SubCategory;

import java.util.List;
import java.util.Scanner;

public class MainClient {
    public static void main(String[] args) {
        InsuranceDAO insuranceDAO = new InsuranceDAOImpl();
        CustomerDAO customerDAO = new CustomerDAOImpl();
        Scanner scanner = new Scanner(System.in);

        boolean running = true;
        while (running) {
            System.out.println("\nWelcome to Insurance Management System");
            System.out.println("1. Admin");
            System.out.println("2. Customer");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int userTypeChoice = scanner.nextInt();

            switch (userTypeChoice) {
                case 1:
                    if (handleAdminOperations(scanner, insuranceDAO)) {
                        System.out.println("Logged out from Admin.");
                    }
                    break;
                case 2:
                    System.out.println("1. Login");
                    System.out.println("2. Register");
                    int customerChoice = scanner.nextInt();

                    switch (customerChoice) {
                        case 1:
                            System.out.print("Enter your username: ");
                            String username = scanner.next();
                            System.out.print("Enter your password: ");
                            String password = scanner.next();
                            Customer customer = customerDAO.loginCustomer(username, password);
                            if (customer != null) {
                                System.out.println("Login successful.");
                                System.out.print("Enter your choice: ");
                                if (handleCustomerOperations(scanner, insuranceDAO, customer)) {
                                    System.out.println("Logged out from Customer.");

                                }
                            } else {
                                System.out.println("Login failed. Invalid username or password.");
                            }
                            break;
                        case 2:
                            System.out.print("Enter your username: ");
                            String registerUsername = scanner.next();
                            System.out.print("Enter your password: ");
                            String registerPassword = scanner.next();
                            System.out.print("Enter your full name: ");
                            String fullName = scanner.next();
                            Customer newCustomer = new Customer((int) Math.random(), registerUsername, registerPassword, fullName);
                            customerDAO.registerCustomer(newCustomer);
                            break;
                    }

                    break;
                case 3:
                    System.out.println("Exiting...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
        scanner.close();
    }


    private static boolean handleAdminOperations(Scanner scanner, InsuranceDAO insuranceDAO) {
        boolean adminLoggedIn = true;
        System.out.println("Admin Login");
        System.out.print("Enter Username: ");
        String username = scanner.next();
        System.out.print("Enter Password: ");
        String password = scanner.next();

        if ("admin".equals(username) && "admin123".equals(password)) {
            while (adminLoggedIn) {
                System.out.println("\nAdmin Menu");
                System.out.println("1. View Users");
                System.out.println("2. Add Category");
                System.out.println("3. View Categories");
                System.out.println("4. Update Category");
                System.out.println("5. Delete Category");
                System.out.println("6. Add Sub-Category");
                System.out.println("7. View Sub-Categories");
                System.out.println("8. Update Sub-Category");
                System.out.println("9. Delete Sub-Category");
                System.out.println("10. Add Policy");
                System.out.println("11. View Policies");
                System.out.println("12. Update Policy");
                System.out.println("13. Delete Policy");
                System.out.println("14. View/Activate/Cancel Policy Requests");
                System.out.println("15. Logout");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.println("User List:");
                        for (Customer c : insuranceDAO.viewCustomers()) {
                            System.out.println(c);
                        }
                        break;
                    case 2:
                        System.out.print("Enter Category ID: ");
                        int categoryId = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter Category Name: ");
                        String categoryName = scanner.nextLine();
                        Category category = new Category(categoryId, categoryName);
                        insuranceDAO.addCategory(category);
                        break;
                    case 3:
                        System.out.println("Category List:");
                        for (Category cat : insuranceDAO.viewCategories()) {
                            System.out.println(cat);
                        }
                        break;
                    case 4:
                        System.out.print("Enter Category ID to Update: ");
                        int updateCategoryId = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter New Category Name: ");
                        String newCategoryName = scanner.nextLine();
                        insuranceDAO.updateCategory(updateCategoryId, newCategoryName);
                        break;
                    case 5:
                        System.out.print("Enter Category ID to Delete: ");
                        int deleteCategoryId = scanner.nextInt();
                        insuranceDAO.deleteCategory(deleteCategoryId);
                        break;
                    case 6:
                        System.out.print("Enter Sub-Category ID: ");
                        int subCategoryId = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter Sub-Category Name: ");
                        String subCategoryName = scanner.nextLine();
                        System.out.print("Enter Category ID: ");
                        int subCategoryCategoryId = scanner.nextInt();

                        boolean categoryFound = false;
                        List<Category> categories = insuranceDAO.viewCategories();
                        for (Category cat : categories) {
                            if (cat.getCategoryId() == subCategoryCategoryId) {
                                categoryFound = true;
                                System.out.println("Category found.");
                                break;
                            }

                        }
                        if (!categoryFound) {
                            System.out.println("Category not found. Please enter a valid category ID again.");
                            break;
                        }

                        SubCategory subCategory = new SubCategory(subCategoryId, subCategoryName, subCategoryCategoryId);
                        insuranceDAO.addSubCategory(subCategory);
                        break;
                    case 7:
                        System.out.println("Sub-Category List:");
                        for (SubCategory subCat : insuranceDAO.viewSubCategories()) {
                            System.out.println(subCat);
                        }
                        break;
                    case 8:
                        System.out.print("Enter Sub-Category ID to Update: ");
                        int updateSubCategoryId = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter New Sub-Category Name: ");
                        String newSubCategoryName = scanner.nextLine();
                        insuranceDAO.updateSubCategory(updateSubCategoryId, newSubCategoryName);
                        break;
                    case 9:
                        System.out.print("Enter Sub-Category ID to Delete: ");
                        int deleteSubCategoryId = scanner.nextInt();
                        insuranceDAO.deleteSubCategory(deleteSubCategoryId);
                        break;
                    case 10:
                        System.out.print("Enter Policy ID: ");
                        int policyId = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter Policy Name: ");
                        String policyName = scanner.nextLine();
                        System.out.print("Enter Sub-Category ID: ");
                        int policySubCategoryId = scanner.nextInt();

                        boolean subcategoryFound = false;
                        List<SubCategory> subCategories = insuranceDAO.viewSubCategories();
                        for (SubCategory subcat : subCategories) {
                            if (subcat.getSubCategoryId() == policySubCategoryId) {
                                subcategoryFound = true;
                                System.out.println("SubCategory found.");
                                break;
                            }

                        }
                        if (!subcategoryFound) {
                            System.out.println("SubCategory not found. Please enter a valid subcategory ID again.");
                            break;
                        }

                        System.out.print("Enter Policy Premium: ");
                        double policypremium = scanner.nextDouble();


                        Policy policy = new Policy(policyId, policyName, policySubCategoryId, policypremium);
                        insuranceDAO.addPolicy(policy);
                        break;
                    case 11:
                        System.out.println("Policy List:");
                        for (Policy policyItem : insuranceDAO.viewPolicies()) {
                            System.out.println(policyItem);
                        }
                        break;
                    case 12:
                        System.out.print("Enter Policy ID to Update: ");
                        int updatePolicyId = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter New Policy Name: ");
                        String newPolicyName = scanner.nextLine();
                        insuranceDAO.updatePolicy(updatePolicyId, newPolicyName);
                        break;
                    case 13:
                        System.out.print("Enter Policy ID to Delete: ");
                        int deletePolicyId = scanner.nextInt();
                        insuranceDAO.deletePolicy(deletePolicyId);
                        break;
                    case 14:
                        System.out.println("Policy Requests Management:");
                        insuranceDAO.managePolicyRequests();
                        break;
                    case 15:
                        adminLoggedIn = false;
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            }
        } else {
            System.out.println("Invalid Admin credentials.");
        }
        return !adminLoggedIn;
    }

    private static boolean handleCustomerOperations(Scanner scanner, InsuranceDAO insuranceDAO, Customer customer) {
        boolean customerLoggedIn = true;


        while (customerLoggedIn) {
            System.out.println("\nCustomer Menu");
            System.out.println("1. View Categories");
            System.out.println("2. View Sub-Categories");
            System.out.println("3. View Policies");
            System.out.println("4. Apply for a Policy");
            System.out.println("5. View Applied Policies");
            System.out.println("6. Logout");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Category List:");
                    for (Category cat : insuranceDAO.viewCategories()) {
                        System.out.println(cat);
                    }
                    break;
                case 2:
                    System.out.println("Sub-Category List:");
                    for (SubCategory subCat : insuranceDAO.viewSubCategories()) {
                        System.out.println(subCat);
                    }
                    break;

                case 3:
                    System.out.println("Policy List:");
                    for (Policy policyItem : insuranceDAO.viewPolicies()) {
                        System.out.println(policyItem);
                    }
                    break;

                case 4:
                    System.out.print("Enter Policy ID to Apply: ");
                    int policyId = scanner.nextInt();
                    insuranceDAO.applyPolicy(customer.getUsername(), policyId);
                    break;
                case 5:
                    System.out.println("Policies You Hold:");
                    for (Policy policy : insuranceDAO.viewCustomerPolicies(customer.getUsername())) {
                        System.out.println(policy);
                    }
                    break;
                case 6:
                    customerLoggedIn = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }

        return !customerLoggedIn;
    }
}


