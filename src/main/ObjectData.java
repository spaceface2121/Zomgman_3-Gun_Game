package main;

import javafx.scene.image.Image;

public class ObjectData {
    public float x, y;
    public byte w, h;
    public Image image; //all of these are public so i can use them without stupid getter methods

    public ObjectData(float x, float y, Image image) {
        this.x = x;
        this.y = y;
        w = (byte)image.getWidth();
        h = (byte)image.getHeight();
        this.image = image;
    }

    public boolean equals(Object object) {
        if (object instanceof ObjectData) {
            return x == ((ObjectData)object).x && y == ((ObjectData)object).y && image == ((ObjectData)object).image;
        }
        throw new ClassCastException("object passed in is not ObjectData");
    }
}
