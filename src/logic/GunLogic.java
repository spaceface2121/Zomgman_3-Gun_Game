package logic;

import objects.Gun;

import java.util.Random;

public class GunLogic {
    public static final byte SEMI = 0, BURST = 1, AUTO = 2, BUCKSHOT = 3;
    public static final byte GLOCK = 0, UZI = 1, MP5 = 2, AK = 3, REVOLVER = 4, SHOTGUN = 5, SNIPER = 6;

    private static final byte[] X_VELS = {8, 10, 12, 15, 15, 13, 20};
    private static final byte[] FIRE_MODES = {SEMI, BURST, AUTO, AUTO, SEMI, BUCKSHOT, SEMI};
    private static final int[] SHOT_DELAY_MILLIS = {75, 50, 75, 120, 800, 1200, 1500};
    private static final int[] RELOAD_TIME_MILLIS = {1200, 1300, 1400, 1500, 2000, 2200, 1500};
    private static final byte[] DAMAGE_PER_HIT = {15, 12, 12, 20, 40, 20, 50};
    private static final byte[] MAG_CAPACITY = {10, 20, 25, 30, 6, 5, 5};
    private static int[] RANGE = {640, 600, 700, 800, 900, 320, 9999999}; //not "final" because these will have to be scaled based on screen resolution

    public static final int BURST_DELAY = 200;

//    public static byte[] getVelocities(byte type, boolean dir) {
//        if (type > X_VELS.length - 1 || type < 0) { //i just use xVels as the reference for how many types of guns there are
//            throwWrongTypeException(type);
//        }
//        byte[] velocities = new byte[2];
//        if (dir) { //facing right
//            velocities[0] = X_VELS[type];
//        } else { //facing left
//            velocities[0] = (byte)-X_VELS[type];
//        }
//
//        switch (type) {
//            case GLOCK:
//            case MP5:
//            case AK: velocities[1] = (byte)(Math.random() * 3); break;
//            case UZI: velocities[1] = (byte)(Math.random() * 5); break;
//            case REVOLVER:
//            case SNIPER:
//            //case SHOTGUN: velocities[1] = 0; break;
//        }
//        return velocities;
//    }

    public static byte getxVel(byte type, boolean dir) {
        if (type < 0 || type >= X_VELS.length) {
            throwWrongTypeException(type);
        }
        if (dir) { //facing right
            return X_VELS[type];
        } else { //facing left
            return (byte)-X_VELS[type];
        }
    }

    public static float getyVel(byte type, boolean dir) {
        switch (type) {
            case GLOCK:
            case MP5:
            case AK: return (float)(Math.random() - 0.5);
            case UZI: return (float)(Math.random() * 2 - 1);
            case REVOLVER:
            case SNIPER:
            case SHOTGUN: return 0;
            default: throwWrongTypeException(type); return 100; //it forces me to provide a return statement what the gay
        }
    }

    public static byte getFireMode(byte type) {
        return FIRE_MODES[type];
    }

    public static int getDelayBetweenShots(byte type) {
        return SHOT_DELAY_MILLIS[type];
    }

    public static int getReloadTime(byte type) {
        return RELOAD_TIME_MILLIS[type];
    }

    public static byte getDamagePerHit(byte type) {
        return DAMAGE_PER_HIT[type];
    }

    public static byte getMagCapacity(byte type) {
        return MAG_CAPACITY[type];
    }

    public static int getRange(byte type) {
        return RANGE[type];
    }

    private static void throwWrongTypeException(byte type) {
        throw new IllegalArgumentException("Invalid gun type: type = " + type);
    }

    public static void generateScaledRanges(double scaleX) {
        for (int i = 0; i < RANGE.length; i++) {
            RANGE[i] = (int)(RANGE[i] * scaleX);
        }
    }
}
