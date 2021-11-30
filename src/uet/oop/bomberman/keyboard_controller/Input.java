package uet.oop.bomberman.keyboard_controller;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.util.Stack;

public class Input {
    private static ArrayList<String> input = new ArrayList<String>();
    private static Scene theScene;
    private static Stack<String> moveInput = new Stack<>();
    private static Stack<String> otherInput = new Stack<>();

    public Input() {
    }

    public static void setScene(Scene scene) {
        theScene = scene;
        theScene.setOnKeyPressed(
                new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(javafx.scene.input.KeyEvent e) {
                        String code = e.getCode().toString();
//                        if(!input.contains(code))
//                            input.add(code);
                        if (code.equals("RIGHT") || code.equals("LEFT") || code.equals("UP") || code.equals("DOWN")) {
                            if (!moveInput.contains(code)) {
                                moveInput.push(code);
                            }
                        } else if (code.equals("SPACE")) {
                            if (!otherInput.contains(code)) {
                                otherInput.push(code);
                            }
                        }

                    }
                }
        );

        theScene.setOnKeyReleased(
                new EventHandler<javafx.scene.input.KeyEvent>() {
                    @Override
                    public void handle(javafx.scene.input.KeyEvent e) {
                        String code = e.getCode().toString();
                        //input.remove(code);
                        moveInput.remove(code);
                        otherInput.remove(code);

                    }
                });
    }

    public boolean right() {
        if (moveInput.size() < 1)
            return false;
        return moveInput.peek().equals("RIGHT"); //input.contains("RIGHT") || input.contains("D");
    }

    public boolean left() {
        if (moveInput.size() < 1)
            return false;
        return moveInput.peek().equals("LEFT"); //input.contains("LEFT") || input.contains("A");
    }

    public boolean up() {
        if (moveInput.size() < 1)
            return false;
        return moveInput.peek().equals("UP"); //input.contains("UP") || input.contains("W");
    }

    public boolean down() {
        if (moveInput.size() < 1)
            return false;
        return moveInput.peek().equals("DOWN"); //input.contains("DOWN") || input.contains("S");
    }

    public boolean placeBomb() {
        if (otherInput.size() < 1)
            return false;
        return otherInput.peek().equals("SPACE"); //input.contains("SPACE") || input.contains("ENTER");
    }
}