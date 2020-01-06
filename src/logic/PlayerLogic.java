package logic;

import javafx.scene.image.Image;
import objects.Player;

public class PlayerLogic {
    private static float X_VEL = 5, Y_VEL = -12, Y_ACCELERATION = (float)0.5;

    public static void generateScaledVels(float scaleX, float scaleY) {
        X_VEL *= scaleX;
        Y_VEL *= scaleY;
        Y_ACCELERATION *= scaleY;
    }


    public static float getxVel() {
        return X_VEL;
    }

    public static float getyVel() {
        return Y_VEL;
    }

    public static float getyAcceleration() {
        return Y_ACCELERATION;
    }

    //public static float getRelativeHandX(Player player, )
}