package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
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

    public static final String FILE_FORMAT = ".txt";

    /**
     * Sets the path of the file that is being read
     * @param inputFileName
     */

    public FileUtility (String inputFileName) {

        path = Paths.get(inputFileName + FILE_FORMAT);

        dateFormat = new SimpleDateFormat(DATE_FORMAT);

        try {
            reader = new Scanner(Files.newBufferedReader(path, Charset.defaultCharset()));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Problem with the path of the file");
        }
    }

    /**
     * Reads the data related to a single user from a file.
     * @return returns <code>{@link UserFileDTO}</code> object which contains user name, user's projects and tasks related to these projects.
     */

    public UserFileDTO loadFromFile () {

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

        return new UserFileDTO(userName, taskCollections);
    }

    /**
     * Writes the data related to a specified <code>{@link User}</code> to a file
     * @param userFileDTO DTO that contains user name, user projects and tasks related to projects
     * @throws IOException Throws an exception if there is a problem with creating or writing to a file.
     */

    public void saveUserDTO (UserFileDTO userFileDTO) throws IOException {

        Path resultsFilePath = Paths.get(userFileDTO.getUserName() + FILE_FORMAT).toAbsolutePath();

        FileWriter writer = new FileWriter(resultsFilePath.toString());

        BufferedWriter bufferedWriter = new BufferedWriter(writer);


        bufferedWriter.write(userFileDTO.getUserName() + ",," + userFileDTO.getProjectCollection().size() + "\n");

        for (TaskCollectionDTO taskCollectionDTO : userFileDTO.getProjectCollection()) {

            bufferedWriter.write(taskCollectionDTO.getProjectName() + ",," + taskCollectionDTO.getTaskCollection().size() + "\n");

            for (TaskDTO taskDTO : taskCollectionDTO.getTaskCollection()) {
                bufferedWriter.write(taskDTO.getTitle() + ",," + dateFormat.format(taskDTO.getDueDate()) + ",," + taskDTO.isStatus() + "\n");
            }
        }

        bufferedWriter.close();

    }


}
