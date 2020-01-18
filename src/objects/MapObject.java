package objects;

import main.data.ObjectData;

public class MapObject {
    private ObjectData objectData;

    public MapObject(ObjectData objectData) {
        this.objectData = objectData;
    }

    public ObjectData getObjectData() {
        return objectData;
    }

    public void setObjectData(ObjectData objectData) {
        this.objectData = objectData;
    }

    public String toString() {
        return "x: " + objectData.x + ", y: " + objectData.y + ". w: " + objectData.w + ", h: " + objectData.h;
    }
}
