package objects;

import javafx.scene.image.Image;
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
}
