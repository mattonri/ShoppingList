package item;

public class Item <name, price>{
    
    private String name;
    private double price;
    private int amount = 1;

    public Item(String name, double price)
    {
        this.name = name;
        this.price = price;
    }
    public Item(String name, double price, int amount)
    {
        this.name = name;
        this.price = price;
        this.amount = amount;
    }
    public Item(String CSVString) // This allows you to make it directly from a CSV string for the purpose of loading
    {
        String[] stringParts = CSVString.split(",");
        this.name = CSVtoString(stringParts[0].trim().replace("\"", ""));
        this.price = Double.parseDouble(stringParts[1].trim().replace("\"", ""));
        if (stringParts.length == 3) 
        {
            this.amount = Integer.parseInt(stringParts[2].trim().replace("\"", ""));
        }   
    }
    private String CSVtoString(String inputString)
    {
        return inputString.replace("||", "\"").replace("__", ",");
    }
    private String stringtoCSV(String inputString)
    {
        return inputString.replace("\"", "||").replace(",", "__");
    }
    public Double returnCost()
    {
        double cost = price * amount;
        return cost;
    }

    public String returnString()
    {
        return String.format("%d: %s @ $%.2f",amount, name, price);
    }
    public String returnStringWithoutAmount() // used for deleting items, so the amount doesn't get mixed up with the index of the list.
    {
        return String.format("%s @ $%.2f", name, price);
    }
    public String returnCSV()
    {
        String modifiedName = stringtoCSV(name);
        return String.format("\"%s\", \"%.2f\", \"%d\"", modifiedName, price, amount);
    }
}
