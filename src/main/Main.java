package main;

/**
 * Main class to run game
 */
public class Main {
    /**
     * Game field
     */
    private static Game game;

    /**
     * Main method
     * @param args
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
    }

    /**
     * Accessor method for game
     * @return
     */
    public static Game getGame() {
        return game;
    }

    /**
     * Mutator method for game
     * @param game
     */
    public static void setGame(Game game) {
        Main.game = game;
    }
}
