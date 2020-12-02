package tba.Editor.EditorMode;

import tba.Editor.Editor;
import tba.Model.RoomTransition;
import tba.Utils.Console;
import tba.Utils.ConsoleColor;

public class RoomTransitionEditMode extends EditorMode {
    
    public RoomTransitionEditMode(Editor editor)
    {
        super(editor);
    }

    @Override
    public void display() {
        Console.colorPrint("Edit room transitions\n", ConsoleColor.MAGENTA);
        for (RoomTransition transition: RoomTransition.findAll()) {
            Console.printChoice(transition.getId(), transition.getFromRoom().getName() + " " + transition.getDirection().getName() + "> " + transition.getToRoom().getName() );
        }
        Console.printChoice(0, "Add new transition...");
    }

    @Override
    public void interpret(int choice) {

    }
    
}
