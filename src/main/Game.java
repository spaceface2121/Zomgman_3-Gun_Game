package main;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import logic.GunLogic;
import main.data.Images;
import main.data.Map;
import objects.Player;


public class Game extends Application {
    private Canvas canvas;
    private GraphicsContext graphicsContext;
    private Scene scene;
    private Stage stage;
    public int w, h, fullW, fullH;

    public static float scaleX, scaleY;
    public static float scaleFullX, scaleFullY;

    private byte screen;

    private Map map;
    private Player player1, player2;

    public void initialize() {
        Game.launch();
    }


    @Override
    public void start(Stage stage){
        try {
            Main.setGame(this);
            this.stage = stage;
            screen = 0;

            Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
            w = (int)primaryScreenBounds.getWidth();
            h = (int)primaryScreenBounds.getHeight();
            System.out.println("w: " + w + " h: " + h);

            primaryScreenBounds = Screen.getPrimary().getBounds();
            fullW = (int)primaryScreenBounds.getWidth();
            fullH = (int)primaryScreenBounds.getHeight();
            System.out.println("fullW: " + fullW + " fullH: " + fullH);

            scaleX = (float)(w / 1920.0);
            scaleY = (float)(h / 1080.0);
            System.out.println("scaleX: " + scaleX + " scaleY: " + scaleY);

            scaleFullX = (float)(fullW / 1920.0);
            scaleFullY = (float)(fullH / 1080.0);
            System.out.println("scaleFullX: " + scaleFullX + " scaleFullY: " + scaleFullY);

            canvas = new Canvas(w, h);
            graphicsContext = canvas.getGraphicsContext2D();
            Group group = new Group();
            group.getChildren().add(canvas);
            scene = new Scene(group);
            scene.setOnKeyPressed(new KeyPressedHandler());
            scene.setOnKeyReleased(new KeyReleasedHandler());

            this.stage.setTitle("Zomgman 3 - Gun Game");
            this.stage.setWidth(w);
            this.stage.setHeight(h);
            this.stage.setScene(scene);
            this.stage.setResizable(false);
            this.stage.setOnCloseRequest((event) -> System.exit(0));

            this.stage.show();

            Timeline loop = new Timeline();
            loop.setCycleCount(Timeline.INDEFINITE);

            KeyFrame keyFrame = new KeyFrame(
                    Duration.seconds(1.0 / 60),
                    new EventHandler<ActionEvent>() {
                        public void handle(ActionEvent actionEvent) {
                            /*Screens:
                            0: Main menu screen
                            1: Game screen
                            2: Game paused screen
                            3: Tournament mode screen
                            */
                            switch (screen) {
                                case 0:
                                    break;
                                case 1:
                                    player1.move();
                                    player2.move();
                                    player1.getGun().moveBullets();
                                    player2.getGun().moveBullets();
                                    Render.drawGame();
                                    break;
                                case 2:
                                    break;
                                case 3:
                                    break;
                            }

                        }
                    }
            );
            Images.generateScaledImages(scaleFullX, scaleFullY);
            GunLogic.generateScaledRanges(scaleFullX);

            player1 = new Player(true);
            player2 = new Player(false);

            Map map = new Map(scaleFullX, scaleFullY);


            loop.getKeyFrames().add(keyFrame);
            loop.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setScreen(byte screen) {
        this.screen = screen;
    }

    public byte getScreen() {
        return screen;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public Stage getStage() {
        return stage;
    }

    public GraphicsContext getGraphicsContext() {
        return graphicsContext;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

}
