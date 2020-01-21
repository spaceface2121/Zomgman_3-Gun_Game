package objects;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import logic.CollisionLogic;
import logic.GunLogic;
import logic.PlayerLogic;
import main.Game;
import main.Main;
import main.data.Images;
import main.data.ObjectData;

/**
 * Player class
 */
public class Player extends MovingDirectionalMapObject {
    /**
     * All player fields
     */
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

    /**
     * PLayer constructor, based on the player's identity
     * @param player1or2
     */
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

    /**
     * Accessor method for the initial player data based on which player it is
     * @param player1or2
     * @return
     */
    private static ObjectData getInitialPlayerData(boolean player1or2) {
        if (player1or2)  { //if player 1
            return new ObjectData(0, 0, Images.getImageFromList(Images.getPlayerImages(), PlayerLogic.PLAYER1, true)); //player 1's "default" image is facing right
        } else { //if player 2
            return new ObjectData((int)(1890 * Game.scaleFullX), 0, Images.getImageFromList(Images.getPlayerImages(), PlayerLogic.PLAYER2, false)); //players 2's is facing left
        }
    }

    /**
     * Accessor method for the initial alternate direction image
     * @param player1or2
     * @return
     */
    private static Image getOtherImage(boolean player1or2) {
        if (player1or2) {
            return Images.getImageFromList(Images.getPlayerImages(), PlayerLogic.PLAYER1, false);
        } else {
            return Images.getImageFromList(Images.getPlayerImages(), PlayerLogic.PLAYER2, true);
        }
    }

    /**
     * Accessor method for the hand based on its direction
     * @param dir
     * @return Hand
     */
    public Hand getHand(boolean dir) {
        if (dir) {
            return leftHand;
        } else {
            return rightHand;
        }
    }

    /**
     * Upgrades a player's gun
     */
    public void upgradeGun() {
        gun.upgrade();
    }

    /**
     * Downgrades a player's gun
     */
    public void downgradeGun() {
        gun.downgrade();
    }

    /**
     * Decreases a player's health when they are injured
     * @param damage
     */
    public void takeDamage(int damage) {
        health -= damage;
        if (!isAlive()) {
            respawn();
        }
    }

    /**
     * Respawns the player after they die
     */
    public void respawn() {
        setObjectData(getInitialPlayerData(player1or2));
        health = 100;
        holdingShoot = false;
        if (player1or2 && !getDir() || !player1or2 && getDir()) {
            changeDirection();
        }
        if (gun.getType() == GunLogic.SNIPER) {
            downgradeGun();
        }
    }

    /**
     * Checks if the player is alive
     * @return
     */
    public boolean isAlive() {
        return health > 0;
    }

    /**
     * Jumps
     */
    public void jump() {
        switch (timesJumped) {
            case 0:
            case 1:
                setyVel(PlayerLogic.getyVel());
                timesJumped++; break;
        }
    }

