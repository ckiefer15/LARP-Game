/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author AfricanJesus
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void GoToPage(ActionEvent event) throws IOException {
        if(event.getSource() == backButton){
            Parent gameScreen = FXMLLoader.load(getClass().getResource("GameScreen.fxml"));
            backButton.getScene().setRoot(gameScreen);
        }
    }
    
}
