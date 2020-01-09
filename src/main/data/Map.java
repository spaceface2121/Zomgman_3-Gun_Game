package main.data;

import objects.MapObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Map {
    private String pathname;
    private File map;
    private final byte NUM_MAPS = 2;
    private static ArrayList<MapObject> blocks = new ArrayList<>();

    public Map(float scaleX, float scaleY) {
        pathname = "resources/map" + (int)(Math.random() * NUM_MAPS + 1) + ".txt";
        map = new File(pathname);


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
                blocks.add(new MapObject(new ObjectData(x, y, Images.getImages().get(Images.BLOCK))));
            }
        }
    }

    public static ArrayList<MapObject> getBlocks() {
        return blocks;
    }
}
