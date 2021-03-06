package main.data;

import javafx.scene.image.Image;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class responsible for all the images used in the game
 */
public class Images {
    //takes all "groups" of images (players, guns, bullets) and puts them in respective arraylists (first all the right images, then left images)

    private static final String P1_RIGHT_IMAGE_PATH = "file:resources/player1_right.png";
    private static final String P2_RIGHT_IMAGE_PATH = "file:resources/player2_right.png";
    private static final String P1_LEFT_IMAGE_PATH = "file:resources/player1_left.png";
    private static final String P2_LEFT_IMAGE_PATH = "file:resources/player2_left.png";
    private static final ArrayList<String> PLAYER_PATHS = new ArrayList<>(Arrays.asList(P1_RIGHT_IMAGE_PATH, P2_RIGHT_IMAGE_PATH, P1_LEFT_IMAGE_PATH, P2_LEFT_IMAGE_PATH));

    private static final String GLOCK_RIGHT_IMAGE_PATH = "file:resources/glock_right.png";
    private static final String UZI_RIGHT_IMAGE_PATH = "file:resources/uzi_right.png";
    private static final String MP5_RIGHT_IMAGE_PATH = "file:resources/mp5_right.png";
    private static final String REVOLVER_RIGHT_IMAGE_PATH = "file:resources/revolver_right.png";
    private static final String FAMAS_RIGHT_IMAGE_PATH = "file:resources/famas_right.png";
    private static final String SAIGA_RIGHT_IMAGE_PATH = "file:resources/saiga_right.png";
    private static final String AK_RIGHT_IMAGE_PATH = "file:resources/ak_right.png";
    private static final String SHOTGUN_RIGHT_IMAGE_PATH = "file:resources/shotgun_right.png";
    private static final String SNIPER_RIGHT_IMAGE_PATH = "file:resources/sniper_right.png";
    private static final String GLOCK_LEFT_IMAGE_PATH = "file:resources/glock_left.png";
    private static final String UZI_LEFT_IMAGE_PATH = "file:resources/uzi_left.png";
    private static final String MP5_LEFT_IMAGE_PATH = "file:resources/mp5_left.png";
    private static final String REVOLVER_LEFT_IMAGE_PATH = "file:resources/revolver_left.png";
    private static final String FAMAS_LEFT_IMAGE_PATH = "file:resources/famas_left.png";
    private static final String SAIGA_LEFT_IMAGE_PATH = "file:resources/saiga_left.png";
    private static final String AK_LEFT_IMAGE_PATH = "file:resources/ak_left.png";
    private static final String SHOTGUN_LEFT_IMAGE_PATH = "file:resources/shotgun_left.png";
    private static final String SNIPER_LEFT_IMAGE_PATH = "file:resources/sniper_left.png";
    private static final ArrayList<String> GUN_PATHS = new ArrayList<>(Arrays.asList(GLOCK_RIGHT_IMAGE_PATH, UZI_RIGHT_IMAGE_PATH, MP5_RIGHT_IMAGE_PATH, REVOLVER_RIGHT_IMAGE_PATH,
            FAMAS_RIGHT_IMAGE_PATH, SAIGA_RIGHT_IMAGE_PATH, AK_RIGHT_IMAGE_PATH, SHOTGUN_RIGHT_IMAGE_PATH, SNIPER_RIGHT_IMAGE_PATH, GLOCK_LEFT_IMAGE_PATH, UZI_LEFT_IMAGE_PATH,
            MP5_LEFT_IMAGE_PATH, REVOLVER_LEFT_IMAGE_PATH, FAMAS_LEFT_IMAGE_PATH, SAIGA_LEFT_IMAGE_PATH, AK_LEFT_IMAGE_PATH, SHOTGUN_LEFT_IMAGE_PATH, SNIPER_LEFT_IMAGE_PATH));

    private static final String LIGHT_BULLET_RIGHT_IMAGE_PATH = "file:resources/bullet_light_right.png";
    private static final String STANDARD_BULLET_RIGHT_IMAGE_PATH = "file:resources/bullet_standard_right.png";
    private static final String HEAVY_BULLET_RIGHT_IMAGE_PATH = "file:resources/bullet_heavy_right.png";
    private static final String SHOTGUN_BULLET_RIGHT_IMAGE_PATH = "file:resources/bullet_shotgun_right.png";
    private static final String LIGHT_BULLET_LEFT_IMAGE_PATH = "file:resources/bullet_light_left.png";
    private static final String STANDARD_BULLET_LEFT_IMAGE_PATH = "file:resources/bullet_standard_left.png";
    private static final String HEAVY_BULLET_LEFT_IMAGE_PATH = "file:resources/bullet_heavy_left.png";
    private static final String SHOTGUN_BULLET_LEFT_IMAGE_PATH = "file:resources/bullet_shotgun_left.png";
    private static final ArrayList<String> BULLET_PATHS = new ArrayList<>(Arrays.asList(LIGHT_BULLET_RIGHT_IMAGE_PATH, STANDARD_BULLET_RIGHT_IMAGE_PATH, HEAVY_BULLET_RIGHT_IMAGE_PATH,
            SHOTGUN_BULLET_RIGHT_IMAGE_PATH, LIGHT_BULLET_LEFT_IMAGE_PATH, STANDARD_BULLET_LEFT_IMAGE_PATH, HEAVY_BULLET_LEFT_IMAGE_PATH, SHOTGUN_BULLET_LEFT_IMAGE_PATH));

