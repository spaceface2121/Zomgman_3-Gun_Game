package main.data;

import javafx.scene.image.Image;
import main.Game;
import objects.MapObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Map {
    private static ArrayList<MapObject> blocks = new ArrayList<>();

    public Map(float scaleX, float scaleY) {
        final byte NUM_MAPS = 4, NUM_BACKGROUNDS = 5, NUM_BLOCKS = 3;


        String mapPath = "resources/map" + (int) (Math.random() * NUM_MAPS + 1) + ".txt";
        File map = new File(mapPath);

        String backgroundPath;// = "file:resources/backgroundL12.jpg";
        backgroundPath = "file:resources/backgroundL" + (int)(Math.random() * NUM_BACKGROUNDS + 9) + ".jpg";

        String blockPath = "file:resources/block" + (int)(Math.random() * NUM_BLOCKS + 1) + ".jpg";
        //System.out.println("background num: " + Integer.parseInt(backgroundPath.substring(backgroundPath.indexOf('L') + 1, backgroundPath.indexOf('.'))));
        switch (Integer.parseInt(backgroundPath.substring(backgroundPath.indexOf('L') + 1, backgroundPath.indexOf('.')))) {
            case 10: blockPath = "file:resources/block2.jpg"; break;
            case 13: blockPath = "file:resources/block1.jpg"; break;
        }
        Images.setBlockImage(scaleX, scaleY, new Image(blockPath, 30, 30, false, false));
        Images.setBackgroundImage(scaleX, scaleY, new Image(backgroundPath, 1920, 1080, false, false));
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
