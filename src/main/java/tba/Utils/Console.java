package tba.Utils;

import java.util.Scanner;

public class Console {

    private static Console instance;
    private Scanner scanner;

    private Console()
    {
        scanner = new Scanner(System.in);
    }

    public static Console getInstance()
    {
        if (instance == null) {
            instance = new Console();
        }

        return instance;
    }

    public static void colorPrint(String message, ConsoleColor color) {
        System.out.println(color + message + ConsoleColor.RESET);
    }
    
    public static void warn(String message)
    {
        colorPrint(message + "\n", ConsoleColor.YELLOW);
    }

    public static void error(String message)
    {
        colorPrint(message + "\n", ConsoleColor.RED);
    }

    public static String input()
    {
        System.out.print("\n> ");
        return getInstance().scanner.nextLine().trim();
    }

    public static void printChoice(int index, String description)
    {
        System.out.println(ConsoleColor.CYAN + "[" +  ConsoleColor.GREEN + index + ConsoleColor.CYAN + "] " + description + ConsoleColor.RESET);
    }

}