    /**
     * Moves the player (this is the best method in the whole game)
     */
    public void move() {
        final Game GAME = Main.getGame();

        //these are all the active player inputted horizontal movements
        if (player1or2) { //if player 1
            if (GAME.isPressed(KeyCode.A) && !GAME.isPressed(KeyCode.D)) { //player 1 is holding left and not holding right
                if (getDir()) { //if player 1 is facing right
                    changeDirection();
                }
                if (getxVel() > -PlayerLogic.getMinXVel()) { //player 1 is slower than the minimum velocity
                    setxVel(-PlayerLogic.getMinXVel()); //set velocity to minimum velocity
                } else if (getxVel() > PlayerLogic.getxAcceleration() - PlayerLogic.getMaxXVel()) { //player 1 is in between minimum and maximum velocity
                    setxVel(getxVel() - PlayerLogic.getxAcceleration()); //accelerate the player
                } else { //player 1 is at or somehow went over the maximum velocity
                    setxVel(-PlayerLogic.getMaxXVel()); //set velocity to maximum velocity
                }
                setStrafing(true); //used for deceleration
            } else if (GAME.isPressed(KeyCode.D) && !GAME.isPressed(KeyCode.A)) { //player 1 is holding right and not holding left
                if (!getDir()) { //if player 1 is facing left
                    changeDirection();
                }
                if (getxVel() < PlayerLogic.getMinXVel()) { //player 1 is slower than the minimum velocity
                    setxVel(PlayerLogic.getMinXVel()); //set velocity to minimum velocity
                } else if (getxVel() < PlayerLogic.getMaxXVel() - PlayerLogic.getxAcceleration()) { //player 1 is in between minimum and maximum velocity
                    setxVel(getxVel() + PlayerLogic.getxAcceleration()); //accelerate the player
                } else { //player 1 is at or somehow went over the maximum velocity
                    setxVel(PlayerLogic.getMaxXVel()); //set velocity to maximum velocity
                }
                setStrafing(true);
            } else if (GAME.isPressed(KeyCode.A) && GAME.isPressed(KeyCode.D)) { //if player 1 is holding both left and right inputs
                if (getDir()) { //if player 1 is facing right
                    if (getxVel() == PlayerLogic.getMaxXVel()) { //if player 1 is traveling at maximum velocity right
                        changeDirection(); //changes direction without changing velocity so player can shoot backwards
                    }
                } else { //if player 1 is facing left
                    if (getxVel() == -PlayerLogic.getMaxXVel()) { //if player 1 is traveling at maximum velocity left
                        changeDirection(); //changes direction without changing velocity so player can shoot backwards
                    }
                }
                setStrafing(true);
            }
        } else { //if player 2 (all the code is the same so it is not commented)
            if (GAME.isPressed(KeyCode.LEFT) && !GAME.isPressed(KeyCode.RIGHT)) {
                if (getDir()) {
                    changeDirection();
                }
                if (getxVel() > -PlayerLogic.getMinXVel()) {
                    setxVel(-PlayerLogic.getMinXVel());
                } else if (getxVel() > PlayerLogic.getxAcceleration() - PlayerLogic.getMaxXVel()) {
                    setxVel(getxVel() - PlayerLogic.getxAcceleration());
                } else {
                    setxVel(-PlayerLogic.getMaxXVel());
                }
                setStrafing(true);
            } else if (GAME.isPressed(KeyCode.RIGHT) && !GAME.isPressed(KeyCode.LEFT)) {
                if (!getDir()) {
                    changeDirection();
                }
                if (getxVel() < PlayerLogic.getMinXVel()) {
                    setxVel(PlayerLogic.getMinXVel());
                } else if (getxVel() < PlayerLogic.getMaxXVel() - PlayerLogic.getxAcceleration()) {
                    setxVel(getxVel() + PlayerLogic.getxAcceleration());
                } else {
                    setxVel(PlayerLogic.getMaxXVel());
                }
                setStrafing(true);
            } else if (GAME.isPressed(KeyCode.LEFT) && GAME.isPressed(KeyCode.RIGHT)) {
                if (getDir()) {
                    if (getxVel() == PlayerLogic.getMaxXVel()) {
                        changeDirection();
                    }
                } else {
                    if (getxVel() == -PlayerLogic.getMaxXVel()) {
                        changeDirection();
                    }
                }
                setStrafing(true);
            }
        }

        //horizontal deceleration
        if (!strafing && !falling && Math.abs(getxVel()) > 0) { //when not making any inputs and when on the ground (surface friction)
            setxVel(getxVel()*(float)0.6);
        } else if (!strafing && falling && Math.abs(getxVel()) > 0) { //when not making any inputs and when in the air (air resistance)
            setxVel(getxVel()*(float)0.9);
        }

        //prevents player traveling out of bounds
        super.move(false);

        //moves all the associated objects with the player
        gun.updateCoordinates(this);
        rightHand.updateCoordinates(this, true);
        leftHand.updateCoordinates(this, false);

        //wall climbing and descending
        if (CollisionLogic.collidedRightWithBlock(getObjectData()) || CollisionLogic.collidedLeftWithBlock(getObjectData())) {
            setxVel(0);
            if (jumping) {
                setyVel(getyVel() - PlayerLogic.getyAcceleration()); //gliding up walls
            } else {
                setyVel(PlayerLogic.getyAcceleration()); //slowly descending down a wall
            }
            timesJumped = 0;
        }

        //falling, vertical collisions, and gravity
        if (falling) {
            if (CollisionLogic.collidedBottomWithBlock(getObjectData()) || CollisionLogic.collidedBottomWithPlayer(getObjectData(), player1or2)) {
                falling = false;
                setyVel(0);
                timesJumped = 0;
            } else if (CollisionLogic.collidedTopWithBlock(getObjectData()) || CollisionLogic.collidedTopWithPlayer(getObjectData(), player1or2) || getObjectData().y <= 0) {
                setyVel((float) (-0.5 * getyVel() + 0.01)); //so you bounce off when you jump and hit ur head
                // + 0.01 cuz if you jump up and touch the ceiling just as your yVel becomes 0 you would stick to the ceiling
            } else {
                setyVel(getyVel() + PlayerLogic.getyAcceleration()); //vertical acceleration
            }
        } else if (!(CollisionLogic.collidedBottomWithBlock(getObjectData()) && !CollisionLogic.collidedBottom(getObjectData(), getOtherPlayer().getObjectData()))) {
            falling = true;
        }
    }

    /**
     * Mutator method for the strafing (holding a directional movement key) variable
     * @param s
     */
    public void setStrafing(boolean s) {
        strafing = s;
    }

    /**
     * Mutator method for the jumping variable
     * @param j
     */
    public void setJumping(boolean j) {
        jumping = j;
    }

    /**
     * Accessor method for the other player
     * @return
     */
    private Player getOtherPlayer() {
        if (player1or2) {
            return Main.getGame().getPlayer2();
        } else {
            return Main.getGame().getPlayer1();
        }
    }

    /**
     * Updates the player
     */
    public void update() {
        move();
        gun.checkReload(System.currentTimeMillis());
    }

    /**
     * Accessor method for the player's gun
     * @return
     */
    public Gun getGun() {
        return gun;
    }

    /**
     * Accessor method for the player's health
     * @return
     */
    public byte getHealth() {
        return health;
    }

    /**
     * Accessor method for the player's identity
     * @return
     */
    public boolean isPlayer1or2() {
        return player1or2;
    }

    /**
     * Mutator method for the player holding the shoot button
     * @param holdingShoot
     */
    public void setHoldingShoot(boolean holdingShoot) {
        this.holdingShoot = holdingShoot;
    }

    /**
     * Accessor method for the player holding the shoot button
     * @return
     */
    public boolean isHoldingShoot() {
        return holdingShoot;
    }

    /**
     * Changes direction
     */
    public void changeDirection() {
        super.changeDirection();
        gun.changeDirection();
    }
}
