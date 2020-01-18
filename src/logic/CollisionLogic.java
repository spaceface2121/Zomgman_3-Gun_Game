package logic;

import main.Main;
import main.data.Images;
import main.data.Map;
import main.data.ObjectData;
import objects.Bullet;
import objects.MapObject;
import objects.MovingDirectionalMapObject;
import objects.Player;

import java.rmi.server.RemoteObjectInvocationHandler;
import java.util.ArrayList;

public class CollisionLogic {
    private static ArrayList<MapObject> blocks = Main.getGame().getMap().getBlocks();
    private static Player[] players = {Main.getGame().getPlayer1(), Main.getGame().getPlayer2()};

    private static boolean intersectsOnHorizontalPlane(ObjectData object1Data, ObjectData object2Data) {
        return object2Data.y - object1Data.y < object1Data.h && object1Data.y - object2Data.y < object2Data.h;
    }

    private static boolean intersectsOnVerticalPlane(ObjectData object1Data, ObjectData object2Data) {
        return object2Data.x - object1Data.x < object1Data.w && object1Data.x - object2Data.x < object2Data.w;
    }



    public static boolean collidedLeft(ObjectData object1Data, ObjectData object2Data) { //checks whether or not the reference object (object1) has collided on its left with object 2
        if (object1Data.x - object2Data.x <= object2Data.w && object1Data.x - object2Data.x > 0 && intersectsOnHorizontalPlane(object1Data, object2Data)) {
            //object1Data.x = object2Data.x + object2Data.w;
            System.out.println("obj1 @ " + (int)object1Data.x + "," + (int)object1Data.y + " w: " + object1Data.w + " h: " + object1Data.h + " collided left with obj2 @ " + (int)object2Data.x + "," + (int)object2Data.y + " w: " + object2Data.w + " h: " + object2Data.h);
            return true;
        }
        return false;
    }

