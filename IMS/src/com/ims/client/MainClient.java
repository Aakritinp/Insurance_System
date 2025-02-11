package com.ims.client;

import com.ims.dao.CustomerDAO;
import com.ims.dao.InsuranceDAO;
import com.ims.impl.CustomerDAOImpl;
import com.ims.impl.InsuranceDAOImpl;
import com.ims.models.Category;
import com.ims.models.Customer;
import com.ims.models.Policy;
import com.ims.models.SubCategory;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MainClient {
    public static void main(String[] args) {
        InsuranceDAO insuranceDAO = new InsuranceDAOImpl();
        CustomerDAO customerDAO = new CustomerDAOImpl();
        Scanner scanner = new Scanner(System.in);

        boolean running = true;
        while (running) {
            try {
                System.out.println("\n-----------------------------");
                System.out.println("Welcome to Insurance Management System");
                System.out.println("1. Admin");
                System.out.println("2. Customer");
                System.out.println("3. Exit");
                System.out.println("-----------------------------");
                System.out.print("Enter your choice: ");
                int userTypeChoice = scanner.nextInt();

                switch (userTypeChoice) {
                    case 1 -> {
                        if (handleAdminOperations(scanner, insuranceDAO)) {
                            System.out.println("Logged out from Admin.");
                        }
                    }
                    case 2 -> handleCustomerLoginAndRegistration(scanner, customerDAO, insuranceDAO);
                    case 3 -> {
                        System.out.println("Exiting... Thank you for using the system!");
                        running = false;
                    }
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid choice.");
                scanner.nextLine(); // Clear the buffer
            }
        }
        scanner.close();
    }

    private static boolean handleAdminOperations(Scanner scanner, InsuranceDAO insuranceDAO) {
        System.out.println("\n*** Admin Login ***");
        System.out.print("Enter Username: ");
        String username = scanner.next();
        System.out.print("Enter Password: ");
        String password = scanner.next();

        if ("admin".equals(username) && "admin123".equals(password)) {
            boolean adminLoggedIn = true;
            while (adminLoggedIn) {
                try {
                    System.out.println("\n-----------------------------");
                    System.out.println("Admin Menu");
                    System.out.println("1. Manage Categories");
                    System.out.println("2. Manage Sub-Categories");
                    System.out.println("3. Manage Policies");
                    System.out.println("4. Logout");
                    System.out.println("-----------------------------");
                    System.out.print("Enter your choice: ");
                    int choice = scanner.nextInt();

                    switch (choice) {
                        case 1 -> handleCategoryManagement(scanner, insuranceDAO);
                        case 2 -> handleSubCategoryManagement(scanner, insuranceDAO);
                        case 3 -> handlePolicyManagement(scanner, insuranceDAO);
                        case 4 -> {
                            adminLoggedIn = false;
                            System.out.println("Logged out from Admin.");
                        }
                        default -> System.out.println("Invalid choice. Please enter a valid option.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.nextLine();
                }
            }
            return true;
        } else {
            System.out.println("Invalid Admin credentials. Please try again.");
            return false;
        }
    }

    private static void handleCategoryManagement(Scanner scanner, InsuranceDAO insuranceDAO) {
        System.out.println("\n--- Category Management ---");
        System.out.println("1. Add Category");
        System.out.println("2. View Categories");
        System.out.println("3. Update Category");
        System.out.println("4. Delete Category");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        try {
            switch (choice) {
                case 1 -> {
                    System.out.print("Enter Category ID (must be an integer): ");
                    int categoryId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Category Name: ");
                    String categoryName = scanner.nextLine();
                    insuranceDAO.addCategory(new Category(categoryId, categoryName));
                }
                case 2 -> {
                    List<Category> categories = insuranceDAO.viewCategories();
                    if (categories.isEmpty()) {
                        System.out.println("Empty list. Please add categories to view.");
                    } else {
                        categories.forEach(System.out::println);
                    }
                }
                case 3 -> {
                    System.out.print("Enter Category ID to Update: ");
                    int updateCategoryId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter New Category Name: ");
                    String newCategoryName = scanner.nextLine();
                    if(updateCategoryId <= 0 || newCategoryName.isEmpty()) {
                        System.out.println("Invalid input. Please enter valid values.");
                    }
                    else {
                        insuranceDAO.updateCategory(updateCategoryId, newCategoryName);
                    }

                }
                case 4 -> {
                    System.out.print("Enter Category ID to Delete: ");
                    int deleteCategoryId = scanner.nextInt();
                    insuranceDAO.deleteCategory(deleteCategoryId);
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private static void handleSubCategoryManagement(Scanner scanner, InsuranceDAO insuranceDAO) {
        System.out.println("\n--- Sub-Category Management ---");
        System.out.println("1. Add Sub-Category");
        System.out.println("2. View Sub-Categories");
        System.out.println("3. Update Sub-Category");
        System.out.println("4. Delete Sub-Category");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        try {
            switch (choice) {
                case 1 -> {
                    System.out.print("Enter Sub-Category ID: ");
                    int subCategoryId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Sub-Category Name: ");
                    String subCategoryName = scanner.nextLine();
                    System.out.print("Enter Category ID: ");
                    int categoryId = scanner.nextInt();
                    if (subCategoryId <= 0 || categoryId <= 0 || subCategoryName.isEmpty()) {
                        System.out.println("Invalid input. Please enter valid values.");

                    }
                    else {
                    insuranceDAO.addSubCategory(new SubCategory(subCategoryId, subCategoryName, categoryId));
                    }
                }
                case 2 -> {
                    List<SubCategory> subCategories = insuranceDAO.viewSubCategories();
                    if (subCategories.isEmpty()) {
                        System.out.println("No sub-categories available. Please add some.");
                    } else {
                        subCategories.forEach(System.out::println);
                    }
                }
                case 3 -> {
                    System.out.print("Enter Sub-Category ID to Update: ");
                    int updateSubCategoryId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter New Sub-Category Name: ");
                    String newSubCategoryName = scanner.nextLine();
                    if(updateSubCategoryId <= 0 || newSubCategoryName.isEmpty()) {
                        System.out.println("Invalid input. Please enter valid values.");
                    }
                    else {
                    insuranceDAO.updateSubCategory(updateSubCategoryId, newSubCategoryName);
                    }
                }
                case 4 -> {
                    System.out.print("Enter Sub-Category ID to Delete: ");
                    int deleteSubCategoryId = scanner.nextInt();
                    insuranceDAO.deleteSubCategory(deleteSubCategoryId);
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private static void handlePolicyManagement(Scanner scanner, InsuranceDAO insuranceDAO) {
        System.out.println("\n--- Policy Management ---");
        System.out.println("1. Add Policy");
        System.out.println("2. View Policies");
        System.out.println("3. Update Policy");
        System.out.println("4. Delete Policy");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        try {
            switch (choice) {
                case 1 -> {
                    System.out.print("Enter Policy ID: ");
                    int policyId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Policy Name: ");
                    String policyName = scanner.nextLine();
                    System.out.print("Enter Sub-Category ID: ");
                    int subCategoryId = scanner.nextInt();
                    System.out.print("Enter Policy Premium: ");
                    double policyPremium = scanner.nextDouble();
                    insuranceDAO.addPolicy(new Policy(policyId, policyName, subCategoryId, policyPremium));
                }
                case 2 -> {
                    List<Policy> policies = insuranceDAO.viewPolicies();
                    if (policies.isEmpty()) {
                        System.out.println("No policies available. Please add some.");
                    } else {
                        policies.forEach(System.out::println);
                    }
                }
                case 3 -> {

                    System.out.print("Enter Policy ID to Update: ");
                    int updatePolicyId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter New Policy Name: ");
                    String newPolicyName = scanner.nextLine();
                    if(updatePolicyId <= 0 || newPolicyName.isEmpty()) {
                        System.out.println("Invalid input. Please enter valid values.");
                    }
                    else {
                        insuranceDAO.updatePolicy(updatePolicyId, newPolicyName);
                    }
                }

                case 4 -> {
                    System.out.print("Enter Policy ID to Delete: ");
                    int deletePolicyId = scanner.nextInt();
                    insuranceDAO.deletePolicy(deletePolicyId);
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private static void handleCustomerLoginAndRegistration(Scanner scanner, CustomerDAO customerDAO, InsuranceDAO insuranceDAO) {
        System.out.println("\n--- Customer Login and Registration ---");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.print("Enter your choice: ");
        int customerChoice = scanner.nextInt();
        scanner.nextLine();

        switch (customerChoice) {
            case 1 -> {
                System.out.print("Enter your username: ");
                String username = scanner.next();

                System.out.print("Enter your password: ");
                String password = scanner.next();
                Customer customer = customerDAO.loginCustomer(username, password);
                if (customer != null) {
                    System.out.println("Login successful.");
                    handleCustomerOperations(scanner, insuranceDAO, customer);
                } else {
                    System.out.println("Login failed. Invalid username or password.");
                }
            }
            case 2 -> {
                System.out.print("Enter your username: ");
                String userName = scanner.next();
                System.out.print("Enter your password: ");
                String registerPassword = scanner.next();


                Customer newCustomer = new Customer((int) Math.random(), userName, registerPassword);
                customerDAO.registerCustomer(newCustomer);
                System.out.println("Registration successful. You can now log in.");
            }
            default -> System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void handleCustomerOperations(Scanner scanner, InsuranceDAO insuranceDAO, Customer customer) {
        boolean customerLoggedIn = true;
        while (customerLoggedIn) {
            try {
                System.out.println("\n--- Customer Menu ---");
                System.out.println("1. View Categories");
                System.out.println("2. View Sub-Categories");
                System.out.println("3. View Policies");
                System.out.println("4. Apply for a Policy");
                System.out.println("5. View Applied Policies");
                System.out.println("6. Logout");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1 -> {
                        List<Category> categories = insuranceDAO.viewCategories();
                        if (categories.isEmpty()) {
                            System.out.println("No categories available.");
                        } else {
                            categories.forEach(System.out::println);
                        }
                    }
                    case 2 -> {
                        List<SubCategory> subCategories = insuranceDAO.viewSubCategories();
                        if (subCategories.isEmpty()) {
                            System.out.println("No sub-categories available.");
                        } else {
                            subCategories.forEach(System.out::println);
                        }
                    }
                    case 3 -> {
                        List<Policy> policies = insuranceDAO.viewPolicies();
                        if (policies.isEmpty()) {
                            System.out.println("No policies available.");
                        } else {
                            policies.forEach(System.out::println);
                        }
                    }
                    case 4 -> {
                        System.out.print("Enter Policy ID to Apply (must be an integer): ");
                        int policyId = scanner.nextInt();
                        insuranceDAO.applyPolicy(customer.getUsername(), policyId);

                    }
                    case 5 -> {
                        List<Policy> appliedPolicies = insuranceDAO.viewCustomerPolicies(customer.getUsername());
                        if (appliedPolicies.isEmpty()) {
                            System.out.println("You have not applied for any policies.");
                        } else {
                            appliedPolicies.forEach(System.out::println);
                        }
                    }
                    case 6 -> {
                        customerLoggedIn = false;
                        System.out.println("Logged out from Customer.");
                    }
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
    }
}
