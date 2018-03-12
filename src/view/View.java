package view;

public class View {

    private Parser parser;

    public View () {

        parser = new Parser();
    }

    public void start() {

        boolean finished = false;

        parser.getPrintStart();

        while (!finished) {
            Command command = parser.getCommand();
            finished = parser.processCommand(command);
        }

    }

}
