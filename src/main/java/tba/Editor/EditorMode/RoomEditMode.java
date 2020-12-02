package tba.Editor.EditorMode;

import tba.Editor.Editor;
import tba.Model.Room;
import tba.Utils.Console;
import tba.Utils.ConsoleColor;

public class RoomEditMode extends EditorMode {
    
    public RoomEditMode(Editor editor)
    {
        super(editor);
    }

    @Override
    public void display() {
        Console.colorPrint("Edit rooms\n", ConsoleColor.MAGENTA);
        for (Room room: Room.findAll()) {
            Console.printChoice(room.getId(), room.getName());
        }
        Console.printChoice(0, "Add new room...");
    }

    @Override
    public void interpret(int choice) {
        //Si l'utilisateur a choisi d'ajouter une pièce
        Room room;
        if(choice == 0){
            //Crée un objet Room vide
            room = new Room();
            Console.colorPrint("Editing new room", ConsoleColor.MAGENTA);
            // Sinon, si l'utilisateur a choisi une pièce déjà existante
        } else {
            // Récupère la pièce choisie en BDD
            room = Room.findById(choice);
            Console.colorPrint("Editing '" + room.getName() + "'", ConsoleColor.MAGENTA);

            // Propose de supprimer la pièce choisie
            Console.colorPrint("Do you want to delete '" + room.getName() + "'? (type YES to delete)", ConsoleColor.YELLOW);
            String input = Console.input().toUpperCase();
            if ("YES".equals(input)) {
                room.delete();
                return;
            }    
        }

        // Propose de modifier les propriétés de pièce
        Console.colorPrint("Room name? [" + room.getName() + "]", ConsoleColor.GREEN_BRIGHT);
        String name = Console.input();
        if (!"".equals(name)) {
            room.setName(name);
        }
        Console.colorPrint("Room description? [" + room.getDescription() + "]", ConsoleColor.GREEN_BRIGHT);
        String description = Console.input().toLowerCase();
        if (!"".equals(description)) {
            room.setDescription(description);
        }

        // Demande à la direction d'aller sauvegarder son état actuel en BDD
        room.save();
    }
    
}
