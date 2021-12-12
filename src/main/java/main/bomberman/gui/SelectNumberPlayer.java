package main.bomberman.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.bomberman.BombermanGame;
import main.bomberman.sound.Sound;

import java.io.IOException;

public class SelectNumberPlayer {
    public void onePlayer(ActionEvent event) {
        try {
            Sound.playSound(Sound.placeBomb);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(BombermanGame.class.getResource("get-name.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void twoPlayer(ActionEvent event) {
        try {
            Sound.playSound(Sound.placeBomb);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(BombermanGame.class.getResource("select-character-2player.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
