/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package larp;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import larp.model.Battle;

/**
 * FXML Controller class
 *
 * @author Tyree Gustafson
 */
public class BattleScreenController implements Initializable {
    
    static Battle battle;
    @FXML
    private Rectangle enemyHealthRec;
    @FXML
    private Color x1;
    @FXML
    private Rectangle playerHealthRec;
    @FXML
    private Button runButton;
    @FXML
    private Font x2;
    @FXML
    private Button attackButton;
    @FXML
    private Button healButton;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void run(ActionEvent event) throws IOException {
        if(battle.run() == 2){
            GoToPage(event);
        }
    }
    private void GoToPage(ActionEvent event) throws IOException {
        if (event.getSource() == runButton || event.getSource() == attackButton) {
            Parent gameScreen = FXMLLoader.load(getClass().getResource("GameScreen.fxml"));
            runButton.getScene().setRoot(gameScreen);
        }
    }

    @FXML
    private void attack(ActionEvent event) throws IOException {
        int x = battle.attack();
        System.out.println(x);
        if(x == 1 || x == 0){
            GoToPage(event);
        }else{
        }
    }

    @FXML
    private void heal(ActionEvent event) {
        battle.heal();
    }
    
}
