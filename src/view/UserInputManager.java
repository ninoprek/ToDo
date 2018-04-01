
package view;

import controller.Controller;
import model.TaskDTO;
import model.TaskFieldValue;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * This class is responsible for scanning and interpreting user input. After it interprets the user input,
 * it calls appropriate methods in the controller.
 */

public class UserInputManager {

    public static final String DATE_FORMAT = "dd/mm/yyyy";

    private CommandWords commands;
    private Controller controller;
    private PrintView printView;
    private Scanner reader;

    public UserInputManager () {

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
                printView.printMessage("This input is unknown");
                showCommands();
                break;

            case HELP:
                printView.printMessage("This is help");
                showCommands();
                System.out.println();
                break;

            case NEW:

                if(secondWord == null) {
                    printView.printMessage("Create new user name, list and task\n");
                    newStart();
                    break;
                } else if (secondWord.equals("user")) {
                    printView.printMessage("Create new user\n");
                    createNewUser();
                    break;
                } else if (secondWord.equals("task")) {
                    printView.printMessage("Create new task\n");
                    newTask();
                    break;
                } else if (secondWord.equals("project")) {
                    printView.printMessage("Create new project\n");
                    createNewProject();
                    break;
                } else {
                    printView.printMessage("What do you want to create?!\nValid input is <new>, <new task> and <new project>");
                    break;
                }

            case CHANGE:

                if (secondWord != null) {

                    if (secondWord.equals("user")) {
                        changeUser();
                        break;
                    } else if (secondWord.equals("project")) {
                        changeProject();
                        break;
                    }

                } else {
                    printView.printMessage("What do you want to change?\nValid input is <change user> and <change project>");
                    break;
                }

            case TASKS:
                printView.showAllTasks(controller.showAllTasks());
                break;

            case USER:
                showCurrentUser();
                break;

            case USERS:
                printView.printMessage("All users");
                showAllUsers();
                break;

            case PROJECTS:
                printView.printMessage("All projects by " + controller.getCurrentUser().getUserName());
                printView.printMessage("---------------");
                showAllProjects();
                System.out.println("---------------");
                break;

            case EDIT:

                if (secondWord == null) {
                    printView.printMessage("What do you want to edit?");
                    printView.printMessage("Valid input is <edit task> and <edit project>");
                    break;

                } else if (secondWord.equals("task")) {
                    printView.printMessage("All tasks");
                    printView.showAllTasks(controller.showAllTasks());
                    editTask();
                    printView.printMessage("The task has been edited successfully!\n\nInput <tasks> to list all the tasks in project");
                    break;
                } else if (secondWord.equals("project")) {
                    printView.printMessage("Edit project name");
                    break;
                }else {
                    printView.printMessage("What do you want to edit?");
                    printView.printMessage("Valid input is <edit task> and <edit project>");
                    break;
                }

            case REMOVE:

                if (secondWord == null) {
                    printView.printMessage("What do you want to remove?\n\nValid input is <remove task>, <remove project> and <remove user>");
                    break;
                } else if (secondWord.equals("task")) {
                    printView.showAllTasks(controller.showAllTasks());
                    removeTask();
                    break;
                }

                   break;


            case LOAD:
                printView.printMessage("This is load");
                controller.loadUser("Giuseppe");
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

        printView.printMessage("Available commands are: \n");

        commands.showAll();
        printView.printMessage("");

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

    private void newStart() {

        newUser();

        newProject();

        newTask();
    }

    private void newUser () {

        String userName = getUserInput("What is your name?");

        controller.createUser(userName);
    }

    private void newProject () {

        String collectionName = getUserInput("What is the name of your new ToDo list project?");

        controller.createTaskCollection(collectionName);
    }

    private void newTask() {

        String title;
        boolean correctDateFormat = true;
        Date dueDate = new Date();
        DateFormat dateParser = new SimpleDateFormat(DATE_FORMAT);

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


        TaskDTO taskDTO = new TaskDTO(title, dueDate, false);

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
        DateFormat dateParser = new SimpleDateFormat(DATE_FORMAT);

        taskNumber = Integer.parseInt(getUserInput("Which task number you want to edit?")) - 1;

        while (!validFieldToEdit) {

            taskFieldToEdit = getUserInput("Which task element do you want to edit?\n\nValid input: \n\n<title>- Change the task title \n<date> - Change task due date \n<status> - Change completion status of the task");

            if(taskFieldToEdit.equals("title") || taskFieldToEdit.equals("date") || taskFieldToEdit.equals("status")) {
                validFieldToEdit = true;
            } else {
                printView.printMessage("Invalid task element!");
            }
        }

        switch (taskFieldToEdit) {
            case "title":
                taskFieldValue = new TaskFieldValue(getUserInput("What is the new title for the task?"));
                break;
            case "date":
                while (!correctDateFormat) {

                       try {
                           taskFieldValue = new TaskFieldValue(dateParser.parse(getUserInput("What is the new due date?\nCorrect date input format is dd/mm/yyyy")));
                           correctDateFormat = true;
                       } catch (ParseException e) {
                             printView.printMessage("Date format is not correct.");
                       }
                }
                break;

            case "status":

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

    private void createNewProject() {

        String newProjectName = getUserInput("What is the name of the new project?");
        boolean projectExists  = false;

        ArrayList<String> projectNames = controller.showAllProjects();

        for (String projectName : projectNames) {
            if (projectName.equals(newProjectName)) {
                projectExists = true;
                break;
            }
        }

        if (!projectExists) {
            controller.createTaskCollection(newProjectName);
            printView.printMessage("Project " + newProjectName + " has been created.");
        } else {
            printView.printMessage("The project already exists!");
        }
    }

    private void createNewUser () {

        String newUserName = getUserInput("What is the new user's name?");
        boolean userExists = false;

        ArrayList<String> userNames = controller.showAllUsers();

        for (String userName : userNames) {
            if (userName.equals(newUserName)) {
                userExists = true;
                break;
            }
        }

        if (!userExists) {
            controller.createUser(newUserName);
            printView.printMessage("User " + newUserName + " is created.");
        }
    }
    private void removeTask () {

        int taskToRemove = Integer.parseInt(getUserInput("Which task do you want to remove?\n")) - 1;

        if (taskToRemove > 0 && taskToRemove < controller.showAllTasks().getTaskCollection().size()) {

              boolean readyToRemove = false;

              while (!readyToRemove) {

                  String removeAnswer = getUserInput("Are you sure you want to remove this task?\n\n<y> or <n>");

                  if (removeAnswer.equals("y")) {
                      controller.removeTask(taskToRemove);
                      printView.printMessage("Task is removed successfully.");
                      readyToRemove = true;

                  } else if (removeAnswer.equals("n")) {
                      printView.printMessage("Task has not been removed!");
                      readyToRemove = true;
                  } else {
                      printView.printMessage("The your answer is not recognized.\n");
                  }
              }                                    

        } else {
            printView.printMessage("This task doesn't exist!");
        }
    }

    private void showCurrentUser () {

        printView.printMessage("Current user is: " + controller.getCurrentUser().getUserName());
    }

    private void showAllUsers () {

        printView.showAllUsers(controller.showAllUsers());
    }

    private void showAllProjects () {

        ArrayList<String> projectNames = controller.showAllProjects();

        printView.showAllProjects(projectNames);

    }

    private void changeProject() {

        boolean correctProjectNumber = false;

        while (!correctProjectNumber) {
            showAllProjects();

            int projectNumber = Integer.parseInt(getUserInput("To which project number do you want to change to?"));

            if (projectNumber > 0 && projectNumber <= controller.showAllProjects().size()) {
                correctProjectNumber = true;
                controller.changeProject(projectNumber - 1);
            } else {
                printView.printMessage("This project number doesn't exist.");
            }
        }
    }

    private void changeUser() {

        boolean correctUserNumber = false;

        while (!correctUserNumber) {

            showAllUsers();
            int userNumber = Integer.parseInt(getUserInput("To which user number do you want to change to?"));


            if (userNumber > 0 && userNumber <= controller.showAllUsers().size()) {
                correctUserNumber = true;
                controller.changeUser(userNumber - 1);
            } else {
                printView.printMessage("This user doesn't exist.");
            }
        }
    }


    private String getUserInput (String messageToUser) {

        printView.printInput(messageToUser);

        String userInput = reader.nextLine();
        printView.printMessage("");

        return userInput;
    }



}
