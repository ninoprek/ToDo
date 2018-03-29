package model;

import java.util.ArrayList;

public class LoadUserDTO {

    private String userName;
    private ArrayList<TaskCollectionDTO> taskCollections;


    public LoadUserDTO (String userName, ArrayList<TaskCollectionDTO> taskCollections) {

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
