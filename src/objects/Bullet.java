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
     * @param gun
     * @return ObjectData
     */
    private static ObjectData getInitialBulletData(Gun gun) {
        ObjectData gunData = gun.getObjectData();

        Image image = Images.getImageFromList(Images.getBulletImages(), GunLogic.getBulletTypes(gun.getType()), gun.getDir());

        float x, y; //where the bullet spawns
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
        y = gunData.y + GunLogic.getRelativeBulletExitPointY(gun.getType());

/*        switch(gun.getType()) { //DONT FORGET TO ACTUALLY DO THIS
            case GunLogic.GLOCK:

            case GunLogic.UZI:

            case GunLogic.MP5:

            case GunLogic.AK:

            case GunLogic.REVOLVER:

            case GunLogic.SHOTGUN:

            case GunLogic.SNIPER:

        }*/

        return new ObjectData(x, y, image);
    }

    /**
     * Accessor method for the left facing bullet image based on the gun
     * @param gun
     * @return
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
     * @return
     */
    public float getDistanceTraveled() {
        return distanceTraveled;
    }

    /**
     * Accessor method for range
     * @return
     */
    public int getRange() {
        return range;
    }

    /**
     * Accessor method for the player identity
     * @return
     */
    public boolean isPlayer1or2() {
        return player1or2;
    }

    /**
     * Accessor method for damage
     * @return
     */
    public byte getDamage() {
        return damage;
    }
}
