package logic;

import javafx.scene.image.Image;
import objects.Gun;
import objects.Hand;
import objects.Player;

public class PlayerLogic {
    public static byte HAND1 = 0, HAND2 = 1, PLAYER1 = 0, PLAYER2 = 1;
    private static float MAX_X_VEL = 7, MIN_X_VEL = 3, Y_VEL = -12, Y_ACCELERATION = (float)0.5, X_ACCELERATION = (float)0.4;
    private static float[] relativeRightHandX = {15, 20, 28, 20, 25, 40, 43}; //absolute value of where the right hand would be relative to the gun (x)
    private static float[] relativeLeftHandX = {-6, -6, -13, -8, -7, -11, -11}; //same thing but left hand
    private static float[] relativeRightHandY = {16, 18, 19, 17, 16, 16, 16}; //absolute value of where the right hand would be relative to the gun (y)
    private static float[] relativeLeftHandY = {22, 16, 23, 27, 18, 18, 20}; //same thing but left hand
    //actually fill in these values nig

    public static float getRelativeRightHandX(byte gunType) {
        return relativeRightHandX[gunType];
    }

    public static float getRelativeLeftHandX(byte gunType) {
        return relativeLeftHandX[gunType];
    }

    public static float getRelativeRightHandY(byte gunType) {
        return relativeRightHandY[gunType];
    }

    public static float getRelativeLeftHandY(byte gunType) {
        return relativeLeftHandY[gunType];
    }

    public static void generateScaledProperties(float scaleX, float scaleY) {
        MAX_X_VEL *= scaleX;
        MIN_X_VEL *= scaleX;
        Y_VEL *= scaleY;
        Y_ACCELERATION *= scaleY;
        X_ACCELERATION *= scaleX;

        for (int i = 0; i < relativeRightHandX.length; i++) {
            relativeRightHandX[i] = relativeRightHandX[i] * scaleX;
            relativeLeftHandX[i] = relativeLeftHandX[i] * scaleX;
            relativeRightHandY[i] = relativeRightHandY[i] * scaleY;
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