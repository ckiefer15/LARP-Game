/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package larp;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import static larp.GameScreenController.game;
import larp.model.DGame;
import larp.model.inventory.Health;
import larp.model.inventory.Item;
import larp.model.inventory.Weapon;

/**
 * FXML Controller class
 *
 * @author Tyree Gustafson
 */
public class InventoryScreenController implements Initializable {
    private static final double X_CELL_SIZE = 84.5;
    private static final double Y_CELL_SIZE = 82.8;
    @FXML
    private Button equipItemButton;
    @FXML
    private Button sellItemButton;
    @FXML
    private Button deleteItemButton;
    @FXML
    private Button backButton;
    @FXML
    private GridPane inventoryGrid;
    @FXML
    private ImageView itemView;
    
    public static DGame game;
    private Item itemSelected;
    private int cellOfItemSelected;
    private ImageView itemImageInGrid;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private Label itemName;
    @FXML
    private Label itemStat;
    @FXML
    private Button showEquipButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadInventory();
        setupKeyPresses();
    }

    private void loadInventory() {
        int count = 0;
        int inventorySize = game.getPlayer().getInventory().getArrayList().size();
        ArrayList<Item>  temp = game.getPlayer().getInventory().getArrayList();
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 4; y++) {
                if(count < inventorySize){
                    itemImageInGrid = new ImageView(temp.get(count).getImage().getStaticImage());
                    itemImageInGrid.setFitHeight(50);
                    itemImageInGrid.setFitWidth(50);
                    GridPane.setHalignment(itemImageInGrid, HPos.CENTER);
                    inventoryGrid.add(itemImageInGrid, y, x);   
                }
                count++;
            }
        }
    }
    public void setupKeyPresses() {
        rootPane.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent e) {
                if(e.getCode().toString()== "ESCAPE"){
                    try {
                        Parent gameScreen = FXMLLoader.load(getClass().getResource("GameScreen.fxml"));
                        rootPane.getScene().setRoot(gameScreen);
                    } catch (IOException ex) {
                        Logger.getLogger(GameScreenController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }

    @FXML
    private void GoToPage(ActionEvent event) throws IOException {
        if (event.getSource() == backButton) {
            Parent gameScreen = FXMLLoader.load(getClass().getResource("GameScreen.fxml"));
            backButton.getScene().setRoot(gameScreen);
        }
    }

    @FXML
    private void getCellSelection(MouseEvent event) {
        double tempX = event.getX() / X_CELL_SIZE;
        double tempY = event.getY() / Y_CELL_SIZE;
        int row, col;
        
        if(tempX <= 1){
            col = 1;
        }else if(tempX <= 2){
            col = 2;
        }else if(tempX <= 3){
            col = 3;
        }else{
            col = 4;
        }
        if(tempY <= 1){
            row = 1;
        }else if(tempY <= 2){
            row = 2;
        }else if(tempY <= 3){
            row = 3;
        }else if(tempY <= 4){
            row = 4;
        }else{
            row = 5;
        }
        
        cellOfItemSelected = (row - 1) * 4 + col;
        if(cellOfItemSelected <= game.getPlayer().getInventory().getArrayList().size()){
            itemSelected = (Item) game.getPlayer().getInventory().getArrayList().get(cellOfItemSelected - 1);
            itemView.setImage(itemSelected.getImage().getStaticImage());
            
            updateItemInfo();
        }
    }
    private void updateItemInfo(){
        itemName.setText("Name: " + itemSelected.getName());
            if(itemSelected instanceof Weapon){
                itemStat.setText("Damage: " + ((Weapon)itemSelected).getWeaponDamage());
            }else if(itemSelected instanceof Health){
                itemStat.setText("Heal: " + ((Health)itemSelected).getHealthPoints());
            }
    }

    @FXML
    private void useItem(ActionEvent event) {
        if(itemSelected != null){
            game.getPlayer().useItem(itemSelected);
        }
        refreshScreen();
    }

    @FXML
    private void sellItem(ActionEvent event) {
    }

    @FXML
    private void deleteItem(ActionEvent event) {
        game.getPlayer().getInventory().getArrayList().remove(itemSelected);
        refreshScreen();
    }
    private void refreshScreen(){
        try {
            Parent inventoryScreen = FXMLLoader.load(getClass().getResource("InventoryScreen.fxml"));
            rootPane.getScene().setRoot(inventoryScreen);
        } catch (IOException ex) {
            Logger.getLogger(GameScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void resetItemSelectedInfo(){
        itemView.setImage(null);
        itemSelected = null;
        cellOfItemSelected = 0;
    }

    @FXML
    private void showEquipped(ActionEvent event) {
        itemSelected = game.getPlayer().getEquippedWeapon();
        if(itemSelected != null){
            itemView.setImage(itemSelected.getImage().getStaticImage());
            updateItemInfo();
        }
        
    }
}
