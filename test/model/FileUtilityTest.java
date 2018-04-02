package model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

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
        fileUtility = new FileUtility("users/Antonio");
    }

    @AfterEach
    void tearDown() {
        fileUtility = null;
    }


    @Test
    void saveAndLoadUserFileTest() {

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

        UserFileDTO testUserFileDTO = new UserFileDTO("Antonio", taskCollectionDTOs);

        try {
            fileUtility.saveUserDTO(testUserFileDTO);
        } catch (IOException e) {
            System.out.println("Problem with creating and writing to file!");
            e.printStackTrace();
        }

        UserFileDTO userFileDTO  = fileUtility.loadFromFile();

        boolean expectedResult = true;

        Assertions.assertEquals( expectedResult, userFileDTO.getUserName().equals("Antonio"), "User name doesn't match");
        Assertions.assertEquals( expectedResult, userFileDTO.getProjectCollection().get(0).getProjectName().equals(taskCollection1.getProjectName()), "Project name doesn't match");
        Assertions.assertEquals( expectedResult, userFileDTO.getProjectCollection().get(1).getProjectName().equals(taskCollection2.getProjectName()), "Project name doesn't match");

        Assertions.assertEquals( expectedResult, userFileDTO.getProjectCollection().get(0).getTaskCollection().get(0).getTitle().equals(taskCollection1.getTaskCollection().get(0).getTitle()), "Task title doesn't match");
        Assertions.assertEquals( expectedResult, userFileDTO.getProjectCollection().get(0).getTaskCollection().get(1).getTitle().equals(taskCollection1.getTaskCollection().get(1).getTitle()), "Task title doesn't match");
        Assertions.assertEquals( expectedResult, userFileDTO.getProjectCollection().get(0).getTaskCollection().get(2).getTitle().equals(taskCollection1.getTaskCollection().get(2).getTitle()), "Task title doesn't match");
        Assertions.assertEquals( expectedResult, userFileDTO.getProjectCollection().get(0).getTaskCollection().get(0).getDueDate().equals(taskCollection1.getTaskCollection().get(0).getDueDate()), "Due date doesn't match");
        Assertions.assertEquals( expectedResult, userFileDTO.getProjectCollection().get(0).getTaskCollection().get(1).getDueDate().equals(taskCollection1.getTaskCollection().get(1).getDueDate()), "Due date doesn't match");
        Assertions.assertEquals( expectedResult, userFileDTO.getProjectCollection().get(0).getTaskCollection().get(2).getDueDate().equals(taskCollection1.getTaskCollection().get(2).getDueDate()), "Due date doesn't match");
        Assertions.assertEquals( expectedResult, userFileDTO.getProjectCollection().get(0).getTaskCollection().get(0).isStatus() == (taskCollection1.getTaskCollection().get(0).isStatus()), "Status doesn't match");
        Assertions.assertEquals( expectedResult, userFileDTO.getProjectCollection().get(0).getTaskCollection().get(1).isStatus() == (taskCollection1.getTaskCollection().get(1).isStatus()), "Status doesn't match");
        Assertions.assertEquals( expectedResult, userFileDTO.getProjectCollection().get(0).getTaskCollection().get(2).isStatus() == (taskCollection1.getTaskCollection().get(2).isStatus()), "Status doesn't match");

        Assertions.assertEquals( expectedResult, userFileDTO.getProjectCollection().get(1).getTaskCollection().get(0).getTitle().equals(taskCollection2.getTaskCollection().get(0).getTitle()), "Task title doesn't match");
        Assertions.assertEquals( expectedResult, userFileDTO.getProjectCollection().get(1).getTaskCollection().get(1).getTitle().equals(taskCollection2.getTaskCollection().get(1).getTitle()), "Task title doesn't match");
        Assertions.assertEquals( expectedResult, userFileDTO.getProjectCollection().get(1).getTaskCollection().get(2).getTitle().equals(taskCollection2.getTaskCollection().get(2).getTitle()), "Task title doesn't match");
        Assertions.assertEquals( expectedResult, userFileDTO.getProjectCollection().get(1).getTaskCollection().get(0).getDueDate().equals(taskCollection2.getTaskCollection().get(0).getDueDate()), "Due date doesn't match");
        Assertions.assertEquals( expectedResult, userFileDTO.getProjectCollection().get(1).getTaskCollection().get(1).getDueDate().equals(taskCollection2.getTaskCollection().get(1).getDueDate()), "Due date doesn't match");
        Assertions.assertEquals( expectedResult, userFileDTO.getProjectCollection().get(1).getTaskCollection().get(2).getDueDate().equals(taskCollection2.getTaskCollection().get(2).getDueDate()), "Due date doesn't match");
        Assertions.assertEquals( expectedResult, userFileDTO.getProjectCollection().get(1).getTaskCollection().get(0).isStatus() == (taskCollection2.getTaskCollection().get(0).isStatus()), "Status doesn't match");
        Assertions.assertEquals( expectedResult, userFileDTO.getProjectCollection().get(1).getTaskCollection().get(1).isStatus() == (taskCollection2.getTaskCollection().get(1).isStatus()), "Status doesn't match");
        Assertions.assertEquals( expectedResult, userFileDTO.getProjectCollection().get(1).getTaskCollection().get(2).isStatus() == (taskCollection2.getTaskCollection().get(2).isStatus()), "Status doesn't match");
    }

    @Test
    void loadAllUsersFromFiles () {

        FileUtility newFileUtility = new FileUtility("users/");

        ArrayList<String> expectedUsers = new ArrayList<>();
        ArrayList<String> usersFromFile = new ArrayList<>();
        boolean expectedResult = true;

        expectedUsers.add("Antonio");
        expectedUsers.add("Giuseppe");
        expectedUsers.add("Marija");
        expectedUsers.add("Nino");

        usersFromFile = newFileUtility.listOfAllUsers();

        Assertions.assertEquals(expectedResult, usersFromFile.get(0).equals(expectedUsers.get(1)), "Name doesn't match");
        Assertions.assertEquals(expectedResult, usersFromFile.get(1).equals(expectedUsers.get(3)), "Name doesn't match");
        Assertions.assertEquals(expectedResult, usersFromFile.get(2).equals(expectedUsers.get(2)), "Name doesn't match");
        Assertions.assertEquals(expectedResult, usersFromFile.get(3).equals(expectedUsers.get(0)), "Name doesn't match");

    }
}