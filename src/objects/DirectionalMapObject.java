package objects;


import javafx.scene.image.Image;
import main.data.ObjectData;

/**
 * Second most complex type of map object, has an ObjectData, an alternate image, and a direction
 */
public class DirectionalMapObject extends MapObject {
    /**
     * DirectionalMapObject fields
     */
    private Image otherImage;
    private boolean dir; //true = right, false = left

    /**
     * DirectionalMapObject constructor
     * @param objectData the ObjectData
     * @param otherImage left facing image
     * @param dir direction (true = right, false = left)
     */
    public DirectionalMapObject(ObjectData objectData, Image otherImage, boolean dir) {
        super(objectData);
        this.otherImage = otherImage;
        this.dir = dir;
    }

    /**
     * Changes direction
     */
    public void changeDirection() { //this is for changing the image from left to right orientation
        Image temp = otherImage;
        otherImage = super.getObjectData().image;
        super.getObjectData().image = temp; //switches this classes otherImage field with the superclass's ObjectData's image.
        dir = !dir;
    }

    /**
     * Mutator method for left facing image
     * @param otherImage the image for the other direction
     */
    public void setOtherImage(Image otherImage) {
        this.otherImage = otherImage;
    }

    /**
     * Accessor method for direction
     * @return the direction
     */
    public boolean getDir() {
        return dir;
    }
}
