package main.data;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Arrays;

public class Images {
    public static final byte P1_R = 0, P1_L = 1, P2_R = 2, P2_L = 3, BLOCK = 4, BACKGROUND = 5, GLOCK_R = 6, GLOCK_L = 7, UZI_R = 8, UZI_L = 9, MP5_R = 10, MP5_L = 12, AK_R = 12, AK_L = 13,
                            REVOLVER_R = 14, REVOLVER_L = 15, SHOTGUN_R = 16, SHOTGUN_L = 17, SNIPER_R = 18, SNIPER_L = 19, LIGHT_BULLET_R = 20, LIGHT_BULLET_L = 21,
                            STANDARD_BULLET_R = 22, STANDARD_BULLET_L = 23, HEAVY_BULLET_R = 24, HEAVY_BULLET_L = 25, HAND1 = 26, HAND2 = 27;

    private static final byte GUNS_START = GLOCK_R, GUNS_END = SNIPER_L;


    private static final String P1_RIGHT_IMAGE_PATHNAME = "file:resources/player1_right.png"; //CHANGE THE NAME NIGGUH
    private static final String P1_LEFT_IMAGE_PATHNAME = "file:resources/player1_left.png";
    private static final String P2_RIGHT_IMAGE_PATHNAME = "file:resources/player2_right.png";
    private static final String P2_LEFT_IMAGE_PATHNAME = "file:resources/player2_left.png";

//    public static Image P1_RIGHT_IMAGE = new Image(P1_RIGHT_IMAGE_PATHNAME);
//    public static Image P1_LEFT_IMAGE = new Image(P1_LEFT_IMAGE_PATHNAME);
//    public static Image P2_RIGHT_IMAGE = new Image(P2_RIGHT_IMAGE_PATHNAME);
//    public static Image P2_LEFT_IMAGE = new Image(P2_LEFT_IMAGE_PATHNAME);

    /*-------------------------*/

    private static final String BLOCK_IMAGE_PATHNAME = "file:resources/block.jpg";

    private static final String BACKGROUND_IMAGE_PATHNAME = "file:resources/background.jpg";

//    public static Image BLOCK_IMAGE = new Image(BLOCK_IMAGE_PATHNAME);

    /*-------------------------*/

    private static final String GLOCK_RIGHT_IMAGE_PATHNAME = "file:resources/glock_right.png";
    private static final String GLOCK_LEFT_IMAGE_PATHNAME = "file:resources/glock_left.png";
    private static final String UZI_RIGHT_IMAGE_PATHNAME = "file:resources/uzi_right.jpg";
    private static final String UZI_LEFT_IMAGE_PATHNAME = "file:resources/uzi_left.jpg";
    private static final String MP5_RIGHT_IMAGE_PATHNAME = "file:resources/mp5_right.jpg";
    private static final String MP5_LEFT_IMAGE_PATHNAME = "file:resources/mp5_left.jpg";
    private static final String REVOLVER_RIGHT_IMAGE_PATHNAME = "file:resources/revolver_right.jpg";
    private static final String REVOLVER_LEFT_IMAGE_PATHNAME = "file:resources/revolver_left.jpg";
    private static final String AK_RIGHT_IMAGE_PATHNAME = "file:resources/ak_right.jpeg";
    private static final String AK_LEFT_IMAGE_PATHNAME = "file:resources/ak_left.jpeg";
    private static final String SHOTGUN_RIGHT_IMAGE_PATHNAME = "file:resources/shotgun_right.png";
    private static final String SHOTGUN_LEFT_IMAGE_PATHNAME = "file:resources/shotgun_left.png";
    private static final String SNIPER_RIGHT_IMAGE_PATHNAME = "file:resources/sniper_right.png";
    private static final String SNIPER_LEFT_IMAGE_PATHNAME = "file:resources/sniper_left.png";

//    public static Image GLOCK_RIGHT_IMAGE = new Image (GLOCK_RIGHT_IMAGE_PATHNAME);
//    public static Image GLOCK_LEFT_IMAGE = new Image (GLOCK_LEFT_IMAGE_PATHNAME);
//    public static Image UZI_RIGHT_IMAGE = new Image (UZI_RIGHT_IMAGE_PATHNAME);
//    public static Image UZI_LEFT_IMAGE = new Image (UZI_LEFT_IMAGE_PATHNAME);
//    public static Image MP5_RIGHT_IMAGE = new Image (MP5_RIGHT_IMAGE_PATHNAME);
//    public static Image MP5_LEFT_IMAGE = new Image (MP5_LEFT_IMAGE_PATHNAME);
//    public static Image AK_RIGHT_IMAGE = new Image (AK_RIGHT_IMAGE_PATHNAME);
//    public static Image AK_LEFT_IMAGE = new Image (AK_LEFT_IMAGE_PATHNAME);
//    public static Image REVOLVER_RIGHT_IMAGE = new Image (REVOLVER_RIGHT_IMAGE_PATHNAME);
//    public static Image REVOLVER_LEFT_IMAGE = new Image (REVOLVER_LEFT_IMAGE_PATHNAME);
//    public static Image SHOTGUN_RIGHT_IMAGE = new Image (SHOTGUN_RIGHT_IMAGE_PATHNAME);
//    public static Image SHOTGUN_LEFT_IMAGE = new Image (SHOTGUN_LEFT_IMAGE_PATHNAME);
//    public static Image SNIPER_RIGHT_IMAGE = new Image (SNIPER_RIGHT_IMAGE_PATHNAME);
//    public static Image SNIPER_LEFT_IMAGE = new Image (SNIPER_LEFT_IMAGE_PATHNAME);

