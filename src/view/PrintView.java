package view;

import model.Task;
import model.TaskCollectionDTO;
import java.text.SimpleDateFormat;


public class PrintView {


    public void printMessage(String message) {
        System.out.println("\n" + message);
    }

    public void printInput(String message) {
        System.out.println(message + "\n");
        System.out.print("> ");
    }

    public void showAllTasks(TaskCollectionDTO sortedTaskCollectin) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");

        System.out.println("Project name: " + sortedTaskCollectin.getProjectName() + "\n");


        int i = 0;
        for (Task task : sortedTaskCollectin.getTaskCollection()) {
                   System.out.println("Task no. " + i + ": " + task.getTitle() + ", due date: " + dateFormat.format(task.getDueDate()) + ", status: " + task.getStatus());
                   i++;
                }
        }
    }
