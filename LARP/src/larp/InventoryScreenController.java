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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import larp.model.DGame;
import larp.model.inventory.Item;

/**
 * FXML Controller class
 *
 * @author Tyree Gustafson
 */
public class InventoryScreenController implements Initializable {

    @FXML
    private Button equipItemButton;
    @FXML
    private Button sellItemButton;
    @FXML
    private Button deleteItemButton;
    @FXML
    private Button backButton;
    public static DGame game;
    @FXML
    private GridPane inventoryGrid;
    @FXML
    private ImageView testview;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadInventory();
    }

    private void loadInventory() {
        int count = 0;
        int inventorySize = game.getPlayer().getInventory().getArrayList().size();
        ArrayList<Item>  temp = game.getPlayer().getInventory().getArrayList();
        System.out.println(temp);
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 5; y++) {
                if(count < inventorySize)
                    inventoryGrid.add(new ImageView(temp.get(count).getImage().getStaticImage()), y, x);
                count++;
            }
        }
        //System.out.println(temp.toString());
    }

    @FXML
    private void GoToPage(ActionEvent event) throws IOException {
        if (event.getSource() == backButton) {
            Parent gameScreen = FXMLLoader.load(getClass().getResource("GameScreen.fxml"));
            backButton.getScene().setRoot(gameScreen);
        }
    }

}
