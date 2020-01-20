package objects;

import javafx.scene.image.Image;
import logic.CollisionLogic;
import main.Main;
import main.data.ObjectData;

/**
 * Most complex type of object that can be drawn on the screen
 */
public class MovingDirectionalMapObject extends DirectionalMapObject {
    /**
     * Velocity fields
     */
    private float xVel, yVel;

    /**
     * Constructor for a MovingDirectionalMapObject
     * @param objectData
     * @param otherImage
     * @param dir
     * @param xVel
     * @param yVel
     */
    public MovingDirectionalMapObject(ObjectData objectData, Image otherImage, boolean dir, float xVel, float yVel) {
        super(objectData, otherImage, dir);
        setxVel(xVel);
        setyVel(yVel);
    }

    /**
     * Moves the object, depends on if it is allowed past the screen edge or not
     * @param allowOutOfBounds
     */
    public void move(boolean allowOutOfBounds) {
        ObjectData data = getObjectData();

//        if (xVel > 0 && !CollisionLogic.collidedRightWithBlock(data) || xVel < 0 && !CollisionLogic.collidedLeftWithBlock(data)) {
//            data.x += xVel;
//        }
//        if (yVel > 0 && !CollisionLogic.collidedBottomWithBlock(data) || yVel < 0 && !CollisionLogic.collidedTopWithBlock(data)) {
//            data.y += yVel;
//        }

        data.x += CollisionLogic.willCollideHorizontallyWithObject(this);
        //System.out.println("in dirMapObj move data.x moved");
        data.y += CollisionLogic.willCollideVerticallyWithObject(this);
        //System.out.println("in dirMapObj move data.y moved");

        if (!allowOutOfBounds) {
            if (data.x < 0) {
                data.x = 0;
            }
            if (data.x > Main.getGame().fullW - data.w) {
                data.x = Main.getGame().fullW - data.w;
            }
            if (data.y < 0) {
                data.y = 0;
            }
            if (data.y > Main.getGame().fullH - data.h) {
                data.y = Main.getGame().fullH - data.h;
            }
        }
    }

    /**
     * Checks if the object is past the screen edge
     * @return
     */
    public boolean isOutOfBounds() { //if the object is COMPLETELY off the map
        ObjectData data = getObjectData();
        return data.x <= -data.w || data.x >= Main.getGame().fullW || data.y < -data.h || data.y >= Main.getGame().fullH;
    }

    /**
     * Changes the object's direction
     */
    public void changeDirection() {
        super.changeDirection();
        //xVel = -xVel;
    }

    /**
     * Accessor method for X velocity
     * @return
     */
    public float getxVel() {
        return xVel;
    }

    /**
     * Accessor method for Y velocity
     * @return
     */
    public float getyVel() {
        return yVel;
    }

    /**
     * Mutator method for X velocity
     * @param xVel
     */
    public void setxVel(float xVel) {
        this.xVel = xVel;
    }

    /**
     * Mutator method for Y velocity
     * @param yVel
     */
    public void setyVel(float yVel) {
        this.yVel = yVel;
    }
}
