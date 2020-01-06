package objects;

import javafx.scene.image.Image;
import main.data.Images;
import main.data.ObjectData;

public class Hand extends MapObject {
    private Image hand1 = Images.getImages().get(Images.HAND1);
    private Image hand2 = Images.getImages().get(Images.HAND2);
    private boolean player1or2;

    public Hand(Player player, boolean dir) {
        super(getInitialHandData(player, dir));
        player1or2 = player.isPlayer1or2();
    }

    private static Image getHandImage(Player player) {
        if (player.isPlayer1or2()) { //if 1
            return Images.getImages().get(Images.HAND1);
        } else { //if 2
            return Images.getImages().get(Images.HAND2);
        }
    }

    private static ObjectData getInitialHandData(Player player, boolean dir) {
        ObjectData playerData = player.getObjectData();
        float x;
        if (player.getDir() && dir || !(player.getDir() || dir)) { //if the player is facing right and hand is right or player faces left and hand is left
            x = playerData.x + playerData.w + 40;
        } else { //if facing right and hand is left or facing left and hand is right
            x = playerData.x - 40;
        }
        return new ObjectData(x, (float)(playerData.y + 1.0 / 3 * playerData.h), getHandImage(player));
    }

    public void updateCoordinates(Player player, boolean dir) {
        if (player.getDir() && dir || !(player.getDir() || dir)) { //if the player is facing right and hand is right or player faces left and hand is left
            getObjectData().x = player.getObjectData().x + player.getObjectData().w + 40;
        } else { //if facing right and hand is left or facing left and hand is right
            getObjectData().x = player.getObjectData().x - 40;
        }
        getObjectData().y = (int)(player.getObjectData().y + 1.0 / 3 * player.getObjectData().h);
    }
}
