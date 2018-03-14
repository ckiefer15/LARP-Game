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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.stage.Stage;



/**
 *
 * @author AfricanJesus
 */
public class MainMenuScreenController implements Initializable {
    
    @FXML
    private Button playButton;
    @FXML
    private Button optionButton;
    @FXML
    private Button tutorialButton;
    @FXML
    private Button creditButton;
    @FXML
    private Button exitButton;
    
    //private Parent gameScreen;
    @FXML
    private Font x1;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void GoToGame(ActionEvent event) throws IOException {
        Parent gameScreen = FXMLLoader.load(getClass().getResource("GameScreen.fxml"));
        playButton.getScene().setRoot(gameScreen);
    }
    
}
