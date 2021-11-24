package main.bomberman.entities.bomb;

import javafx.scene.canvas.GraphicsContext;
import main.bomberman.graphics.AnimatedImage;
import main.bomberman.graphics.Sprite;

import java.net.MalformedURLException;

public class Bomb extends AnimatedImage {
    private boolean isExplored = false;
    private int timeToExplode = 120; //2s
    private Flame flame;
    private boolean isDestroyed = false;
    private int powerFlames = 0;

    public Bomb(int x, int y, int length){
        try {
            duration = 0.2;
            setPosition(x, y);
            powerFlames = length;
            setFrame(Sprite.getListImage("sprites\\bomb_", 3, 3));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void update(){
        if(timeToExplode > 0)
            timeToExplode--;
        else {
            if(!isExplored) {
                isExplored = true;
                flame = new Flame(positionX, positionY, powerFlames);
            }
            else {

            }
        }
    }

    public boolean isDestroyed(){
        return isDestroyed;
    }

    @Override
    public void render(GraphicsContext gc, double time){
        if(isExplored){
            if(!isDestroyed) {
                flame.render(gc, time);

                if(flame.isShowed())
                    isDestroyed = true;
            }
        }
        else{
            super.render(gc, time);
        }
    }

}
