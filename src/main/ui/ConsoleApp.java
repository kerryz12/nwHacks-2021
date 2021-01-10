package main.ui;

import main.model.Class;
import main.model.ClassList;

import java.util.Locale;
import java.util.Scanner;

public class ConsoleApp {
    private ClassList classlist;
    private Scanner input;
    private String user;


    public ConsoleApp() {
        runConsoleApp();
    }

    private void setup() {
        classlist = new ClassList();
        input = new Scanner(System.in);

        System.out.println("\nGreetings new user! Thanks for choosing to use this program.");
        System.out.println("\nTo get started, please enter your username: ");

        user = input.next();
    }

    public void runConsoleApp() {
        boolean keepRunning = true;
        String command;

        setup();

        while (keepRunning) {
            displayMainMenu();
            command = input.next();
            command = command.toLowerCase();
            input.nextLine();

            if (command.equals("exit")) {
                keepRunning = false;
            } else {
                processMainCommand(command);
            }
        }
    }

    public void processMainCommand(String command) {
        switch (command) {
            case "add":
                doAdd();
                break;

            case "remove":
                doRemove();
                break;

            case "edit":
                doEdit();
                break;

            case "view":
                doView();
        }
    }

    public void displayMainMenu() {
        System.out.println("\n Welcome, " + user);
        System.out.println("\t add - add new class");
        System.out.println("\t remove - remove a class");
        System.out.println("\t edit - edit a class");
        System.out.println("\t view - view classes");
        System.out.println("\t save - save classes");
        System.out.println("\t load - load classes");
        System.out.println("\t exit - quit app");
    }

    public void doAdd() {
        System.out.println("Enter class code: ");
        String code = input.next();
        System.out.println("Enter importance of class (0 to 10): ");
        int importance = input.nextInt();

    }

    public static void main(String[] args) {
    }
}
