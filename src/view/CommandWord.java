package view;

/**
 * List of valid commands that represent application functionalities
 */

public enum CommandWord {

    NEW("new"), TASK("task"), TASKS("tasks"), USER("user"), USERS("users"), EDIT("edit"), CHANGE("change"), PROJECT("project"), PROJECTS("projects"),
    SAVE("save"), LOAD("load"), HELP("help"), REMOVE("remove"), QUIT("quit"), UNKNOWN("?");

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
