package logic;

import javafx.scene.image.Image;
import objects.Gun;
import objects.Hand;
import objects.Player;

/**
 * All static class that stores arrays of static variables for players and hand positions and contains accessor methods for these variables
 */
public class PlayerLogic {

    // Static fields and arrays
    public static byte PLAYER1 = 0, PLAYER2 = 1;
    private static float MAX_X_VEL = 7, MIN_X_VEL = 3, Y_VEL = -12, Y_ACCELERATION = (float)0.5, X_ACCELERATION = (float)0.4; //values that define player movement velocities and accelerations
    private static float[] relativeRightHandX = {15, 20, 28, 20, 30, 30, 25, 40, 43}; //absolute value of where the right hand would be relative to the gun (x)
    private static float[] relativeLeftHandX = {-6, -6, -13, -8, -8, -9, -7, -11, -11}; //same thing but left hand
    private static float[] relativeRightHandY = {16, 18, 19, 17, 19, 13, 16, 16, 16}; //absolute value of where the right hand would be relative to the gun (y)
    private static float[] relativeLeftHandY = {22, 16, 23, 24, 20, 15, 18, 18, 20}; //same thing but left hand

    // Accessor methods for hand positions, based on type of gun

    /**
     * Returns right hand x position, based on type of gun
     * @param gunType
     * @return right hand x position
     */
    public static float getRelativeRightHandX(byte gunType) {
        return relativeRightHandX[gunType];
    }

    /**
     * Returns left hand x position, based on type of gun
     * @param gunType
     * @return left hand x position
     */
    public static float getRelativeLeftHandX(byte gunType) {
        return relativeLeftHandX[gunType];
    }

    /**
     * Returns right hand y position, based on type of gun
     * @param gunType
     * @return right hand y position
     */
    public static float getRelativeRightHandY(byte gunType) {
        return relativeRightHandY[gunType];
    }

    /**
     * Returns left hand y position, based on type of gun
     * @param gunType
     * @return left hand y position
     */
    public static float getRelativeLeftHandY(byte gunType) {
        return relativeLeftHandY[gunType];
    }

    /**
     * Scaling method for all fields based on screen resolution
     * @param scaleX horizontal scaling factor
     * @param scaleY vertical scaling factor
     */
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

    // Accessor methods for movement fields

    /**
     * Accessor method for MAX_X_VEL
     * @return maximum X velocity
     */
    public static float getMaxXVel() {
        return MAX_X_VEL;
    }

    /**
     * Accessor method for MIN_X_VEL
     * @return minimum X velocity
     */
    public static float getMinXVel() {
        return MIN_X_VEL;
    }

    /**
     * Accessor method for Y_VEL (jumping velocity)
     * @return Y velocity when jumping
     */
    public static float getyVel() {
        return Y_VEL;
    }

    /**
     * Accessor method for Y_ACCELERATION (when falling)
     * @return Y acceleration when falling
     */
    public static float getyAcceleration() {
        return Y_ACCELERATION;
    }

    /**
     * Accessor method for X_ACCELERATION (when accelerating from MIN_X_VEL to MAX_X_VEL)
     * @return X acceleration
     */
    public static float getxAcceleration() {
        return X_ACCELERATION;
    }
}