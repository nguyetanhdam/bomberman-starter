package main.bomberman.gui;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;
import main.bomberman.Game;
import main.bomberman.sound.Sound;

public class SelectCharacter2Player {
    private int mode = 1;
    private int characterPlayer1 = 1;
    private int characterPlayer2 = 1;

    public CheckBox normalMode;
    public CheckBox specialMode;
    public CheckBox char1P1;
    public CheckBox char2P1;
    public CheckBox char3P1;
    public CheckBox char1P2;
    public CheckBox char2P2;
    public CheckBox char3P2;

    public void continueBtn(ActionEvent event) {
        Sound.playSound(Sound.placeBomb);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        new Game(stage, mode, characterPlayer1, characterPlayer2);
    }

    public void selectNormalMode(ActionEvent event) {
        mode = 1;
        specialMode.setSelected(false);
    }

    public void selectSpecialMode(ActionEvent event) {
        mode = 2;
        normalMode.setSelected(false);
    }

    public void selectChar1P1(ActionEvent event) {
        characterPlayer1 = 1;
        char2P1.setSelected(false);
        char3P1.setSelected(false);
    }

    public void selectChar2P1(ActionEvent event) {
        characterPlayer1 = 2;
        char1P1.setSelected(false);
        char3P1.setSelected(false);
    }

    public void selectChar3P1(ActionEvent event) {
        characterPlayer1 = 3;
        char2P1.setSelected(false);
        char1P1.setSelected(false);
    }

    public void selectChar1P2(ActionEvent event) {
        characterPlayer2 = 1;
        char2P2.setSelected(false);
        char3P2.setSelected(false);
    }

    public void selectChar2P2(ActionEvent event) {
        characterPlayer2 = 2;
        char1P2.setSelected(false);
        char3P2.setSelected(false);
    }

    public void selectChar3P2(ActionEvent event) {
        characterPlayer2 = 3;
        char2P2.setSelected(false);
        char1P2.setSelected(false);
    }
}
