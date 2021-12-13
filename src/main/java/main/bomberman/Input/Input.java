package main.bomberman.Input;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import main.bomberman.board.BoardGame;

import java.util.Stack;

public class Input {
    private static Scene theScene;
    private static Stack<String> listInput = new Stack<>();
    private static Input input = new Input();
    private Input(){
    }

    public static Input getInput() {
        return input;
    }


    public static void setScene(Scene scene){
        theScene = scene;
        listInput.clear();
        theScene.setOnKeyPressed(
                new EventHandler<javafx.scene.input.KeyEvent>() {
                    @Override
                    public void handle(javafx.scene.input.KeyEvent e) {
                        String code = e.getCode().toString();
                        if(code.equalsIgnoreCase("P")){
                            BoardGame.pause();
                        }
                        else if(!listInput.contains(code)){
                            listInput.push(code);
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
                        if(listInput.size() > 0)
                            listInput.remove(code);
                    }
                });
    }

    public boolean right(){
        if (listInput.size() < 1)
            return false;

        return listInput.peek().equals("RIGHT");
    }
    public boolean left(){
        if(listInput.size() < 1)
            return false;

        return listInput.peek().equals("LEFT");
    }
    public boolean up(){
        if(listInput.size() < 1)
            return false;
        return listInput.peek().equals("UP");

    }
    public boolean down(){
        if(listInput.size() < 1)
            return false;

        return listInput.peek().equals("DOWN");

    }
    public boolean placeBomb(){
        if(listInput.size() < 1)
            return false;

        return listInput.peek().equals("SPACE"); //input.contains("SPACE") || input.contains("ENTER");
    }

    public static boolean quit(){
        if(listInput.size() < 1)
            return false;
        return listInput.peek().equals("Q");
    }
}
