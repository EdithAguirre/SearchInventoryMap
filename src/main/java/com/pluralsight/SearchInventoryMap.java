/*
Manage the inventory with a Map instead of with an ArrayList
 */
package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class SearchInventoryMap {
    // The key is the product name, the value is a product object
    static HashMap<String, Product> inventory = new HashMap<String, Product>();
    public static void main(String[] args) {
        // This method loads product objects into inventory
        loadInventory(inventory);

        // Create scanner to receive user input
        Scanner scanner = new Scanner(System.in);

        // Prompt user to search product they are interested in by typing the product name
        System.out.print("What item are you interested in? (Type in the product name): ");
        String productName = scanner.nextLine().trim();

        // Check to see if the item they entered is in the inventory
        Product matchedProduct = inventory.get(productName);
        if (matchedProduct == null) {
            System.out.println("We don't carry that product");
            return;
        }
        System.out.printf("We carry %s and the price is $%.2f",
                matchedProduct.getName(), matchedProduct.getPrice());
    }

    //  Method to load all the products from the Inventory.csv file to HashMap
    private static void loadInventory(HashMap<String, Product> inventory) {
        try {
            // Create a bufferedReader to read from the Inventory file
            BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/Inventory.csv"));

            // Read the file a line at a time
            String input;
            while((input = bufferedReader.readLine()) != null) {

                //  Split the string where you find the pipe ( | ) character
                String[] tokens = input.split("\\|");

                // Create a product from each line and add it to a Map.
                // Use product name as map key so that users can search for products by name.
                inventory.put(tokens[1], new Product(Integer.parseInt(tokens[0]),tokens[1],Float.parseFloat(tokens[2])));
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}