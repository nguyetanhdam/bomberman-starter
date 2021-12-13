package main.bomberman.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.bomberman.BombermanGame;
import main.bomberman.Game;
import main.bomberman.sound.Sound;

import java.io.IOException;

public class GetNamePlayer {
    @FXML
    TextField namePlayer;

    public void continueBtn(ActionEvent event) throws IOException {
        Sound.playSound(Sound.placeBomb);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        new Game(stage, namePlayer.getText());
    }
}
