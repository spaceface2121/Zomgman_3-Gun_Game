package main;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import main.data.Map;
import main.data.ObjectData;
import objects.Bullet;
import objects.Gun;
import objects.MapObject;
import objects.Player;

import java.util.ArrayList;

public class Render {
    private static final Game GAME = Main.getGame();
    private static final Canvas canvas = GAME.getCanvas();
    private static GraphicsContext graphicsContext = GAME.getGraphicsContext();

    public static void drawMainMenu() {

    }

    public static void drawGame() {
        // draws background
        graphicsContext.setFill(Color.WHITE);
        graphicsContext.fillRect(0, 0, GAME.fullW, GAME.fullH);

        // draws blocks
        for (MapObject block : Map.getBlocks()) {
            ObjectData blockData = block.getObjectData();
            graphicsContext.drawImage(blockData.image, blockData.x, blockData.y);
        }

        // draws players
        Player player1 = GAME.getPlayer1(), player2 = GAME.getPlayer2();

        graphicsContext.drawImage(player1.getObjectData().image, player1.getObjectData().x, player1.getObjectData().y);
        graphicsContext.drawImage(player2.getObjectData().image, player2.getObjectData().x, player2.getObjectData().y);

        // draws guns and their respective bullets
        Gun gun1 = player1.getGun(), gun2 = player2.getGun();

        graphicsContext.drawImage(gun1.getObjectData().image, gun1.getObjectData().x, gun1.getObjectData().y);
        graphicsContext.drawImage(gun2.getObjectData().image, gun2.getObjectData().x, gun2.getObjectData().y);

        ArrayList<Bullet> bullets1 = gun1.getBullets(), bullets2 = gun2.getBullets();

        for (Bullet bullet : bullets1) {
            graphicsContext.drawImage(bullet.getObjectData().image, bullet.getObjectData().x, bullet.getObjectData().y);
        }
        for (Bullet bullet : bullets2) {
            graphicsContext.drawImage(bullet.getObjectData().image, bullet.getObjectData().x, bullet.getObjectData().y);
        }
    }

    public static void drawPaused() {

    }

    public static void drawTournamentScreen() {

    }


}
