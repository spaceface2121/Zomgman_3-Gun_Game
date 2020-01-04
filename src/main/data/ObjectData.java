package main.data;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
}
