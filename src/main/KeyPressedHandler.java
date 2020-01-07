package main;

import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Screen;
import logic.GunLogic;
import logic.PlayerLogic;
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
                    if (p1.getxVel() != PlayerLogic.getxVel()) {
                        if (p1.getxVel() > -PlayerLogic.getMinXVel()) {
                            p1.setxVel(-PlayerLogic.getMinXVel());
                        } else if (p1.getxVel() > PlayerLogic.getxAcceleration() - PlayerLogic.getxVel()) {
                            p1.setxVel(p1.getxVel() - PlayerLogic.getxAcceleration());
                        } else {
                            p1.setxVel(-PlayerLogic.getxVel());
                        }
                    }
                    p1.setStrafing(true);
                } else if (code == KeyCode.D) {
                    if (!p1.getDir()) { //if player 1 is moving left
                        p1.changeDirection();
                        p1.getGun().changeDirection();
                    }
                    if (p1.getxVel() != -PlayerLogic.getxVel()) {
                        if (p1.getxVel() < PlayerLogic.getMinXVel()) {
                            p1.setxVel(PlayerLogic.getMinXVel());
                        } else if (p1.getxVel() < PlayerLogic.getxVel() - PlayerLogic.getxAcceleration()) {
                            p1.setxVel(p1.getxVel() + PlayerLogic.getxAcceleration());
                        } else {
                            p1.setxVel(PlayerLogic.getxVel());
                        }
                    }
                    p1.setStrafing(true);
                } else if (code == KeyCode.SPACE) {
                    p1.setHoldingShoot(true);
                    p1.getGun().fire();
                } else if /*player2 controls \/  */ (code == KeyCode.UP) {
                    p2.jump();
                } else if (code == KeyCode.LEFT) {
                    if (p2.getDir()) { //if player 2 is moving right
                        p2.changeDirection();
                        p2.getGun().changeDirection();
                    }
                    if (p2.getxVel() != PlayerLogic.getxVel()) {
                        if (p2.getxVel() > -PlayerLogic.getMinXVel()) {
                            p2.setxVel(-PlayerLogic.getMinXVel());
                        } else if (p2.getxVel() > PlayerLogic.getxAcceleration() - PlayerLogic.getxVel()) {
                            p2.setxVel(p2.getxVel() - PlayerLogic.getxAcceleration());
                        } else {
                            p2.setxVel(-PlayerLogic.getxVel());
                        }
                    }
                    p2.setStrafing(true);
                } else if (code ==  KeyCode.RIGHT) {
                    if (!p2.getDir()) { //if player 2 is moving right
                        p2.changeDirection();
                        p2.getGun().changeDirection();
                    }
                    if (p2.getxVel() != -PlayerLogic.getxVel()) {
                        if (p2.getxVel() < PlayerLogic.getMinXVel()) {
                            p2.setxVel(PlayerLogic.getMinXVel());
                        } else if (p2.getxVel() < PlayerLogic.getxVel() - PlayerLogic.getxAcceleration()) {
                            p2.setxVel(p2.getxVel() + PlayerLogic.getxAcceleration());
                        } else {
                            p2.setxVel(PlayerLogic.getxVel());
                        }
                    }
                    p2.setStrafing(true);
                } else if (code == KeyCode.SLASH) {
                    p2.setHoldingShoot(true);
                    p2.getGun().fire();
                } else if (code == KeyCode.DIGIT1) {
                    p1.downgradeGun();
                } else if (code == KeyCode.DIGIT2) {
                    p1.upgradeGun();
                } else if (code == KeyCode.DIGIT9) {
                    p2.downgradeGun();
                } else if (code == KeyCode.DIGIT0) {
                    p2.upgradeGun();
                }
                break;
            case 2:
                if (code == KeyCode.ENTER) {
                    GAME.setScreen((byte)1);
                    maximize();
                    Render.drawGame();
                    System.out.println("screen: " + GAME.getScreen());
                } else {
                    //blah
                }
                break;
            case 3: break;
        }
    }

    private void minimize() {
        //GAME.getStage().setMaximized(false);
        GAME.getStage().setFullScreen(false);
        GAME.getStage().setHeight(GAME.h);
        GAME.getStage().setWidth(GAME.w);
        GAME.getCanvas().setHeight(GAME.h);
        GAME.getCanvas().setWidth(GAME.w);
    }

    private void maximize() {
        //GAME.getStage().setMaximized(true);
        GAME.getStage().setFullScreen(true);
        GAME.getStage().setHeight(GAME.fullH);
        GAME.getStage().setWidth(GAME.fullW);
        GAME.getCanvas().setHeight(GAME.fullH);
        GAME.getCanvas().setWidth(GAME.fullW);
    }
}
