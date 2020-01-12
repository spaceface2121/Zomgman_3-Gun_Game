package objects;

import javafx.scene.image.Image;
import logic.GunLogic;
import main.data.Images;
import main.data.ObjectData;

public class Bullet extends MovingDirectionalMapObject {
    private byte damage;
    private int range;
    private float distanceTraveled;
    private boolean player1or2;
    private byte bulletType;

    public Bullet(Gun gun) {
        super(getInitialBulletData(gun), getOtherImage(gun), gun.getDir(), GunLogic.getxVel(gun.getType(), gun.getDir()), GunLogic.getyVel(gun.getType()));
        distanceTraveled = 0;
        range = GunLogic.getRange(gun.getType());
        damage = GunLogic.getDamagePerHit(gun.getType());
        player1or2 = gun.isPlayer1or2();
        bulletType = GunLogic.getBulletTypes(gun.getType());
    }

    private static ObjectData getInitialBulletData(Gun gun) {
        ObjectData gunData = gun.getObjectData();

        Image image = Images.getImageFromList(Images.getBulletImages(), GunLogic.getBulletTypes(gun.getType()), gun.getDir());

        float x, y; //where the bullet spawns
        if (gun.getDir()) { //if facing right
            x = gunData.x + gunData.w;
        } else { //if facing left
            x = gunData.x - (float)image.getWidth();
        }
        y = gunData.y + GunLogic.getRelativeBulletExitPointY(gun.getType());

        switch(gun.getType()) { //DONT FORGET TO ACTUALLY DO THIS
            case GunLogic.GLOCK:

            case GunLogic.UZI:

            case GunLogic.MP5:

            case GunLogic.AK:

            case GunLogic.REVOLVER:

            case GunLogic.SHOTGUN:

            case GunLogic.SNIPER:

        }

        return new ObjectData(x, y, image);
    }

    private static Image getOtherImage(Gun gun) {
        return Images.getImageFromList(Images.getBulletImages(), GunLogic.getBulletTypes(gun.getType()), !gun.getDir());
    }

    public void move() {
        super.move(true);
        distanceTraveled += getxVel();
    }

    public float getDistanceTraveled() {
        return distanceTraveled;
    }

    public int getRange() {
        return range;
    }

    public boolean isPlayer1or2() {
        return player1or2;
    }

    public byte getDamage() {
        return damage;
    }
}
