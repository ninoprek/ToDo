package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Hold a list of tasks.
 */

public class TaskCollection {

    private ArrayList<Task> taskCollection;
    private String projectName;

    public TaskCollection (String collectionName) {

        taskCollection = new ArrayList<>();
        this.projectName = collectionName;
    }

    /**
     * Adds a new task to the collection
     * @param taskDTO Holds information about the <code>task</code>
     */

    public void crateNewTask (TaskDTO taskDTO) {

        taskCollection.add(new Task(taskDTO));
    }

    public TaskCollectionDTO showAllTasks() {

        List<Task> sortedTaskCollectin = taskCollection.stream().sorted((t1, t2) -> t1.getDueDate().compareTo(t2.getDueDate())).collect(Collectors.toList());

        System.out.println("I'm in TaskCollection");
        return new TaskCollectionDTO(sortedTaskCollectin, projectName);

    }
}
