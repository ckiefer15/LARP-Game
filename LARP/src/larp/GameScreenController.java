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
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import larp.model.*;
import larp.model.graphic.*;
import larp.model.character.*;
import larp.model.room.object.*;
import larp.model.inventory.Item;


/**
 * FXML Controller class
 *
 * @author Tyree Gustafson
 */
public class GameScreenController implements Initializable {

    static public final int WIDTH = 640;
    static public final int HEIGHT = 640;
    static public final int MOVEMENT = 5;
    static public final int COLLISION_BOX_OFFSET_Y = 30;
    static public final int COLLISION_BOX_OFFSET_X = 5;
    static public final int COLLISION_BOX_X = 20;
    static public final int COLLISION_BOX_Y = 45;
    static public final int TILE_SIZE = 32;
    static private boolean trace = false;
    static private Rectangle playerBounds = new Rectangle();

    Media bossMusic = new Media("file:///Users/we2423hd/Documents/larp2-cs410-winonaNew/sounds/ds3.mp3"); //replace /Movies/test.mp3 with your file
    //Media backgroundMusic = new Media("file://")
    MediaPlayer musicPlayer = new MediaPlayer(bossMusic);
    
    static Scene scene;
    static Canvas graphics;
    static GraphicsContext gc;
    static ArrayList<String> keyPressed;
    static AnimationTimer timer;
    static ImagePattern backgroundPattern;
    double playerHealthRecWidth;
    
    @FXML
    private ImageView overlayImage;
    @FXML
    private Pane itemReceivedPane;
    @FXML
    private ImageView itemImage;
    @FXML
    private AnchorPane rootPane;
    
    
    //============DGame Attributes===============
    static DGame game;
    static DRoom currentRoom;
    static ArrayList<RoomObject> roomObjects;
    static ArrayList<RoomObject> blockable;
    static Knight player;
    static Sprite playerSprite;
    @FXML
    private Rectangle playerHealthRec;
    @FXML
    private Label playerHPLabel;

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
        playerHealthRecWidth = playerHealthRec.getWidth();
        
