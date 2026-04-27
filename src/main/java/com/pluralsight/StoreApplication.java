package com.pluralsight;
import java.io.*;
import java.util.*;

public class StoreApplication {
    static Scanner scanner = new Scanner(System.in);
    private static Inventory inventory;
    private static CustomerCart customerCart;

    public static void main(String[] args) {
        // make sure all products are loaded before we display the home screen
          inventory = Inventory.createLoadedInventory();
          customerCart = new CustomerCart();
          inventory.displayProducts();
        // start the application
        displayHomeScreen();
    }

    static void displayHomeScreen() {
        while (true) {
            System.out.println();
            System.out.println("---------------------------------");
            System.out.println("Welcome to my Store!");
            System.out.println("---------------------------------");
            System.out.println("D) Display Products");
            System.out.println("C) Display Cart");
            System.out.println("X) Exit");
            System.out.print("Make a selection: ");
            String choice = scanner.nextLine().toUpperCase().strip();
            System.out.println();

            switch (choice) {
                case "D":
                    searchScreen();
                    break;
                case "C":
                    customerCart.displayCartScreen();
                    break;
                case "X":
                    System.out.println("Goodbye!");
                    // return exits the loop and the method, which in this case ends the application
                    return;
                default:
                    System.out.println("Invalid selection. Please try again.");
            }
        }
    }

    public static void searchScreen() {
        System.out.println("Would you like to search for a product or filter through the list of products?\n" +
                "S) Search Products\n" +
                "F) Filter List of Products\n" +
                "X) Go Back\n");
        String choice = scanner.nextLine().toUpperCase().strip();

        switch (choice) {
            case "S":
                customerCart.addToCart(inventory.searchProductOptionMenu());
                break;
            case "F":
                customerCart.addToCart(inventory.searchProductFromDepartment());
                break;
            case "X":
                return;
            default:
                System.out.println("Invalid selection. Please try again.");
        }
    }
}