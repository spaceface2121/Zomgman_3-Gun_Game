package objects;

import javafx.scene.image.Image;
import logic.CollisionLogic;
import logic.GunLogic;
import main.Main;
import main.data.Images;
import main.data.ObjectData;
import objects.Bullet;

import java.util.ArrayList;

public class Gun extends DirectionalMapObject {
    private byte type, fireMode, ammoRemaining, numSuccessiveRoundsFired;
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
        //damagePerHit = GunLogic.getDamagePerHit(type);
        ammoRemaining = GunLogic.getMagCapacity(type);
    }


    public void fire() {
        switch (fireMode) {
            case GunLogic.SEMI:
            case GunLogic.BURST:
                if (numSuccessiveRoundsFired >= 3) {
                    break;
                }
            case GunLogic.AUTO: bullets.add(new Bullet(this)); break;
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
            Player player;

            if (player1or2) { //if this gun (and subsequently these bullets) belong to player 1
                player = Main.getGame().getPlayer2();
            } else { //if player 2
                player = Main.getGame().getPlayer1(); //you may notice that im getting the "wrong" player here but this is actually the player i wanna check for collision with the bullets
            }

            if (Math.abs(bullet.getDistanceTraveled()) > bullet.getRange() || bullet.isOutOfBounds() || CollisionLogic.collidedWithBlock(bullet.getObjectData()) || CollisionLogic.collided(player.getObjectData(), bullet.getObjectData())) {
                if (CollisionLogic.collided(player.getObjectData(), bullet.getObjectData())) {
                    player.takeDamage(bullet.getDamage());
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
        type++;
        setGunProperties();
    }
    public void downgrade() {
        type--;
        setGunProperties();
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
