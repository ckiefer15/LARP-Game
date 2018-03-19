/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package larp;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;



/**
 *
 * @author Tyree Gustafson
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
    @FXML
    private AnchorPane rootPane;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void GoToGame(ActionEvent event) throws IOException {
        Parent gameScreen = FXMLLoader.load(getClass().getResource("GameScreen.fxml"));
        playButton.getScene().setRoot(gameScreen);
    }
    @FXML
    private void closeGame(ActionEvent event) throws IOException {
        Platform.exit();
        System.exit(0);
    }
}
