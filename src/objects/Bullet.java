package objects;

import javafx.scene.image.Image;
import logic.GunLogic;
import main.data.Images;
import main.data.ObjectData;

/**
 * Bullet class
 */
public class Bullet extends MovingDirectionalMapObject {

    /**
     * All bullet fields
     */
    private byte damage;
    private int range;
    private float distanceTraveled;
    private boolean player1or2;
    private byte bulletType;

    /**
     * Constructor for bullets based on the gun they come out of
     * @param gun
     */
    public Bullet(Gun gun) {
        super(getInitialBulletData(gun), getOtherImage(gun), gun.getDir(), GunLogic.getxVel(gun.getType(), gun.getDir()), GunLogic.getyVel(gun.getType()));
        distanceTraveled = 0;
        range = GunLogic.getRange(gun.getType());
        damage = GunLogic.getDamagePerHit(gun.getType());
        player1or2 = gun.isPlayer1or2();
        bulletType = GunLogic.getBulletTypes(gun.getType());
    }

    /**
     * Mutator method for the bullet's initial object data
     * @param gun the Gun which is shooting this bullet
     * @return the initial ObjectData of the bullet
     */
    private static ObjectData getInitialBulletData(Gun gun) {
        ObjectData gunData = gun.getObjectData();

        Image image = Images.getImageFromList(Images.getBulletImages(), GunLogic.getBulletTypes(gun.getType()), gun.getDir());

        float x; //where the bullet spawns
        if (gun.getDir()) { //if facing right
            x = gunData.x + gunData.w;
            if (gun.getFireMode() == GunLogic.SEMI && gun.getType() != GunLogic.REVOLVER) {
                x -= image.getWidth();
            }
        } else { //if facing left
            x = gunData.x;
            if (gun.getFireMode() != GunLogic.SEMI || gun.getType() == GunLogic.REVOLVER) {
                x -= image.getWidth();
            }
        }
        return new ObjectData(x, gunData.y + GunLogic.getRelativeBulletExitPointY(gun.getType()), image);
    }

    /**
     * Accessor method for the left facing bullet image based on the gun
     * @param gun the Gun which is shooting this bullet
     * @return the "other" image of the bullet
     */
    private static Image getOtherImage(Gun gun) {
        return Images.getImageFromList(Images.getBulletImages(), GunLogic.getBulletTypes(gun.getType()), !gun.getDir());
    }

    /**
     * Moves the bullet
     */
    public void move() {
        super.move(true);
        distanceTraveled += getxVel();
    }

    /**
     * Accessor method for the distance traveled
     * @return the distance traveled by the bullet
     */
    public float getDistanceTraveled() {
        return distanceTraveled;
    }

    /**
     * Accessor method for range
     * @return the max range of the bullet
     */
    public int getRange() {
        return range;
    }

    /**
     * Accessor method for the player identity
     * @return whether this bullet belongs to player 1 or 2 (true or false)
     */
    public boolean isPlayer1or2() {
        return player1or2;
    }

    /**
     * Accessor method for damage
     * @return the damage the bullet should do
     */
    public byte getDamage() {
        return damage;
    }
}
