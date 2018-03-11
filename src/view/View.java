package view;

import model.Command;

public class View {

    private Parser parser;
    PrintView printView;


    public View () {

        parser = new Parser();
        printView = new PrintView();
    }

    public void start() {

        boolean finished = false;

        printView.printStart();

        while (!finished) {
            Command command = parser.getCommand();
            finished = parser.processCommand(command);
        }

    }

}
