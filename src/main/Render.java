package main;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import main.data.Images;
import main.data.Map;
import main.data.ObjectData;
import objects.*;

import java.util.ArrayList;

public class Render {
    private static final Game GAME = Main.getGame();
    private static final Canvas canvas = GAME.getCanvas();
    private static GraphicsContext graphicsContext = GAME.getGraphicsContext();

    public static void drawMainMenu() {
        graphicsContext.drawImage(Images.getMainMenuImage(), 0, 0);
    }

    public static void drawGame() {
        // draws background
        graphicsContext.drawImage(Images.getBackgroundImage(), 0, 0);

        // draws blocks
        for (MapObject block : Map.getBlocks()) {
            ObjectData blockData = block.getObjectData();
            graphicsContext.drawImage(blockData.image, blockData.x, blockData.y);
        }

        // draws players
        Player player1 = GAME.getPlayer1(), player2 = GAME.getPlayer2();
//        graphicsContext.setFont(Font.getDefault());
//        graphicsContext.fillText(player1.getHealth() + "%", player1.getObjectData().x, player1.getObjectData().y - 20);
//        graphicsContext.fill();
        graphicsContext.drawImage(player1.getObjectData().image, player1.getObjectData().x, player1.getObjectData().y);
//        graphicsContext.fillText(player2.getHealth() + "%", player2.getObjectData().x, player2.getObjectData().y - 20);
//        graphicsContext.fill();
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

        // draws hands
        Hand hand1r = player1.getHand(false), hand1l = player1.getHand(true), hand2r = player2.getHand(false), hand2l = player2.getHand(true);

        graphicsContext.drawImage(hand1r.getObjectData().image, hand1r.getObjectData().x, hand1r.getObjectData().y);
        graphicsContext.drawImage(hand1l.getObjectData().image, hand1l.getObjectData().x, hand1l.getObjectData().y);
        graphicsContext.drawImage(hand2r.getObjectData().image, hand2r.getObjectData().x, hand2r.getObjectData().y);
        graphicsContext.drawImage(hand2l.getObjectData().image, hand2l.getObjectData().x, hand2l.getObjectData().y);

        graphicsContext.setFont(Font.font("Impact", 20 * Game.scaleFullX));
        graphicsContext.setFill(Color.rgb(117, 148, 224));
        graphicsContext.fillText(GAME.getPlayer1().getHealth() + "%", 10 * Game.scaleFullX, GAME.fullH - 8 * Game.scaleFullY);

        graphicsContext.setFill(Color.rgb(228, 136, 157));
        graphicsContext.fillText(GAME.getPlayer2().getHealth() + "%", GAME.fullW - 55 * Game.scaleFullX, GAME.fullH - 8 * Game.scaleFullY);
    }

    public static void drawPaused() {

    }
}
