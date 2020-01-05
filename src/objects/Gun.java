package objects;

import javafx.scene.image.Image;
import javafx.util.Duration;
import logic.CollisionLogic;
import logic.GunLogic;
import main.Main;
import main.data.Images;
import main.data.ObjectData;

import java.util.ArrayList;

public class Gun extends DirectionalMapObject {
    private byte type, fireMode, ammoRemaining, numSuccessiveRoundsFired;
    private int delay;
    private long timeAtLastShot = 0;
    private boolean player1or2; //who this gun belongs to

    private ArrayList<Bullet> bullets;

    public Gun(Player player) {
        super(getInitialGunData(player), getOtherImage(player), player.getDir());
        setGunProperties();
        player1or2 = player.isPlayer1or2();
        bullets = new ArrayList<>();
    }

    //gets the initial gunData
    private static ObjectData getInitialGunData(Player player) {
        ObjectData playerData = player.getObjectData();
        float x;
        Image image;
        if (player.getDir()) { //if the player is facing right
            x = playerData.x + playerData.w - 5;
            image = Images.getImages().get(Images.GLOCK_R);
        } else { //if facing left
            x = playerData.x + 5;
            image = Images.getImages().get(Images.GLOCK_L);
        }
        return new ObjectData(x, (float)(playerData.y + 1.0 / 3 * playerData.h), image);
    }
    //get the initial "other" image of the gun
    private static Image getOtherImage(Player player) {
        if (player.getDir()) { //if right
            return Images.getImages().get(Images.GLOCK_L);
        } else { //if left
            return Images.getImages().get(Images.GLOCK_R);
        }
    }


    private void setGunProperties() {
        fireMode = GunLogic.getFireMode(type); // 0 = semi, 1 = burst, 2 = auto, 3 = shotgun
        ammoRemaining = GunLogic.getMagCapacity(type);
        delay = GunLogic.getDelayBetweenShots(type);
    }


    public void fire() {
        long currTime = System.currentTimeMillis();
        switch (fireMode) {
            case GunLogic.SEMI:
            case GunLogic.BURST:
//                if (numSuccessiveRoundsFired >= 3) {
//                    break;
//                }
            case GunLogic.AUTO:
                if (currTime - timeAtLastShot > delay) {
                    bullets.add(new Bullet(this));
                    timeAtLastShot = currTime;
                }
                break;
            case GunLogic.BUCKSHOT:
                for (int i = -1; i <= 1; i++) {
                    Bullet bullet = new Bullet(this);
                    bullet.setyVel((byte)i);
                    bullets.add(bullet);
                } break;
        }
    }

    public void moveBullets() {
        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            Player playerToTakeDamage, thisPlayer;

            if (player1or2) { //if this gun (and subsequently these bullets) belong to player 1
                playerToTakeDamage = Main.getGame().getPlayer2();
            } else { //if player 2
                thisPlayer = Main.getGame().getPlayer1();
                playerToTakeDamage = Main.getGame().getPlayer1(); //you may notice that im getting the "wrong" player here but this is actually the player i wanna check for collision with the bullets
            }

            if (Math.abs(bullet.getDistanceTraveled()) > bullet.getRange() || bullet.isOutOfBounds() || CollisionLogic.collidedWithBlock(bullet.getObjectData()) || CollisionLogic.collided(playerToTakeDamage.getObjectData(), bullet.getObjectData())) {
                if (CollisionLogic.collided(playerToTakeDamage.getObjectData(), bullet.getObjectData())) {
                    if (playerToTakeDamage.getHealth() - bullet.getDamage() <= 0) {
                        upgrade();
                    }
                    playerToTakeDamage.takeDamage(bullet.getDamage());
                }
                bullets.remove(i);
                i--;
            } else {
                bullet.move();
            }
        }
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public byte getType() {
        return type;
    }

    public byte getFireMode() {
        return fireMode;
    }

    public byte getAmmoRemaining() {
        return ammoRemaining;
    }

    public byte getNumSuccessiveRoundsFired() {
        return numSuccessiveRoundsFired;
    }

    public void upgrade() {
        if (type < GunLogic.SNIPER) {
            type++;
            System.out.println("gun upgraded " + type);
            setGunProperties();
            setImages();
        }
    }
    public void downgrade() {
        if (type > 0) {
            type--;
            System.out.println("gun downgraded " + type);
            setGunProperties();
            setImages();
        }
    }

    public void setImages() {
        if (getDir()) {
            getObjectData().image = Images.rightGunImages.get(type);
            setOtherImage(Images.leftGunImages.get(type));
        } else {
            getObjectData().image = Images.leftGunImages.get(type);
            setOtherImage(Images.rightGunImages.get(type));
        }
    }

    public boolean isPlayer1or2() {
        return player1or2;
    }

    public void updateCoordinates(Player player) {
        if (player.getDir()) { //if facing right
            getObjectData().x = player.getObjectData().x + player.getObjectData().w - 5;
        } else { //if facing left
            getObjectData().x = player.getObjectData().x + 5;
        }
        getObjectData().y = (int)(player.getObjectData().y + 1.0 / 3 * player.getObjectData().h);
    }
}