    /*-------------------------*/

    private static final String LIGHT_BULLET_RIGHT_IMAGE_PATHNAME = "file:resources/bullet.jpg";
    private static final String LIGHT_BULLET_LEFT_IMAGE_PATHNAME = "file:resources/bullet.jpg";
    private static final String STANDARD_BULLET_RIGHT_IMAGE_PATHNAME = "file:resources/bullet.jpg";
    private static final String STANDARD_BULLET_LEFT_IMAGE_PATHNAME = "file:resources/bullet.jpg";
    private static final String HEAVY_BULLET_RIGHT_IMAGE_PATHNAME = "file:resources/bullet.jpg";
    private static final String HEAVY_BULLET_LEFT_IMAGE_PATHNAME = "file:resources/bullet.jpg";

    private static final String HAND1_IMAGE_PATHNAME = "file:resources/hand1.png";
    private static final String HAND2_IMAGE_PATHNAME = "file:resources/hand2.png";



//    public static Image LIGHT_BULLET_RIGHT_IMAGE = new Image(LIGHT_BULLET_RIGHT_IMAGE_PATHNAME);
//    public static Image LIGHT_BULLET_LEFT_IMAGE = new Image(LIGHT_BULLET_LEFT_IMAGE_PATHNAME);
//    public static Image STANDARD_BULLET_RIGHT_IMAGE = new Image(STANDARD_BULLET_RIGHT_IMAGE_PATHNAME);
//    public static Image STANDARD_BULLET_LEFT_IMAGE = new Image(STANDARD_BULLET_LEFT_IMAGE_PATHNAME);
//    public static Image HEAVY_BULLET_RIGHT_IMAGE = new Image(HEAVY_BULLET_RIGHT_IMAGE_PATHNAME);
//    public static Image HEAVY_BULLET_LEFT_IMAGE = new Image(HEAVY_BULLET_LEFT_IMAGE_PATHNAME);

    /*----------------------------------------------------------------------------------------------------------------*/

