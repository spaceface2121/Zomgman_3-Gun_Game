package main.data;

import javafx.scene.image.Image;
import main.Game;
import objects.MapObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Map {
    private String mapPath, backgroundPath;
    private File map;
    private final byte NUM_MAPS = 4, NUM_BACKGROUNDS = 5;
    private static ArrayList<MapObject> blocks = new ArrayList<>();

    public Map(float scaleX, float scaleY) {
        //mapPath = "resources/map4.txt";
        mapPath = "resources/map" + (int)(Math.random() * NUM_MAPS + 1) + ".txt";
        map = new File(mapPath);
        //backgroundPath = "file:resources/background1.jpg";
        backgroundPath = "file:resources/background" + (int)(Math.random() * NUM_BACKGROUNDS + 9) + ".jpg";
        Images.setBackgroundImage(scaleX, scaleY, new Image(backgroundPath));
        Images.generateScaledImages(scaleX, scaleY);


        Scanner input = null;

        boolean fileExists = true;
        try {
            input = new Scanner(map);
        } catch (FileNotFoundException e) {
            fileExists = false;
            System.out.println("File not found");
        }

        if (fileExists) {
            while(input.hasNextLine()) {
                String block = input.nextLine().trim();
                float x = Float.parseFloat(block.substring(0, block.indexOf(',')));
                float y = Float.parseFloat(block.substring(block.indexOf(',') + 1));
                x *= scaleX;
                y *= scaleY;
                blocks.add(new MapObject(new ObjectData(x, y, Images.getBlockImage())));
            }
        }
    }

    public static ArrayList<MapObject> getBlocks() {
        return blocks;
    }
}
