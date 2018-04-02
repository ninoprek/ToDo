package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Holds a list of tasks and calls methods on <code>{@link Task}</code> objects
 */

public class TaskCollection {

    private ArrayList<Task> taskCollection;
    private String projectName;

    /**
     * Creates an collection of <code>{@link Task}</code> objects and gives it a name
     * @param collectionName The name of the collection
     */

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

    /**
     *
     * @return Returns <code>{@link TaskCollectionDTO}</code> object that contains <code>List</code> of <code>Task</code> objects to <code>View</code>
     */

    public TaskCollectionDTO showAllTasks() {

        if(!taskCollection.isEmpty()) {

            ArrayList<TaskDTO> taskCollectionDTO = new ArrayList<>();

            for (Task task : taskCollection) {

                taskCollectionDTO.add(new TaskDTO(task.getTitle(), task.getDueDate(), task.getBooleanStatus()));
            }

            List<TaskDTO> sortedTaskCollectin = taskCollectionDTO.stream().sorted((t1, t2) -> t1.getDueDate().compareTo(t2.getDueDate())).collect(Collectors.toList());

            return new TaskCollectionDTO(sortedTaskCollectin, projectName);
        } else {

            TaskCollectionDTO taskCollectionDTOEmpty = null;
            return taskCollectionDTOEmpty;
        }
    }

    /**
     *
     * @return Returns <code>{@link TaskCollectionDTO}</code> object that contains <code>List</code> of finished or unfinished <code>Task</code> objects.
     */

    public TaskCollectionDTO showAllUnFinishedTasks(boolean status) {

        if(!taskCollection.isEmpty()) {

            ArrayList<TaskDTO> taskCollectionDTO = new ArrayList<>();

            for (Task task : taskCollection) {

                taskCollectionDTO.add(new TaskDTO(task.getTitle(), task.getDueDate(), task.getBooleanStatus()));
            }

            List<TaskDTO> sortedTaskCollectin = taskCollectionDTO.stream().filter(task -> task.isStatus() == status).collect(Collectors.toList());

            return new TaskCollectionDTO(sortedTaskCollectin, projectName);
        } else {

            TaskCollectionDTO taskCollectionDTOEmpty = null;
            return taskCollectionDTOEmpty;
        }
    }

    /**
     * Selects the <code>{@link Task}</code> object at certain position number from <code>taskCollection</code> and calls it's edit function.
     * @param taskFieldToEdit Name of the field that has to edited.
     * @param taskFieldValue Value that has to be stored at <code>taskFieldToEdit</code> field.
     * @param taskNumber Number of the <code>{@link Task}</code> object in <code>taskCollection</code>.
     */

    public void editTask(String taskFieldToEdit , TaskFieldValue taskFieldValue , Integer taskNumber) {

        Task taskToEdit = taskCollection.get(taskNumber);

        taskToEdit.editTask(taskFieldToEdit, taskFieldValue);
    }

    /**
     * Removes a <code>{@link Task}</code> from the collection.
     * @param taskToRemove Number of a <code>{@link Task}</code> that needs to be removed.
     */

    public void removeTask (int taskToRemove) {

        taskCollection.remove(taskToRemove);
    }

    public String getProjectName() {
        return projectName;
    }

    public ArrayList<Task> getTaskCollection() {
        return taskCollection;
    }
}
