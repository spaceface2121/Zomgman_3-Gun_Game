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
import javafx.scene.input.KeyCode;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import logic.CollisionLogic;
import logic.GunLogic;
import logic.PlayerLogic;
import objects.Player;
import java.util.BitSet;


/**
 * Michael Mityushkin, Sean Vasserman
 * Zomgman 3 - Gun Game
 * Mr. Benum
 * ICS 4UE
 * Game class is the "main" class although there is a class called Main. It is the window in which everything is drawn and all events are handled
 */
public class Game extends Application {
    private Canvas canvas;
    private GraphicsContext graphicsContext;
    private Scene scene;
    private Stage stage;
    public int fullW, fullH;
    public final int w = 1280, h = 720;

    public static float scaleX, scaleY;
    public static float scaleFullX, scaleFullY;

    private byte screen;
    public static final byte MENU_SCREEN = 0, GAME_SCREEN = 1, PAUSE_SCREEN = 2, WIN_SCREEN = 3, RULES_SCREEN = 4;

    private Map map;
    private Player player1, player2;
    private boolean winner, finished = false;

    private BitSet keyboardBitSet = new BitSet();

    public void initialize() {
        Game.launch();
    }


    @Override
    public void start(Stage stage){
        try {
            Main.setGame(this);
            this.stage = stage;
            screen = 0;

//            Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
//            w = (int)primaryScreenBounds.getWidth();
//            h = (int)primaryScreenBounds.getHeight();
//            System.out.println("w: " + w + " h: " + h);

            Rectangle2D primaryScreenBounds = Screen.getPrimary().getBounds();
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
                            switch (screen) {
                                case MENU_SCREEN:
                                    //Render.drawMainMenu();
                                    break;
                                case GAME_SCREEN:
                                    player1.update();
                                    player2.update();
                                    player1.getGun().update();
                                    player2.getGun().update();
                                    Render.drawGame();
                                    break;
                                case PAUSE_SCREEN:
                                    //Render.drawPaused();
                                    break;
                                case WIN_SCREEN:
                                    Render.drawWinScreen();
                                    break;
                                case RULES_SCREEN:

                                    break;
                            }

                        }
                    }
            );
            reset();

            loop.getKeyFrames().add(keyFrame);
            loop.play();
            Render.drawMainMenu();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setFxThings() {
        int width, height;
        if (finished) {
            width = fullW;
            height = fullH;
        } else {
            width = w;
            height = h;
        }
        canvas = new Canvas(width, height);
        graphicsContext = canvas.getGraphicsContext2D();
        Group group = new Group();
        group.getChildren().add(canvas);
        scene = new Scene(group);
        scene.setOnKeyPressed(new KeyPressedHandler());
        scene.setOnKeyReleased(new KeyReleasedHandler());

        this.stage.setTitle("Zomgman 3 - Gun Game");
        this.stage.setWidth(width);
        this.stage.setHeight(height);
        this.stage.setScene(scene);
        this.stage.setResizable(false);
        this.stage.setOnCloseRequest((event) -> System.exit(0));
        this.stage.show();
    }

    public void reset() {
        Main.setGame(this);

        map = new Map(scaleFullX, scaleFullY);

        if (!finished) {
            GunLogic.generateScaledProperties(scaleFullX, scaleFullY);
            PlayerLogic.generateScaledProperties(scaleFullX, scaleFullY);
        }

        player1 = new Player(true);
        player2 = new Player(false);

        if (finished) {
            Render.setGame(this);
            Render.setGraphicsContext(graphicsContext);
            CollisionLogic.setBlocks(map.getBlocks());
            Player[] players = {player1, player2};
            CollisionLogic.setPlayers(players);
            finished = false;
        }
    }

    public void setKey(KeyCode key, boolean pressed) {
        keyboardBitSet.set(key.ordinal(), pressed);
    }

    public boolean isPressed(KeyCode key) {
        return keyboardBitSet.get(key.ordinal());
    }

    public void setScreen(byte screen) {
        this.screen = screen;
        System.out.println("screen: " + screen);
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

    public void setStage(Stage stage) {
        this.stage = stage;
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

    public boolean getWinner() {
        return winner;
    }

    public void setWinner(boolean player1or2) {
        winner = player1or2;
        finished = true;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public boolean isFinished() {
        return finished;
    }

    public Map getMap() {
        return map;
    }
}
