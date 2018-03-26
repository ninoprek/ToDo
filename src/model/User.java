package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Holds users name and a collection of task lists related to a <code>user</code>.
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
     * Creates a new <code>task</code> for a current <code>user</code> in a current <code>task</code> collection
     * @param taskDTO Holds information about the <code>task</code>
     */

    public void createTask (TaskDTO taskDTO) {

        currentTaskCollection.crateNewTask(taskDTO);

        taskCollections.add(currentTaskCollection);
    }

    /**
     *
     *
     * @return
     */

    public String getUserName() {
        return  userName;
    }

    public TaskCollectionDTO showAllTasks() {

        return currentTaskCollection.showAllTasks();
    }

}

