package main;

import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import logic.GunLogic;
import logic.PlayerLogic;
import main.data.Images;
import objects.Player;

public class KeyPressedHandler implements EventHandler<KeyEvent> {
    private Game GAME = Main.getGame();

    @Override
    public void handle(KeyEvent keyEvent) {
        KeyCode code = keyEvent.getCode();
        GAME.setKey(code, true);
        switch (GAME.getScreen()) {
            case Game.MENU_SCREEN:
                if (code == KeyCode.ENTER) {
                    if (GAME.isFinished()) {
                        GAME.reset();
                    }
                    GAME.setScreen(Game.GAME_SCREEN);
                    maximize();
                    //Render.drawGame();
                }
                break;
            case Game.GAME_SCREEN:
                Player p1 = GAME.getPlayer1();
                Player p2 = GAME.getPlayer2();
                if (code == KeyCode.ESCAPE) {
                    GAME.setScreen(Game.PAUSE_SCREEN);
                    //minimize();
                    Render.drawPaused();
                } else if /*player1 controls \/  */ (code == KeyCode.W) {
                    p1.jump();
                    p1.setJumping(true);
//                } else if (code == KeyCode.A) {
//                    if (p1.getDir() || p1.getxVel() > 0) { //if player 1 is moving right
//                        p1.changeDirection();
//                    }
//                    if (p1.getxVel() != PlayerLogic.getxVel()) {
//                        if (p1.getxVel() > -PlayerLogic.getMinXVel()) {
//                            p1.setxVel(-PlayerLogic.getMinXVel());
//                        } else if (p1.getxVel() > PlayerLogic.getxAcceleration() - PlayerLogic.getxVel()) {
//                            p1.setxVel(p1.getxVel() - PlayerLogic.getxAcceleration());
//                        } else {
//                            p1.setxVel(-PlayerLogic.getxVel());
//                        }
//                    }
//                    p1.setStrafing(true);
//                } else if (code == KeyCode.D) {
//                    if (!p1.getDir() || p1.getxVel() < 0) { //if player 1 is moving left
//                        p1.changeDirection();
//                    }
//                    if (p1.getxVel() != -PlayerLogic.getxVel()) {
//                        if (p1.getxVel() < PlayerLogic.getMinXVel()) {
//                            p1.setxVel(PlayerLogic.getMinXVel());
//                        } else if (p1.getxVel() < PlayerLogic.getxVel() - PlayerLogic.getxAcceleration()) {
//                            p1.setxVel(p1.getxVel() + PlayerLogic.getxAcceleration());
//                        } else {
//                            p1.setxVel(PlayerLogic.getxVel());
//                        }
//                    }
//                    p1.setStrafing(true);
                } else if (code == KeyCode.SPACE) {
                    p1.setHoldingShoot(true);
                    p1.getGun().fire();
                } else if /*player2 controls \/  */ (code == KeyCode.UP) {
                    p2.jump();
                    p2.setJumping(true);
                } else if (code == KeyCode.R) {
                    p1.getGun().reload(System.currentTimeMillis());
                } else if (code == KeyCode.SHIFT) {
                    p2.getGun().reload(System.currentTimeMillis());
//                else if (code == KeyCode.LEFT) {
//                    System.out.println("left pressed");
//                    if (p2.getDir() || p2.getxVel() > 0) { //if player 2 is moving right
//                        p2.changeDirection();
//                    }
//                    if (p2.getxVel() != PlayerLogic.getxVel()) {
//                        if (p2.getxVel() > -PlayerLogic.getMinXVel()) {
//                            p2.setxVel(-PlayerLogic.getMinXVel());
//                        } else if (p2.getxVel() > PlayerLogic.getxAcceleration() - PlayerLogic.getxVel()) {
//                            p2.setxVel(p2.getxVel() - PlayerLogic.getxAcceleration());
//                        } else {
//                            p2.setxVel(-PlayerLogic.getxVel());
//                        }
//                    }
//                    p2.setStrafing(true);
//                } else if (code ==  KeyCode.RIGHT) {
//                    System.out.println("right pressed");
//                    if (!p2.getDir() || p2.getxVel() < 0) { //if player 2 is moving right
//                        p2.changeDirection();
//                    }
//                    if (p2.getxVel() != -PlayerLogic.getxVel()) {
//                        if (p2.getxVel() < PlayerLogic.getMinXVel()) {
//                            p2.setxVel(PlayerLogic.getMinXVel());
//                        } else if (p2.getxVel() < PlayerLogic.getxVel() - PlayerLogic.getxAcceleration()) {
//                            p2.setxVel(p2.getxVel() + PlayerLogic.getxAcceleration());
//                        } else {
//                            p2.setxVel(PlayerLogic.getxVel());
//                        }
//                    }
//                    p2.setStrafing(true);
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
            case Game.PAUSE_SCREEN:
                if (code == KeyCode.ENTER) {
                    GAME.setScreen((Game.GAME_SCREEN));
                    maximize();
                    //Render.drawGame();
                } else if (code == KeyCode.ESCAPE){
                    GAME.setFinished(true);
                    GAME.setScreen(Game.MENU_SCREEN);
                    Render.drawMainMenu();
                }
                break;
            case Game.WIN_SCREEN:
                if (code == KeyCode.ENTER) {
                    GAME.reset();
                    GAME.setScreen(Game.GAME_SCREEN);
                    Render.setDrawnWinScreen(false);
                } else if (code == KeyCode.ESCAPE) {
                    GAME.setScreen(Game.MENU_SCREEN);
                    minimize();
                    Render.drawMainMenu();
                    Render.setDrawnWinScreen(false);
                }
                break;
        }
    }

    public void setGame(Game game) {
        this.GAME = game;
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
