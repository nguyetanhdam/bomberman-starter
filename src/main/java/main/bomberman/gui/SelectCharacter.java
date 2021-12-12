package main.bomberman.gui;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;
import main.bomberman.Game;
import main.bomberman.sound.Sound;

public class SelectCharacter {
    public CheckBox normalMode;
    public CheckBox specialMode;
    public CheckBox AIMode;

    private int mode = 1;

    private String namePlayer;

    public void setName(String name){
        namePlayer = name;
    }

    public void selectFirst(ActionEvent event) {
        Sound.playSound(Sound.placeBomb);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        new Game(stage, namePlayer, 1, mode);
    }

    public void selectSecond(ActionEvent event) {
        Sound.playSound(Sound.placeBomb);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        new Game(stage, namePlayer, 2, mode);
    }

    public void selectThird(ActionEvent event) {
        Sound.playSound(Sound.placeBomb);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        new Game(stage, namePlayer, 3, mode);
    }


    public void selectNormalMode(ActionEvent event) {
        mode = 1;
        specialMode.setSelected(false);
        AIMode.setSelected(false);
    }

    public void selectSpecialMode(ActionEvent event) {
        mode = 2;
        normalMode.setSelected(false);
        AIMode.setSelected(false);
    }

    public void selectAIMode(ActionEvent event) {
        mode = 3;
        specialMode.setSelected(false);
        normalMode.setSelected(false);
    }
}