    private static final String HAND1_IMAGE_PATH = "file:resources/hand1.png";
    private static final String HAND2_IMAGE_PATH = "file:resources/hand2.png";

    private static ArrayList<Image> playerImages = new ArrayList<>();
    private static Image block;
    private static Image background, mainMenuImage = new Image("file:resources/menu_image.png", 1280, 720, false, false);
    private static ArrayList<Image> gunImages = new ArrayList<>();
    private static ArrayList<Image> bulletImages = new ArrayList<>();
    private static Image hand1 = new Image(HAND1_IMAGE_PATH);
    private static Image hand2 = new Image(HAND2_IMAGE_PATH);

    /**
     * Scaling method for all images that are scaled based on screen resolution
     * @param scaleX horizontal scaling factor
     * @param scaleY vertical scaling factor
     */
    public static void generateScaledImages(float scaleX, float scaleY) {
        fillArrayListWithScaledImages(scaleX, scaleY, PLAYER_PATHS, playerImages);
        fillArrayListWithScaledImages(scaleX, scaleY, GUN_PATHS, gunImages);
        fillArrayListWithScaledImages(scaleX, scaleY, BULLET_PATHS, bulletImages);
        hand1 = getScaledImage(scaleX, scaleY, hand1);
        hand2 = getScaledImage(scaleX, scaleY, hand2);
    }

    /**
     * Method responsible for adding scaled images to the final arraylist of images
     * @param scaleX horizontal scaling factor
     * @param scaleY vertical scaling factor
     * @param paths image file path
     * @param destination arraylist that the image needs to be added to
     */
    private static void fillArrayListWithScaledImages(float scaleX, float scaleY, ArrayList<String> paths, ArrayList<Image> destination) {
        for (int i = 0; i < paths.size(); i++) {
            destination.add(getScaledImage(scaleX, scaleY, new Image(paths.get(i))));
        }
    }

    /**
     * Method for actually scaling individual images
     * @param scaleX horizontal scaling factor
     * @param scaleY vertical scaling factor
     * @param original original image
     * @return new scaled image
     */
    private static Image getScaledImage(float scaleX, float scaleY, Image original) {
        return new Image(original.getUrl(), original.getWidth() * scaleX, original.getHeight() * scaleY, false, false); //makes a new image with the proper scaled dimensions
    }

    /**
     * Accessor method for returning an image from a list
     * @param list the list you want to get an image from
     * @param index the index of the image (not taking into account whether it is a right or left image)
     * @param dir whether it is a right (true) image or left (false) image
     * @return the Image you were looking for
     */
    public static Image getImageFromList(ArrayList<Image> list, byte index, boolean dir) {
        if (dir) {
            return list.get(index); //all the right images are first
        } else {
            return list.get(list.size() / 2 + index); //and the left images start in the middle index with the same order
        }
    }

    /**
     * Accessor methods for images and arrays
     */
    public static Image getBlockImage() {
        return block;
    }
    public static Image getBackgroundImage() {
        return background;
    }
    public static Image getMainMenuImage() {
        return mainMenuImage;
    }
    public static ArrayList<Image> getPlayerImages() {
        return playerImages;
    }
    public static ArrayList<Image> getGunImages() {
        return gunImages;
    }
    public static ArrayList<Image> getBulletImages() {
        return bulletImages;
    }

    /**
     * Mutator methods for scaled background and block images
     */
    public static void setBackgroundImage(float scaleX, float scaleY, Image backgroundImage) {
        background = getScaledImage(scaleX, scaleY, backgroundImage);
    }

    public static void setBlockImage(float scaleX, float scaleY, Image blockImage) {
        block = getScaledImage(scaleX, scaleY, blockImage);
    }

    /**
     * Accessor method for hand images based on the identity of the player
     * @param player1or2 identity of player
     * @return hand image
     */
    public static Image getHandImage(boolean player1or2) {
        if (player1or2) {
            return hand1;
        } else {
            return hand2;
        }
    }

    //unused
    public static void clearAll() {
        playerImages.clear();
        gunImages.clear();
        bulletImages.clear();
    }
}
