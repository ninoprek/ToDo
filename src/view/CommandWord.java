package view;

/**
 * List of valid commands that represent application functionalities
 */

public enum CommandWord {

    NEW("new"), TASK("task"), TASKS("tasks"), USERS("users"), EDIT("edit"), CHANGE("change"),
    LOAD("load"), HELP("help"), REMOVE("remove"), QUIT("quit"), UNKNOWN("?");

    private String commandString;

    CommandWord (String commandString) {

        this.commandString = commandString;
    }

    /**
     * @return String value of the valid command
     */

    public String toString() {

        return commandString;
    }


}
