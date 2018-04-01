package model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static view.UserInputManager.DATE_FORMAT;


class FileUtilityTest {

    FileUtility fileUtility;
    SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

    @BeforeEach
    void setUp() {
        fileUtility = new FileUtility("users/inputFile");
    }

    @AfterEach
    void tearDown() {
    }


    @Disabled
    @Test
    void readUserFileTest() {

        fileUtility.loadFromFile();
    }

    @Disabled
    @Test
    void writeUserFileTest() {

        ArrayList<TaskCollectionDTO> taskCollectionDTOs = new ArrayList<>();

        TaskCollectionDTO taskCollection1;
        TaskCollectionDTO taskCollection2;
        List<TaskDTO> taskDTOList = new ArrayList<>();
        List<TaskDTO> taskDTOList2 = new ArrayList<>();

        try {
            taskDTOList.add(new TaskDTO("clean my room", dateFormat.parse("2/5/2018"), false));
            taskDTOList.add(new TaskDTO("clean the kitchen", dateFormat.parse("4/6/2018"), false));
            taskDTOList.add(new TaskDTO("clean the wc", dateFormat.parse("6/7/2018"), false));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        taskCollection1 = new TaskCollectionDTO(taskDTOList, "cleaning");

        try {
            taskDTOList2.add(new TaskDTO("chillax", dateFormat.parse("5/8/2018"), false));
            taskDTOList2.add(new TaskDTO("walk in the sun", dateFormat.parse("10/8/2018"), false));
            taskDTOList2.add(new TaskDTO("got to sleep", dateFormat.parse("11/9/2018"), false));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        taskCollection2 = new TaskCollectionDTO(taskDTOList2, "chilling");

        taskCollectionDTOs.add(taskCollection1);
        taskCollectionDTOs.add(taskCollection2);

        UserFileDTO testUserFileDTO = new UserFileDTO("Giuseppe", taskCollectionDTOs);

        try {
            fileUtility.saveUserDTO(testUserFileDTO);
        } catch (IOException e) {
            System.out.println("Problem with creating and writing to file!");
            e.printStackTrace();
        }

    }

    @Test
    void readFilesFromDirectoryTest() {

        try {
            fileUtility.listOfAllFiles();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}