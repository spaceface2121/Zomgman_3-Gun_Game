package objects;

import javafx.scene.image.Image;
import logic.CollisionLogic;
import logic.GunLogic;
import main.Game;
import main.data.Images;
import main.data.ObjectData;

public class Player extends MovingDirectionalMapObject {
    private Hand rightHand, leftHand;
    private Gun gun;
    private byte health;
    private byte points;

    private boolean falling = false;
    private boolean player1or2;
    private boolean holdingShoot = false;

    private byte timesJumped = 0;

    public Player(boolean player1or2) {
        super(getInitialPlayerData(player1or2), getOtherImage(player1or2), player1or2, 0, 0); //player1or2 is equivalent in terms of its boolean value to direction (dir)

        health = 100;
        points = 0;
        this.player1or2 = player1or2;
        //make sure player1or2 is set before making the gun
        gun = new Gun(this);
        rightHand = new Hand(this, false);
        leftHand = new Hand(this, true);
    }

    //gets initial player data based on which player it is
    private static ObjectData getInitialPlayerData(boolean player1or2) {
        if (player1or2)  { //if player 1
            return new ObjectData(0, 0, Images.getImages().get(Images.P1_R)); //player 1's "default" image is facing right
        } else { //if player 2
            return new ObjectData((int)(1890 * Game.scaleFullX), 0, Images.getImages().get(Images.P2_L)); //players 2's is facing left
        }
    }
    //gets the initial "other" image
    private static Image getOtherImage(boolean player1or2) {
        if (player1or2) {
            return Images.getImages().get(Images.P1_L);
        } else {
            return Images.getImages().get(Images.P2_R);
        }
    }

    public Hand getHand(boolean dir) {
        if (dir) {
            return leftHand;
        } else {
            return rightHand;
        }
    }

    public void upgradeGun() {
        gun.upgrade();
    }

    public void downgradeGun() {
        gun.downgrade();
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (!isAlive()) {
            respawn();
        }
    }

    public void respawn() {
        setObjectData(getInitialPlayerData(player1or2));
        health = 100;
        downgradeGun();
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void jump() {
        switch (timesJumped) {
            case 0:
            case 1:
                setyVel(-12);
                timesJumped++; break;
        }
    }

    public void move() {
        super.move(false);
        gun.updateCoordinates(this);
        rightHand.updateCoordinates(this, false);
        leftHand.updateCoordinates(this, true);

        if (falling) {
            if (CollisionLogic.collidedBottomWithBlock(getObjectData())) {
                falling = false;
                setyVel(0);
                timesJumped = 0;
            } else if (CollisionLogic.collidedTopWithBlock(getObjectData()) || getObjectData().y <= 0) {
                setyVel((float)(- 0.5 * getyVel() + 0.01)); //glue on ceiling
            } else {
                setyVel((float)(getyVel() + 0.5));
            }
        } else if (!CollisionLogic.collidedBottomWithBlock(getObjectData())) {
            falling = true;
        }
    }

    public void update() {
        move();
        //more will be added potentially
    }

    public Gun getGun() {
        return gun;
    }

    public byte getHealth() {
        return health;
    }

    public boolean isPlayer1or2() {
        return player1or2;
    }

    public void setHoldingShoot(boolean holdingShoot) {
        this.holdingShoot = holdingShoot;
    }

    public boolean isHoldingShoot() {
        return holdingShoot;
    }
}
