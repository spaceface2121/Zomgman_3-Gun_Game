package main.data;

import main.data.Images;
import main.data.ObjectData;
import objects.MapObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Map {
    private final String PATHNAME = "resources/map.txt";
    private final File MAP = new File(PATHNAME);
    private static ArrayList<MapObject> blocks = new ArrayList<>();

    public Map(double scaleX, double scaleY) {
        Scanner input = null;

        boolean fileExists = true;
        try {
            input = new Scanner(MAP);
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
