package objects;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import logic.CollisionLogic;
import logic.GunLogic;
import main.Game;
import main.Main;
import main.data.Images;
import main.data.ObjectData;

import java.util.ArrayList;

public class Gun extends DirectionalMapObject {
    private byte type = 0, fireMode, ammoRemaining, numSuccessiveRoundsFired;
    private int delayBetweenShots, reloadTime;
    private long timeAtLastShot = 0;
    private long timeAtLastPress = 0;
    private long timeAtReloadStart = 0;
    private boolean player1or2; //who this gun belongs to
    private boolean firing = false, reloading = false;

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
        Image image = Images.getImageFromList(Images.getGunImages(), (byte)0, player.getDir());
        if (player.getDir()) { //if the player is facing right
            x = playerData.x + playerData.w + 2;
        } else { //if facing left
            x = (float)(playerData.x - image.getWidth() - 2);
        }
        return new ObjectData(x, (float)(playerData.y + 1.0 / 4 * playerData.h), image);
    }
    //get the initial "other" image of the gun
    private static Image getOtherImage(Player player) {
        return Images.getImageFromList(Images.getGunImages(), (byte)0, !player.getDir());
    }


    private void setGunProperties() {
        fireMode = GunLogic.getFireMode(type); // 0 = semi, 1 = burst, 2 = auto, 3 = shotgun
        ammoRemaining = GunLogic.getMagCapacity(type);
        delayBetweenShots = GunLogic.getDelayBetweenShots(type);
        reloadTime = GunLogic.getReloadTime(type);
    }

    public void fire() {
        long currTime = System.currentTimeMillis();
        checkReload(currTime);

        if (!reloading) {
            if (firing) {
                switch (fireMode) {
                    case GunLogic.SEMI:
                    case GunLogic.BUCKSHOT: return;
                }
            } else if (currTime - timeAtLastPress >= GunLogic.getBurstDelay()){
                firing = true;
            }

            switch (fireMode) {
                case GunLogic.BUCKSHOT:
                    System.out.println("in buckshot");
                    System.out.println("delay: " + delayBetweenShots + " curr - last: " + (currTime - timeAtLastShot));
                    if (currTime - timeAtLastShot > delayBetweenShots) {
                        for (int i = -2; i <= 2; i += 2) {
                            Bullet bullet = new Bullet(this);
                            bullet.setyVel(i * Game.scaleFullY);
                            bullets.add(bullet);
                            timeAtLastShot = currTime;
                        }
                        ammoRemaining--;
                    } break;

                case GunLogic.BURST:
                    System.out.println("in burst fire");
                    if (numSuccessiveRoundsFired >= 3) {
                        if (player1or2 && Main.getGame().getPlayer1().isHoldingShoot() || !player1or2 && Main.getGame().getPlayer2().isHoldingShoot()) {
                            System.out.println("holding shoot");
                            return;
                        } else {
                            stopFiring();
                            break;
                        }
                    } else if (numSuccessiveRoundsFired == 0 && currTime - timeAtLastPress < GunLogic.getBurstDelay()) {
                        return;
                    }

                case GunLogic.SEMI:
                case GunLogic.AUTO:
                    System.out.println("delay: " + delayBetweenShots + " curr - last: " + (currTime - timeAtLastShot));
                    if (currTime - timeAtLastShot > delayBetweenShots) {
                        bullets.add(new Bullet(this));
                        numSuccessiveRoundsFired++;
                        timeAtLastShot = currTime;
                        ammoRemaining--;
                    }
                    break;
            }
        }
    }

    public void reload(long currTime) {
        reloading = true;
        timeAtReloadStart = currTime;
        numSuccessiveRoundsFired = 0;
        firing = false;
        if (player1or2) {
            Main.getGame().getPlayer1().setHoldingShoot(false);
        } else {
            Main.getGame().getPlayer2().setHoldingShoot(false);
        }
    }

    public void stopFiring() {
        System.out.println("sf");
        if (firing) {
            timeAtLastPress = System.currentTimeMillis();
        }
        if (!(fireMode == GunLogic.BURST && numSuccessiveRoundsFired < 3)) {
            firing = false;
            numSuccessiveRoundsFired = 0;
            System.out.println("stopped firing: " + firing + " num: " + numSuccessiveRoundsFired);
        }
    }

    private void moveBullets() {
        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            Player playerToTakeDamage, thisPlayer;

            if (player1or2) { //if this gun (and subsequently these bullets) belong to player 1
                //thisPlayer = Main.getGame().getPlayer1();
                playerToTakeDamage = Main.getGame().getPlayer2();
            } else { //if player 2
                //thisPlayer = Main.getGame().getPlayer2();
                playerToTakeDamage = Main.getGame().getPlayer1();
            }

            if (Math.abs(bullet.getDistanceTraveled()) > bullet.getRange() || bullet.isOutOfBounds() || CollisionLogic.collidedWithBlock(bullet.getObjectData()) || Math.abs(CollisionLogic.willCollideHorizontallyWithPlayer(bullet)) < Math.abs(bullet.getxVel()) || CollisionLogic.collided(playerToTakeDamage.getObjectData(), bullet.getObjectData())) {
                if (Math.abs(CollisionLogic.willCollideHorizontallyWithPlayer(bullet)) < Math.abs(bullet.getxVel()) || CollisionLogic.collided(playerToTakeDamage.getObjectData(), bullet.getObjectData())) {
                    if (playerToTakeDamage.getHealth() - bullet.getDamage() <= 0) {
                        upgrade();
                        System.out.println("upgrading in moving bullets");
                        //thisPlayer.addHealth((byte)40);
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

    public void update() {
        moveBullets();
        if (firing) {
            fire();
        }
        checkReload(System.currentTimeMillis());
    }

    public void checkReload(long currTime) {
        if (reloading && currTime - timeAtReloadStart < reloadTime) {
            return;
        } else if (reloading) {
            reloading = false;
            ammoRemaining = GunLogic.getMagCapacity(type);
        } else if (ammoRemaining <= 0) {
            reload(currTime);
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

    public void upgrade() {
        if (type < GunLogic.SNIPER) {
            type++;
            reset();
        }
    }
    public void downgrade() {
        if (type > 0) {
            type--;
            reset();
        }
    }

    public void setImages() {
        getObjectData().image = Images.getImageFromList(Images.getGunImages(), type, getDir());
        getObjectData().w = (byte) getObjectData().image.getWidth();
        getObjectData().h = (byte) getObjectData().image.getHeight();
        setOtherImage(Images.getImageFromList(Images.getGunImages(), type, !getDir()));
    }

    /*public void updateCoordinates(Player player) {
        if (player.getDir()) { //if facing right
            getObjectData().x = player.getObjectData().x + player.getObjectData().w - 2;
        } else { //if facing left
            getObjectData().x = (float)(player.getObjectData().x - getObjectData().image.getWidth() + 2);
        }
        getObjectData().y = (int)(player.getObjectData().y + 1.0 / 3 * player.getObjectData().h);
    }
*/
    public void updateCoordinates(Player player) {
        if (getDir()) { //if facing right
            getObjectData().x = player.getObjectData().x + GunLogic.getRelativeXPosition(type);
        } else { //if left
            getObjectData().x = player.getObjectData().x + player.getObjectData().w - getObjectData().w - GunLogic.getRelativeXPosition(type);
        }
        getObjectData().y = (float)(player.getObjectData().y + 1.0 / 6 * player.getObjectData().h);
    }

    public byte getRemainingAmmo() {
        return ammoRemaining;
    }

    public boolean isReloading() {
        return reloading;
    }

    public boolean isPlayer1or2() {
        return player1or2;
    }

    private void reset() {
        numSuccessiveRoundsFired = 0;
        timeAtLastShot = 0;
        timeAtLastPress = 0;
        timeAtReloadStart = 0;
        firing = false;
        reloading = false;
        setGunProperties();
        setImages();
    }
}
