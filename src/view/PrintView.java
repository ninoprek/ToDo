package view;

import model.TaskCollectionDTO;
import model.TaskDTO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static view.UserInputManager.DATE_FORMAT;


public class PrintView {


    public void printMessage(String message) {
        System.out.println("\n" + message);
    }

    public void printInput(String message) {
        System.out.println(message + "\n");
        System.out.print("> ");
    }

    public void showAllUsers (ArrayList<String> userNames) {

        System.out.println("\n---------------");
        for (int i = 0; i < userNames.size(); i++) {
            System.out.println((i + 1) + ": " + userNames.get(i));
        }
        System.out.println("---------------\n");
    }

    public void showAllTasks(TaskCollectionDTO sortedTaskCollectin) {

        if (sortedTaskCollectin != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

            System.out.println("\nProject name: " + sortedTaskCollectin.getProjectName());

            System.out.println("\n---------------");
            int i = 1;
            for (TaskDTO task : sortedTaskCollectin.getTaskCollection()) {
                System.out.println("Task no. " + i + ": " + task.getTitle() + ", due date: " + dateFormat.format(task.getDueDate()) + ", status: " + (task.isStatus() ? "finished" : "unfinished"));
                i++;
            }
            System.out.println("---------------\n");
        } else {
            printMessage("There are no tasks!");
        }
    }

    public void showAllProjects (ArrayList<String> projectNames) {

        if (projectNames != null ) {
            int i = 1;
            for (String projectName : projectNames) {
                System.out.println( i + ": " + projectName);
                i++;
            }

        } else {
            System.out.println("There are no projects created");
        }
    }

}


