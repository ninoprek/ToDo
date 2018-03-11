package model;

public enum  CommandWord {

    NEW("new"), EDIT("edit"), HELP("help"), QUIT("quit"), UNKNOWN("?");

    private String commandString;

    CommandWord (String commandString) {

        this.commandString = commandString;
    }

    public String toString() {

        return commandString;
    }


}
