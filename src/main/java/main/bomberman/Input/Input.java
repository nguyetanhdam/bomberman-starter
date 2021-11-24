package main.bomberman.Input;

import javafx.event.EventHandler;
import javafx.scene.Scene;

import java.util.ArrayList;
import java.util.Stack;

public class Input {
    private static ArrayList<String> input = new ArrayList<String>();
    private static Scene theScene;
    private static Stack<String> listInput = new Stack<>();
    public Input(){
    }

    public static void setScene(Scene scene){
        theScene = scene;
        theScene.setOnKeyPressed(
                new EventHandler<javafx.scene.input.KeyEvent>() {
                    @Override
                    public void handle(javafx.scene.input.KeyEvent e) {
                        String code = e.getCode().toString();
//                        if(!input.contains(code))
//                            input.add(code);
                        if(!listInput.contains(code)){
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
        if(listInput.size() < 1)
            return false;
        return listInput.peek().equals("RIGHT"); //input.contains("RIGHT") || input.contains("D");
    }
    public boolean left(){
        if(listInput.size() < 1)
            return false;
        return listInput.peek().equals("LEFT"); //input.contains("LEFT") || input.contains("A");
    }
    public boolean up(){
        if(listInput.size() < 1)
            return false;
        return listInput.peek().equals("UP"); //input.contains("UP") || input.contains("W");
    }
    public boolean down(){
        if(listInput.size() < 1)
            return false;
        return listInput.peek().equals("DOWN"); //input.contains("DOWN") || input.contains("S");
    }
    public boolean placeBomb(){
        if(listInput.size() < 1)
            return false;
        return listInput.peek().equals("SPACE"); //input.contains("SPACE") || input.contains("ENTER");
    }
}
