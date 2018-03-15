package model;

import java.util.Date;

/**
 * Represent an instance of a task. It holds the <code>title</code>, due <code>date</code>, <code>status</code> and
 * <code>project</code> of the task.
 */

public class Task {

    private String title;
    //private Date dueDate;
    private boolean status;
    private String project;

    public Task (String title, String project) {

        this.title = title;
        this.status= false;
        this.project = project;
    }
}
