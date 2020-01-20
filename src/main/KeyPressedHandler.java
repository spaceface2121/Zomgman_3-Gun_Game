package main;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import objects.Player;

public class KeyPressedHandler implements EventHandler<KeyEvent> {
    private Game GAME = Main.getGame();

    @Override
    /**
     * Handles all the Key presses except for sideways player movement
     */
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

    /**
     * Mutator method for game
     * @param game
     */
    public void setGame(Game game) {
        this.GAME = game;
    }

    /**
     * Minimizes game window
     */
    private void minimize() {
        //GAME.getStage().setMaximized(false);
        GAME.getStage().setFullScreen(false);
        GAME.getStage().setHeight(GAME.h);
        GAME.getStage().setWidth(GAME.w);
        GAME.getCanvas().setHeight(GAME.h);
        GAME.getCanvas().setWidth(GAME.w);
    }

    /**
     * Maximizes game window
     */
    private void maximize() {
        //GAME.getStage().setMaximized(true);
        GAME.getStage().setFullScreen(true);
        GAME.getStage().setHeight(GAME.fullH);
        GAME.getStage().setWidth(GAME.fullW);
        GAME.getCanvas().setHeight(GAME.fullH);
        GAME.getCanvas().setWidth(GAME.fullW);
    }
}
