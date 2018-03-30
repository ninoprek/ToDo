package model;

import java.util.ArrayList;

public class UserFileDTO {

    private String userName;
    private ArrayList<TaskCollectionDTO> taskCollections;


    public UserFileDTO (String userName, ArrayList<TaskCollectionDTO> taskCollections) {

        this.userName = userName;
        this.taskCollections = taskCollections;
    }

    public String getUserName() {
        return userName;
    }

    public ArrayList<TaskCollectionDTO> getTaskCollections() {
        return taskCollections;
    }
}
