package com.pluralsight;
import java.util.*;

public class CustomerCart {
    private Scanner scanner = new Scanner(System.in);
    private ArrayList<Product> shoppingCart = new ArrayList<>();

    static void displayProductsUi() {
        String productId = "Product ID", productName = "Product Name", price = "Price";
        System.out.println();
        System.out.println("-".repeat(70));
        System.out.printf("%-20s %-30s %-15s%n", productId, productName, price);
        System.out.println("-".repeat(70));
    }

    public void displayCartScreen() {
        System.out.println();
        System.out.println("-".repeat(70));
        System.out.println("Current Product(s) in Cart ");
        System.out.println("-".repeat(70));

        displayProductsUi();

        int index = 1;
        double totalCost = 0;
        for (Product product : shoppingCart) {
            System.out.printf("%-20d %-30s $%-15.2f %n", index, product.getProductName(), product.getPrice());
            index++;
            totalCost += product.getPrice();
        }

        System.out.println("-".repeat(70));
        System.out.printf("Subtotal: $%.2f%n", totalCost);      // display the total sales amount of the cart.

        System.out.println();
        System.out.println("What would you like to do next? ");
        System.out.println("C) Check Out");
        System.out.println("R) Remove Product from the Cart");
        System.out.println("X) Go Back to the Home Screen");
        System.out.print("Make a selection: ");
        String choice = scanner.nextLine().toUpperCase().strip();

        switch (choice) {
            case "C":
                break;
            case "R":
                removeFromCart();
                break;
            case "X":
                return;
            default:
                System.out.println("Invalid selection. Please try again.");
        }
    }

    public void addToCart(Product selectedProduct) {
        if(selectedProduct == null){
            System.out.println("No product selected.");
            return;
        }

        System.out.println("\n" + selectedProduct.getProductName() + " has been added to cart!");
        shoppingCart.add(selectedProduct);
    }

    public void removeFromCart() {
        if (shoppingCart.isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }

        while (true) {
            System.out.println("Please select the Product ID of the product to remove.");
            System.out.print("Make a selection: ");

            int productChoice = scanner.nextInt();
            scanner.nextLine();

            if (productChoice >= 1 && productChoice <= shoppingCart.size()) {
                Product selectedProduct = shoppingCart.remove(productChoice - 1);
                System.out.println();
                System.out.println(selectedProduct.getProductName() + " has been removed from cart!");
                displayCartScreen();
                return;
            }
            System.out.println("Selection out of range. Please enter a number between 1 and " + shoppingCart.size() + ".");

        }
    }
}