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

    public ArrayList<String> showAllUsers () {

        ArrayList<String> allUsers = new ArrayList<>();

        for (User user : users) {

            allUsers.add(user.getUserName());
        }

        return allUsers;
    }

    /**
     * Gets the list of all <code>{@link Task}</code> objects sorted by <code>dueDate</code> from the current <code>{@link User}</code> in a <code>currentTaskCollection</code>
     * @return Object that contains all <code>{@link Task}</code> objects.
     */

    public TaskCollectionDTO showAllTasks() {

        if (currentUser != null) {
            return currentUser.showAllTasks();
        } else {

            TaskCollectionDTO empty = null;

            return empty;
        }
    }

    /**
     * Returns names of all projects (<code>{@link TaskCollection}</code>s)
     * @return <code>ArrayList<String></code> of project names
     */

    public ArrayList<String> showAllProjects () {
        return currentUser.showAllProjects();
    }

    /**
     * Get's the name of the current user
     * @return <code>String</code> value of the <code>currentUser</code>
     */

    public User getCurrentUser() {
        return currentUser;
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

    /**
     * Reads a <code>{@link User}</code> object form the class with all of it's fields
     */

    public void loadUser (String userName) {

        FileUtility fileUtility = new FileUtility(userName);

        UserFileDTO loadUser = fileUtility.loadFromFile();

        addUser(loadUser.getUserName());

        ArrayList<TaskCollectionDTO> taskCollectionsDTO = loadUser.getProjectCollection();


        for (TaskCollectionDTO taskCol : taskCollectionsDTO) {

            currentUser.createTaskCollection(taskCol.getProjectName());

            for (TaskDTO task : taskCol.getTaskCollection()) {

                currentUser.createTask(task);
            }

        }
    }

    /**
     * Changes the current project of the current user
     * @param projectNumber Project number which will be set as current
     */

    public void changeProject (int projectNumber) {

        currentUser.changeProject(projectNumber);
    }

    /**
     * Changes the current user
     * @param userNumber User number which will be set as current
     */

    public void changeUser (int userNumber) {

        currentUser = users.get(userNumber);
    }

    /**
     * Removes a <code>{@link Task}</code> from the collection.
     * @param taskToRemove Number of a <code>{@link Task}</code> that needs to be removed.
     */

    public void removeTask (int taskToRemove) {

        currentUser.removeTask(taskToRemove);
    }

    /**
     * Removes the project from the project collection.
     * @param projectToRemove Number of the project in project collection that has to be removed.
     */

    public void removeProject (int projectToRemove) {
        currentUser.removeProject(projectToRemove);
    }


}
