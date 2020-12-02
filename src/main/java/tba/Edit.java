package tba;

import tba.Editor.Editor;

public class Edit {
    
    public static void main(String[] args) throws Exception {
        Editor editor = new Editor();

        while (editor.isRunning()) {
            editor.update();
        }
    }

}
