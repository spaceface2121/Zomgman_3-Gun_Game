package main;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import objects.Player;

public class KeyReleasedHandler implements EventHandler<KeyEvent> {
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

                break;
            case 1:
                Player p1 = GAME.getPlayer1();
                Player p2 = GAME.getPlayer2();
                if /*player1 controls \/  */ (code == KeyCode.W) {

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

                } else if (code == KeyCode.LEFT) {
                    if (!p2.getDir()) { //if player 2 is moving left
                        //p2.setxVel((byte)0);
                    }
                    p2.setStrafing(false);
                } else if (code ==  KeyCode.RIGHT) {
                    if (p2.getDir()) { //if player 2 is moving right
                        //p2.setxVel((byte)0);
                    }
                    p2.setStrafing(false);
                } else if (code == KeyCode.SLASH) {
                    p2.getGun().stopFiring();
                    p2.setHoldingShoot(false);
                }
                break;
            case 2:
                if (code == KeyCode.ESCAPE) {

                }
                break;
            case 3: break;
        }
    }
}
