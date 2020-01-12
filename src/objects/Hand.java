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
            x = (float)(playerData.x + (playerData.w / 2.0) + 10);
        } else { //if facing right and hand is left or facing left and hand is right
            x = (float)(playerData.x + (playerData.w / 2.0) - 10);
        }
        return new ObjectData(x, (float)(playerData.y + 1.0 / 3 * playerData.h), getHandImage(player));
    }

    public void updateCoordinates(Player player, boolean dir) {
        if (player.getDir()) { //if the player is facing right
            if (dir) {
                getObjectData().x = (float)(player.getObjectData().x + (player.getObjectData().w - this.getObjectData().w) / 2.0)  + PlayerLogic.getRelativeRightHandX(player.getGun().getType());
            } else {
                getObjectData().x = (float)(player.getObjectData().x + (player.getObjectData().w - this.getObjectData().w) / 2.0) - PlayerLogic.getRelativeLeftHandX(player.getGun().getType());
            }
        } else { //if facing right and hand is left or facing left and hand is right
            if (dir) {
                getObjectData().x = (float)(player.getObjectData().x + (player.getObjectData().w - this.getObjectData().w) / 2.0) - PlayerLogic.getRelativeRightHandX(player.getGun().getType());
            } else {
                getObjectData().x = (float)(player.getObjectData().x + (player.getObjectData().w - this.getObjectData().w) / 2.0) + PlayerLogic.getRelativeLeftHandX(player.getGun().getType());
            }
        }
        if (dir) {
            getObjectData().y = player.getObjectData().y + PlayerLogic.getRelativeRightHandY(player.getGun().getType());
        } else {
            getObjectData().y = player.getObjectData().y + PlayerLogic.getRelativeLeftHandY(player.getGun().getType());
        }
    }
}
