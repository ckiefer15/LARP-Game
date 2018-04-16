package larp;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import larp.model.*;
import larp.model.graphic.*;
import larp.model.character.*;
import larp.model.room.object.*;

/**
 * FXML Controller class
 *
 * @author Tyree Gustafson
 */
public class GameScreenController implements Initializable {

    private Button inventoryButton;
    private Button menuButton;

    static public final int WIDTH = 640;
    static public final int HEIGHT = 640;
    static public final int MOVEMENT = 5;
    static public final int OVERLAP_OFFSET = 45;
    static public final int TILE_SIZE = 32;
    static private boolean trace = true;

    //static ArrayList<MapObjects> thingyList = new ArrayList<MapObjects>();
    static Scene scene;
    static Canvas graphics;
    static GraphicsContext gc;
    static ArrayList<String> keyPressed;
    static AnimationTimer timer;
    static ImagePattern backgroundPattern;

    //============DGame Attributes===============
    static DGame game;
    static DRoom currentRoom;
    static ArrayList<RoomObject> roomObjects;
    static ArrayList<RoomObject> blockable;
    static Knight player;
    static Sprite playerSprite;

    @FXML
    private AnchorPane rootPane;
    @FXML
    private Button fightButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setup();
    }

    public void setup() {

        graphics = new Canvas(WIDTH, HEIGHT);
        gc = graphics.getGraphicsContext2D();
        keyPressed = new ArrayList<>();

        //============Initialize Game and Reference Variables==============
        if (game == null) {
            game = SetupDGame.setupGame(false);
            currentRoom = game.getCurrentRoom();
            backgroundPattern = new ImagePattern(currentRoom.getImage().getStaticImage());
            roomObjects = currentRoom.getRoomObjects();
            blockable = currentRoom.getBlockable();
            player = game.getPlayer();
            playerSprite = player.getSprite();
        }
        rootPane.getChildren().add(0, graphics);
        addThingyToPane();
        setupKeyPresses();
        startGameLoop();
    }

    public void addThingyToPane() {

        for (int i = 0; i < roomObjects.size(); i++) {
            roomObjects.get(i).bounds.setFill(Color.TRANSPARENT);
            roomObjects.get(i).bounds.setStroke(Color.RED);
            rootPane.getChildren().add(roomObjects.get(i).bounds);
        }
    }
    public void removeRoomObjects(){
        for (int i = 0; i < roomObjects.size(); i++) {
            rootPane.getChildren().remove(roomObjects.get(i).bounds);
        }
    }

    public void setupKeyPresses() {

        rootPane.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent e) {
                if (e.getCode().toString() == "I") {
                    InventoryScreenController.game = game;
                    try {
                        Parent inventoryScreen = FXMLLoader.load(getClass().getResource("InventoryScreen.fxml"));
                        rootPane.getScene().setRoot(inventoryScreen);
                        timer.stop();
                    } catch (IOException ex) {
                        Logger.getLogger(GameScreenController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (e.getCode().toString() == "M") {
                    try {
                        Parent inventoryScreen = FXMLLoader.load(getClass().getResource("MainMenuScreen.fxml"));
                        rootPane.getScene().setRoot(inventoryScreen);
                        timer.stop();
                    } catch (IOException ex) {
                        Logger.getLogger(GameScreenController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (e.getCode().toString() == "T") {
                    trace = !trace;
                    if (trace == false) {
                        for (int i = 0; i < roomObjects.size(); i++) {
                            roomObjects.get(i).bounds.setFill(Color.TRANSPARENT);
                            roomObjects.get(i).bounds.setStroke(Color.TRANSPARENT);
                        }
                    } else {
                        for (int i = 0; i < roomObjects.size(); i++) {
                            roomObjects.get(i).bounds.setFill(Color.TRANSPARENT);
                            roomObjects.get(i).bounds.setStroke(Color.RED);
                        }
                    }

                } else if (!keyPressed.contains(e.getCode().toString())) {
                    keyPressed.add(e.getCode().toString());
                }
            }
        });

        rootPane.setOnKeyReleased(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent e) {
                keyPressed.remove(e.getCode().toString());
            }
        });
    }

    public void startGameLoop() {
        timer = new AnimationTimer() {
            @Override
            public void handle(long nanoseconds) {
                update();
                paint();
                try {
                    Thread.sleep(45);
                } catch (InterruptedException ex) {
                    Logger.getLogger(GameScreenController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        timer.start();
    }

    public void update() {
        RoomObject tempObj;

        if (keyPressed.contains("UP") || keyPressed.contains("W")) {
            if (playerSprite.getYCoordinate() - MOVEMENT >= 0) {
                tempObj = checkCollisionAllObjects();
                if (tempObj == null) {
                    playerSprite.updateCoord(0, (0 - MOVEMENT));
                } else {
                    handleRoomObject(tempObj);
                    gc.drawImage(playerSprite.move(), playerSprite.getXCoordinate(), playerSprite.getYCoordinate());
                }
            }
        }
        if (keyPressed.contains("DOWN") || keyPressed.contains("S")) {
            if (playerSprite.getYOffset() + playerSprite.getYCoordinate() + MOVEMENT <= HEIGHT) {
                tempObj = checkCollisionAllObjects();
                if (tempObj == null) {
                    playerSprite.updateCoord(0, MOVEMENT);
                } else {
                    handleRoomObject(tempObj);
                    gc.drawImage(playerSprite.move(), playerSprite.getXCoordinate(), playerSprite.getYCoordinate());
                }
            }
        }
        if (keyPressed.contains("LEFT") || keyPressed.contains("A")) {
            if (playerSprite.getXCoordinate() - MOVEMENT >= 0) {
                tempObj = checkCollisionAllObjects();
                if (tempObj == null) {
                    playerSprite.updateCoord((0 - MOVEMENT), 0);
                } else {
                    handleRoomObject(tempObj);
                    gc.drawImage(playerSprite.move(), playerSprite.getXCoordinate(), playerSprite.getYCoordinate());
                }
            }
        }
        if (keyPressed.contains("RIGHT") || keyPressed.contains("D")) {
            if (playerSprite.getXOffset() + playerSprite.getXCoordinate() + MOVEMENT <= WIDTH) {
                tempObj = checkCollisionAllObjects();
                if (tempObj == null) {
                    playerSprite.updateCoord(MOVEMENT, 0);
                } else {
                    handleRoomObject(tempObj);
                    gc.drawImage(playerSprite.move(), playerSprite.getXCoordinate(), playerSprite.getYCoordinate());
                }
            }
        }
    }

    public static RoomObject checkCollisionAllObjects() {
        for (int i = 0; i < roomObjects.size(); i++) {
            if (checkCollision(roomObjects.get(i))) {
                return roomObjects.get(i);
            }
        }
        return null;
    }

    public void handleRoomObject(RoomObject obj) {
        if (obj instanceof Door) {
            removeRoomObjects();
            currentRoom = game.changeRoom((Door) obj);
            roomObjects = currentRoom.getRoomObjects();
            backgroundPattern = new ImagePattern(currentRoom.getImage().getStaticImage());
            addThingyToPane();
            
        }
    }

    public static void paint() {
        gc.setFill(backgroundPattern);
        gc.fillRect(0, 0, graphics.getWidth(), graphics.getHeight());

        StaticImage temp;
        for (RoomObject obj : roomObjects) {
            temp = obj.getImage();
            gc.drawImage(temp.getStaticImage(), temp.getXCoordinate(), temp.getYCoordinate());
        }

        if (keyPressed.isEmpty()) {
            gc.drawImage(playerSprite.stay(), playerSprite.getXCoordinate(), playerSprite.getYCoordinate());
        } else {
            gc.drawImage(playerSprite.move(), playerSprite.getXCoordinate(), playerSprite.getYCoordinate());
        }
    }

    public static boolean checkCollision(RoomObject objectBounds) {
        if (keyPressed.contains("RIGHT") || keyPressed.contains("D")) {
            javafx.scene.shape.Rectangle temp = new Rectangle(playerSprite.getXCoordinate() + MOVEMENT, playerSprite.getYCoordinate() + OVERLAP_OFFSET, 15, 60 - OVERLAP_OFFSET);
            if (temp.getBoundsInParent().intersects(objectBounds.bounds.getBoundsInParent())) {
                return true;
            }
        }
        if (keyPressed.contains("LEFT") || keyPressed.contains("A")) {
            javafx.scene.shape.Rectangle temp = new Rectangle(playerSprite.getXCoordinate() - MOVEMENT, playerSprite.getYCoordinate() + OVERLAP_OFFSET, 15, 60 - OVERLAP_OFFSET);
            if (temp.getBoundsInParent().intersects(objectBounds.bounds.getBoundsInParent())) {
                return true;
            }
        }
        if (keyPressed.contains("UP") || keyPressed.contains("W")) {
            javafx.scene.shape.Rectangle temp = new Rectangle(playerSprite.getXCoordinate(), playerSprite.getYCoordinate() - MOVEMENT + OVERLAP_OFFSET, 15, 60 - OVERLAP_OFFSET);
            if (temp.getBoundsInParent().intersects(objectBounds.bounds.getBoundsInParent())) {
                return true;
            }
        }
        if (keyPressed.contains("DOWN") || keyPressed.contains("S")) {
            javafx.scene.shape.Rectangle temp = new Rectangle(playerSprite.getXCoordinate(), playerSprite.getYCoordinate() + MOVEMENT + OVERLAP_OFFSET, 15, 60 - OVERLAP_OFFSET);
            if (temp.getBoundsInParent().intersects(objectBounds.bounds.getBoundsInParent())) {
                return true;
            }
        }
        return false;
    }

    @FXML
    private void GoToPage(ActionEvent event) throws IOException {
        if (event.getSource() == fightButton) {
            Parent fightScreen = FXMLLoader.load(getClass().getResource("BattleScreen.fxml"));
            fightButton.getScene().setRoot(fightScreen);
        }
        timer.stop();
    }
}
