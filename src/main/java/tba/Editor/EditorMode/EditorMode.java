package tba.Editor.EditorMode;

import tba.Editor.Editor;

public abstract class EditorMode {

    protected Editor editor;

    public EditorMode(Editor editor)
    {
        this.editor = editor;
    }
    
    abstract public void display();

    abstract public void interpret(int userInput);

}
