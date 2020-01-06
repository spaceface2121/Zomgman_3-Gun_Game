package logic;

import javafx.scene.image.Image;
import objects.Gun;
import objects.Hand;
import objects.Player;

public class PlayerLogic {
    private static float X_VEL = 5, Y_VEL = -12, Y_ACCELERATION = (float)0.5;
    private static float[] relativeRightHandX = {5, 5, 5, 5, 5, 5, 5}; //absolute value of where the right hand would be relative to the gun (x)
    private static float[] relativeLeftHandX = {10, 10, 10, 10, 10, 10, 10}; //same thing but left hand
    private static float[] relativeRightHandY = {}; //absolute value of where the right hand would be relative to the gun (y)
    private static float[] relativeLeftHandY = {}; //same thing but left hand
    //actually fill in these values nig


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

//    public static float getRelativeHandX(Gun gun, Hand hand) {
//
//    }
}