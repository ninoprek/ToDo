package model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileUtilityTest {

    FileUtility fileUtility;

    @BeforeEach
    void setUp() {
        fileUtility = new FileUtility("inputFile.txt");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void readTaskListTest() {

        fileUtility.loadFromFile();
    }
}