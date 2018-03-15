package view;

public class PrintView {


    public void printMessage(String mesage) {
        System.out.println();
        System.out.println(mesage);
    }

    public void printInput(String message) {
        System.out.println();
        System.out.println(message);
        System.out.println();
        System.out.print("> ");
    }

}
