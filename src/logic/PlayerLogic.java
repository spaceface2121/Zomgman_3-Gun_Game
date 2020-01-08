package logic;

import javafx.scene.image.Image;
import objects.Gun;
import objects.Hand;
import objects.Player;

public class PlayerLogic {
    private static float MAX_X_VEL = 7, MIN_X_VEL = 3, Y_VEL = -12, Y_ACCELERATION = (float)0.5, X_ACCELERATION = (float)0.4;
    private static float[] relativeRightHandX = {5, 5, 5, 5, 5, 5, 5}; //absolute value of where the right hand would be relative to the gun (x)
    private static float[] relativeLeftHandX = {10, 10, 10, 10, 10, 10, 10}; //same thing but left hand
    private static float[] relativeRightHandY = {5, 5, 5, 5, 5, 5, 5}; //absolute value of where the right hand would be relative to the gun (y)
    private static float[] relativeLeftHandY = {5, 5, 5, 5, 5, 5, 5}; //same thing but left hand
    //actually fill in these values nig


    public static void generateScaledProperties(float scaleX, float scaleY) {
        MAX_X_VEL *= scaleX;
        MIN_X_VEL *= scaleX;
        Y_VEL *= scaleY;
        Y_ACCELERATION *= scaleY;
        X_ACCELERATION *= scaleX;

        for (int i = 0; i < relativeRightHandX.length; i++) {
            relativeRightHandX[i] = relativeRightHandX[i] * scaleX;
        }
        for (int i = 0; i < relativeLeftHandX.length; i++) {
            relativeLeftHandX[i] = relativeLeftHandX[i] * scaleX;
        }

        for (int i = 0; i < relativeRightHandY.length; i++) {
            relativeRightHandY[i] = relativeRightHandY[i] * scaleY;
        }
        for (int i = 0; i < relativeLeftHandY.length; i++) {
            relativeLeftHandY[i] = relativeLeftHandY[i] * scaleY;
        }
    }


    public static float getxVel() {
        return MAX_X_VEL;
    }

    public static float getMinXVel() {return MIN_X_VEL;}

    public static float getyVel() {
        return Y_VEL;
    }

    public static float getyAcceleration() {
        return Y_ACCELERATION;
    }

    public static float getxAcceleration() {
        return X_ACCELERATION;
    }

//    public static float getRelativeRightHandX(Gun gun, Hand hand) {
//
//    }
}