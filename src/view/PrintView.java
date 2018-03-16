package view;

public class PrintView {


    public void printMessage(String message) {
        System.out.println("\n" + message);
    }

    public void printInput(String message) {
        System.out.println("\n" + message + "\n");
        System.out.print("> ");
    }

}
