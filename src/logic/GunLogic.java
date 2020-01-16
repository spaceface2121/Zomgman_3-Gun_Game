package logic;

public class GunLogic {
    public static final byte SEMI = 0, BURST = 1, AUTO = 2, BUCKSHOT = 3;
    public static final byte GLOCK = 0, UZI = 1, MP5 = 2, REVOLVER = 3, FAMAS = 4, SAIGA = 5, AK = 6, SHOTGUN = 7, SNIPER = 8;
    public static final byte LIGHT_BULLET = 0, STANDARD_BULLET = 1, HEAVY_BULLET = 2, SHOTGUN_BULLET = 3;

    //private static float[] X_VELS = {1, 1, 1, 1, 1, 1, 1, 1, 1};
    private static float[] X_VELS = {19, 23, 23, 28, 26, 25, 24, 24, 40};
    private static final byte[] FIRE_MODES = {SEMI, BURST, AUTO, SEMI, BURST, AUTO, AUTO, BUCKSHOT, SEMI};
    private static final byte[] BULLET_TYPES = {LIGHT_BULLET, LIGHT_BULLET, LIGHT_BULLET, HEAVY_BULLET, STANDARD_BULLET, SHOTGUN_BULLET, STANDARD_BULLET, SHOTGUN_BULLET, HEAVY_BULLET};
    private static final int[] SHOT_DELAY_MILLIS = {100, 20, 100, 600, 30, 200, 120, 800, 1200};
    private static final int[] RELOAD_TIME_MILLIS = {1300, 1400, 1700, 2000, 2300, 3000, 2400, 2500, 3500};
    private static final byte[] DAMAGE_PER_HIT = {13, 13, 12, 35, 14, 20, 17, 17, 50};
    private static final byte[] MAG_CAPACITY = {16, 25, 30, 6, 30, 10, 30, 5, 5};
    private static int[] RANGE = {680, 720, 760, 1000, 800, 500, 900, 400, 9999999}; //not "final" because these will have to be scaled based on screen resolution
    private static float[] RELATIVE_BULLET_EXIT_POINT_Y = {-3, 0, 0, -8, 4, -3, 1, -2, -5};
    private static float[] RELATIVE_X_POSITION = {12, 12, 0, 12, 5, 2, 0, 3, 0};

    private static final int BURST_DELAY = 150;

    public static float getxVel(byte type, boolean dir) {
        if (dir) { //facing right
            return X_VELS[type];
        } else { //facing left
            return (byte)-X_VELS[type];
        }
    }

    public static float getyVel(byte type) {
        //return 0;
        switch (type) {
            case GLOCK:
            case MP5: return (float)(Math.random() * 3 - 1.5);
            case UZI:
            case FAMAS:
            case SAIGA:
            case AK: return (float)(Math.random() * 2 - 1);
            case REVOLVER:
            case SNIPER:
            case SHOTGUN: return 0;
            default: return 100; //it forces me to provide a return statement what the gay
        }
    }

    public static byte getFireMode(byte type) {
        return FIRE_MODES[type];
    }

    public static byte getBulletTypes(byte type) {
        return BULLET_TYPES[type];
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

    public static int getBurstDelay() {
        return BURST_DELAY;
    }

    public static float getRelativeBulletExitPointY(byte type) {
        return RELATIVE_BULLET_EXIT_POINT_Y[type];
    }

    public static float getRelativeXPosition(byte type) {
        return RELATIVE_X_POSITION[type];
    }

    public static void generateScaledProperties(float scaleX, float scaleY) {
        for (int i = 0; i < RANGE.length; i++) { //all the arrays are the same length
            RANGE[i] = (int)(RANGE[i] * scaleX);
            X_VELS[i] = (byte)(X_VELS[i] * scaleX);
            RELATIVE_X_POSITION[i] = RELATIVE_X_POSITION[i] * scaleX;
            RELATIVE_BULLET_EXIT_POINT_Y[i] = RELATIVE_BULLET_EXIT_POINT_Y[i] * scaleY;
        }
    }
}
