package model;

import java.util.ArrayList;

/**
 * Holds users name and a collection of task lists related to a <code>user</code>.
 */

public class User {

    private String userName;
    private ArrayList<TaskCollection> projectCollection;
    private TaskCollection currentProject;

    public User (String userName) {

        this.userName = userName;
        projectCollection = new ArrayList<>();
    }

    /**
     * Create a new collection of tasks
     * @param collectionName Name of the <code>task</code>  collection
     */

    public void createTaskCollection (String collectionName) {

        currentProject = new TaskCollection(collectionName);
        projectCollection.add(currentProject);
    }

    /**
     * Creates a new <code>task</code> for a current <code>user</code> in a current <code>task</code> collection
     * @param taskDTO Holds information about the <code>task</code>
     */

    public void createTask (TaskDTO taskDTO) {

        currentProject.crateNewTask(taskDTO);
    }

    /**
     *
     * @return Returns the <code>userName</code> of the task collection holder.
     */

    public String getUserName() {
        return  userName;
    }

    /**
     *
     * @return Returns <code>TaskCollectionDTO</code> of tasks sorted by date
     */

    public TaskCollectionDTO showAllTasks() {

        return currentProject.showAllTasks();
    }

    /**
     * Returns names of all projects (<code>{@link TaskCollection}</code>s)
     * @return <code>ArrayList<String></code> of project names
     */

    public ArrayList<String> showAllProjects() {

        ArrayList<String> allProjects = new ArrayList<>();

        for (TaskCollection taskCollection : projectCollection) {
            allProjects.add(taskCollection.getProjectName());

        }
        return allProjects;
    }

    /**
     * Calls method to edit <code>{@link Task}</code> object field
     * @param taskFieldToEdit Name of the field that has to edited.
     * @param taskFieldValue Value that has to be stored at <code>taskFieldToEdit</code> field.
     * @param taskNumber Number of the <code>{@link Task}</code> object in <code>taskCollection</code>
     */

    public void editTask(String taskFieldToEdit , TaskFieldValue taskFieldValue , Integer taskNumber) {

        currentProject.editTask(taskFieldToEdit, taskFieldValue, taskNumber);
    }

    /**
     * Changes the current project of the current user
     * @param projectNumber Project number which will be set as current
     */

    public void changeProject (int projectNumber) {

        currentProject = projectCollection.get(projectNumber);
    }

    /**
     * Removes a <code>{@link Task}</code> from the collection.
     * @param taskToRemove Number of a <code>{@link Task}</code> that needs to be removed.
     */

    public void removeTask (int taskToRemove) {

        currentProject.removeTask(taskToRemove);
    }

    /**
     * Removes specified project from the <code>projectCollection</code>. If the project is set as <code>currentProject</code>,
     * different one is set as the current one if it exits. If not, <code>currentProject</code> is set to null.
     * @param projectToRemove Number of the project in <code>projectCollection</code> that has to be removed.
     */

    public void removeProject (int projectToRemove) {

        if (projectCollection.get(projectToRemove).getProjectName().equals(currentProject.getProjectName())) {

            if (projectCollection.size() > 1) {

                if (projectToRemove > 0 ) {

                    currentProject = projectCollection.get(projectToRemove - 1);
                    projectCollection.remove(projectToRemove);

                } else {

                    currentProject = projectCollection.get(projectToRemove + 1);
                    projectCollection.remove(projectToRemove);
                }

            } else {
                currentProject = null;
                projectCollection.remove(projectToRemove);
            }

        } else {

            projectCollection.remove(projectToRemove);
        }
    }

}

