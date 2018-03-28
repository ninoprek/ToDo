package view;

/**
 * Represents the <code>View</code> of the application
 *
 * Holds a reference to the <code>Parser</code>
 */

public class View {

    private UserInputManager userTaskManager;

    public View () {

        userTaskManager = new UserInputManager();
    }

    /**
     * The main function that keeps the application running until the <code>quit</code> command has been entered by the user
     */

    public void start() {

        boolean finished = false;

        userTaskManager.getPrintStart();
        userTaskManager.showCommands();

        while (!finished) {
            Command command = userTaskManager.getCommand();
            finished = userTaskManager.processCommand(command);
        }
        userTaskManager.getPrintExit();
    }

}
