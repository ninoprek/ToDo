
package view;

import controller.Controller;
import model.TaskDTO;
import model.TaskFieldValue;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

        printView.printInput("");

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
        String secondWord = command.getSecondWord();

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
                printView.showAllTasks(controller.showAllTasks());
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
                editTask();

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
        System.out.println();

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

    public void getPrintExit() {

        printView.printMessage("Thank for using ToDo app");
        printView.printMessage("-------------------------------");
    }


    private void newUserInput() {
        String userName;
        String collectionName;
        String title;
        boolean correctDateFormat = true;
        Date dueDate = new Date();
        DateFormat dateParser = new SimpleDateFormat("dd/mm/yyyy");

        userName = getUserInput("What is your name?");

        controller.createUser(userName);

        collectionName = getUserInput("What is the name of your ToDo list project?");

        controller.createTaskCollection(collectionName);

        title = getUserInput("What is your task?");


        printView.printMessage("What is the due date for the task?");

        while(correctDateFormat){
            printView.printInput("Correct date input format is dd/mm/yyyy");

            try {
                dueDate = dateParser.parse(reader.nextLine());
                correctDateFormat = false;
            } catch (ParseException e) {
                printView.printMessage("Date format is not correct.");
            }
        }

        TaskDTO taskDTO = new TaskDTO(title, dueDate);

        controller.createTask(taskDTO);
        printView.printMessage("User name, list name and your first task are saved.");
        printView.printMessage("!!!!!!!  Amazing  !!!!!!");
        printView.printMessage("-------------------------------");

    }

    private void newTaskInput() {

        String title;
        boolean correctDateFormat = true;
        Date dueDate = new Date();
        DateFormat dateParser = new SimpleDateFormat("dd/mm/yyyy");

        title = getUserInput("What is your task?");


        printView.printMessage("What is the due date for the task?");

        while(correctDateFormat){

            try {
                dueDate = dateParser.parse(getUserInput("Correct date input format is dd/mm/yyyy"));
                correctDateFormat = false;
            } catch (ParseException e) {
                printView.printMessage("Date format is not correct.");
            }
        }


        TaskDTO taskDTO = new TaskDTO(title, dueDate);

        controller.createTask(taskDTO);

        printView.printMessage("Your task is saved");
        printView.printMessage("-- What a joy! --");
        printView.printMessage("-------------------------------");
    }


    private void editTask() {

        String taskFieldToEdit = null;
        TaskFieldValue taskFieldValue = null;
        Integer taskNumber;
        String newStatus;
        boolean validFieldToEdit = false;
        boolean correctDateFormat = false;
        boolean correctStatus = false;
        DateFormat dateParser = new SimpleDateFormat("dd/mm/yyyy");

        taskNumber = Integer.parseInt(getUserInput("Which task number you want to edit?"));

        while (!validFieldToEdit) {

            taskFieldToEdit = getUserInput("Which task element do you want to edit?\nValid input: \n<Title>- Change the task title \n<Date> - Change task due date \n<Status> - Change completion status of the task");

            if(taskFieldToEdit.equals("Title") || taskFieldToEdit.equals("Date") || taskFieldToEdit.equals("Status")) {
                validFieldToEdit = true;
            } else {
                printView.printMessage("Invalid task element!");
            }
        }

        switch (taskFieldToEdit) {
            case "Title":
                taskFieldValue = new TaskFieldValue(getUserInput("What is the new title for the task?"));
                System.out.println("I received the taskFieldValue");
                break;
            case "Date":
                while (!correctDateFormat) {

                       try {
                           taskFieldValue = new TaskFieldValue(dateParser.parse(getUserInput("What is the new due date?\nCorrect date input format is dd/mm/yyyy")));
                           correctDateFormat = true;
                       } catch (ParseException e) {
                             printView.printMessage("Date format is not correct.");
                       }
                }
                break;

            case "Status":

                while (!correctStatus) {
                    newStatus = getUserInput("What is the new status?\nValid input is <finished> and <unfinished>.");

                    if (newStatus.equals("finished")) {
                        taskFieldValue = new TaskFieldValue(true);
                        correctStatus = true;
                    } else if (newStatus.equals("unfinished")) {
                        taskFieldValue = new TaskFieldValue(false);
                        correctStatus = true;
                    } else {
                        printView.printMessage("Invalid input for task status!");
                    }
                }
                break;
        }

        controller.editTask(taskFieldToEdit, taskFieldValue, taskNumber);
    }

    private String getUserInput (String messageToUser) {

        printView.printInput(messageToUser);

        String userInpur = reader.nextLine();
        printView.printMessage("");

        return userInpur;
    }

}
