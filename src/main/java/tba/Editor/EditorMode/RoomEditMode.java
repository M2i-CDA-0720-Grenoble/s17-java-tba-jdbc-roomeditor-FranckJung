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

    }
    
}
