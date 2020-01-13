package objects;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import logic.CollisionLogic;
import logic.GunLogic;
import logic.PlayerLogic;
import main.Game;
import main.KeyPressedHandler;
import main.Main;
import main.data.Images;
import main.data.ObjectData;

public class Player extends MovingDirectionalMapObject {
    private Hand rightHand, leftHand;
    private Gun gun;
    private byte health;
    private byte points;

    private boolean falling = false;
    private boolean strafing = false;
    private boolean jumping = false;
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
            return new ObjectData(0, 0, Images.getImageFromList(Images.getPlayerImages(), PlayerLogic.PLAYER1, true)); //player 1's "default" image is facing right
        } else { //if player 2
            return new ObjectData((int)(1890 * Game.scaleFullX), 0, Images.getImageFromList(Images.getPlayerImages(), PlayerLogic.PLAYER2, false)); //players 2's is facing left
        }
    }
    //gets the initial "other" image
    private static Image getOtherImage(boolean player1or2) {
        if (player1or2) {
            return Images.getImageFromList(Images.getPlayerImages(), PlayerLogic.PLAYER1, false);
        } else {
            return Images.getImageFromList(Images.getPlayerImages(), PlayerLogic.PLAYER2, true);
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
        holdingShoot = false;
        if (player1or2 && !getDir() || !player1or2 && getDir()) {
            changeDirection();
        }
        downgradeGun();
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void jump() {
        switch (timesJumped) {
            case 0:
            case 1:
                setyVel(PlayerLogic.getyVel());
//                if (CollisionLogic.collidedRightWithBlock(getObjectData()) || CollisionLogic.collidedLeftWithBlock(getObjectData())) {
//                    setxVel(-getxVel() * (float)0.4);
//                    changeDirection();
//                }
                timesJumped++; break;
        }
    }

    public void move() {
        final Game GAME = Main.getGame();
        if (player1or2) { //if player 1
            if (GAME.isPressed(KeyCode.A) && !GAME.isPressed(KeyCode.D)) {
                if (getDir()) { //if player 1 is moving right
                    changeDirection();
                }
                if (getxVel() > -PlayerLogic.getMinXVel()) {
                    setxVel(-PlayerLogic.getMinXVel());
                } else if (getxVel() > PlayerLogic.getxAcceleration() - PlayerLogic.getxVel()) {
                    setxVel(getxVel() - PlayerLogic.getxAcceleration());
                } else {
                    setxVel(-PlayerLogic.getxVel());
                }
                setStrafing(true);
            } else if (GAME.isPressed(KeyCode.D) && !GAME.isPressed(KeyCode.A)) {
                if (!getDir()) { //if player 1 is moving left
                    changeDirection();
                }
                if (getxVel() < PlayerLogic.getMinXVel()) {
                    setxVel(PlayerLogic.getMinXVel());
                } else if (getxVel() < PlayerLogic.getxVel() - PlayerLogic.getxAcceleration()) {
                    setxVel(getxVel() + PlayerLogic.getxAcceleration());
                } else {
                    setxVel(PlayerLogic.getxVel());
                }
                setStrafing(true);
            } else if (GAME.isPressed(KeyCode.A) && GAME.isPressed(KeyCode.D)) {
                if (getDir()) {
                    if (getxVel() == PlayerLogic.getxVel()) {
                        changeDirection();
                    }
                } else {
                    if (getxVel() == -PlayerLogic.getxVel()) {
                        changeDirection();
                    }
                }
                setStrafing(true);
            } else if (GAME.isPressed(KeyCode.SPACE)) {
//                setHoldingShoot(true);
//                getGun().fire();
            }
        } else { //if player 2
            if (GAME.isPressed(KeyCode.LEFT) && !GAME.isPressed(KeyCode.RIGHT)) {
                if (getDir()) { //if player 2 is moving right
                    changeDirection();
                }
                if (getxVel() > -PlayerLogic.getMinXVel()) {
                    setxVel(-PlayerLogic.getMinXVel());
                } else if (getxVel() > PlayerLogic.getxAcceleration() - PlayerLogic.getxVel()) {
                    setxVel(getxVel() - PlayerLogic.getxAcceleration());
                } else {
                    setxVel(-PlayerLogic.getxVel());
                }
                setStrafing(true);
            } else if (GAME.isPressed(KeyCode.RIGHT) && !GAME.isPressed(KeyCode.LEFT)) {
                if (!getDir()) { //if player 2 is moving left
                    changeDirection();
                }
                if (getxVel() < PlayerLogic.getMinXVel()) {
                    setxVel(PlayerLogic.getMinXVel());
                } else if (getxVel() < PlayerLogic.getxVel() - PlayerLogic.getxAcceleration()) {
                    setxVel(getxVel() + PlayerLogic.getxAcceleration());
                } else {
                    setxVel(PlayerLogic.getxVel());
                }
                setStrafing(true);
            } else if (GAME.isPressed(KeyCode.LEFT) && GAME.isPressed(KeyCode.RIGHT)) {
                if (getDir()) {
                    if (getxVel() == PlayerLogic.getxVel()) {
                        changeDirection();
                    }
                } else {
                    if (getxVel() == -PlayerLogic.getxVel()) {
                        changeDirection();
                    }
                }
                setStrafing(true);
            } else if (GAME.isPressed(KeyCode.SLASH)) {
//                setHoldingShoot(true);
//                getGun().fire();
            }
        }

        if (!strafing && !falling && Math.abs(getxVel()) > 0) {
            setxVel(getxVel()*(float)0.6);
        } else if (!strafing && falling && Math.abs(getxVel()) > 0) {
            setxVel(getxVel()*(float)0.9);
        }

        super.move(false);
        gun.updateCoordinates(this);
        rightHand.updateCoordinates(this, true);
        leftHand.updateCoordinates(this, false);

        if (CollisionLogic.collidedRightWithBlock(getObjectData()) || CollisionLogic.collidedLeftWithBlock(getObjectData())) {
            setxVel(0);
            if (jumping) {
                setyVel(getyVel() - PlayerLogic.getyAcceleration());
            } else {
                setyVel(PlayerLogic.getyAcceleration());
            }
            timesJumped = 0;
        }

        if (falling) {
            if (CollisionLogic.collidedBottomWithBlock(getObjectData()) || CollisionLogic.collidedBottomWithPlayer(getObjectData(), player1or2)) {
                falling = false;
                setyVel(0);
                timesJumped = 0;
            } else if (CollisionLogic.collidedTopWithBlock(getObjectData()) || CollisionLogic.collidedTopWithPlayer(getObjectData(), player1or2) || getObjectData().y <= 0) {
                setyVel((float) (-0.5 * getyVel() + 0.01)); //so you bounce off when you jump and hit ur head
            } /*else if (CollisionLogic.collidedRightWithBlock(getObjectData()) || CollisionLogic.collidedLeftWithBlock(getObjectData())) {
                //setyVel(getyVel() - PlayerLogic.getyAcceleration());
                setxVel(0);
                if (jumping) {
                    setyVel(getyVel() - PlayerLogic.getyAcceleration());
                } else {
                    setyVel(PlayerLogic.getyAcceleration());
                }
                timesJumped = 0;
            }*/ else {
                setyVel(getyVel() + PlayerLogic.getyAcceleration());
            }
        } else if (!(CollisionLogic.collidedBottomWithBlock(getObjectData()) && !CollisionLogic.collidedBottom(getObjectData(), getOtherPlayer().getObjectData()))) {
            falling = true;
        }
    }


    public void setStrafing(boolean s) {
        strafing = s;
    }

    public void setJumping(boolean j) {
        jumping = j;
    }

    private Player getOtherPlayer() {
        if (player1or2) {
            return Main.getGame().getPlayer2();
        } else {
            return Main.getGame().getPlayer1();
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

    public void changeDirection() {
        super.changeDirection();
        gun.changeDirection();
    }
}
