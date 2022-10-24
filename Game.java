import java.util.Stack;
import java.util.ArrayList;
/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @author Colin Francis
 * @version 2022.10.23
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    Stack<Room> stack = new Stack<Room>();
    int maxNumberOfMoves = 30;
    int currentNumberOfMoves = 0;
    ArrayList<Item> inventory = new ArrayList<Item>();
    double currentWeight = 0;
    double maxWeight = 1.2;
    
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room outside, theater, pub, computerLab, office, gym, eastEntranceGate,
            westEntranceGate, northEntranceGate, pool, scienceLab, mathRoom,
            helpDesk, testingCenter, studyHall, cafeteria, researchRoom,
            trapRoom;
      
            
        ArrayList<Item> gymArray = new ArrayList<Item>(); 
        gymArray.add(0, new Item("basketball", 1));
        
        ArrayList<Item> cafeteriaArray = new ArrayList<Item>();
        cafeteriaArray.add(0, new Item("fries", 0.5));
        
        ArrayList<Item> helpDeskArray = new ArrayList<Item>();
        helpDeskArray.add(0, new Item("keys", 0.2));
        
        ArrayList<Item> testingCenterArray = new ArrayList<Item>();
        testingCenterArray.add(0, new Item("pencil", 0.1));
        testingCenterArray.add(1, new Item("paper", 0.15));
        
        ArrayList<Item> outsideArray = new ArrayList<Item>();
        ArrayList<Item> theaterArray = new ArrayList<Item>();
        ArrayList<Item> pubArray = new ArrayList<Item>();
        ArrayList<Item> computerLabArray = new ArrayList<Item>();
        ArrayList<Item> officeArray = new ArrayList<Item>();
        ArrayList<Item> eastEntranceGateArray = new ArrayList<Item>();
        ArrayList<Item> westEntranceGateArray = new ArrayList<Item>();
        ArrayList<Item> northEntranceGateArray = new ArrayList<Item>();
        ArrayList<Item> poolArray = new ArrayList<Item>();
        ArrayList<Item> scienceLabArray = new ArrayList<Item>();
        ArrayList<Item> mathRoomArray = new ArrayList<Item>();
        ArrayList<Item> studyHallArray = new ArrayList<Item>();
        ArrayList<Item> researchRoomArray = new ArrayList<Item>();
        ArrayList<Item> trapRoomArray = new ArrayList<Item>();
        
        // create the rooms
        outside = new Room("outside the main entrance of the university", outsideArray);
        theater = new Room("in a lecture theater", theaterArray);
        pub = new Room("in the campus pub", pubArray);
        computerLab = new Room("in a computer lab", computerLabArray);
        office = new Room("in the computing admin office", officeArray);
        gym = new Room("at the campus gym", gymArray);
        eastEntranceGate = new Room("at the east entrance gate", eastEntranceGateArray);
        westEntranceGate = new Room("at the west entrance gate", westEntranceGateArray);
        northEntranceGate = new Room("at the north entrance gate", northEntranceGateArray);
        pool = new Room("at the campus pool", poolArray);
        scienceLab = new Room("in a science lab", scienceLabArray);
        mathRoom = new Room("in a math classroom", mathRoomArray);
        helpDesk = new Room("at the campus help desk", helpDeskArray);
        testingCenter = new Room("at the testing center", testingCenterArray);
        studyHall = new Room("at the study hall", testingCenterArray);
        cafeteria = new Room("at the cafeteria", cafeteriaArray);
        researchRoom = new Room("in a research room", researchRoomArray);
        trapRoom = new Room("now trapped in a dark room without an exit \n"
                            + "after falling through a trapped door", trapRoomArray);
        
                
        // initialise room exits
        
        //1,1
        outside.setExit("east", theater); 
        outside.setExit("south", computerLab); 
        outside.setExit("west", pub);
        outside.setExit("north", northEntranceGate);
        outside.setExit("northeast", eastEntranceGate);
        outside.setExit("northwest", westEntranceGate);
        
        //0,0
        westEntranceGate.setExit("east", northEntranceGate);
        westEntranceGate.setExit("southeast", outside);
        
        //0,2
        eastEntranceGate.setExit("west", northEntranceGate);
        eastEntranceGate.setExit("southwest", outside);
        
        //0,1
        northEntranceGate.setExit("east", eastEntranceGate);
        northEntranceGate.setExit("west", westEntranceGate);
        northEntranceGate.setExit("south", outside);
        
        //2,0
        gym.setExit("north", pub);
        gym.setExit("east", computerLab);
        gym.setExit("south", pool);

        //1,2
        theater.setExit("west", outside);
        theater.setExit("south", office);
        theater.setExit("down", trapRoom);
        //1,0
        pub.setExit("east", outside);
        pub.setExit("south", gym);
        
        //2,1
        computerLab.setExit("north", outside);
        computerLab.setExit("east", office);
        computerLab.setExit("west", gym);
        computerLab.setExit("south", cafeteria);

        //2,2
        office.setExit("west", computerLab);
        office.setExit("north", theater);
        office.setExit("south", mathRoom);
        
        //3,0
        pool.setExit("north", gym);
        pool.setExit("east", cafeteria);
        pool.setExit("south", helpDesk);
        
        //3,1
        cafeteria.setExit("north", computerLab);
        cafeteria.setExit("west", pool);
        cafeteria.setExit("east", mathRoom);
        cafeteria.setExit("south", studyHall);
        
        //3,2
        mathRoom.setExit("west", cafeteria);
        mathRoom.setExit("north", office);
        mathRoom.setExit("south", testingCenter);
        
        //4,0
        helpDesk.setExit("north", pool);
        helpDesk.setExit("east", studyHall);
        
        //4,1
        studyHall.setExit("west", helpDesk);
        studyHall.setExit("north", cafeteria);
        studyHall.setExit("east", testingCenter);
        studyHall.setExit("south", scienceLab);
        
        //4,2
        testingCenter.setExit("west", studyHall);
        testingCenter.setExit("north", mathRoom);
        
        //5,1
        scienceLab.setExit("north", studyHall);
        scienceLab.setExit("south", researchRoom);
        
        //6,1
        researchRoom.setExit("north", scienceLab);
        
        
        
        currentRoom = outside;  // start game outside
    }
    
    /**
     * The main method.
     */
    public static void main(String[] args){
        Game playGame = new Game();
        playGame.play();
    }
    
    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {           
        currentNumberOfMoves = 0;
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
            if(currentNumberOfMoves >= maxNumberOfMoves)
            {
                System.out.println("\nYou have starved to death, and your corpse" +
                    " is\n" + currentRoom.getShortDescription());
                finished = true;
            }
        }
        System.out.println("\nThank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to Colin's version of the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("\nType '" + CommandWord.HELP + "' if you need help.");
        System.out.println("\nYou can only move between " + maxNumberOfMoves + " rooms before");
        System.out.println("you starve to death. Try to find a key to get out.\n" +
            "You can only carry 1.2 pounds of items at a time.\n");
        System.out.println(currentRoom.getLongDescription(currentWeight));
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) {
            case UNKNOWN:
                System.out.println("I don't know what you mean...");
                break;

            case HELP:
                printHelp();
                break;

            case GO:
                goRoom(command);
                break;
                
            case LOOK:
                look();
                break;

            case QUIT:
                wantToQuit = quit(command);
                break;
                
            case EAT:
                eat();
                break;
                
            case BACK:
                back(command);
                break;
                
            case TAKE:
                take(command.getSecondWord());
                break;
                
            case DROP:
                drop(command.getSecondWord());
                break;
                
                
        }
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println("\nYou can only move between " + maxNumberOfMoves + " rooms");
        System.out.println("before you starve to death. Try to find a key and door to get out");
        System.out.println("\nYour command words are:");
        parser.showCommands();
    }

    /** 
     * Try to go in one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        
        else {
            currentNumberOfMoves++;
            stack.push(currentRoom);
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription(currentWeight));
        }
    }
    
    /**
     * The look method shows you what room you are in, and what your room's exits are.
     */
    private void look()
    {
        System.out.println(currentRoom.getLongDescription(currentWeight));
    }
    
    /**
     * The eat method lets you eat some fake food and pretends that you arent hungry.
     */
    private void eat()
    {
        System.out.println("You have eaten now and you are not hungry any more.");
    }
    
    /**
     * The back method brings the player back to where they were last move,
     * or more depending on the parameter.
     * @param command  The command that the player puts in, used to detect whether there is
     *   a second phrase after the "back" command, and if there is and its an int, go back
     *   that many moves.
     */
    private void back(Command command)
    {
        if(command.secondWordIsInt())
        {
            backMultipleRooms(command.getSecondWordAsInt());
        }
        else if(!stack.isEmpty())
        {
            currentRoom = stack.pop();
            currentNumberOfMoves++;
            System.out.println(currentRoom.getLongDescription(currentWeight));
        }
        else
        {
            System.out.println("You can't go back any further!");
        }
    }
    
    /**
     * The backMultipleRooms method takes the player back the inputted amount of rooms.
     * @param times  the number of rooms to be retracing through.
     */
    private void backMultipleRooms(int times)
    {
        if(stack.isEmpty())
        {
            System.out.println("You can't go back any further!");
        }
        else
        {
            for(int i = 0; i < times && !stack.isEmpty(); i++)
            {
                currentRoom = stack.pop();//stack of rooms to trace through
                currentNumberOfMoves++;
            }
            System.out.println(currentRoom.getLongDescription(currentWeight));
        }
    }
    
    /**
     * Picks up an item to put into inventory
     */
    private void take(String input)
    {

        for(int i = 0; i < currentRoom.getItemsInRoom().size(); i++)
        {
            if(currentRoom.getItemsInRoom().get(i).getName().equals(input))
            {
                if(currentRoom.getItemsInRoom().get(i).getWeight() + currentWeight > maxWeight)
                {
                    System.out.println("Picking that up would exceed" +
                        " the weight limit(1.2 pounds)!");
                }
                else
                {
                    currentWeight += currentRoom.getItemsInRoom().get(i).getWeight();
                    System.out.println(input + " was taken by you" +
                        "and is now\nin your inventory.");
                    inventory.add(currentRoom.getItemInRoom(i));
                }
            }
        }
    }
    
    /**
     * Drops an item from inventory
     */
    private void drop(String input)
    {
        for(int i = 0; i < inventory.size(); i++)
        {
            if(inventory.get(i).getName().equals(input))
            {
                currentWeight -= inventory.get(i).getWeight();
                System.out.println(input + " was dropped by you and is now\non the floor " +
                currentRoom.getShortDescription());
                currentRoom.setItemInRoom(inventory.get(i));
                inventory.remove(inventory.get(i));
            }
        }
    }
    
    
    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
