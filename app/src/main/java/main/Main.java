package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import item.Item;
import shoppingList.ShoppingList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class Main {
    public static void main(String[] args) {
        Main mainInstance = new Main();
        Scanner console = new Scanner(System.in);
        ShoppingList mainShoppingList = new ShoppingList();
        String userMenuSelectionInput = "99";
        System.out.println("Welcome to the Shopping Cart Program!");
        while(!userMenuSelectionInput.equals("6")){
            System.out.println("Please select one of the following options: \n1. Add Item\n2. Print List\n3. Delete Item\n4. Save List\n5. Load List\n6. Quit");
            userMenuSelectionInput = console.nextLine();
            
            if(userMenuSelectionInput.equals("1")) //Add Item
            {
                System.out.println("What is the name of the item you would like to add?");
                String itemNameString = console.nextLine();
                System.out.println("What is the price of this item?");
                String itemPriceString = console.nextLine();
                System.out.println("How many would you like to add?");
                String itemAmountString = console.nextLine();
                Item userInputItem = new Item(itemNameString, Double.parseDouble(itemPriceString), Integer.parseInt(itemAmountString));                
                mainShoppingList.add(userInputItem);
                
            }
            else if(userMenuSelectionInput.equals("2")) //Print List
            {
                System.out.println(mainShoppingList.returnPrintString());
            }
            else if(userMenuSelectionInput.equals("3")) //Delete Item
            {
                System.out.println("Please enter the number of the item you would like to delete.");
                System.out.println(mainShoppingList.returnIndexString());
                String itemSelection = console.nextLine();
                mainShoppingList.deleteItem(itemSelection);
                
            }
            else if(userMenuSelectionInput.equals("4")) //Save List
            {
                System.out.println("Under what name would you like to save this list? (without .csv extension)");
                String listName = console.nextLine();
                mainInstance.saveFile(listName, mainShoppingList);
                
            }
            else if(userMenuSelectionInput.equals("5")) //Load List
            {
                System.out.println("What is the name of the list you would like to load? (without .csv extension)");
                System.out.println("Warning: This will overwrite your current selected list");
                String listName = console.nextLine();
                mainShoppingList = mainInstance.loadFile(listName);
            }
            else if(userMenuSelectionInput.equals("6")) //Quit
            {
                System.out.println("Thank You and Goodbye!");
            }
            else //Exception Handling
            {
                System.out.println("Sorry, that's not a valid input. Please try again!");
            }
        }
        console.close();
    }
    private void saveFile(String fileName, ShoppingList list)
    {
        try (FileWriter fileObject = new FileWriter(fileName + ".csv"))
        {
            fileObject.write(list.returnCSV());
            System.out.println("Successfully saved " + fileName + "!");
        }
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
    private ShoppingList loadFile(String fileName)
    {
        ShoppingList constructedList = new ShoppingList();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName + ".csv"))) {
            reader.readLine(); //Skip the first line
            String line;
            while ((line = reader.readLine()) != null) {
                Item constructedItem = new Item(line);
                constructedList.add(constructedItem);
            }
            System.out.println("Successfully loaded " + fileName + "!");
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return constructedList;
    }
}