package view;

import java.util.HashMap;

/**
 * This class contains list of valid methods
 */

public class CommandWords {

    private HashMap<String, CommandWord> validCommands;

    public CommandWords() {

        validCommands = new HashMap<>();
        for (CommandWord command : CommandWord.values()) {

            if(command != CommandWord.UNKNOWN) {
                validCommands.put(command.toString(), command);
            }
        }
    }

    /**
     * Gets the the command that user has inputted from the list of valid commands.
     * @param commandWord - <code>commandWord</code> user inputted command
     * @return valid command or <code>UNKNOWN</code> if there is no valid command related to user input
     */

    public CommandWord getCommandWord (String commandWord) {

        CommandWord command = validCommands.get(commandWord);

        if (command != null) {

            return command;

        } else {
            return CommandWord.UNKNOWN;
        }
    }

    /**
     * Checks if the command is valid
     * @param aString string that represents potentially valid command
     * @return <code>true</code> if command is valid, <code>false</code> it it is not.
     */

    public boolean isCommand(String aString) {

        return validCommands.containsKey(aString);
    }

    /**
     * Shows all valid commands
     */

    public void showAll() {

        for(String command: validCommands.keySet()) {

            System.out.print(command + "  ");
        }
        System.out.println();
    }
}
