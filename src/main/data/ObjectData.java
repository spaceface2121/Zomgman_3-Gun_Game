package main.data;

import javafx.scene.image.Image;

/**
 * Class that holds position, dimensions, and image of all the objects in the game
 */
public class ObjectData {

    //Fields
    public float x, y;
    public byte w, h;
    public Image image; //all of these are public so i can use them without stupid getter methods

    /**
     * Constructor for ObjectData
     * @param x horizontal position
     * @param y vertical position
     * @param image object image
     */
    public ObjectData(float x, float y, Image image) {
        this.x = x;
        this.y = y;
        w = (byte)image.getWidth();
        h = (byte)image.getHeight();
        this.image = image;
    }

    /**
     * Comparison to an Object
     * @param object
     * @return either true or false
     */
    public boolean equals(Object object) {
        if (object instanceof ObjectData) {
            return x == ((ObjectData)object).x && y == ((ObjectData)object).y && image == ((ObjectData)object).image;
        }
        throw new ClassCastException("object passed in is not ObjectData");
    }
}
