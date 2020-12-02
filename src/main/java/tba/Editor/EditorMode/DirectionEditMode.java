package tba.Editor.EditorMode;

import tba.Editor.Editor;
import tba.Model.Direction;
import tba.Utils.Console;
import tba.Utils.ConsoleColor;

public class DirectionEditMode extends EditorMode {
    
    public DirectionEditMode(Editor editor)
    {
        super(editor);
    }
    
    @Override
    public void display() {
        Console.colorPrint("Edit directions\n", ConsoleColor.MAGENTA);
        for (Direction direction: Direction.findAll()) {
            Console.printChoice(direction.getId(), direction.getName());
        }
        Console.printChoice(0, "Add new direction...");
    }

    @Override
    public void interpret(int choice) {
        // Si l'utilisateur a choisi d'ajouter une direction
        Direction direction;
        if (choice == 0) {
            // Crée un objet Direction vide
            direction = new Direction();
            Console.colorPrint("Editing new direction", ConsoleColor.MAGENTA);
        // Sinon, si l'utilisateur a choisi une direction déjà existante
        } else {
            // Récupère la direction choisie en BDD
            direction = Direction.findById(choice);
            Console.colorPrint("Editing '" + direction.getName() + "'", ConsoleColor.MAGENTA);

            // Propose de supprimer la direction choisie
            Console.colorPrint("Do you want to delete '" + direction.getName() + "'? (type YES to delete)", ConsoleColor.YELLOW);
            String input = Console.input().toUpperCase();
            if ("YES".equals(input)) {
                direction.delete();
                return;
            }    
        }

        // Propose de modifier les propriétés de la direction
        Console.colorPrint("Direction name? [" + direction.getName() + "]", ConsoleColor.GREEN_BRIGHT);
        String name = Console.input();
        if (!"".equals(name)) {
            direction.setName(name);
        }
        Console.colorPrint("Direction command? [" + direction.getCommand() + "]", ConsoleColor.GREEN_BRIGHT);
        String command = Console.input().toLowerCase();
        if (!"".equals(command)) {
            direction.setCommand(command);
        }

        // Demande à la direction d'aller sauvegarder son état actuel en BDD
        direction.save();
    }
}
