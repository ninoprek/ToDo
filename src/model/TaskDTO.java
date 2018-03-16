package model;

import java.util.Date;

/**
 * This class holds parameters and getters form <code>Task</code> class. Enables easier transfer of data between view and model.
 *
 */

public class TaskDTO {

    private String title;
    private Date dueDate;
    private boolean status;
    private String project;

    public TaskDTO (String title, String project, Date dueDate) {

        this.title = title;
        this.status= false;
        this.project = project;
        this.dueDate = dueDate;
    }


    public String getTitle() {
        return title;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public boolean isStatus() {
        return status;
    }

    public String getProject() {
        return project;
    }
}
