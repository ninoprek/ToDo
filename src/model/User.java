package model;

import java.util.ArrayList;

/**
 * Holds users name and a collection of task lists related to every user.
 */

public class User {

    private String userName;
    private ArrayList<TaskCollection> taskCollections;
    private TaskCollection currentTaskCollection;

    public User (String userName) {

        this.userName = userName;
        taskCollections = new ArrayList<>();
    }

    /**
     * Create a new collection of tasks
     * @param collectionName Name of the <code>task</code>  collection
     */

    public void createTaskCollection (String collectionName) {

        currentTaskCollection = new TaskCollection(collectionName);
        taskCollections.add(currentTaskCollection);
    }

    /**
     * Creates a new <code>Task</code> for a current <code>user</code> in a current <code>task</code> collection
     * @param title Title of the <code>task</code>
     * @param project Name of the project
     */

    public void createTask (String title, String project) {

        currentTaskCollection.crateNewTask(title, project);
    }
}

