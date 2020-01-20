package objects;

import javafx.scene.image.Image;
import logic.PlayerLogic;
import main.data.Images;
import main.data.ObjectData;

/**
 * Hand class (most important class)
 */
public class Hand extends MapObject {

    /**
     * Constructor for the hand based on its leftness or rightness and player identity
     * @param player
     * @param dir
     */
    public Hand(Player player, boolean dir) {
        super(getInitialHandData(player, dir));
    }

    /**
     * Accessor method for the hand image based on the player
     * @param player
     * @return
     */
    private static Image getHandImage(Player player) {
        return Images.getHandImage(player.isPlayer1or2());
    }

    /**
     * Accessor method for the initial hand data based on the player and their direction
     * @param player
     * @param dir
     * @return ObjectData
     */
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

    /**
     * Moves the hand with the player
     * @param player
     * @param dir
     */
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