        //============Initialize Game and Reference Variables==============
        if (game == null) {
            game = SetupDGame.setupGame(false);
            currentRoom = game.getCurrentRoom();
            backgroundPattern = new ImagePattern(currentRoom.getImage().getStaticImage());
            roomObjects = currentRoom.getRoomObjects();
            blockable = currentRoom.getBlockable();
            player = game.getPlayer();
            playerSprite = player.getSprite();

            musicPlayer.play();
        }

        
        rootPane.getChildren().add(0, graphics);
        addBoundsToPane();
        setupKeyPresses();
        updateHealthBar();
        startGameLoop();

    }
    public void addBoundsToPane() {
        for (int i = 0; i < roomObjects.size(); i++) {
            roomObjects.get(i).bounds.setFill(Color.TRANSPARENT);
            rootPane.getChildren().add(roomObjects.get(i).bounds);
        }
        playerBounds.setFill(Color.TRANSPARENT);
        rootPane.getChildren().add(playerBounds);
    }
    private void updateBoundsOnPlayer() {
        playerBounds.setHeight(COLLISION_BOX_Y - COLLISION_BOX_OFFSET_Y);
        playerBounds.setWidth(COLLISION_BOX_X);
        playerBounds.setX(playerSprite.getXCoordinate() + COLLISION_BOX_OFFSET_X);
        playerBounds.setY(playerSprite.getYCoordinate() + COLLISION_BOX_OFFSET_Y);
    }
    private void updateHealthBar(){
        double playerHPPercent = ((double) game.getPlayer().getHitPoints() / (double) game.getPlayer().getMaxHitPoints()) * 100;
        playerHealthRec.setWidth(playerHealthRecWidth * (playerHPPercent / 100));
        playerHPLabel.setText(game.getPlayer().getHitPoints() + "/" + game.getPlayer().getMaxHitPoints());
    }

    public void removeObjectBounds() {
        for (int i = 0; i < roomObjects.size(); i++) {
            rootPane.getChildren().remove(roomObjects.get(i).bounds);
        }
        rootPane.getChildren().remove(playerBounds);
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
                        game = null;
                        timer.stop();
                    } catch (IOException ex) {
                        Logger.getLogger(GameScreenController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (e.getCode().toString() == "T") {
                    //Toggle Trace
                    trace = !trace;
                    
                    //Remove Trace
                    if (trace == false) {
                        for (int i = 0; i < roomObjects.size(); i++) {
                            roomObjects.get(i).bounds.setStroke(Color.TRANSPARENT);
                        }
                        playerBounds.setStroke(Color.TRANSPARENT);
                    } else {
                        //Apply Trace (Door: Green, Conflict: BLUE, Chest: Yellow, Nonmoveable: Red)
                        for (int i = 0; i < roomObjects.size(); i++) {
                            if (roomObjects.get(i) instanceof Door) {
                                roomObjects.get(i).bounds.setStroke(Color.GREEN);
                            } else if (roomObjects.get(i) instanceof Conflict) {
                                roomObjects.get(i).bounds.setStroke(Color.BLUE);
                            } else if (roomObjects.get(i) instanceof Chest) {
                                roomObjects.get(i).bounds.setStroke(Color.YELLOW);
                            } else {
                                roomObjects.get(i).bounds.setStroke(Color.RED);
                            }
                        }
                        //Apply Trace On Player (Player: Purple)
                        playerBounds.setStroke(Color.BLUEVIOLET);
                    }
                  //Add pressed movement keys to keypressed array (W, A, S, D, UP, DOWN, LEFT, RIGHT)
                } else if (!keyPressed.contains(e.getCode().toString())) {
                    if(e.getCode().toString() == "W" || e.getCode().toString() == "A" || e.getCode().toString() == "S" || e.getCode().toString() == "D" ||
                            e.getCode().toString() == "UP" || e.getCode().toString() == "DOWN" || e.getCode().toString() == "LEFT" || e.getCode().toString() == "RIGHT"){
                        keyPressed.add(e.getCode().toString());
                    }
                }
            }
        });
        //Remove pressed keys on release from keypressed array
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
                updateBoundsOnPlayer();
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
    
    //Check collision for all objects in current room
    public static RoomObject checkCollisionAllObjects() {
        for (int i = 0; i < roomObjects.size(); i++) {
            if (checkCollision(roomObjects.get(i))) {
                return roomObjects.get(i);
            }
        }
        return null;
    }
    
    //Determine action when collision occurs with room objects
    public void handleRoomObject(RoomObject obj) {
        if (obj instanceof Door) {
            removeObjectBounds();
            currentRoom = game.changeRoom((Door) obj);
            roomObjects = currentRoom.getRoomObjects();
            backgroundPattern = new ImagePattern(currentRoom.getImage().getStaticImage());
            addBoundsToPane();
        } else if (obj instanceof Conflict) {
            try {
                BattleScreenController.battle = game.initBattle((Conflict) obj);
                BattleScreenController.game = game;
                Parent fightScreen = FXMLLoader.load(getClass().getResource("BattleScreen.fxml"));
                rootPane.getScene().setRoot(fightScreen);
                timer.stop();
            } catch (IOException ex) {
                Logger.getLogger(GameScreenController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (obj instanceof Chest) {
            game.openChest((Chest) obj);
            showItemReceived();
        }
    }
    
    //Show lastest item in inventory
    private void showItemReceived(){
        itemReceivedPane.setVisible(true);
        overlayImage.setVisible(true);
        ArrayList<Item>  temp = game.getPlayer().getInventory().getArrayList();
        itemImage.setImage(temp.get(temp.size()-1).getImage().getStaticImage());
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
    
    //Determine if the movement in a given direction is valid by checking one move ahead of the current position.
    //Create the bounds of the next move from the player's current position and check to see if it interscts any room objects
    public static boolean checkCollision(RoomObject objectBounds) {
        if (keyPressed.contains("RIGHT") || keyPressed.contains("D")) {
            javafx.scene.shape.Rectangle temp = new Rectangle(playerSprite.getXCoordinate() + MOVEMENT + COLLISION_BOX_OFFSET_X, playerSprite.getYCoordinate() + COLLISION_BOX_OFFSET_Y, COLLISION_BOX_X, COLLISION_BOX_Y - COLLISION_BOX_OFFSET_Y);
            if (temp.getBoundsInParent().intersects(objectBounds.bounds.getBoundsInParent())) {
                return true;
            }
        }
        if (keyPressed.contains("LEFT") || keyPressed.contains("A")) {
            javafx.scene.shape.Rectangle temp = new Rectangle(playerSprite.getXCoordinate() - MOVEMENT + COLLISION_BOX_OFFSET_X, playerSprite.getYCoordinate() + COLLISION_BOX_OFFSET_Y, COLLISION_BOX_X, COLLISION_BOX_Y - COLLISION_BOX_OFFSET_Y);
            if (temp.getBoundsInParent().intersects(objectBounds.bounds.getBoundsInParent())) {
                return true;
            }
        }
        if (keyPressed.contains("UP") || keyPressed.contains("W")) {
            javafx.scene.shape.Rectangle temp = new Rectangle(playerSprite.getXCoordinate() + COLLISION_BOX_OFFSET_X, playerSprite.getYCoordinate() - MOVEMENT + COLLISION_BOX_OFFSET_Y, COLLISION_BOX_X, COLLISION_BOX_Y - COLLISION_BOX_OFFSET_Y);
            if (temp.getBoundsInParent().intersects(objectBounds.bounds.getBoundsInParent())) {
                return true;
            }
        }
        if (keyPressed.contains("DOWN") || keyPressed.contains("S")) {
            javafx.scene.shape.Rectangle temp = new Rectangle(playerSprite.getXCoordinate() + COLLISION_BOX_OFFSET_X, playerSprite.getYCoordinate() + MOVEMENT + COLLISION_BOX_OFFSET_Y, COLLISION_BOX_X, COLLISION_BOX_Y - COLLISION_BOX_OFFSET_Y);
            if (temp.getBoundsInParent().intersects(objectBounds.bounds.getBoundsInParent())) {
                return true;
            }
        }
        return false;
    }
    @FXML
    private void hideItemReceived(ActionEvent event) {
        itemReceivedPane.setVisible(false);
        overlayImage.setVisible(false);
    }
}
