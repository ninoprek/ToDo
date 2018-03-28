package model;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * This class reads from and writes to the file.
 */

public class FileUtility {

    private Path path;

    /**
     * Sets the path of the file that is being read
     * @param inputFileName
     */

    public FileUtility (String inputFileName) {

        path = Paths.get(inputFileName);
    }

    public void readTaskList () {

        try {
            Files.exists(path);

            Scanner reader = new Scanner(Files.newBufferedReader(path, Charset.defaultCharset()));
            String line;

            while ((reader.hasNext())) {
                line = reader.nextLine();
                String[] taskFields = line.split(" ");

                for (String field : taskFields) {
                    System.out.println(field);
                }
                System.out.println("---------------");
            }

        } catch (NoSuchFileException e) {

            System.out.println("This file doesn't exist!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
