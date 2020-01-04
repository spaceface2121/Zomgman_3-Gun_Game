package main;

public class Main {
    private static Game game;

    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
    }

    public static Game getGame() {
        return game;
    }

    public static void setGame(Game game) {
        Main.game = game;
    }
}
