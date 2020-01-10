package objects;

import javafx.scene.image.Image;
import logic.PlayerLogic;
import main.data.Images;
import main.data.ObjectData;

public class Hand extends MapObject {
    private boolean player1or2;

    public Hand(Player player, boolean dir) {
        super(getInitialHandData(player, dir));
        player1or2 = player.isPlayer1or2();
    }

    private static Image getHandImage(Player player) {
        return Images.getHandImage(player.isPlayer1or2());
    }

    private static ObjectData getInitialHandData(Player player, boolean dir) {
        ObjectData playerData = player.getObjectData();
        float x;
        if (player.getDir() && dir || !(player.getDir() || dir)) { //if the player is facing right and hand is right or player faces left and hand is left
            x = playerData.x + playerData.w + 8;
        } else { //if facing right and hand is left or facing left and hand is right
            x = playerData.x - 5;
        }
        return new ObjectData(x, (float)(playerData.y + 1.0 / 3 * playerData.h), getHandImage(player));
    }

    public void updateCoordinates(Player player, boolean dir) {
        if (player.getDir() && dir || !(player.getDir() || dir)) { //if the player is facing right and hand is right or player faces left and hand is left
            getObjectData().x = player.getObjectData().x + player.getObjectData().w + 10;
        } else { //if facing right and hand is left or facing left and hand is right
            getObjectData().x = player.getObjectData().x - 10;
        }
        getObjectData().y = (int)(player.getObjectData().y + 1.0 / 3 * player.getObjectData().h);
    }
}
