package objects;

import javafx.scene.image.Image;
import logic.CollisionLogic;
import main.Main;
import main.data.ObjectData;

public class MovingDirectionalMapObject extends DirectionalMapObject {
    private float xVel, yVel;

    public MovingDirectionalMapObject(ObjectData objectData, Image otherImage, boolean dir, float xVel, float yVel) {
        super(objectData, otherImage, dir);
        setxVel(xVel);
        setyVel(yVel);
    }

    public void move(boolean allowOutOfBounds) {
        ObjectData data = getObjectData();

//        if (xVel > 0 && !CollisionLogic.collidedRightWithBlock(data) || xVel < 0 && !CollisionLogic.collidedLeftWithBlock(data)) {
//            data.x += xVel;
//        }
//        if (yVel > 0 && !CollisionLogic.collidedBottomWithBlock(data) || yVel < 0 && !CollisionLogic.collidedTopWithBlock(data)) {
//            data.y += yVel;
//        }

        data.x += CollisionLogic.willCollideHorizontallyWithObject(this, xVel);
        //System.out.println("in dirMapObj move data.x moved");
        data.y += CollisionLogic.willCollideVerticallyWithObject(data, yVel);
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

    public boolean isOutOfBounds() { //if the object is COMPLETELY off the map
        ObjectData data = getObjectData();
        return data.x <= -data.w || data.x >= Main.getGame().fullW || data.y < -data.h || data.y >= Main.getGame().fullH;
    }

    public void changeDirection() {
        super.changeDirection();
        xVel = -xVel;
    }

    public float getxVel() {
        return xVel;
    }

    public float getyVel() {
        return yVel;
    }

    public void setxVel(float xVel) {
        this.xVel = xVel;
    }

    public void setyVel(float yVel) {
        this.yVel = yVel;
    }
}
