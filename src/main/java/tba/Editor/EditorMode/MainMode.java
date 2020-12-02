package tba.Editor.EditorMode;

import tba.Editor.Editor;
import tba.Utils.Console;
import tba.Utils.ConsoleColor;

public class MainMode extends EditorMode {

    static final private int CHOICE_DIRECTIONS = 1;
    static final private int CHOICE_ROOMS = 2;
    static final private int CHOICE_TRANSITIONS = 3;

    public MainMode(Editor editor)
    {
        super(editor);
    }
    
    @Override
    public void display() {
        Console.colorPrint("What kind of resource do you want to update?\n", ConsoleColor.MAGENTA);
        Console.printChoice(CHOICE_DIRECTIONS, "Directions");
        Console.printChoice(CHOICE_ROOMS, "Rooms");
        Console.printChoice(CHOICE_TRANSITIONS, "Room transitions");
    }

    @Override
    public void interpret(int choice) {
        switch (choice) {
            case CHOICE_DIRECTIONS:
                //
                editor.setMode( new DirectionEditMode(editor) );
                return;
            case CHOICE_ROOMS:
                //
                editor.setMode( new RoomEditMode(editor) );
                return;
            case CHOICE_TRANSITIONS:
                // 
                editor.setMode( new RoomTransitionEditMode(editor) );
                return;
            default:
                Console.warn("Invalid choice!");
        }
    }

}
