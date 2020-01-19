package logic;

import main.Main;
import main.ObjectData;
import objects.Bullet;
import objects.MapObject;
import objects.MovingDirectionalMapObject;
import objects.Player;
import java.util.ArrayList;


/**
 * All static class that contains various methods for detecting collisions between objects
 */
public class CollisionLogic {
    private static ArrayList<MapObject> blocks = Main.getGame().getMap().getBlocks();
    private static Player[] players = {Main.getGame().getPlayer1(), Main.getGame().getPlayer2()};

    /**
     * Checks whether 2 objects intersect on the same horizontal plane (if you move the objects horizontally they would meet each other)
     * takes the objectDatas of the 2 objects and returns true or false (whether they intersect on the same horizontal plane or not)
     */
    private static boolean intersectsOnHorizontalPlane(ObjectData object1Data, ObjectData object2Data) {
        return object2Data.y - object1Data.y < object1Data.h && object1Data.y - object2Data.y < object2Data.h;
    }

    /**
     * Checks whether 2 objects intersect on the same vertical plane (if you move the objects vertically they would meet each other)
     * takes the objectDatas of the 2 objects and returns true or false (whether they intersect on the same vertical plane or not)
     */
    private static boolean intersectsOnVerticalPlane(ObjectData object1Data, ObjectData object2Data) {
        return object2Data.x - object1Data.x < object1Data.w && object1Data.x - object2Data.x < object2Data.w;
    }

    /**
     * Checks whether or not the reference object (object1) has collided on its left side with object 2
     * Takes the 2 objects' objectDatas and returns true or false (whether object 1 collided left with object 2 or not)
     */
    public static boolean collidedLeft(ObjectData object1Data, ObjectData object2Data) {
        return object1Data.x - object2Data.x <= object2Data.w && object1Data.x - object2Data.x > 0 && intersectsOnHorizontalPlane(object1Data, object2Data);
    }

    /**
     * Checks whether or not the reference object (object1) has collided on its right side with object 2
     * Takes the 2 objects' objectDatas and returns true or false (whether object 1 collided right with object 2 or not)
     */
    public static boolean collidedRight(ObjectData object1Data, ObjectData object2Data) {
        return object2Data.x - object1Data.x <= object1Data.w && object2Data.x - object1Data.x > 0 && intersectsOnHorizontalPlane(object1Data, object2Data);
    }

    /**
     * Checks whether or not the reference object (object1) has collided on the top side with object 2
     * Takes the 2 objects' objectDatas and returns true or false (whether object 1 collided top with object 2 or not)
     */
    public static boolean collidedTop(ObjectData object1Data, ObjectData object2Data) {
        if (object1Data.y - object2Data.y <= object2Data.h && object1Data.y - object2Data.y > 0 && intersectsOnVerticalPlane(object1Data, object2Data)) {
            object1Data.y = object2Data.y + object2Data.h; // kicks object1 out if it has clipped into object2 above it
            return true;
        }
        return false;
    }

    /**
     * Checks whether or not the reference object (object1) has collided on the bottom side with object 2
     * Takes the 2 objects' objectDatas and returns true or false (whether object 1 collided bottom with object 2 or not)
     */
    public static boolean collidedBottom(ObjectData object1Data, ObjectData object2Data) {
        if (object2Data.y - object1Data.y <= object1Data.h && object2Data.y - object1Data.y > 0 && intersectsOnVerticalPlane(object1Data, object2Data)) {
            object1Data.y = object2Data.y - object1Data.h; // kicks object1 out if it has clipped into object2 below it
            return true;
        }
        return false;
    }

    /**
     * Checks whether or not the reference object (object1) has collided on any of the 4 sides with object 2
     * Takes the 2 objects' objectDatas and returns true or false (whether object 1 collided with object 2 or not)
     */
    public static boolean collided(ObjectData object1Data, ObjectData object2Data) {
        return collidedLeft(object1Data, object2Data) || collidedRight(object1Data, object2Data) || collidedTop(object1Data, object2Data) || collidedBottom(object1Data, object2Data);
    }



