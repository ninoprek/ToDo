package model;


import java.util.ArrayList;

/**
 * Holds the list of all users, current user and manages user, list and task creation.
 */

public class TaskManager {

    private ArrayList<User> users;
    private User currentUser;

    public TaskManager() {

        users = new ArrayList<>();
    }

    /**
     * Creates a new <code>user</code> and puts him in collection of <code>users</code> and sets him as a <code>currentUser</code>.
     * @param name Name of the <code>user</code>
     */

    public void addUser(String name) {

        currentUser = new User(name);

        users.add(currentUser);
    }

    /**
     * Creates a new collection for storing <code>tasks</code> for a current <code>user</code>
     * @param collectionName Name of the new collection
     */

    public void addTaskCollection(String collectionName) {

        currentUser.createTaskCollection(collectionName);
    }

    /**
     * Creates a new <code>Task</code> for a current <code>user</code> in a current <code>task</code> collection
     * @param taskDTO Holds information about the <code>task</code>
     */

    public void addTask (TaskDTO taskDTO) {

        currentUser.createTask(taskDTO);
    }

    /**
     * Prints out names of all users.
     */

    public void showAllUsers () {

        for (User user : users) {

            System.out.println(user.getUserName());
        }
    }

    public void showAllTasks() {
        currentUser.showAllTasks();
    }

}
