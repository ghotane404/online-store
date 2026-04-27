package com.pluralsight;

import java.io.*;
import java.util.*;

public class Inventory {
    private Scanner scanner = new Scanner(System.in);
    private HashMap<String, String> allDepartment = new HashMap<>();        // stores department choices
    private ArrayList<Product> products = new ArrayList<>();        // stores all products listed from the CSV file
    private ArrayList<Product> departmentProducts = new ArrayList<>();      // for filtering by department
    private ArrayList<Product> priceFilter = new ArrayList<>();     // filtering products by price

    public Product searchProductOptionMenu() {
        System.out.println("Search product by: ");
        System.out.println("N) Product Name");
        System.out.println("P) Price");
        System.out.println("D) Department");
        System.out.println("X) Exit");
        String searchOption = scanner.nextLine().toUpperCase().strip();

        switch (searchOption) {
            case "N":
                return searchProductName();
            case "P":
                return searchProductPrice();
            case "D":
                return searchProductFromDepartment();
            case "X":
                System.out.println("Goodbye!");
                return null;        // return exits the loop and the method, which in this case ends the application
            default:
                System.out.println("Invalid selection. Please try again.");
                return null;
        }
    }

    public Product searchProductName() {
        System.out.println("Please type the name of the product you would like to search: ");
        String searchedProduct = scanner.nextLine().toUpperCase().strip();

        for (Product product : products) {
            if (product.getProductName().toUpperCase().equals(searchedProduct)) {
                System.out.println();
                System.out.printf("%s found.%nWould you like to add this to cart? (Y/N) %n", product.getProductName());

                String choice = scanner.nextLine().toUpperCase().strip();

                switch (choice) {
                    case "Y":
                        return product;
                    case "N":
                        return null;
                    default:
                        System.out.println("Invalid selection. Please try again.");
                        return null;
                }
            }
        }
        System.out.println("No matches found for Product Name typed. Please try again.");
        return null;
    }

    public Product searchProductPrice() {
        System.out.println();
        System.out.println("Please enter the max and the min price of the product to filter: \n");
        System.out.print("Max Price: ");
        String maxPrice = scanner.nextLine().toUpperCase().strip();
        System.out.print("Min Price: ");
        String minPrice = scanner.nextLine().toUpperCase().strip();

        double min = Double.parseDouble(minPrice);
        double max = Double.parseDouble(maxPrice);
        int index = 1;

        CustomerCart.displayProductsUi();
        for (Product product : products) {
            if (product.getPrice() >= min && product.getPrice() <= max) {
                priceFilter.add(product);
                System.out.printf("%-20d %-30s $%-15.2f %n", index, product.getProductName(), product.getPrice());
                index++;
            }
        }

        if (priceFilter.isEmpty()) {
            System.out.println("No products found in that price range.");
            return null;
        }

        System.out.println("\nWhich product would you like to add to cart? ");
        System.out.print("Make a selection: ");
        int productChoice = scanner.nextInt();
        scanner.nextLine();

        return priceFilter.get(productChoice - 1);
    }

    public Product searchProductFromDepartment() {
        System.out.println("\nPlease select which product department to filter? \n" +
                "1) Audio Video\n" +
                "2) Computers\n" +
                "3) Electronics\n" +
                "4) Games\n" +
                "5) Clothing\n" +
                "6) Grocery\n" +
                "7) Home\n" +
                "8) Sports\n" +
                "9) Toys\n" +
                "10) Beauty\n" +
                "11) Office\n" +
                "12) Pets\n" +
                "X) Exit");
        System.out.print("Make a selection: ");
        String choice = scanner.nextLine().toUpperCase().strip();

        if (choice.equals("X")) {
            System.out.println("Goodbye!");
            // return exits the loop and the method, which in this case ends the application
        }
        else if (allDepartment.containsKey(choice)) {
            System.out.println();
            System.out.println("-".repeat(40));
            System.out.printf("Department Product for %s %n", allDepartment.get(choice));
            System.out.println("-".repeat(40));

            int index = 1;
            for (Product product : products) {
                if (product.getDepartment().equals(allDepartment.get(choice))) {
                    departmentProducts.add(product);
                    System.out.println(index + ") " + product.getProductName());
                    index++;
                }
            }

            System.out.println();
            System.out.println("Which product would you like to add to cart? ");
            System.out.print("Make a selection: ");
            int productChoice = scanner.nextInt();
            scanner.nextLine();

            return departmentProducts.get(productChoice - 1);
        }
        else {
            System.out.println("Invalid choice.");
        }
        return null;
    }


    public void displayProducts() {
        String sku = "SKU", productName = "Product Name", price = "Price", department = "Department";
        System.out.printf("%-10s %-30s %-15s %-20s%n", sku, productName, price, department);
        System.out.println("-".repeat(70));

        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            System.out.printf("%-10s %-30s $%-8.2f %-20s%n", product.getSku(), product.getProductName(), product.getPrice(), product.getDepartment());
        }
    }

    private void loadDepartments() {
        allDepartment.put("1", "Audio Video");
        allDepartment.put("2", "Computers");
        allDepartment.put("3", "Electronics");
        allDepartment.put("4", "Games");
        allDepartment.put("5", "Clothing");
        allDepartment.put("6", "Grocery");
        allDepartment.put("7", "Home");
        allDepartment.put("8", "Sports");
        allDepartment.put("9", "Toys");
        allDepartment.put("10", "Beauty");
        allDepartment.put("11", "Office");
        allDepartment.put("12", "Pets");
    }

    //creating array
    private void loadProducts() {
        // 1. create the new ArrayList

        // 2. populate the list
        // load all products from the "data/products.csv" file here
        try {
            FileReader fileReader = new FileReader("products.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            bufferedReader.readLine();
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] cols = line.split("\\|");

                String sku = cols[0];
                String productName = cols[1];
                Double price = Double.parseDouble(cols[2]);
                String department = cols[3];

                Product product = new Product(sku, productName, price, department);
                products.add(product);

                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Failed to read file. Exiting....");
            System.exit(0);
        } catch (IOException e) {
            System.out.println("Failed to load products. Exiting...");
            System.exit(0);
        }
    }

    public static Inventory createLoadedInventory() {
        Inventory inventory = new Inventory();
        inventory.loadProducts();
        inventory.loadDepartments();

        return inventory;
    }
}