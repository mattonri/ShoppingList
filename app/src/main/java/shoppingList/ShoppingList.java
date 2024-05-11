package shoppingList;

import item.Item;
import java.util.ArrayList;
import java.util.List;

public class ShoppingList {
    private List<Item> shopping;
    private String listName;
    
    public ShoppingList(Item... ConstructorItemsList)
    {
        shopping = new ArrayList<Item>();
        for (Item selectedItem : ConstructorItemsList)
        {
            shopping.add(selectedItem);
        }
    }
    public ShoppingList()
    {
        shopping = new ArrayList<Item>();
    }

    public void add(Item item)
    {
        shopping.add(item);
    }

    public String returnCSV()
    {
        String resultString = "\"name\",\"price\",\"amount\"";
        for (Item item : shopping)
        {
            resultString = resultString.concat("\n" + item.returnCSV());
        }
        return resultString;
    }
    public String returnPrintString()
    {
        String resultString = "Amount: Item @ $$$";
        double totalCost = 0;
        for (Item item : shopping)
        {
            resultString = resultString.concat("\n").concat(item.returnString());
            totalCost += item.returnCost();
        }
        resultString = resultString.concat(String.format("\nTotal Cost: $%.2f", totalCost));
        return resultString;
    }
    public String returnIndexString()
    {
        String resultString = "index: Item @ $$$";
        int i = 1;
        for (Item item : shopping)
        {
            resultString = resultString.concat("\n" + Integer.toString(i) + ": " + item.returnStringWithoutAmount());
            i++;
        }
        return resultString;
    }
    public void deleteItem(String selectedItemIndex)
    {
        try {
            int intIndex = Integer.parseInt(selectedItemIndex);
            shopping.remove(intIndex - 1);
            
            System.out.println("Item deleted successfully.");
        } catch (NumberFormatException e) {
            System.err.println("Error: Invalid index format. Please provide a valid integer index.");
        } catch (IllegalArgumentException e) {
            System.err.println("Error: Invalid index. The number you selected is outside the bounds of the list.");
        }
    }
}
