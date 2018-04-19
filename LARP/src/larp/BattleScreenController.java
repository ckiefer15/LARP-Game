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
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import larp.model.Battle;
import larp.model.DGame;

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
    @FXML
    private Label playerNameLabel;
    @FXML
    private Label enemyNameLabel;
    @FXML
    private Label playerHPLabel;
    @FXML
    private Label enemyHPLabel;
    
    private double playerHealthRecWidth;
    private double enemyHealthRecWidth;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        playerHealthRecWidth = playerHealthRec.getWidth();
        enemyHealthRecWidth = enemyHealthRec.getWidth();
        updateDisplayInfo();
    }    

    @FXML
    private void run(ActionEvent event) throws IOException {
        if(battle.run() == 2){
            GoToPage(event);
        }
        updateDisplayInfo();
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
        System.out.println(battle.getPlayer().getDamage());
        if(x == 1 || x == 0){
            if(DGame.getInstance().getGameStatus()){
               //display winning message
            } else{
            GoToPage(event);
            }
        }else{
            updateDisplayInfo();
        }
    }
    private void updateDisplayInfo(){
        playerNameLabel.setText(battle.getPlayer().getName());
        double playerHPPercent = ((double)battle.getPlayer().getHitPoints()/(double)battle.getPlayer().getMaxHitPoints()) * 100;
        playerHPLabel.setText(battle.getPlayer().getHitPoints() + "/" + battle.getPlayer().getMaxHitPoints());
        playerHealthRec.setWidth(playerHealthRecWidth * (playerHPPercent/100));
        
        enemyNameLabel.setText(battle.getEnemy().getName());
        double enemyHPPercent = ((double)battle.getEnemy().getHitPoints()/(double)battle.getEnemy().getMaxHitPoints()) * 100;
        enemyHPLabel.setText(battle.getEnemy().getHitPoints() + "/" + battle.getEnemy().getMaxHitPoints());
        enemyHealthRec.setWidth(enemyHealthRecWidth * (enemyHPPercent/100));
    }

    @FXML
    private void heal(ActionEvent event) {
        battle.heal();
    }
    
}
