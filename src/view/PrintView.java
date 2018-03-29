package view;

import model.TaskCollectionDTO;
import model.TaskDTO;

import java.text.SimpleDateFormat;

import static view.UserInputManager.DATE_FORMAT;


public class PrintView {


    public void printMessage(String message) {
        System.out.println("\n" + message);
    }

    public void printInput(String message) {
        System.out.println(message + "\n");
        System.out.print("> ");
    }

    public void showAllTasks(TaskCollectionDTO sortedTaskCollectin) {

        if (sortedTaskCollectin != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

            System.out.println("Project name: " + sortedTaskCollectin.getProjectName() + "\n");


            int i = 0;
            for (TaskDTO task : sortedTaskCollectin.getTaskCollection()) {
                System.out.println("Task no. " + i + ": " + task.getTitle() + ", due date: " + dateFormat.format(task.getDueDate()) + ", status: " + (task.isStatus() ? "finished" : "unfinished"));
                i++;
            }
        } else {
            printMessage("There are no tasks!");
        }

        }

    }
