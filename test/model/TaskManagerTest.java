package model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

class TaskManagerTest {

    TaskManager taskManager = new TaskManager();


    @BeforeEach
    void setUp() {

        taskManager.loadUser("users/Antonio");
    }

    @AfterEach
    void tearDown() {

        taskManager = null;
    }

    @Test
    void addNewUserAndCheckIfItIsCurrent() {

        taskManager.addUser("Nemanja");

        boolean expecteResult = true;

        Assertions.assertEquals(expecteResult, taskManager.getCurrentUser().getUserName().equals("Nemanja"), "New user is created but not current user.");

    }

    @Test
    void getCurrentUserTest() {

        boolean expecteResult = true;

        Assertions.assertEquals(expecteResult, taskManager.getCurrentUser().getUserName().equals("Antonio"), "Current user doesn't match.");

    }

    @Test
    void editTaskTest() throws ParseException {

        SimpleDateFormat dateFormat  = new SimpleDateFormat("dd/mm/yyyy");

        boolean expectedResult = true;

        TaskFieldValue inputTitle = new TaskFieldValue( "start cleaning the room");
        TaskFieldValue inputDate = new TaskFieldValue( dateFormat.parse("12/3/2018"));
        TaskFieldValue inputStatus = new TaskFieldValue( true);

        taskManager.editTask("title", inputTitle, 0);
        taskManager.editTask("date", inputDate, 0);
        taskManager.editTask("status", inputStatus, 0);

        Assertions.assertEquals(expectedResult, taskManager.getCurrentUser().getProjectCollection().get(1).showAllTasks().getTaskCollection().get(2).getTitle().equals( "start cleaning the room"), "The title doesn't match after edit.");
        Assertions.assertEquals(expectedResult, taskManager.getCurrentUser().getProjectCollection().get(1).showAllTasks().getTaskCollection().get(2).getDueDate().equals( dateFormat.parse("12/3/2018")), "The date doesn't match after edit.");
        Assertions.assertEquals(expectedResult, taskManager.getCurrentUser().getProjectCollection().get(1).showAllTasks().getTaskCollection().get(2).isStatus() == true, "The Status doesn't match after edit.");



    }
}