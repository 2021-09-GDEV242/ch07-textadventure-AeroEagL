import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;
/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @author Colin Francis
 * @version 2022.10.23
 */

public class Room 
{
    private String description;
    private HashMap<String, Room> exits;        // stores exits of this room.
    ArrayList<Item> itemsInRoom = new ArrayList<Item>();
    Item itemInRoom = new Item("default", 1);
    /**
     * Create a room described "description", and an Item array in it "itemsInRoom"
     * Initially, it has no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     * @param itemsInRoom arraylist of all of the items in a room that are not
     * in the player's inventory
     */
    public Room(String description, ArrayList<Item> itemsInRoom) 
    {
        this.description = description;
        this.itemsInRoom = itemsInRoom;
        exits = new HashMap<>();
    }
    
    /**
     * This method returns an arraylist of the items in a room
     * @return all of the items in the current room.
     */
    public ArrayList<Item> getItemsInRoom()
    {
        return itemsInRoom;
    }
    
    /**
     * This method puts an item in the current room, and takes it out of the player's inventory.
     * @param item  item to be put into the room, and takes it out of the player's inventory.
     */
    public void setItemInRoom(Item item)
    {
        itemsInRoom.add(item);
    }
    
    /**
     * This method removes an item form the room to be put in the player's inventory
     * @return the item to be taken from the room.
     */
    public Item getItemInRoom(int index)
    {
        
        Item tempItem = itemsInRoom.get(index);
        itemsInRoom.remove(index);
        return tempItem;
        
    }

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Return a description of the room in the form:
     *     You are in the kitchen.
     *     You are holding 1.0 pounds of weight.
     *     Exits: north west
     * @return A long description of this room
     */
    public String getLongDescription(double weight)
    {
        
        if(itemsInRoom.size() == 0)
            return "You are " + description + ".\nYou are holding " + weight + 
            " pounds of weight.\n" + getExitString();
        else
        {
            String tempString = "You are " + description + ".\nYou are holding " + weight + 
            " pounds of weight.\nItems in the room:";
            
            for(Item item : itemsInRoom)
            {
                 tempString += (item.getName() + " ");
            }
            tempString += ("\n" + getExitString());
            return tempString;
        }
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     */
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
}

