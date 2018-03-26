package controller;

import model.Task;
import model.TaskCollectionDTO;
import model.TaskDTO;
import model.TaskManager;

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

}
