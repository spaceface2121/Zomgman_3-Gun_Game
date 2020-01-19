package main;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import logic.GunLogic;
import objects.*;

import java.util.ArrayList;

public class Render {
    private static Game GAME = Main.getGame();
    private static GraphicsContext graphicsContext = GAME.getGraphicsContext();
    private static boolean drawnWinScreen = false;

    private static Color p1Color = Color.rgb(117, 148, 224);
    private static Color p2Color = Color.rgb(228, 136, 157);
    private static Color transparentBlack = Color.rgb(0, 0, 0, 0.6);
    private static float winScreenTextX = (GAME.fullW / (float)2.0) - 290 * Game.scaleFullX, winScreenTextY = (GAME.fullH / (float)2.0) - 50 * Game.scaleFullY;
    private static float pauseScreenTextX = (GAME.w / (float)2.0) - 390 * Game.scaleX, pauseScreenTextY = (GAME.h / (float)2.0) - 60 * Game.scaleY;

    public static void drawMainMenu() {
        graphicsContext.drawImage(Images.getMainMenuImage(), 0, 0);
    }

    public static void drawGame() {
        // draws background
        graphicsContext.drawImage(Images.getBackgroundImage(), 0, 0);

        // draws blocks
        for (MapObject block : Main.getGame().getMap().getBlocks()) {
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

        // draws health percentage and ammo remaining
        graphicsContext.setFont(Font.font("Impact", 20 * Game.scaleFullX));

        graphicsContext.setFill(p1Color);
        graphicsContext.fillText(player1.getHealth() + "%", 10 * Game.scaleFullX, GAME.fullH - 8 * Game.scaleFullY);
        String ammoText;
        if (player1.getGun().isReloading()) {
            ammoText = "reloading...";
        } else {
            ammoText = player1.getGun().getRemainingAmmo() + "/" + GunLogic.getMagCapacity(player1.getGun().getType());
        }
        graphicsContext.fillText(ammoText, 160 * Game.scaleFullX, GAME.fullH - GAME.getMap().getBlocks().get(0).getObjectData().h / 3.75 * Game.scaleFullY);

        graphicsContext.setFill(p2Color);
        graphicsContext.fillText(player2.getHealth() + "%", GAME.fullW - 50 * Game.scaleFullX, GAME.fullH - 8 * Game.scaleFullY);
        if (player2.getGun().isReloading()) {
            ammoText = "reloading...";
        } else {
            ammoText = player2.getGun().getRemainingAmmo() + "/" + GunLogic.getMagCapacity(player2.getGun().getType());
        }
        graphicsContext.fillText(ammoText, GAME.fullW - 205 * Game.scaleFullX, GAME.fullH - GAME.getMap().getBlocks().get(0).getObjectData().h / 3.75 * Game.scaleFullY);
    }

    public static void drawPaused() {
        System.out.println("draw pause screen");

        //drawGame();
        graphicsContext.setFill(transparentBlack);
        graphicsContext.fillRect(0, 0, GAME.fullW, GAME.fullH);

        graphicsContext.setFont(Font.font("Impact", 100));

        graphicsContext.setFill(Color.WHITE);
        graphicsContext.fillText("Game paused", pauseScreenTextX, pauseScreenTextY);
        graphicsContext.setFont(Font.font("Impact", 50));
        graphicsContext.fillText("Press enter to return to game", pauseScreenTextX - 15, pauseScreenTextY + 100);
        graphicsContext.fillText("Press escape to go to main menu", pauseScreenTextX - 50, pauseScreenTextY + 150);
    }

    public static void drawWinScreen() {
        if (!drawnWinScreen) {
            drawnWinScreen = true;
            System.out.println("draw win screen");

            //drawGame();
            graphicsContext.setFill(transparentBlack);
            graphicsContext.fillRect(0, 0, GAME.fullW, GAME.fullH);

            graphicsContext.setFont(Font.font("Impact", 100 * Game.scaleFullX));
            String winScreenText;
            if (GAME.getWinner()) { //player 1 won
                winScreenText = "Player 1 Wins!";
                graphicsContext.setFill(p1Color);
            } else { //player 2 won
                winScreenText = "Player 2 Wins!";
                graphicsContext.setFill(p2Color);
            }
            graphicsContext.fillText(winScreenText, winScreenTextX, winScreenTextY);
            graphicsContext.setFont(Font.font("Impact", 50 * Game.scaleFullX));
            graphicsContext.fillText("Press enter to rematch", winScreenTextX + 50 * Game.scaleFullX, winScreenTextY + 100 * Game.scaleFullY);
            graphicsContext.fillText("Press escape to return to main menu", winScreenTextX - 90 * Game.scaleFullX, winScreenTextY + 150 * Game.scaleFullY);
        }
    }

    public static void drawRulesScreen() {
        
    }

    public static void setDrawnWinScreen(boolean drawnWinScreen) {
        Render.drawnWinScreen = drawnWinScreen;
    }

    public static void setGame(Game game) {
        Render.GAME = game;
    }

    public static void setGraphicsContext(GraphicsContext graphicsContext) {
        Render.graphicsContext = graphicsContext;
    }
}
