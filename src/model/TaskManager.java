package model;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


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
     * Calls a function in <code>{@link FileUtility}</code> that saves the <code>{@link User}</code> and all of his related tasks and projects to a file.
     * @param userNumber Number of the user in <code>users</code>.
     */

    public void saveUser(int userNumber) {


        User userToSave = users.get(userNumber);
        ArrayList<TaskCollectionDTO> projectCollectionDTOs = new ArrayList<>();
        FileUtility fileUtility = new FileUtility("users/");


        userToSave.getProjectCollection().forEach(project -> {

            List<TaskDTO> taskListToSaveDTO = new ArrayList<>();

            for(Task task : project.getTaskCollection()) {

                taskListToSaveDTO.add(new TaskDTO(task.getTitle(), task.getDueDate(), task.getBooleanStatus()));
            }

            TaskCollectionDTO taskCollectionToSaveDTO = new TaskCollectionDTO(taskListToSaveDTO, project.getProjectName());
            projectCollectionDTOs.add(taskCollectionToSaveDTO);
        });

        UserFileDTO userToSaveDTO = new UserFileDTO(userToSave.getUserName(), projectCollectionDTOs);

        try {
            fileUtility.saveUserDTO(userToSaveDTO);
        } catch (IOException e) {

            System.out.println("There is a problem with saving the user!");
            e.printStackTrace();
        }

    }

    /**
     * Returns the list of saved users that can be loaded
     * @param inputFolder <code>Path</code> name that points to the location of saved users
     * @return Returns a list of saved users
     */

    public ArrayList<String> listAllUsers (String inputFolder) {

        FileUtility fileUtility = new FileUtility(inputFolder);

        return fileUtility.listOfAllUsers();
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
