package model;

import java.util.ArrayList;

public class UserFileDTO {

    private String userName;
    private ArrayList<TaskCollectionDTO> projectCollection;


    public UserFileDTO (String userName, ArrayList<TaskCollectionDTO> taskCollections) {

        this.userName = userName;
        this.projectCollection = taskCollections;
    }

    public String getUserName() {
        return userName;
    }

    public ArrayList<TaskCollectionDTO> getProjectCollection() {
        return projectCollection;
    }
}