    /**
     * Checks whether or not the object has collided on its left side with any block on the map
     * Takes the objectData of the object as a parameter and returns true or false (whether or not it has collided on the left with any block)
     */
    public static boolean collidedLeftWithBlock(ObjectData objectData) {
        for (MapObject block : blocks) {
            if (collidedLeft(objectData, block.getObjectData())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks whether or not the object has collided on its right side with any block on the map
     * Takes the objectData of the object as a parameter and returns true or false (whether or not it has collided on the right with any block)
     */
    public static boolean collidedRightWithBlock(ObjectData objectData) {
        for (MapObject block : blocks) {
            if (collidedRight(objectData, block.getObjectData())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks whether or not the object has collided on the top with any block on the map
     * Takes the objectData of the object as a parameter and returns true or false (whether or not it has collided on the top with any block)
     */
    public static boolean collidedTopWithBlock(ObjectData objectData) {
        for (MapObject block : blocks) {
            if (collidedTop(objectData, block.getObjectData())) {
                //System.out.println("collided top");
                return true;
            }
        }
        return false;
    }

    /**
     * Checks whether or not the object has collided on the bottom with any block on the map
     * Takes the objectData of the object as a parameter and returns true or false (whether or not it has collided on the bottom with any block)
     */
    public static boolean collidedBottomWithBlock(ObjectData objectData) {
        for (MapObject block : blocks) {
            if (collidedBottom(objectData, block.getObjectData())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks whether or not the object has collided on any side with any of the blocks on the map
     * Takes the objectData of the object as a parameter and returns true or false (whether or not it has collided with any block)
     */
    public static boolean collidedWithBlock(ObjectData objectData) {
        return collidedLeftWithBlock(objectData) || collidedRightWithBlock(objectData) || collidedTopWithBlock(objectData) || collidedBottomWithBlock(objectData);
    }


    /**
     * Checks whether or not this player has collided top with the other player
     * Takes this player's object data and whether this player is player 1 or 2 and returns true or false (collided top or not)
     */
    public static boolean collidedTopWithPlayer(ObjectData objectData, boolean player1or2) { //player1or2 is THIS player, not the one we check collision with
        ObjectData otherPlayerData;
        if (player1or2) { //if p1
            otherPlayerData = Main.getGame().getPlayer2().getObjectData();
        } else { //if p2
            otherPlayerData = Main.getGame().getPlayer1().getObjectData();
        }
        return collidedTop(objectData, otherPlayerData);
    }

    /**
     * Checks whether or not this player has collided bottom with the other player
     * Takes this player's object data and whether this player is player 1 or 2 and returns true or false (collided bottom or not)
     */
    public static boolean collidedBottomWithPlayer(ObjectData objectData, boolean player1or2) { //player1or2 is THIS player, not the one we check collision with
        ObjectData otherPlayerData;
        if (player1or2) { //if p1
            otherPlayerData = Main.getGame().getPlayer2().getObjectData();
        } else { //if p2
            otherPlayerData = Main.getGame().getPlayer1().getObjectData();
        }
        return collidedBottom(objectData, otherPlayerData);
    }


    /**
     * Checks whether or not a MovingDirectionalMapObject will collide horizontally with any block on the map within the next frame (between its current position and its current position + velocity)
     * Takes the moving object as a parameter and returns the x velocity it must move within the next frame in order to not clip into or through a block. If, within the next frame, the moving
     * directional map object will not collide with any block, its regular velocity is returned.
     */
    public static float willCollideHorizontallyWithBlock(MovingDirectionalMapObject object) {
        float xVel = object.getxVel();
        ObjectData objectData = object.getObjectData();
        ObjectData changedObjectData = new ObjectData(objectData.x + xVel, objectData.y, objectData.image); // the objectData of the object in the next frame

        for (MapObject block : blocks) {
            if (xVel > 0) { //moving right
                if (collidedRight(changedObjectData, block.getObjectData())) {
                    return block.getObjectData().x - objectData.x - objectData.w; //the distance it must travel to just touch the block (not go into it or throught it)
                }
            } else if (xVel < 0){ //moving left
                if (collidedLeft(changedObjectData, block.getObjectData())) {
                    return block.getObjectData().x - objectData.x + block.getObjectData().w;
                }
            }
        }
        return xVel;
    }

    /**
     * Checks whether or not a MovingDirectionalMapObject will collide horizontally with either of the players on the map within the next frame (between its current position and its current position + velocity)
     * Takes the moving object as a parameter and returns the x velocity it must move within the next frame in order to not clip into or through a player. If, within the next frame, the moving
     * directional map object will not collide with either player, its regular velocity is returned.
     */
    public static float willCollideHorizontallyWithPlayer(MovingDirectionalMapObject object) {
        float xVel = object.getxVel();
        ObjectData originalObjectData = object.getObjectData();
        ObjectData changedObjectData = new ObjectData(originalObjectData.x + xVel, originalObjectData.y, originalObjectData.image);

        for (Player player : players) {
            if (object instanceof Bullet && ((Bullet)object).isPlayer1or2() == player.isPlayer1or2()) { //so it ignores collisions between a player and his own gun's bullets
                continue;
            }

            ObjectData changedPlayerData = new ObjectData(player.getObjectData().x + player.getxVel(), player.getObjectData().y + player.getyVel(), player.getObjectData().image);

            if (Math.abs(xVel) > Main.getGame().getPlayer1().getObjectData().image.getWidth()) { //if the x velocity of the object is greater than the width of the player (allowing it to go right through them)
                for (int i = 1; i < Math.abs(xVel); i += 5) { //loops 5 pixels at a time and checks collisions at each of those intervals
                    changedObjectData = new ObjectData(originalObjectData.x + i, originalObjectData.y, originalObjectData.image);
                    if (xVel > 0) { // if moving right
                        if (collidedRight(changedObjectData, changedPlayerData)) {
                            return player.getObjectData().x - originalObjectData.x - originalObjectData.w;
                        }
                    } else if (xVel < 0) { //if left
                        if (collidedLeft(changedObjectData, changedPlayerData)) {
                            return player.getObjectData().x - originalObjectData.x + player.getObjectData().w;
                        }
                    }
                }
            } else { //it breaks without the duplication of this code idk why
                if (xVel > 0) { // if moving right
                    if (collidedRight(changedObjectData, changedPlayerData)) {
                        return player.getObjectData().x - originalObjectData.x - originalObjectData.w;
                    }
                } else if (xVel < 0) { //if left
                    if (collidedLeft(changedObjectData, changedPlayerData)) {
                        return player.getObjectData().x - originalObjectData.x + player.getObjectData().w;
                    }
                }
            }
        }
        return xVel;
    }

    /**
     * Checks whether or not a MovingDirectionalMapObject will collide vertically with any block on the map within the next frame (between its current position and its current position + velocity)
     * Takes the moving object as a parameter and returns the y velocity it must move within the next frame in order to not clip into or through a block. If, within the next frame, the moving
     * directional map object will not collide with any block, its regular velocity is returned.
     */
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

    /**
     * Checks whether or not a MovingDirectionalMapObject will collide vertically with either of the players on the map within the next frame (between its current position and its current position + velocity)
     * Takes the moving object as a parameter and returns the y velocity it must move within the next frame in order to not clip into or through a player. If, within the next frame, the moving
     * directional map object will not collide with either player, its regular velocity is returned.
     */
    public static float willCollideVerticallyWithPlayer(MovingDirectionalMapObject object) {
        float yVel = object.getyVel();
        ObjectData objectData = object.getObjectData();
        ObjectData changedObjectData = new ObjectData(objectData.x, objectData.y + yVel, objectData.image);
        for (Player player : players) {
            if (object instanceof Player && object.equals(player)) { //doesnt check collisions between the player and the same player
                continue;
            }
            //this method doesnt have to do the frame by frame shit the the horizontal one had to cuz nothing moves that fast in the vertical plane to ever run into that issue
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


    /**
     * Checks whether or not a MovingDirectionalMapObject will collide horizontally with either of the players or blocks on the map within the next frame (between its current position and its current position + velocity)
     * Takes the moving object as a parameter and returns the x velocity it must move within the next frame in order to not clip into or through a player or block. If, within the next frame, the moving
     * directional map object will not collide with either player or block, its regular velocity is returned.
     */
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


    /**
     * Checks whether or not a MovingDirectionalMapObject will collide vertically with either of the players or blocks on the map within the next frame (between its current position and its current position + velocity)
     * @param object the moving object
     * @return returns the y velocity it must move within the next frame in order to not clip into or through a player or block. If, within the next frame, the moving directional map object will not collide with either player or block, its regular velocity is returned.
     */
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

    /**
     * Sets the blocks array for when a new map is generated
     */
    public static void setBlocks(ArrayList<MapObject> blocks) {
        CollisionLogic.blocks = blocks;
    }

    /**
     * Sets the blocks array for when a new map is generated
     */
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
