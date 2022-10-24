
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

}