    private static final ArrayList<String> IMAGE_PATHNAMES = new ArrayList<>(Arrays.asList(P1_RIGHT_IMAGE_PATHNAME, P1_LEFT_IMAGE_PATHNAME, P2_RIGHT_IMAGE_PATHNAME,
            P2_LEFT_IMAGE_PATHNAME, BLOCK_IMAGE_PATHNAME, BACKGROUND_IMAGE_PATHNAME, GLOCK_RIGHT_IMAGE_PATHNAME, GLOCK_LEFT_IMAGE_PATHNAME, UZI_RIGHT_IMAGE_PATHNAME, UZI_LEFT_IMAGE_PATHNAME,
            MP5_RIGHT_IMAGE_PATHNAME, MP5_LEFT_IMAGE_PATHNAME, REVOLVER_RIGHT_IMAGE_PATHNAME, REVOLVER_LEFT_IMAGE_PATHNAME, AK_RIGHT_IMAGE_PATHNAME, AK_LEFT_IMAGE_PATHNAME,
            SHOTGUN_RIGHT_IMAGE_PATHNAME, SHOTGUN_LEFT_IMAGE_PATHNAME, SNIPER_RIGHT_IMAGE_PATHNAME, SNIPER_LEFT_IMAGE_PATHNAME, LIGHT_BULLET_RIGHT_IMAGE_PATHNAME,
            LIGHT_BULLET_LEFT_IMAGE_PATHNAME, STANDARD_BULLET_RIGHT_IMAGE_PATHNAME, STANDARD_BULLET_LEFT_IMAGE_PATHNAME, HEAVY_BULLET_RIGHT_IMAGE_PATHNAME,
            HEAVY_BULLET_LEFT_IMAGE_PATHNAME, HAND1_IMAGE_PATHNAME, HAND2_IMAGE_PATHNAME));

    private static ArrayList<Image> images = new ArrayList<>();

    public static ArrayList<Image> rightGunImages = new ArrayList<>();

    public static ArrayList<Image>  leftGunImages = new ArrayList<>();

//            new ArrayList<>(Arrays.asList(P1_RIGHT_IMAGE, P1_LEFT_IMAGE, P2_RIGHT_IMAGE, P2_LEFT_IMAGE, BLOCK_IMAGE, GLOCK_RIGHT_IMAGE,
//            GLOCK_LEFT_IMAGE, UZI_RIGHT_IMAGE, UZI_LEFT_IMAGE, MP5_RIGHT_IMAGE, MP5_LEFT_IMAGE, AK_RIGHT_IMAGE, AK_LEFT_IMAGE, REVOLVER_RIGHT_IMAGE,
//            REVOLVER_LEFT_IMAGE, SHOTGUN_RIGHT_IMAGE, SHOTGUN_LEFT_IMAGE, SNIPER_RIGHT_IMAGE, SNIPER_LEFT_IMAGE, LIGHT_BULLET_RIGHT_IMAGE,
//            LIGHT_BULLET_LEFT_IMAGE, STANDARD_BULLET_RIGHT_IMAGE, STANDARD_BULLET_LEFT_IMAGE, HEAVY_BULLET_RIGHT_IMAGE, HEAVY_BULLET_LEFT_IMAGE));


    public static void generateScaledImages(float scaleX, float scaleY) {
        for (int i = 0; i < IMAGE_PATHNAMES.size(); i++) {
            Image originalImage = new Image(IMAGE_PATHNAMES.get(i)); //only generates original image cuz its the only way to obtain its dimensions
            double w = originalImage.getWidth(), h = originalImage.getHeight();
            Image scaledImage = new Image(IMAGE_PATHNAMES.get(i), w * scaleX, h * scaleY, false, false);
            if (i >= GUNS_START && i <= GUNS_END) {
                if (IMAGE_PATHNAMES.get(i).contains("right")) {
                    rightGunImages.add(scaledImage);
                } else if (IMAGE_PATHNAMES.get(i).contains("left")) {
                    leftGunImages.add(scaledImage);
                } else {
                    System.out.println("something gay is going on");
                }
            }
            images.add(scaledImage); //makes a new image with the proper scaled dimensions
        }
    }



    public static ArrayList<Image> getImages() {
        return images;
    }
}
