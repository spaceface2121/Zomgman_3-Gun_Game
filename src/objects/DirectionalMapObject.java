package objects;


import javafx.scene.image.Image;
import main.data.ObjectData;

public class DirectionalMapObject extends MapObject {
    private Image otherImage;
    private boolean dir; //true = right, false = left

    public DirectionalMapObject(ObjectData objectData, Image otherImage, boolean dir) {
        super(objectData);
        this.otherImage = otherImage;
        this.dir = dir;
    }

    public void changeDirection() { //this is for changing the image from left to right orientation
        Image temp = otherImage;
        otherImage = super.getObjectData().image;
        super.getObjectData().image = temp; //switches this classes otherImage field with the superclass's ObjectData's image.
        dir = !dir;
    }

    public void setOtherImage(Image otherImage) {
        this.otherImage = otherImage;
    }

    public boolean getDir() {
        return dir;
    }

    public Image getOtherImage() {
        return otherImage;
    }
}
