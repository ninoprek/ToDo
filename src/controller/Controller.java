package controller;

import model.*;

import java.util.ArrayList;


/**
 * Controller class that calls appropriate methods in the model.
 * It hold the reference for <code>TaskManagerClass</code>
 */

public class Controller {

    private TaskManager taskManager;

    public Controller () {

        taskManager = new TaskManager();
    }

    /**
     * Creates a new <code>{@link User}</code> object
     * @param name Name of the <code>{@link User}</code>
     */
    public void createUser(String name) {

        taskManager.addUser(name);
    }

    /**
     * Creates new collection of <code>{@link Task}</code> objects for the  <code>currentUser</code>
     * @param collectionName Name of the collection
     */

    public void createTaskCollection(String collectionName) {

        taskManager.addTaskCollection(collectionName);
    }

    /**
     * Creates a new <code>Task</code> for a current <code>user</code> in a current <code>task</code> collection
     * @param taskDTO Holds information about the <code>task</code>
     */

    public void createTask(TaskDTO taskDTO) {

        taskManager.addTask(taskDTO);
    }

    /**
     * Prints out names of all registered users
     */

    public ArrayList<String> showAllUsers() {

        return taskManager.showAllUsers();
    }

    /**
     *
     * @return List of all tasks in current project sorted by date.
     */

    public TaskCollectionDTO showAllTasks() {

        return taskManager.showAllTasks();
    }

    /**
     * @param status Defines if the returned list is list of finished or unfinished tasks
     * @return List of all finished or tasks in current project sorted by date.
     */

    public TaskCollectionDTO showAllUnFinishedTasks(boolean status) {

        return taskManager.showAllUnFinishedTasks(status);
    }

    /**
     * Returns names of all projects (<code>{@link TaskCollection}</code>s)
     * @return <code>ArrayList<String></code> of project names
     */

    public ArrayList<String> showAllProjects () {
        return taskManager.showAllProjects();
    }

    /**
     * Calls method to edit <code>{@link Task}</code> object field in <code>currentTaskCollection</code> for <code>currentUser</code>
     * @param taskFieldToEdit Name of the field that has to edited.
     * @param taskFieldValue Value that has to be stored at <code>taskFieldToEdit</code> field.
     * @param taskNumber Number of the <code>{@link Task}</code> object in <code>taskCollection</code>
     */

    /**
     * Get's the name of the current user
     * @return <code>String</code> value of the <code>currentUser</code>
     */

    public User getCurrentUser() {
        return taskManager.getCurrentUser();
    }

    /**
     * Calls method to edit <code>{@link Task}</code> object field in <code>currentTaskCollection</code> for <code>currentUser</code>
     * @param taskFieldToEdit Name of the field that has to edited.
     * @param taskFieldValue Value that has to be stored at <code>taskFieldToEdit</code> field.
     * @param taskNumber Number of the <code>{@link Task}</code> object in <code>taskCollection</code>
     */

    public void editTask(String taskFieldToEdit , TaskFieldValue taskFieldValue , Integer taskNumber) {

        taskManager.editTask(taskFieldToEdit, taskFieldValue, taskNumber);
    }

    /**
     * Calls a method in <code>{@link TaskManager}</code> to load <code>{@link User}</code> information from file.
     */

    public void loadUser (String userName) {

        taskManager.loadUser(userName);
    }

    /**
     * Calls a method in <code>{@link TaskManager}</code> to save <code>{@link User}</code> information to a file.
     * @param userNumber
     */

    public void saveUser (int userNumber) {
        taskManager.saveUser(userNumber);
    }

    /**
     * Returns the list of saved users that can be loaded
     * @param inputFolder <code>Path</code> name that points to the location of saved users
     * @return Returns a list of saved users
     */

    public ArrayList<String> listAllUsers (String inputFolder) {

        return taskManager.listAllUsers(inputFolder);
    }

    /**
     * Changes the current project of the current user
     * @param projectNumber Project number which will be set as current
     */

    public void changeProject (int projectNumber) {

        taskManager.changeProject(projectNumber);
    }

    /**
     * Changes the current user
     * @param userNumber User number which will be set as current
     */

    public void changeUser (int userNumber) {

        taskManager.changeUser(userNumber);
    }

    /**
     * Removes a <code>{@link Task}</code> from the collection.
     * @param taskToRemove Number of a <code>{@link Task}</code> that needs to be removed.
     */

    public void removeTask (int taskToRemove) {

        taskManager.removeTask(taskToRemove);
    }

    /**
     * Removes the project from the project collection.
     * @param projectToRemove Number of the project in project collection that has to be removed.
     */

    public void removeProject (int projectToRemove) {
        taskManager.removeProject(projectToRemove);
    }
}
