package objects;

import javafx.scene.image.Image;
import main.data.Images;
import main.data.ObjectData;

public class Ghost extends MovingDirectionalMapObject {
    public Ghost(Player player) {
        super(getInitialGhostData(player), getOtherImage(player.getDir()), player.getDir(), player.getxVel(), -10);
    }

    private static ObjectData getInitialGhostData(Player player) {
        if (player.getDir()) {
            return new ObjectData(player.getObjectData().x, player.getObjectData().y, Images.getImages().get(Images.GHOST_R));
        } else {
            return new ObjectData(player.getObjectData().x, player.getObjectData().y, Images.getImages().get(Images.GHOST_L));
        }
    }

    private static Image getOtherImage(Boolean dir) {
        if (dir) {
            return Images.getImages().get(Images.GHOST_L);
        } else {
            return Images.getImages().get(Images.GHOST_R);
        }
    }
}