    public static boolean collidedRight(ObjectData object1Data, ObjectData object2Data) {
        if (object2Data.x - object1Data.x <= object1Data.w && object2Data.x - object1Data.x > 0 && intersectsOnHorizontalPlane(object1Data, object2Data)) {
            //object1Data.x = object2Data.x - object1Data.w;
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



    public static boolean collidedTopWithPlayer(ObjectData objectData, boolean player1or2) { //player1or2 is THIS player, not the one we check collision with
        ObjectData otherPlayerData;
        if (player1or2) { //if p1
            otherPlayerData = Main.getGame().getPlayer2().getObjectData();
        } else { //if p2
            otherPlayerData = Main.getGame().getPlayer1().getObjectData();
        }
        return collidedTop(objectData, otherPlayerData);
    }

    public static boolean collidedBottomWithPlayer(ObjectData objectData, boolean player1or2) { //player1or2 is THIS player, not the one we check collision with
        ObjectData otherPlayerData;
        if (player1or2) { //if p1
            otherPlayerData = Main.getGame().getPlayer2().getObjectData();
        } else { //if p2
            otherPlayerData = Main.getGame().getPlayer1().getObjectData();
        }
        return collidedBottom(objectData, otherPlayerData);
    }






    public static float willCollideHorizontallyWithBlock(MovingDirectionalMapObject object) {
        float xVel = object.getxVel();
        ObjectData objectData = object.getObjectData();
        ObjectData changedObjectData = new ObjectData(objectData.x + xVel, objectData.y, objectData.image);

        for (MapObject block : blocks) {
            if (xVel > 0) { //moving right
                if (collidedRight(changedObjectData, block.getObjectData())) {
                    System.out.println("returned xVel: " + (block.getObjectData().x - objectData.x - objectData.w));
                    return block.getObjectData().x - objectData.x - objectData.w;
                }
            } else if (xVel < 0){ //moving left
                if (collidedLeft(changedObjectData, block.getObjectData())) {
                    System.out.println("returned xVel: " + (block.getObjectData().x - objectData.x + block.getObjectData().w));
                    return block.getObjectData().x - objectData.x + block.getObjectData().w;
                }
            }
        }
        return xVel;
    }

    public static float willCollideHorizontallyWithPlayer(MovingDirectionalMapObject object) {
        float xVel = object.getxVel();
        ObjectData originalObjectData = object.getObjectData();
        ObjectData changedObjectData = new ObjectData(originalObjectData.x + xVel, originalObjectData.y, originalObjectData.image);

        for (Player player : players) {
            if (object instanceof Bullet && ((Bullet)object).isPlayer1or2() == player.isPlayer1or2()) {
                continue;
            }

            ObjectData changedPlayerData = new ObjectData(player.getObjectData().x + player.getxVel(), player.getObjectData().y + player.getyVel(), player.getObjectData().image);

            if (Math.abs(xVel) > Main.getGame().getPlayer1().getObjectData().image.getWidth()) {
                for (int i = 1; i < Math.abs(xVel); i += 2) {
                    changedObjectData = new ObjectData(originalObjectData.x + i, originalObjectData.y, originalObjectData.image);
                    if (xVel > 0) { // if moving right
                        if (collidedRight(changedObjectData, changedPlayerData)) {
                            System.out.println("returned xVel: " + (player.getObjectData().x - originalObjectData.x - originalObjectData.w));
                            return player.getObjectData().x - originalObjectData.x - originalObjectData.w;
                        }
                    } else if (xVel < 0) { //if left
                        if (collidedLeft(changedObjectData, changedPlayerData)) {
                            System.out.println("returned xVel: " + (player.getObjectData().x - originalObjectData.x + player.getObjectData().w));
                            return player.getObjectData().x - originalObjectData.x + player.getObjectData().w;
                        }
                    }
                }
            } else { //it breaks without the duplication of this code idk why
                if (xVel > 0) { // if moving right
                    if (collidedRight(changedObjectData, changedPlayerData)) {
                        System.out.println("returned xVel: " + (player.getObjectData().x - originalObjectData.x - originalObjectData.w));
                        return player.getObjectData().x - originalObjectData.x - originalObjectData.w;
                    }
                } else if (xVel < 0) { //if left
                    if (collidedLeft(changedObjectData, changedPlayerData)) {
                        System.out.println("returned xVel: " + (player.getObjectData().x - originalObjectData.x + player.getObjectData().w));
                        return player.getObjectData().x - originalObjectData.x + player.getObjectData().w;
                    }
                }
            }
        }
        return xVel;
    }

    public static float willCollideVerticallyWithBlock(MovingDirectionalMapObject object) {
        float yVel = object.getyVel();
        ObjectData objectData = object.getObjectData();
        ObjectData changedObjectData = new ObjectData(objectData.x, objectData.y + yVel, objectData.image);
        for (MapObject block : blocks) {
            if (yVel > 0) { //moving down
                if (collidedBottom(changedObjectData, block.getObjectData())) {
                    System.out.println("returned yVel: " + (block.getObjectData().y - objectData.y - objectData.h));
                    return block.getObjectData().y - objectData.y - objectData.h;
                }
            } else if (yVel < 0){ //moving up
                if (collidedTop(changedObjectData, block.getObjectData())) {
                    System.out.println("returned yVel: " + (block.getObjectData().y - objectData.y + block.getObjectData().h));
                    return block.getObjectData().y - objectData.y + block.getObjectData().h;
                }
            }
        }
        return yVel;
    }

    public static float willCollideVerticallyWithPlayer(MovingDirectionalMapObject object) {
        float yVel = object.getyVel();
        ObjectData objectData = object.getObjectData();
        ObjectData changedObjectData = new ObjectData(objectData.x, objectData.y + yVel, objectData.image);
        for (Player player : players) {
            if (object instanceof Player && object.equals(player)) {
                continue;
            }
            if (yVel > 0) { //moving down
                if (collidedBottom(changedObjectData, player.getObjectData())) {
                    System.out.println("returned yVel: " + (player.getObjectData().y - objectData.y - player.getObjectData().h));
                    return player.getObjectData().y - objectData.y - player.getObjectData().h;
                }
            } else if (yVel < 0){ //moving up
                if (collidedTop(changedObjectData, player.getObjectData())) {
                    System.out.println("returned yVel: " + (player.getObjectData().y - objectData.y + player.getObjectData().h));
                    return player.getObjectData().y - objectData.y + player.getObjectData().h;
                }
            }
        }
        return yVel;
    }



    public static float willCollideHorizontallyWithObject(MovingDirectionalMapObject object) { //returns the maximum displacement without clipping into a block
        float xVel = object.getxVel();

        float returnedXVel = willCollideHorizontallyWithBlock(object);
        if (Math.abs(returnedXVel) < Math.abs(xVel)) {
            return returnedXVel;
        }

        returnedXVel = willCollideHorizontallyWithPlayer(object);
        if (Math.abs(returnedXVel) < Math.abs(xVel)) {
            return returnedXVel;
        }

        return xVel;
    }

    public static float willCollideVerticallyWithObject(MovingDirectionalMapObject object) {
        float yVel = object.getyVel();

        float returnedYVel = willCollideVerticallyWithBlock(object);
        if (Math.abs(returnedYVel) < Math.abs(yVel)) {
            return returnedYVel;
        }

        returnedYVel = willCollideVerticallyWithPlayer(object);
        if (Math.abs(returnedYVel) < Math.abs(yVel)) {
            return returnedYVel;
        }

        return yVel;
    }

    public static void setBlocks(ArrayList<MapObject> blocks) {
        CollisionLogic.blocks = blocks;
    }

    public static void setPlayers(Player[] players) {
        CollisionLogic.players = players;
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
