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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
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

    public static DGame game;
    private double playerHealthRecWidth;
    private double enemyHealthRecWidth;
    private Media music = new Media(getClass().getResource("/sounds/ds3.mp3").toExternalForm()); //replace /Movies/test.mp3 with your file
    private Media winMusic = new Media(getClass().getResource("/sounds/youwon.mp3").toExternalForm());
    private Media loseMusic = new Media(getClass().getResource("/sounds/youlose.mp3").toExternalForm());
    private MediaPlayer musicPlayer = new MediaPlayer(music);

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
    @FXML
    private Pane finishGamePane;
    @FXML
    private Button finishButton;
    @FXML
    private Pane endGamePane;
    @FXML
    private Font x3;
    @FXML
    private ImageView overlayImage;
    @FXML
    private Label playerTurnResult;
    @FXML
    private Font x4;
    @FXML
    private Label enemyTurnResult;
    @FXML
    private AnchorPane rootPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        playerNameLabel.setText(battle.getPlayer().getName());
        enemyNameLabel.setText(battle.getEnemy().getName());
        playerHealthRecWidth = playerHealthRec.getWidth();
        enemyHealthRecWidth = enemyHealthRec.getWidth();
        musicPlayer.play();
        updateDisplayInfo();
    }
    //Decide what screen to switch to when battle ends
    private void GoToPage(int x) throws IOException {
        musicPlayer.stop();
        if (x == 1 || x == 2) {
            if (!game.getGameStatus() || x == 2) {
                Parent gameScreen = FXMLLoader.load(getClass().getResource("GameScreen.fxml"));
                rootPane.getScene().setRoot(gameScreen);
            } else {
                updateDisplayInfo();
                GameScreenController.game = null;
                musicPlayer = new MediaPlayer(winMusic);
                musicPlayer.play();
                showFinishGame();
            }
        } else {
            updateDisplayInfo();
            GameScreenController.game = null;
            musicPlayer = new MediaPlayer(loseMusic);
            musicPlayer.play();
            showEndGame();
        }
    }

    @FXML
    private void attack(ActionEvent event) throws IOException {
        int healthHPBeforeAttack = battle.getPlayer().getHitPoints();
        int enemyHPBeforeAttack = battle.getEnemy().getHitPoints();
        int x = battle.attack();
        if (x == 1 || x == 0) {
            GoToPage(x);
        } else {
            updateDisplayInfo();
        }

        //display result of player after attack turn
        if (healthHPBeforeAttack == battle.getPlayer().getHitPoints()) {
            playerTurnResult.setText("MISS");
        } else {
            playerTurnResult.setText("-" + (healthHPBeforeAttack - battle.getPlayer().getHitPoints()));
        }
        //display result of enemy after attack turn
        if (enemyHPBeforeAttack == battle.getEnemy().getHitPoints()) {
            enemyTurnResult.setText("MISS");
        } else {
            enemyTurnResult.setText("-" + (enemyHPBeforeAttack - battle.getEnemy().getHitPoints()));
        }
    }

    @FXML
    private void heal(ActionEvent event) {
        int healthHPBeforeHeal = battle.getPlayer().getHitPoints();
        battle.heal();
        playerTurnResult.setText("+" + (battle.getPlayer().getHitPoints() - healthHPBeforeHeal));
        updateDisplayInfo();
    }

    @FXML
    private void run(ActionEvent event) throws IOException {
        int healthHPBeforeRun = battle.getPlayer().getHitPoints();
        if (battle.run() == 2) {
            GoToPage(2);
        } else {
            //display result of player after run turn
            if (healthHPBeforeRun == battle.getPlayer().getHitPoints()) {
                playerTurnResult.setText("MISS");
            } else {
                playerTurnResult.setText("-" + (healthHPBeforeRun - battle.getPlayer().getHitPoints()));
            }
        }
        updateDisplayInfo();
        enemyTurnResult.setText("");
    }
    //Display finish game pane
    private void showFinishGame() {
        overlayImage.setVisible(true);
        finishGamePane.setVisible(true);
        attackButton.setDisable(true);
        healButton.setDisable(true);
        runButton.setDisable(true);
    }
    
    //Display game over pane
    private void showEndGame() {
        overlayImage.setVisible(true);
        endGamePane.setVisible(true);
        attackButton.setDisable(true);
        healButton.setDisable(true);
        runButton.setDisable(true);
    }
    
    //Update player health info
    private void updateDisplayInfo() {
        double playerHPPercent = ((double) battle.getPlayer().getHitPoints() / (double) battle.getPlayer().getMaxHitPoints()) * 100;
        playerHPLabel.setText(battle.getPlayer().getHitPoints() + "/" + battle.getPlayer().getMaxHitPoints());
        playerHealthRec.setWidth(playerHealthRecWidth * (playerHPPercent / 100));

        double enemyHPPercent = ((double) battle.getEnemy().getHitPoints() / (double) battle.getEnemy().getMaxHitPoints()) * 100;
        enemyHPLabel.setText(battle.getEnemy().getHitPoints() + "/" + battle.getEnemy().getMaxHitPoints());
        enemyHealthRec.setWidth(enemyHealthRecWidth * (enemyHPPercent / 100));
    }

    @FXML
    private void finishGame(ActionEvent event) throws IOException {
        musicPlayer.stop();
        Parent menuScreen = FXMLLoader.load(getClass().getResource("MainMenuScreen.fxml"));
        finishButton.getScene().setRoot(menuScreen);
    }

}
