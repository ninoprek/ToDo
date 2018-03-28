package model;

import view.Command;

import java.util.Date;

/**
 * Represent an instance of a task. It holds the <code>title</code>, due <code>date</code>, <code>status</code> and
 * <code>project</code> of the task.
 */

public class Task {

    private String title;
    private Date dueDate;
    private Boolean status;

    public Task (TaskDTO taskDTO) {

        this.title = taskDTO.getTitle();
        this.dueDate = taskDTO.getDueDate();
        this.status= taskDTO.isStatus(); 
    }

    public String getTitle() {
        return this.title;
    }

    /**
     *
     * @return Returns <code>String</code> "finished" if <code>status</code> is <code>true</code>, otherwise returns "unfinished"
     */

    public String getStatus () {
        if (status) {
            return "finished";
        } else {
            return "unfinished";
        }
    }

    /**
     * @return Returns dueDate in <code>String</code> format
     */

    public Date getDueDate() {

        return dueDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * Method that edits fields in a <code>Task</code> object
     * @param taskFieldToEdit Second word in a <code>{@link Command}</code> object
     * @param taskFieldValue Generic object that can hold objects of types of <code>{@link Task}</code> fields
     */

    public void editTask (String taskFieldToEdit, TaskFieldValue taskFieldValue) {

        switch (taskFieldToEdit) {

            case "title":
                setTitle((String) taskFieldValue.getT());
                break;
            case "date":
                setDueDate((Date) taskFieldValue.getT());
                break;
            case "status":
                setStatus((Boolean) taskFieldValue.getT());
                break;
        }

    }
}
