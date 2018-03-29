package model;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import static view.UserInputManager.DATE_FORMAT;

/**
 * This class reads from and writes to the file.
 */

public class FileUtility {

    private Path path;
    private SimpleDateFormat dateFormat;
    private Scanner reader;

    /**
     * Sets the path of the file that is being read
     * @param inputFileName
     */

    public FileUtility (String inputFileName) {

        path = Paths.get(inputFileName);

        dateFormat = new SimpleDateFormat(DATE_FORMAT);
        try {
            reader = new Scanner(Files.newBufferedReader(path, Charset.defaultCharset()));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Problem with the path of the file");
        }
    }

    public LoadUserDTO loadFromFile () {

        String line;
        ArrayList<String> projecList = new ArrayList<>();
        ArrayList<TaskCollectionDTO> taskCollections = new ArrayList<>();

        String user = reader.nextLine();

        String[] userData = user.split(",,");

        String userName = userData[0];


        for (int i = 0; i < Integer.parseInt(userData[1]); i++) {

            String project = reader.nextLine();

            String[] projectData  = project.split(",,");

            projecList.add(projectData[0]);


            List<TaskDTO> taskDTOCollection = new ArrayList<>();

            for (int j = 0; j < Integer.parseInt(projectData[1]); j++) {

                line = reader.nextLine();

                String[] taskFields = line.split(",,");

                String title = taskFields[0];

                Date dueDate = null;
                try {
                    dueDate = dateFormat.parse(taskFields[1]);
                } catch (ParseException e) {
                    System.out.println("Invalid date format!");
                }

                boolean status = Boolean.getBoolean(taskFields[2]);

                taskDTOCollection.add(new TaskDTO(title, dueDate, status));
            }

            taskCollections.add(new TaskCollectionDTO(taskDTOCollection, projecList.get(i)));
        }

        return new LoadUserDTO(userName, taskCollections);
    }

}
