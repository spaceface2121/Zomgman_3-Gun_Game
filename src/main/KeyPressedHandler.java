package main;

import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Screen;
import logic.GunLogic;
import main.data.Images;
import objects.Player;

public class KeyPressedHandler implements EventHandler<KeyEvent> {
    private final Game GAME = Main.getGame();

    @Override
    public void handle(KeyEvent keyEvent) {
        KeyCode code = keyEvent.getCode();
        switch (GAME.getScreen()) {
            /*Screens:
              0: Main menu screen
              1: Game screen
              2: Game paused screen
              3: Tournament mode screen
            */
            case 0:
                if (code == KeyCode.ENTER) {
                    GAME.setScreen((byte)1);
                    maximize();
                    Render.drawGame();
                    System.out.println("screen: " + GAME.getScreen());
                }
                break;
            case 1:
                Player p1 = GAME.getPlayer1();
                Player p2 = GAME.getPlayer2();
                if (code == KeyCode.ESCAPE) {
                    GAME.setScreen((byte)2);
                    minimize();
                    Render.drawPaused();
                    System.out.println("screen: " + GAME.getScreen());
                } else if /*player1 controls \/  */ (code == KeyCode.W) {
                    p1.jump();
                } else if (code == KeyCode.A) {
                    if (p1.getDir()) { //if player 1 is moving right
                        p1.changeDirection();
                        p1.getGun().changeDirection();
                    }
                    p1.setxVel(-5);
                } else if (code == KeyCode.D) {
                    if (!p1.getDir()) { //if player 1 is moving left
                        p1.changeDirection();
                        p1.getGun().changeDirection();
                    }
                    p1.setxVel(5);
                } else if (code == KeyCode.SPACE) {
                    p1.getGun().fire();
                    p1.setHoldingShoot(true);
                } else if /*player2 controls \/  */ (code == KeyCode.UP) {
                    GAME.getPlayer2().jump();
                } else if (code == KeyCode.LEFT) {
                    if (p2.getDir()) { //if player 2 is moving right
                        p2.changeDirection();
                        p2.getGun().changeDirection();
                    }
                    GAME.getPlayer2().setxVel(-5);
                } else if (code ==  KeyCode.RIGHT) {
                    if (!p2.getDir()) { //if player 2 is moving right
                        p2.changeDirection();
                        p2.getGun().changeDirection();
                    }
                    GAME.getPlayer2().setxVel(5);
                } else if (code == KeyCode.SLASH) {
                    p2.getGun().fire();
                    p2.setHoldingShoot(true);
                } else if (code == KeyCode.B) {
                    for (int i = p2.getGun().getType(); i < GunLogic.UZI; i++) {
                        p2.upgradeGun();
                    }
                }
                break;
            case 2:
                if (code == KeyCode.ESCAPE) {
                    GAME.setScreen((byte)1);
                    maximize();
                    Render.drawGame();
                    System.out.println("screen: " + GAME.getScreen());
                }
                break;
            case 3: break;
        }
    }

    private void minimize() {
        GAME.getStage().setMaximized(false);
        GAME.getStage().setFullScreen(false);
        GAME.getStage().setHeight(GAME.h);
        GAME.getStage().setWidth(GAME.w);
        GAME.getCanvas().setHeight(GAME.h);
        GAME.getCanvas().setWidth(GAME.w);
    }

    private void maximize() {
        GAME.getStage().setMaximized(true);
        GAME.getStage().setFullScreen(true);
        GAME.getStage().setHeight(GAME.fullH);
        GAME.getStage().setWidth(GAME.fullW);
        GAME.getCanvas().setHeight(GAME.fullH);
        GAME.getCanvas().setWidth(GAME.fullW);
    }
}
