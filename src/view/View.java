package view;

/**
 * Represents the <code>View</code> of the application
 *
 * Holds a reference to the <code>Parser</code>
 */

public class View {

    private Parser parser;

    public View () {

        parser = new Parser();
    }

    /**
     * The main function that keeps the application running until the <code>quit</code> command has been entered by the user
     */

    public void start() {

        boolean finished = false;

        parser.getPrintStart();
        parser.showCommands();

        while (!finished) {
            Command command = parser.getCommand();
            finished = parser.processCommand(command);
        }

    }

}
