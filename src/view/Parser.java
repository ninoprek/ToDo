
package view;

import controller.Controller;

import java.util.Scanner;

/**
 * This class is responsible for scanning and interpreting user input. After it interprets the user input,
 * it calls appropriate methods in the controller.
 */

public class Parser {

    private CommandWords commands;
    private Controller controller;
    private PrintView printView;
    private Scanner reader;

    public Parser () {

        commands = new CommandWords();
        controller = new Controller();
        printView = new PrintView();
        reader = new Scanner(System.in);
    }

    /**
     * Reads user input and takes first two words in order to create a <code>Command</code> object.
     *
     * @return New command detected from user input.
     */

    public Command getCommand() {
        String inputLine;
        String word1 = null;
        String word2 = null;

        System.out.print("> ");

        inputLine = reader.nextLine();

        Scanner tokanizer = new Scanner(inputLine);

        if (tokanizer.hasNext()) {

            word1 = tokanizer.next();

            if (tokanizer.hasNext()) {

                word2 = tokanizer.next();
            }
        }

        return new Command(commands.getCommandWord(word1), word2);
    }

    /**
     * Analyses the <code>Command</code> received from the user and calls appropriate function.
     *
     * @param command - command inputted by the user.
     * @return If the <code>quit</code> command is detected returns <code>true</code>, if not returns <code>false<code/>.
     */

    public boolean processCommand(Command command) {

        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) {

            case UNKNOWN:
                printView.printUnknown();
                break;

            case HELP:
                printView.printHelp();
                commands.showAll();
                break;

            case NEW:
                printView.printNew();
                break;

            case EDIT:
                printView.printEdit();
                break;

            case REMOVE:
                printView.printRemove();
                break;

            case QUIT:
                wantToQuit = quit(command);
                break;
        }
        return wantToQuit;
    }

    /**
     * Shows all valid commands
     */
    public void showCommands() {

        System.out.println("Available commands are: ");

        commands.showAll();
    }

    /**
     * Performs <code>quit</code> command and quits the application
     *
     * @param command - receives <code>quit</code> command
     * @return false is there are two words and true if it is only <code>quit</code> command
     */

    private boolean quit(Command command) {

        if(command.hasSecondWord()) {
            printView.printQuitWhat();
            return false;
        } else {

            return true;
        }
    }

    /**
     * Prints out beginning message
     */

    public void getPrintStart() {

        printView.printStart();
    }

}
