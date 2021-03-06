
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

                if (secondWord == null){
                    printView.showAllTasks(controller.showAllTasks());
                    break;
                } else if (secondWord.equals("finished")) {

                    printView.showAllTasks(controller.showAllUnFinishedTasks(true));
                    break;
                } else if (secondWord.equals("unfinished")) {

                    printView.showAllTasks(controller.showAllUnFinishedTasks(false));
                    break;
                } else {
                    printView.printMessage("Invalid command.\nValid input is <task>, <task finished> of <task unfinished>");
                    break;
                }

            case USER:
                showCurrentUser();
                break;

            case USERS:
                showAllUsers();
                break;

            case PROJECT:
                showCurrentProject();
                break;

            case PROJECTS:
                showAllProjects();
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

                if (secondWord == null || (!secondWord.equals("task") && !secondWord.equals("project")) ) {
                    printView.printMessage("What do you want to remove?\n\nValid input is <remove task> and <remove project>");
                    break;
                } else if (secondWord.equals("task")) {
                    printView.showAllTasks(controller.showAllTasks());
                    removeTask();
                    break;
                } else if (secondWord.equals("project")) {
                    showAllProjects();
                    removeProject();
                    break;
                }

            case LOAD:
                loadUser();
                break;

            case SAVE:

                saveUser();
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
        printView.printMessage("-------------------------------");
        printView.printMessage("Welcome to the coolest ToDo app");
        printView.printMessage(" ,--.--------.   _,.---._                   _,.---._     \n" +
                "/==/,  -   , -\\,-.' , -  `.   _,..---._   ,-.' , -  `.   \n" +
                "\\==\\.-.  - ,-./==/_,  ,  - \\/==/,   -  \\ /==/_,  ,  - \\  \n" +
                " `--`\\==\\- \\ |==|   .=.     |==|   _   _\\==|   .=.     | \n" +
                "      \\==\\_ \\|==|_ : ;=:  - |==|  .=.   |==|_ : ;=:  - | \n" +
                "      |==|- ||==| , '='     |==|,|   | -|==| , '='     | \n" +
                "      |==|, | \\==\\ -    ,_ /|==|  '='   /\\==\\ -    ,_ /  \n" +
                "      /==/ -/  '.='. -   .' |==|-,   _`/  '.='. -   .'   \n" +
                "      `--`--`    `--`--''   `-.`.____.'     `--`--''  ");
        printView.printMessage("-------------------------------");
    }

    public void getPrintExit() {
        printView.printMessage("-------------------------------");
        printView.printMessage("Thanks for using");
        printView.printMessage(" ,--.--------.   _,.---._                   _,.---._     \n" +
                "/==/,  -   , -\\,-.' , -  `.   _,..---._   ,-.' , -  `.   \n" +
                "\\==\\.-.  - ,-./==/_,  ,  - \\/==/,   -  \\ /==/_,  ,  - \\  \n" +
                " `--`\\==\\- \\ |==|   .=.     |==|   _   _\\==|   .=.     | \n" +
                "      \\==\\_ \\|==|_ : ;=:  - |==|  .=.   |==|_ : ;=:  - | \n" +
                "      |==|- ||==| , '='     |==|,|   | -|==| , '='     | \n" +
                "      |==|, | \\==\\ -    ,_ /|==|  '='   /\\==\\ -    ,_ /  \n" +
                "      /==/ -/  '.='. -   .' |==|-,   _`/  '.='. -   .'   \n" +
                "      `--`--`    `--`--''   `-.`.____.'     `--`--''  ");
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

    private void loadUser () {

        boolean userExists = false;
        int userNumber = 0;
        ArrayList<String> availableUsers = controller.listAllUsers("users/");

        if (availableUsers.size() > 0) {
            while (!userExists) {

                printView.showAllLoadUsers(availableUsers);

                userNumber = Integer.parseInt(getUserInput("Which user do you want to load?\n\nEnter user number.")) - 1;

                if (userNumber < 0 || userNumber > availableUsers.size() - 1) {

                    printView.printMessage("This user doesn't exist.\n");
                } else {
                    controller.loadUser("users/" + availableUsers.get(userNumber));
                    printView.printMessage("User " + availableUsers.get(userNumber) + " is loaded.");
                    userExists = true;
                }
            }


        } else {
            printView.printMessage("There are no users to load!\nCreate a new user by entering <new> or <new user>");
        }

    }

    private void saveUser () {

        boolean userExists = false;
        int userNumber = 0;
        ArrayList<String> availableUsers = controller.showAllUsers();

        if (availableUsers.size() > 0) {

            while (!userExists) {

                showAllUsers();
                userNumber = Integer.parseInt(getUserInput("Which user do you want to save?")) - 1;


                if (userNumber < 0 || userNumber > availableUsers.size() - 1) {

                    printView.printMessage("This user doesn't exist.\n");
                } else {
                    controller.saveUser(userNumber);
                    printView.printMessage("User " + availableUsers.get(userNumber) + " is saved.");
                    userExists = true;
                }
            }

        }

    }

    private void removeTask () {

        int taskToRemove = Integer.parseInt(getUserInput("Which task number do you want to remove?\n")) - 1;

        if (taskToRemove >= 0 && taskToRemove < controller.showAllTasks().getTaskCollection().size()) {

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

    private void removeProject () {

        int projectToRemove = Integer.parseInt(getUserInput("\nWhich project number do you want to remove?")) - 1;

        if (projectToRemove >= 0 && projectToRemove < controller.showAllProjects().size()) {

            boolean readyToRemove = false;

            while (!readyToRemove) {

                String removeAnswer = getUserInput("Are you sure you want to remove this project?\n\n<y> or <n>");

                if (removeAnswer.equals("y")) {
                    controller.removeProject(projectToRemove);
                    printView.printMessage("Project is removed successfully.");
                    readyToRemove = true;

                } else if (removeAnswer.equals("n")) {
                    printView.printMessage("Project has not been removed!");
                    readyToRemove = true;
                } else {
                    printView.printMessage("The your answer is not recognized.\n");
                }
            }

        } else {
            printView.printMessage("This project doesn't exist!");
        }
    }

    private void showCurrentUser () {

        printView.printMessage("Current user is: " + controller.getCurrentUser().getUserName());
    }

    private void showAllUsers () {
        printView.printMessage("All users");
        printView.showAllUsers(controller.showAllUsers());
    }

    private void showCurrentProject () {

        printView.printMessage("Current project is: " + controller.getCurrentUser().getCurrentProjectName());
    }
    private void showAllProjects () {

        String userName = controller.getCurrentUser().getUserName();

        String currentProject = controller.getCurrentUser().getCurrentProjectName();

        if (userName != null) {

            printView.printMessage("All projects by " + userName);

            ArrayList<String> projectNames = controller.showAllProjects();

            printView.showAllProjects(projectNames, currentProject);
        } else {
            printView.printMessage("Please create a user first by using <new> or <new user> command.");
        }


    }

    private void changeProject() {

        boolean correctProjectNumber = false;

        while (!correctProjectNumber) {
            showAllProjects();

            int projectNumber = Integer.parseInt(getUserInput("To which project number do you want to change to?"));

            if (projectNumber > 0 && projectNumber <= controller.showAllProjects().size()) {
                correctProjectNumber = true;
                controller.changeProject(projectNumber - 1);
                printView.printMessage("Current project has been changed to " + controller.showAllProjects().get(projectNumber - 1));
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
                printView.printMessage("Current user has been changed to: " + controller.getCurrentUser());
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
