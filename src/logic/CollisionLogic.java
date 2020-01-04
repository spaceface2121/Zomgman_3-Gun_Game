package logic;

import main.data.Map;
import main.data.ObjectData;
import objects.MapObject;

import java.util.ArrayList;

public class CollisionLogic {
    private static ArrayList<MapObject> blocks = Map.getBlocks();

    private static boolean intersectsOnHorizontalPlane(ObjectData object1Data, ObjectData object2Data) {
        return object2Data.y - object1Data.y < object1Data.h && object1Data.y - object2Data.y < object2Data.h;
    }

    private static boolean intersectsOnVerticalPlane(ObjectData object1Data, ObjectData object2Data) {
        return object2Data.x - object1Data.x < object1Data.w && object1Data.x - object2Data.x < object2Data.w;
    }



    public static boolean collidedLeft(ObjectData object1Data, ObjectData object2Data) { //checks whether or not the reference object (object1) has collided on its left with object 2
        if (object1Data.x - object2Data.x <= object2Data.w && object1Data.x - object2Data.x > 0 && intersectsOnHorizontalPlane(object1Data, object2Data)) {
            object1Data.x = object2Data.x + object2Data.w;
            System.out.println("obj1 @ " + (int)object1Data.x + "," + (int)object1Data.y + " w: " + object1Data.w + " h: " + object1Data.h + " collided left with obj2 @ " + (int)object2Data.x + "," + (int)object2Data.y + " w: " + object2Data.w + " h: " + object2Data.h);
            return true;
        }
        return false;
    }

    public static boolean collidedRight(ObjectData object1Data, ObjectData object2Data) {
        if (object2Data.x - object1Data.x <= object1Data.w && object2Data.x - object1Data.x > 0 && intersectsOnHorizontalPlane(object1Data, object2Data)) {
            object1Data.x = object2Data.x - object1Data.w;
            System.out.println("obj1 @ " + (int)object1Data.x + "," + (int)object1Data.y + " w: " + object1Data.w + " h: " + object1Data.h + " collided right with obj2 @ " + (int)object2Data.x + "," + (int)object2Data.y + " w: " + object2Data.w + " h: " + object2Data.h);
            return true;
        }
        return false;
    }

    public static boolean collidedTop(ObjectData object1Data, ObjectData object2Data) {
        if (object1Data.y - object2Data.y <= object2Data.h && object1Data.y - object2Data.y > 0 && intersectsOnVerticalPlane(object1Data, object2Data)) {
            object1Data.y = object2Data.y + object2Data.h;
            System.out.println("obj1 @ " + (int)object1Data.x + "," + (int)object1Data.y + " w: " + object1Data.w + " h: " + object1Data.h + " collided top with obj2 @ " + (int)object2Data.x + "," + (int)object2Data.y + " w: " + object2Data.w + " h: " + object2Data.h);
            return true;
        }
        return false;
    }

    public static boolean collidedBottom(ObjectData object1Data, ObjectData object2Data) {
        if (object2Data.y - object1Data.y <= object1Data.h && object2Data.y - object1Data.y > 0 && intersectsOnVerticalPlane(object1Data, object2Data)) {
            object1Data.y = object2Data.y - object1Data.h; //kicks the player out of glitching into the bottom blocks
            return true;
        }
        return false;
    }

    public static boolean collided(ObjectData object1Data, ObjectData object2Data) {
        return collidedLeft(object1Data, object2Data) || collidedRight(object1Data, object2Data) || collidedTop(object1Data, object2Data) || collidedBottom(object1Data, object2Data);
    }



    public static boolean collidedLeftWithBlock(ObjectData objectData) {
        for (MapObject block : blocks) {
            if (collidedLeft(objectData, block.getObjectData())) {
                //System.out.println("collided left");
                return true;
            }
        }
        return false;
    }

    public static boolean collidedRightWithBlock(ObjectData objectData) {
        for (MapObject block : blocks) {
            if (collidedRight(objectData, block.getObjectData())) {
                //System.out.println("collided right");
                return true;
            }
        }
        return false;
    }

    public static boolean collidedTopWithBlock(ObjectData objectData) {
        for (MapObject block : blocks) {
            if (collidedTop(objectData, block.getObjectData())) {
                //System.out.println("collided top");
                return true;
            }
        }
        return false;
    }

    public static boolean collidedBottomWithBlock(ObjectData objectData) {
        for (MapObject block : blocks) {
            if (collidedBottom(objectData, block.getObjectData())) {
                //System.out.println("collided bottom");
                return true;
            }
        }
        return false;
    }

    public static boolean collidedWithBlock(ObjectData objectData) {
        return collidedLeftWithBlock(objectData) || collidedRightWithBlock(objectData) || collidedTopWithBlock(objectData) || collidedBottomWithBlock(objectData);
    }



    public static float willCollideHorizontallyWithBlock(ObjectData objectData, float xVel) { //returns the maximum displacement without clipping into a block
        ObjectData changedObjectData = new ObjectData(objectData.x + xVel, objectData.y, objectData.image);
        for (MapObject block : blocks) {
            if (xVel > 0) { //moving right
                if (collidedRight(changedObjectData, block.getObjectData())) {
                    System.out.println("returned xVel: " + (block.getObjectData().x - objectData.x - objectData.w));
                    return block.getObjectData().x - objectData.x - objectData.w;
                }
            } else { //moving left
                if (collidedLeft(changedObjectData, block.getObjectData())) {
                    System.out.println("returned xVel: " + (block.getObjectData().x - objectData.x - block.getObjectData().w));
                    return block.getObjectData().x - objectData.x + block.getObjectData().w;
                }
            }
        }
        return xVel;
    }

    public static float willCollideVerticallyWithBlock(ObjectData objectData, float yVel) {
        ObjectData changedObjectData = new ObjectData(objectData.x, objectData.y + yVel, objectData.image);
        for (MapObject block : blocks) {
            if (yVel > 0) { //moving down
                if (collidedBottom(changedObjectData, block.getObjectData())) {
                    System.out.println("returned yVel: " + (block.getObjectData().y - objectData.y - objectData.h));
                    return block.getObjectData().y - objectData.y - objectData.h;
                }
            } else { //moving up
                if (collidedTop(changedObjectData, block.getObjectData())) {
                    System.out.println("returned yVel: " + (block.getObjectData().y - objectData.y - block.getObjectData().h));
                    return block.getObjectData().y - objectData.y + block.getObjectData().h;
                }
            }
        }
        return yVel;
    }



//    public static boolean collidedLeft(MapObject object) {
//        ObjectData objectData = object.getObjectData();
//        for (MapObject block : blocks) {
//            ObjectData blockData = block.getObjectData();
//            if (objectData.x - blockData.x <= blockData.w && objectData.x - blockData.x > 0 && intersectsOnHorizontalPlane(objectData, blockData)) {
//                System.out.println("collided left with block at " + blockData.x + "," + blockData.y);
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public static boolean collidedRight(MapObject object) {
//        ObjectData objectData = object.getObjectData();
//        for (MapObject block : blocks) {
//            ObjectData blockData = block.getObjectData();
//            if (blockData.x - objectData.x <= objectData.w && blockData.x - objectData.x > 0 && intersectsOnHorizontalPlane(objectData, blockData)) {
//                System.out.println("object at " + objectData.x + "," + objectData.y + " collided right with block at " + blockData.x + "," + blockData.y);
//                return true;
//            }
//        }
//        return false;
//    }
}
