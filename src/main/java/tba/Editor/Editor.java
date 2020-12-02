package tba.Editor;

import tba.Editor.EditorMode.EditorMode;
import tba.Editor.EditorMode.MainMode;
import tba.Utils.Console;

public class Editor {
    
    private boolean isRunning;
    private EditorMode mode;

    public Editor()
    {
        isRunning = true;

        mode = new MainMode(this);
    }

    public boolean isRunning()
    {
        return isRunning;
    }

    public void terminate()
    {
        isRunning = false;
    }

    public Editor setMode(EditorMode newMode)
    {
        mode = newMode;
        return this;
    }

    public void update()
    {
        // Affiche le menu actuel
        mode.display();
        
        // Attend une saisie de l'utilisateur
        String userInput = Console.input();

        // Si la saisie de l'utilisateur est vide, retourne dans le menu principal
        if ("".equals(userInput)) {
            setMode( new MainMode(this) );
            return;
        }

        // Sinon, tente de convertir la saisie de l'utilisateur en nombre
        int choice;
        try {
            choice = Integer.parseInt(userInput);
        }
        catch (NumberFormatException exception) {
            Console.warn("You must input a number!");
            return;
        }

        // Interprète la saisie de l'utilisateur
        mode.interpret(choice);
    }

}
