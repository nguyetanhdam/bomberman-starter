package main.bomberman.graphics;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import main.bomberman.board.BoardGame;
import main.bomberman.entities.Entity;
import main.bomberman.entities.tile.Brick;
import main.bomberman.entities.tile.Wall;

import java.net.MalformedURLException;

abstract public class AnimatedCharacter extends Entity {
    protected double duration = 0.10;
    protected double velocityX;
    protected double velocityY;
    protected double speed;
    protected Image[][] framesMove = new Image[4][];
    protected boolean isRunning;
    protected int statusMove = 0; //0: down, 1: left, 2: right, 3: up

    public AnimatedCharacter(){
        super();
        velocityX = 0;
        velocityY = 0;
        speed = 200;
    }

    public void setFrame(String down, String left, String right, String up, int _number) {
        try {
            for (int i = 0; i < _number; i++)
                framesMove[0] = Sprite.getListImage(down, _number, scale);
            for (int i = 0; i < _number; i++)
                framesMove[1] = Sprite.getListImage(left, _number, scale);
            for (int i = 0; i < _number; i++)
                framesMove[2] = Sprite.getListImage(right, _number, scale);
            for (int i = 0; i < _number; i++)
                framesMove[3] = Sprite.getListImage(up, _number, scale);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public Image getFrame(double time, Image[] frames){
        int index = (int)((time % (frames.length * duration)) / duration);
        return frames[index];
    }

    public void setVelocity(double x, double y)
    {
        velocityX = x;
        velocityY = y;
    }

    public void addVelocity(double x, double y)
    {
        velocityX += x;
        velocityY += y;
    }

    public void setStatusMove(String statusMove){
        setVelocity(0, 0);
        if(statusMove.equals("DOWN")) {
            this.statusMove = 0;
            velocityY += speed;
            isRunning = true;
        }
        else if(statusMove.equals("LEFT")){
            this.statusMove = 1;
            velocityX -= speed;
            isRunning = true;
        }
        else if(statusMove.equals("RIGHT")){
            this.statusMove = 2;
            velocityX += speed;
            isRunning = true;
        }
        else if(statusMove.equals("UP")){
            this.statusMove = 3;
            velocityY -= speed;
            isRunning = true;
        }
        else{
            isRunning = false;
        }
    }

    public void setStatusMove(int statusMove){
        isRunning = true;
        setVelocity(0, 0);
        switch (statusMove){
            case 0:
                velocityY += speed;
                break;
            case 1:
                velocityX -= speed;
                break;
            case 2:
                velocityX += speed;
                break;
            case 3:
                velocityY -= speed;
                break;
            default:
                isRunning = false;
        }
        if(isRunning)
            this.statusMove = statusMove;
    }
    public void update(double time) {
        if(canMove((int) (positionX + velocityX * time), (int)(positionY + velocityY * time))){
            positionX += velocityX * time;
            positionY += velocityY * time;
        }
    }

    public void render(GraphicsContext gc, double time) {
        if(isRunning) {
            gc.drawImage(getFrame(time, framesMove[statusMove]), positionX, positionY);
        }
        else gc.drawImage(framesMove[statusMove][0], positionX, positionY);
    }

    public boolean canMove(int x, int y){
        x += width/3;
        y += height/3;

        Entity upperLeft = BoardGame.getEntityAt((x - width/4)/width, y/height);
        Entity upperRight = BoardGame.getEntityAt((x + width/3)/width, y/height);
        Entity downLeft = BoardGame.getEntityAt((x - width/4)/width, (y+ 2*height/3)/height);
        Entity downRight = BoardGame.getEntityAt((x + width/3)/width, (y+ 2*height/3)/height);
        if(upperLeft instanceof Wall || upperRight instanceof Wall ||
                downLeft instanceof Wall || downRight instanceof Wall){
            return false;
        }
        if((upperLeft instanceof Brick && !((Brick) upperLeft).isDestroyed())||
                (upperRight instanceof Brick && !((Brick) upperRight).isDestroyed())||
                (downLeft instanceof Brick && !((Brick) downLeft).isDestroyed())||
                (downRight instanceof Brick && !((Brick) downRight).isDestroyed())){
            return false;
        }

        return true;
    }
}
