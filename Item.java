import java.util.ArrayList;
/**
 * Write a description of class Item here.
 *
 * @author Colin Francis
 * @version 2022.10.23
 */
public class Item
{
    // instance variables - replace the example below with your own
    private String name;
    private double weight;

    /**
     * Constructor for objects of class Item
     */
    public Item(String name, double weight)
    {
        this.name = name;
        this.weight = weight;
    }
    
    /**
     * This method returns the weight of an item
     * @return name of the item
     */
    public double getWeight()
    {
        return weight;
    }
    
    /**
     * This method returns the name of an item
     * @return name of the item
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * The consumeKey method returns a boolean based on if there is a key in the array
     * @param items the array list to check if there is a key in
     * @return hasKey the boolean based on if there is a key in the item array
     */
    public boolean consumeKey(ArrayList<Item> items)
    {
        boolean hasKey = false;
        
        for(int i = 0; i < items.size(); i++)
        {
            if(items.get(i).getName().equals("keys"))
            {
                hasKey = true;
            }
        }
        return hasKey;
    }
    
    /**
     * The consumeFries method returns a boolean based on if there are fries in the array
     * @param items the array list to check if there are fries in
     * @return hasFries the boolean based on if there are fries in the item array
     */
    public boolean consumeFries(ArrayList<Item> items)
    {
        boolean hasFries = false;
        
        for(int i = 0; i < items.size(); i++)
        {
            if(items.get(i).getName().equals("fries"))
            {
                System.out.println("You have consumed fries, and can now carry double"+
                    " the previous weight and traverse double the amount of rooms!");
                hasFries = true;
                items.remove(items.get(i));
            }
        }
        return hasFries;
    }

}
