package objects;

import main.data.ObjectData;

/**
 * Simplest object that can be drawn on the map
 */
public class MapObject {
    /**
     * ObjectData field
     */
    private ObjectData objectData;

    /**
     * MapObject constructor
     * @param objectData
     */
    public MapObject(ObjectData objectData) {
        this.objectData = objectData;
    }

    /**
     * Accessor method for ObjectData
     * @return ObjectData
     */
    public ObjectData getObjectData() {
        return objectData;
    }

    /**
     * Mutator method for ObjectData
     * @param objectData
     */
    public void setObjectData(ObjectData objectData) {
        this.objectData = objectData;
    }

    /**
     * Converts the position and dimensions of the object to a string
     * @return
     */
    public String toString() {
        return "x: " + objectData.x + ", y: " + objectData.y + ". w: " + objectData.w + ", h: " + objectData.h;
    }
}
