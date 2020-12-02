package tba;

import tba.Game.Game;

/**
 * Hello world!
 */
public final class App {

    private App() {
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) throws Exception {
        Game game = new Game();

        while (game.isRunning()) {
            game.update();
        }
    }

}
