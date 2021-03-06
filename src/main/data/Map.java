package main.data;

import javafx.scene.image.Image;
import main.Main;
import main.data.Images;
import main.data.ObjectData;
import objects.MapObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Map class that handles the background image and block placement
 */
public class Map {
    private ArrayList<MapObject> blocks = new ArrayList<>();

    /**
     * Constructor to create a random map based on screen resolution
     * @param scaleX horizontal scaling factor
     * @param scaleY vertical scaling factor
     */
    public Map(float scaleX, float scaleY) {
        final byte NUM_MAPS = 4, NUM_BACKGROUNDS = 7, NUM_BLOCKS = 6;

        //randomly generates the map (block arrangement), background image, and block texture
        String mapPath = "resources/map" + (int) (Math.random() * NUM_MAPS + 1) + ".txt";
        File map = new File(mapPath);

        String backgroundPath = "file:resources/background" + (int)(Math.random() * NUM_BACKGROUNDS + 1) + ".jpg";

        String blockPath = "file:resources/block" + (int)(Math.random() * NUM_BLOCKS + 1) + ".jpg";

        //some backgrounds dont go with some blocks so i have to specify them
        switch (Integer.parseInt(backgroundPath.substring(backgroundPath.lastIndexOf('d') + 1, backgroundPath.indexOf('.')))) {
            case 10: blockPath = "file:resources/block2.jpg"; break;
            case 13: blockPath = "file:resources/block1.jpg"; break;
        }

        Images.setBlockImage(scaleX, scaleY, new Image(blockPath, 30, 30, false, false));
        Images.setBackgroundImage(scaleX, scaleY, new Image(backgroundPath, 1920, 1080, false, false));
        if (!Main.getGame().isFinished()) { //no need to re-scale the images
            Images.generateScaledImages(scaleX, scaleY);
        }

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
                //extracts the block coordinates from the file and adds to blocks arraylist
            }
        }
    }

    /**
     * Accessor method for the arraylist of blocks
     * @return arraylist of blocks
     */
    public ArrayList<MapObject> getBlocks() {
        return blocks;
    }
}
