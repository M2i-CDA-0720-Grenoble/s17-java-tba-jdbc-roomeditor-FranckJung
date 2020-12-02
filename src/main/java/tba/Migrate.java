package tba;

import java.util.Scanner;

import tba.Utils.DatabaseHandler;

public class Migrate {

    private static Scanner scanner;

    public static void main(String[] args) throws Exception
    {
        scanner = new Scanner(System.in);

        if (yesNoQuestion("Do you want to drop the database and recreate the schema?")) {
            DatabaseHandler.runScript("schema.sql");
        }

        if (yesNoQuestion("Do you want to repopulate the database with sample data?")) {
            DatabaseHandler.runScript("data.sql");
        }

        scanner.close();
    }

    private static boolean yesNoQuestion(String question)
    {
        System.out.println(question + " [Y/N]");
        String userInput = scanner.nextLine().trim().toUpperCase();
        return "Y".equals(userInput);
    }
    
}
