package controller;

import model.*;

import java.util.List;


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
     * Creates a new <code>user</code>
     * @param name Name of the <code>user</code>
     */
    public void createUser(String name) {

        taskManager.addUser(name);
    }

    /**
     * Creates new collection of <code>tasks</code> for the current <code>user</code>
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

    public void showAllUsers() {

        taskManager.showAllUsers();
    }

    /**
     * Prints out titles of all tasks in a current task collection
     */

    public TaskCollectionDTO showAllTasks() {

        return taskManager.showAllTasks();
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
     * Calls method in <code>{@link TaskManager}</code> to load <code>{@link User}</code> information from file.
     */

    public void loadUser () {

        taskManager.loadUser();
    }


}
