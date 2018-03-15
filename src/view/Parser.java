
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
                printView.printMessage("This is unknown");
                break;

            case HELP:
                printView.printMessage("This is help");
                commands.showAll();
                System.out.println();
                break;

            case NEW:
                printView.printMessage("Create new user name, list and task");
                newUserInput();
                break;

            case TASK:
                printView.printMessage("Create new task");
                newTaskInput();
                break;

            case TASKS:
                printView.printMessage("All tasks");
                printView.printMessage("---------------");
                controller.showAllTasks();
                System.out.println("---------------");
                break;

            case USERS:
                printView.printMessage("All users");
                printView.printMessage("---------------");
                controller.showAllUsers();
                System.out.println("---------------");
                break;

            case EDIT:
                printView.printMessage("This is edit");
                break;

            case REMOVE:
                printView.printMessage("This is remove");
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

        printView.printMessage("Available commands are: ");
        printView.printMessage("");

        commands.showAll();
        System.out.println();

    }

    /**
     * Performs <code>quit</code> command and quits the application
     *
     * @param command - receives <code>quit</code> command
     * @return false is there are two words and true if it is only <code>quit</code> command
     */

    private boolean quit(Command command) {

        if(command.hasSecondWord()) {
            printView.printMessage("You want to quit what?!");
            return false;
        } else {

            return true;
        }
    }

    /**
     * Prints out beginning message
     */

    public void getPrintStart() {

        printView.printMessage("Welcome to the coolest ToDo app");
        printView.printMessage("-------------------------------");
    }


    private void newUserInput() {
        String userName;
        String collectionName;
        String title;
        String project;

        printView.printInput("What is your name?");


        userName = reader.nextLine();

        controller.createUser(userName);

        printView.printInput("What is the name of your ToDo list?");

        collectionName = reader.nextLine();

        controller.createTaskCollection(collectionName);

        printView.printInput("What is your task?");

        title = reader.nextLine();

        printView.printInput("To which project does it belong?");

        project = reader.nextLine();

        controller.createTask(title, project);
        printView.printMessage("User name, list name and your first task are saved.");
        printView.printMessage("!!!!!!!  Amazing  !!!!!!");
        printView.printMessage("-------------------------------");

    }

    private void newTaskInput() {
        String title;
        String project;


        printView.printInput("What is you're task?");

        title = reader.nextLine();

        System.out.println();
        printView.printInput("To which project does it belong?");

        project = reader.nextLine();

        printView.printMessage("Your task is saved");
        printView.printMessage("-- What a joy! --");
        printView.printMessage("-------------------------------");

        controller.createTask(title, project);


    }

}
