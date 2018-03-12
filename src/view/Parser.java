package view;

import controller.Controller;

import java.util.Scanner;

public class Parser {

    private CommandWords commands;
    private Controller controller;
    private PrintView printView;
    private Scanner reader;

    public Parser () {

        commands = new CommandWords();
        controller = new Controller();
        printView = new PrintView();
        reader = new Scanner(System.in);
    }

    public Command getCommand() {
        String inputLine;
        String word1 = null;
        String word2 = null;

        System.out.print("> ");

        inputLine = reader.nextLine();

        Scanner tokanizer = new Scanner(inputLine);

        if (tokanizer.hasNext()) {

            word1 = tokanizer.next();

            if (tokanizer.hasNext()) {

                word2 = tokanizer.next();
            }
        }

        return new Command(commands.getCommandWord(word1), word2);
    }

    public boolean processCommand(Command command) {

        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) {

            case UNKNOWN:
                printView.printUnknown();
                break;

            case HELP:
                printView.printHelp();
                commands.showAll();
                break;

            case NEW:
                printView.printNew();
                break;

            case EDIT:
                printView.printEdit();
                break;

            case REMOVE:
                printView.printRemove();
                break;

            case QUIT:
                wantToQuit = quit(command);
                break;
        }
        return wantToQuit;
    }

    public void showCommands() {

        commands.showAll();
    }

    private boolean quit(Command command) {

        if(command.hasSecondWord()) {
            printView.printQuitWhat();
            return false;
        } else {

            return true;
        }
    }

    public void getPrintStart() {

        printView.printStart();
    }

}
