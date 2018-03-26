package model;

import java.util.List;

/**
 * This class contains <code>TaskCollection</code> elements and it is used for transferring data from <code>model</code> to <code>view</code>.
 */

public class TaskCollectionDTO {

    private List<Task> taskCollection;
    private String projectName;

    public TaskCollectionDTO (List<Task> taskCollection, String collectionName) {

        this.taskCollection = taskCollection;
        this.projectName = collectionName;
    }

    public List<Task> getTaskCollection() {
        return this.taskCollection;
    }

    public String getProjectName() {
        return this.projectName;
    }
}
