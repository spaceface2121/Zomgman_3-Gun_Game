package main;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import objects.Player;

public class KeyReleasedHandler implements EventHandler<KeyEvent> {
    private Game GAME = Main.getGame();

    @Override
    public void handle(KeyEvent keyEvent) {
        KeyCode code = keyEvent.getCode();
        GAME.setKey(code, false);
        switch (GAME.getScreen()) {

            case Game.GAME_SCREEN:

                Player p1 = GAME.getPlayer1();
                Player p2 = GAME.getPlayer2();
                if /*player1 controls \/  */ (code == KeyCode.W) {
                    p1.setJumping(false);
                } else if (code == KeyCode.A) {
                    if (!p1.getDir()) { //if player 1 is moving left
                        //p1.setxVel((byte)0);
                    }
                    p1.setStrafing(false);
                } else if (code == KeyCode.D) {
                    if (p1.getDir()) { //if player 1 is moving right
                        //p1.setxVel((byte)0);
                    }
                    p1.setStrafing(false);
                } else if (code == KeyCode.SPACE) {
                    System.out.println("space released");
                    p1.getGun().stopFiring();
                    p1.setHoldingShoot(false);
                } else if /*player2 controls \/  */ (code == KeyCode.UP) {
                    p2.setJumping(false);
                } else if (code == KeyCode.LEFT) {
                    System.out.println("left released");
                    if (!p2.getDir()) { //if player 2 is moving left
                        //p2.setxVel((byte)0);
                    }
                    p2.setStrafing(false);
                } else if (code ==  KeyCode.RIGHT) {
                    System.out.println("right released");
                    if (p2.getDir()) { //if player 2 is moving right
                        //p2.setxVel((byte)0);
                    }
                    p2.setStrafing(false);
                } else if (code == KeyCode.SLASH) {
                    p2.getGun().stopFiring();
                    p2.setHoldingShoot(false);
                }
                break;
        }
    }

    public void setGame(Game game) {
        this.GAME = game;
    }
}
