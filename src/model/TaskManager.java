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
     * Creates a new collection for storing <code>Tasks</code> for a current <code>User</code>
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

    /**
     * Gets the list of all <code>{@link Task}</code> objects sorted by <code>dueDate</code> from the current <code>{@link User}</code> in a <code>currentTaskCollection</code>
     * @return Object that contains all <code>{@link Task}</code> objects.
     */

    public TaskCollectionDTO showAllTasks() {
        return currentUser.showAllTasks();
    }

    /**
     * Calls method to edit <code>{@link Task}</code> object field in <code>currentTaskCollection</code> for <code>currentUser</code>
     * @param taskFieldToEdit Name of the field that has to edited.
     * @param taskFieldValue Value that has to be stored at <code>taskFieldToEdit</code> field.
     * @param taskNumber Number of the <code>{@link Task}</code> object in <code>taskCollection</code>
     */

    public void editTask(String taskFieldToEdit , TaskFieldValue taskFieldValue , Integer taskNumber) {

        currentUser.editTask(taskFieldToEdit, taskFieldValue, taskNumber);
    }

}
