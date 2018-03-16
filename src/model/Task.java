package model;

import java.util.Date;

/**
 * Represent an instance of a task. It holds the <code>title</code>, due <code>date</code>, <code>status</code> and
 * <code>project</code> of the task.
 */

public class Task {

    private String title;
    private Date dueDate;
    private boolean status;
    private String project;

    public Task (TaskDTO taskDTO) {

        this.title = taskDTO.getTitle();
        this.status= taskDTO.isStatus();
        this.project = taskDTO.getProject();
        this.dueDate = taskDTO.getDueDate();
    }

    public String getTitle() {
        return this.title;
    }

    /**
     *
     * @return Returns <code>String</code> "finished" if <code>status</code> is <code>true</code>, otherwise returns "unfinished"
     */

    public String getStatus() {
        if (status) {
            return "finished";
        } else {
            return "unfinished";
        }
    }

    public String getProject() {
        return project;
    }

    /**
     * @return Returns dueDate in <code>String</code> format
     */

    public Date getDueDate() {

        return dueDate;
    }
}
