/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * This class holds information about a command that was issued by the user.
 * A command currently consists of two parts: a CommandWord and a string
 * (for example, if the command was "take map", then the two parts
 * are TAKE and "map").
 * 
 * The way this is used is: Commands are already checked for being valid
 * command words. If the user entered an invalid command (a word that is not
 * known) then the CommandWord is UNKNOWN.
 *
 * If the command had only one word, then the second word is <null>.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @author Colin Francis
 * @version 2022.10.23
 */

public class Command
{
    private CommandWord commandWord;
    private String secondWord;
    private int secondWordAsInt;
    /**
     * Create a command object. First and second words must be supplied, but
     * the second may be null.
     * @param commandWord The CommandWord. UNKNOWN if the command word
     *                  was not recognised.
     * @param secondWord The second word of the command. May be null.
     */
    public Command(CommandWord commandWord, String secondWord)
    {
        this.commandWord = commandWord;
        this.secondWord = secondWord;
    }
    

    /**
     * Return the command word (the first word) of this command.
     * @return The command word.
     */
    public CommandWord getCommandWord()
    {
        return commandWord;
    }

    /**
     * @return The second word of this command. Returns null if there was no
     * second word.
     */
    public String getSecondWord()
    {
        return secondWord;
    }
    
    /**
     * This method returns the second phrase of the command as an int.
     * @return the second phrase of the command, as an int.
     */
    public int getSecondWordAsInt()
    {
        return Integer.parseInt(secondWord);
    }
    
    /**
     * This method returns true if the second phrase in the command can be turned into an int.
     * @return boolean determining if the second phrase can be turned into an int.
     */
    public boolean secondWordIsInt()
    {
        try
        {
            Integer.parseInt(secondWord);
            return true;
        }
        catch (NumberFormatException e)
        {
            return false;
        }

    }

    /**
     * @return true if this command was not understood.
     */
    public boolean isUnknown()
    {
        return (commandWord == CommandWord.UNKNOWN);
    }

    /**
     * @return true if the command has a second word.
     */
    public boolean hasSecondWord()
    {
        return (secondWord != null);
    }
    
}

