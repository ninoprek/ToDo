package model;

import java.util.ArrayList;

/**
 * Hold a list of tasks.
 */

public class TaskCollection {

    private ArrayList<Task> taskCollection;
    private String collectionName;

    public TaskCollection (String collectionName) {

        taskCollection = new ArrayList<>();
        this.collectionName = collectionName;
    }

    /**
     * Adds a new task to the collection
     * @param title Title of the <code>task</code>
     * @param project Name of the <code>project</code> the <code>task</code> belongs to.
     */

    public void crateNewTask (String title, String project) {

        taskCollection.add(new Task(title, project));
    }

    public  void showAllTasks() {
        for (Task task : taskCollection) {
            System.out.println(task.getTitle());
        }
    }
}
