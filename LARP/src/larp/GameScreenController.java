package larp;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
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
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import larp.model.MapObjects;
import larp.model.graphic.Sprite;

/**
 * FXML Controller class
 *
 * @author Tyree Gustafson
 */
public class GameScreenController implements Initializable {

    @FXML
    private Button inventoryButton;
    @FXML
    private Button menuButton;
    /*@FXML
    private Button playButton;*/
    
    static public final int WIDTH = 700;
    static public final int HEIGHT = 700;
    static public final int MOVEMENT = 3;

    static MapObjects thingy = new MapObjects();
    static Scene scene;
    static Canvas graphics;
    static GraphicsContext gc;
    static ArrayList<String> keyPressed;
    static Sprite player;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private Font x1;
    @FXML
    private Pane inventoryPane;
    @FXML
    private Button backButton;
    @FXML
    private Button equipItemButton;
    @FXML
    private Button sellItemButton;
    @FXML
    private Button deleteItemButton;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setup();
    }
    
    public void setup(){


        graphics = new Canvas(WIDTH, HEIGHT);
        //rootPane.getChildren().add(graphics);
        rootPane.getChildren().add(0, graphics);
        rootPane.getChildren().add(thingy.bounds);
        gc = graphics.getGraphicsContext2D();
        player = new Sprite();
        keyPressed = new ArrayList<>();
        
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
        
        new AnimationTimer() {
            public void handle(long currentNanoTime){
                update();
                paint();


            }
        }.start();
    
    }
    public static void update(){
        if(keyPressed.contains("UP")){
            if(player.getYCoordinate() - MOVEMENT >= 0){
                if (!checkCollision())
                    player.updateCoord(0,(0 - MOVEMENT));
            }
        }
        if(keyPressed.contains("DOWN")){
            if(player.getYOffset() + MOVEMENT <= HEIGHT){
                if (!checkCollision())
                    player.updateCoord(0, MOVEMENT);
            }
        }
        if(keyPressed.contains("LEFT")){
            if(player.getXCoordinate() - MOVEMENT >= 0){
                if (!checkCollision())
                    player.updateCoord((0 - MOVEMENT), 0);
            }
        }
        if(keyPressed.contains("RIGHT")){
            if(player.getXOffset() + MOVEMENT <= WIDTH){
                if (!checkCollision()) {
                    player.updateCoord(MOVEMENT, 0);
                }
            }
        }
    }
    
    public static void paint(){
        gc.setFill(Paint.valueOf("white"));
        gc.fillRect(0, 0, graphics.getWidth(), graphics.getHeight());
        
        if(keyPressed.isEmpty()) {
            gc.drawImage(player.stay(), player.getXCoordinate(), player.getYCoordinate());
            //gc.a (thingy.bounds, thingy.getXCoordinate(), thingy.getYCoordinate());
        }
        else {
            gc.drawImage(player.move(), player.getXCoordinate(), player.getYCoordinate());
        }
    }

    public static boolean checkCollision(){
        if(keyPressed.contains("RIGHT")){
            javafx.scene.shape.Rectangle temp = new Rectangle(player.getXCoordinate()+3, player.getYCoordinate(), 60, 60);
            if (temp.getBoundsInParent().intersects(thingy.bounds.getBoundsInParent())){

                return true;
            }

        }
        if(keyPressed.contains("LEFT")){
            javafx.scene.shape.Rectangle temp = new Rectangle(player.getXCoordinate()-3, player.getYCoordinate(), 60, 60);
            if (temp.getBoundsInParent().intersects(thingy.bounds.getBoundsInParent())){

                return true;
            }

        }
        if(keyPressed.contains("UP")){
            javafx.scene.shape.Rectangle temp = new Rectangle(player.getXCoordinate(), player.getYCoordinate()-3, 60, 60);
            if (temp.getBoundsInParent().intersects(thingy.bounds.getBoundsInParent())){

                return true;
            }

        }
        if(keyPressed.contains("DOWN")){
            javafx.scene.shape.Rectangle temp = new Rectangle(player.getXCoordinate(), player.getYCoordinate()+3, 60, 60);
            if (temp.getBoundsInParent().intersects(thingy.bounds.getBoundsInParent())){

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
    }

    @FXML
    private void showInventory(ActionEvent event) {
        inventoryPane.setDisable(false);
        inventoryPane.setOpacity(1.0);
    }

    @FXML
    private void hideInventory(ActionEvent event) {
        inventoryPane.setDisable(true);
        inventoryPane.setOpacity(0);
    }

}
