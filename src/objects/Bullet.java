package objects;

import javafx.scene.image.Image;
import logic.GunLogic;
import main.data.Images;
import main.data.ObjectData;

public class Bullet extends MovingDirectionalMapObject {
    private byte damage;
    private int range;
    private float distanceTraveled;

    public Bullet(Gun gun) {
        super(getInitialBulletData(gun), getOtherImage(gun), gun.getDir(), GunLogic.getxVel(gun.getType(), gun.getDir()), GunLogic.getyVel(gun.getType(), gun.getDir()));
        distanceTraveled = 0;
        range = GunLogic.getRange(gun.getType());
        damage = GunLogic.getDamagePerHit(gun.getType());
    }

    private static ObjectData getInitialBulletData(Gun gun) {
        ObjectData gunData = gun.getObjectData();

        float x;
        if (gun.getDir()) { //if facing right
            x = gunData.x + gunData.w;
        } else { //if facing left
            x = gunData.x;
        }
        switch(gun.getType()) { //DONT FORGET TO ACTUALLY DO THIS
            case GunLogic.GLOCK:
                return new ObjectData(x, (int)(gunData.y + gunData.h / 2.0), getOtherImage(gun));
            case GunLogic.UZI:

            case GunLogic.MP5:

            case GunLogic.AK:

            case GunLogic.REVOLVER:

            case GunLogic.SHOTGUN:

            case GunLogic.SNIPER:

        }
        return new ObjectData(x, (int)(gunData.y + gunData.h / 2.0), getOtherImage(gun));
    }

    private static Image getOtherImage(Gun gun) {
        return Images.getImages().get(Images.LIGHT_BULLET_L);
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

    public byte getDamage() {
        return damage;
    }
}
