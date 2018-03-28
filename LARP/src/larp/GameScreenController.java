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
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import larp.model.MapObjects;
import larp.model.graphic.Sprite;

/**
 * FXML Controller class
 *
 * @author Tyree Gustafson
 */
public class GameScreenController implements Initializable  {

    @FXML
    private Button inventoryButton;
    @FXML
    private Button menuButton;
    
    static public final int WIDTH = 640;
    static public final int HEIGHT = 640;
    static public final int MOVEMENT = 5;
    static public final int OVERLAP_OFFSET = 45;
    static public final int TILE_SIZE = 32;

    static MapObjects thingy = new MapObjects(0,0,20 * TILE_SIZE,4 * TILE_SIZE);
    static MapObjects thingy1 = new MapObjects(400,400,50,50);
    static MapObjects thingy2 = new MapObjects(9 * TILE_SIZE,14 * TILE_SIZE,4 * TILE_SIZE,4 * TILE_SIZE);
    static MapObjects thingy3 = new MapObjects(200,500,50,50);
    static ArrayList<MapObjects> thingyList = new ArrayList<MapObjects>();
    static Scene scene;
    static Canvas graphics;
    static GraphicsContext gc;
    static ArrayList<String> keyPressed;
    static Sprite player;
    static AnimationTimer timer;
    static Image background = new Image("/img/DungeonMap2.png");
    static ImagePattern backgroundPattern = new ImagePattern(background);
    //static DGame game = new DGame();
    @FXML
    private AnchorPane rootPane;
    @FXML
    private Font x1;
    @FXML
    private Font x2;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setup();
    }
    
    public void setup(){

        graphics = new Canvas(WIDTH, HEIGHT);
        gc = graphics.getGraphicsContext2D();
        player = new Sprite();
        keyPressed = new ArrayList<>();
        
        thingyList.add(thingy);
        thingyList.add(thingy1);
        thingyList.add(thingy2);
        thingyList.add(thingy3);
        
        rootPane.getChildren().add(graphics);
        addThingyToPane();
        setupKeyPresses();
        startGameLoop();
    }
    public void addThingyToPane(){
        for(int i = 0; i < thingyList.size(); i++){
            thingyList.get(i).bounds.setFill(Color.TRANSPARENT);
            thingyList.get(i).bounds.setStroke(Color.RED);
            rootPane.getChildren().add(thingyList.get(i).bounds);
        }
    }
    public void setupKeyPresses(){
        rootPane.setOnKeyPressed( new EventHandler<KeyEvent>(){
            
            public void handle(KeyEvent e){
                if(!keyPressed.contains(e.getCode().toString()))
                    keyPressed.add(e.getCode().toString());
            }
        });
        rootPane.setOnKeyReleased( new EventHandler<KeyEvent>(){
            
            public void handle(KeyEvent e){
                keyPressed.remove(e.getCode().toString());
            }
        });
    }
    public void startGameLoop(){
        timer = new AnimationTimer(){
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
    
    public static void update(){
        if(keyPressed.contains("UP")){
            if(player.getYCoordinate() - MOVEMENT >= 0){
                if (!checkCollisionAllObjects())
                    player.updateCoord(0,(0 - MOVEMENT));
                else
                    gc.drawImage(player.move(), player.getXCoordinate(), player.getYCoordinate());
            }
        }
        if(keyPressed.contains("DOWN")){
            if(player.getYOffset() + MOVEMENT <= HEIGHT){
                if (!checkCollisionAllObjects())
                    player.updateCoord(0, MOVEMENT);
                else
                    gc.drawImage(player.move(), player.getXCoordinate(), player.getYCoordinate());
            }
        }
        if(keyPressed.contains("LEFT")){
            if(player.getXCoordinate() - MOVEMENT >= 0){
                if (!checkCollisionAllObjects())
                    player.updateCoord((0 - MOVEMENT), 0);
                else
                    gc.drawImage(player.move(), player.getXCoordinate(), player.getYCoordinate());
            }
        }
        if(keyPressed.contains("RIGHT")){
            if(player.getXOffset() + MOVEMENT <= WIDTH){
                if (!checkCollisionAllObjects())
                    player.updateCoord(MOVEMENT, 0);
                else
                    gc.drawImage(player.move(), player.getXCoordinate(), player.getYCoordinate());
            }
        }
    }
    public static boolean checkCollisionAllObjects(){
        for(int i = 0; i < thingyList.size(); i++){
            if(checkCollision(thingyList.get(i)))
                return true;
        }
        return false;
    }
    
    public static void paint(){
        gc.setFill(backgroundPattern);
        gc.fillRect(0, 0, graphics.getWidth(), graphics.getHeight());
        
        if(keyPressed.isEmpty()) {
            gc.drawImage(player.stay(), player.getXCoordinate(), player.getYCoordinate());
        }
        else {
            gc.drawImage(player.move(), player.getXCoordinate(), player.getYCoordinate());
        }
    }

    public static boolean checkCollision(MapObjects objectBounds){
        if(keyPressed.contains("RIGHT")){
            javafx.scene.shape.Rectangle temp = new Rectangle(player.getXCoordinate() + MOVEMENT  , player.getYCoordinate() + OVERLAP_OFFSET, 45, 60 - OVERLAP_OFFSET);
            if (temp.getBoundsInParent().intersects(objectBounds.bounds.getBoundsInParent())){
                return true;
            }
        }
        if(keyPressed.contains("LEFT")){
            javafx.scene.shape.Rectangle temp = new Rectangle(player.getXCoordinate() - MOVEMENT, player.getYCoordinate() + OVERLAP_OFFSET, 45, 60 - OVERLAP_OFFSET);
            if (temp.getBoundsInParent().intersects(objectBounds.bounds.getBoundsInParent())){
                return true;
            }
        }
        if(keyPressed.contains("UP")){
            javafx.scene.shape.Rectangle temp = new Rectangle(player.getXCoordinate(), player.getYCoordinate() - MOVEMENT + OVERLAP_OFFSET, 45, 60 - OVERLAP_OFFSET);
            if (temp.getBoundsInParent().intersects(objectBounds.bounds.getBoundsInParent())){
                return true;
            }
        }
        if(keyPressed.contains("DOWN")){
            javafx.scene.shape.Rectangle temp = new Rectangle(player.getXCoordinate(), player.getYCoordinate() + MOVEMENT + OVERLAP_OFFSET, 45, 60 - OVERLAP_OFFSET);
            if (temp.getBoundsInParent().intersects(objectBounds.bounds.getBoundsInParent())){
                return true;
            }
        }
            return false;
    }

    @FXML
    private void GoToPage(ActionEvent event) throws IOException {
        if(event.getSource() == inventoryButton){
            Parent inventoryScreen = FXMLLoader.load(getClass().getResource("InventoryScreen.fxml"));
            inventoryButton.getScene().setRoot(inventoryScreen);
        }else if(event.getSource() == menuButton){
            Parent menuScreen = FXMLLoader.load(getClass().getResource("MainMenuScreen.fxml"));
            menuButton.getScene().setRoot(menuScreen);
        }
        timer.stop();
    }
}
