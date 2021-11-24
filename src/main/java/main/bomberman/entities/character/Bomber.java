package main.bomberman.entities.character;

import javafx.scene.canvas.GraphicsContext;
import main.bomberman.Input.Input;
import main.bomberman.board.BoardGame;
import main.bomberman.entities.Entity;
import main.bomberman.entities.bomb.Bomb;
import main.bomberman.entities.tile.Brick;
import main.bomberman.entities.tile.Wall;
import main.bomberman.graphics.AnimatedCharacter;

import java.util.ArrayList;

public class Bomber extends AnimatedCharacter {
    private ArrayList<Bomb> listBomb = new ArrayList<>();
    private Input input = new Input();
    private int timeToPlaceNextBomb = 0;
    private int numberBomb = 2;
    private int powerFlames = 0;

    public Bomber() {
        setScale(3);
        setFrame("sprites\\player_down_", "sprites\\player_left_",
                "sprites\\player_right_", "sprites\\player_up_", 3);
        setPosition(48, 48);
    }

    public void readInput(){
        setVelocity(0,0);
        isRunning = false;
        if (input.up()){
            setStatusMove("UP");
        }
        else if (input.down()) {
            setStatusMove("DOWN");
        }
        else if (input.left()){
            setStatusMove("LEFT");
        }
        else if (input.right()){
            setStatusMove("RIGHT");
        }
        if(numberBomb > listBomb.size() && input.placeBomb() && timeToPlaceNextBomb < 0){
            listBomb.add(new  Bomb(((positionX + width / 2)/width)*width, ((positionY + height / 2)/height)*height, powerFlames));
            timeToPlaceNextBomb = 20;
        }
    }

    @Override
    public void update(double time){
        timeToPlaceNextBomb--;

        super.update(time);

        if(listBomb.size() > 0) {
            for(int i = 0; i < listBomb.size(); i++) {
                Bomb bomb = listBomb.get(i);
                bomb.update();
                if (bomb.isDestroyed()) {
                    listBomb.remove(bomb);
                    i--;
                }
            }
        }
    }

    @Override
    public boolean canMove(int x, int y){
        x += width/3;
        y += height/3;

        Entity upperLeft = BoardGame.getEntityAt((x - width/4)/width, y/height);
        Entity upperRight = BoardGame.getEntityAt((x + width/3)/width, y/height);
        Entity lowerLeft = BoardGame.getEntityAt((x - width/4)/width, (y+ 2*height/3)/height);
        Entity lowerRight = BoardGame.getEntityAt((x + width/3)/width, (y+ 2*height/3)/height);

        if(upperLeft instanceof Wall || upperRight instanceof Wall ||
                lowerLeft instanceof Wall || lowerRight instanceof Wall){
            return false;
        }

        Brick brick = null;
        if(upperLeft instanceof Brick){
            brick = (Brick) upperLeft;
            if(!brick.isDestroyed()){
                return false;
            }
            else if(brick.hasItem()){
                brick.setCollectedItem(powerUp(brick.getItem().getProperties()));
            }
        }
        if(upperRight instanceof Brick){
            brick = (Brick) upperRight;
            if(!brick.isDestroyed()){
                return false;
            }
            else if(brick.hasItem()){
                brick.setCollectedItem(powerUp(brick.getItem().getProperties()));
            }
        }
        if(lowerLeft instanceof Brick){
            brick = (Brick) lowerLeft;
            if(!brick.isDestroyed()){
                return false;
            }
            else if(brick.hasItem()){
                brick.setCollectedItem(powerUp(brick.getItem().getProperties()));
            }
        }
        if(lowerRight instanceof Brick){
            brick = (Brick) lowerRight;
            if(!brick.isDestroyed()){
                return false;
            }
            else if(brick.hasItem()){
                brick.setCollectedItem(powerUp(brick.getItem().getProperties()));
            }
        }

        return true;
    }

    public boolean powerUp(double[] properties){
        //0: speed, 1:flame, 2:bomb
        this.speed *= properties[0];
        this.powerFlames += properties[1];
        this.numberBomb += properties[2];
        return true;
    }

    public void render(GraphicsContext gc, double time){
        super.render(gc, time);

        if(listBomb.size() > 0) {
            for (Bomb bomb : listBomb) {
                bomb.render(gc, time);
            }
        }
    }

}
