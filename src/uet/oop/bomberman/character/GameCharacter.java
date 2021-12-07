package uet.oop.bomberman.character;

import javafx.scene.image.Image;
import uet.oop.bomberman.data.Calc;
import uet.oop.bomberman.data.GameData;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.tile_object.Brick;
import uet.oop.bomberman.tile_object.Wall;

public abstract class GameCharacter extends Entity {
    protected static final int DURATION = 6;    //frame per rendered_frame
    protected static final int DEAD_TIME = 60;
    protected double velocityX;
    protected double velocityY;
    protected double speed;
    protected Image[][] framesMove = new Image[4][];
    protected Image[] framesDead;
    protected boolean isRunning;                //checking moving or stand
    protected boolean is_alive = true;
    protected boolean is_removed = false;
    protected int statusMove = 0;   //0:down, 1:left, 2:right, 3:up
    protected int dead_duration = 0;


    public GameCharacter(int x, int y, Image img) {
        super(x, y, img);
        velocityX = 0;
        velocityY = 0;
        speed = 0.5;
    }

    protected abstract void createFramesInfo();

    public Image getFrame(int frame_rendered) {
        //if character is alive
        if (is_alive) {
            Image[] frames = framesMove[statusMove];

            if(!isRunning) return frames[0];    //character is standing

            //player is moving
            int index = (frame_rendered % (frames.length * DURATION)) / DURATION;
            return frames[index];
        }

        //character is dead
        int i = (dead_duration % (framesDead.length * DEAD_TIME)) / DEAD_TIME;
        return framesDead[i];
    }

    public void setVelocity(double x, double y) {
        velocityX = x;
        velocityY = y;
    }

    public void setStatusMove(String statusMove) {
        setVelocity(0, 0);
        switch (statusMove) {
            case "DOWN" -> {
                this.statusMove = 0;
                velocityY += speed;
                isRunning = true;
            }
            case "LEFT" -> {
                this.statusMove = 1;
                velocityX -= speed;
                isRunning = true;
            }
            case "RIGHT" -> {
                this.statusMove = 2;
                velocityX += speed;
                isRunning = true;
            }
            case "UP" -> {
                this.statusMove = 3;
                velocityY -= speed;
                isRunning = true;
            }
            default -> isRunning = false;
        }
    }

    public void setStatusMove(int statusMove) {
        isRunning = true;
        setVelocity(0, 0);
        switch (statusMove) {
            case 0 -> velocityY = speed;
            case 1 -> velocityX = -speed;
            case 2 -> velocityX = speed;
            case 3 -> velocityY = -speed;
            default -> isRunning = false;
        }
        if (isRunning)
            this.statusMove = statusMove;
    }

    public void update(int frame_rendered) {
        if (is_alive) {
            if (canMove()) {
                pixel_x += velocityX;
                pixel_y += velocityY;
            }
        } else {
            dead_duration++;
        }


        this.img = getFrame(frame_rendered);
    }

    public void dead() {
        is_alive = false;
    }

    public boolean isRemoved() {
        return is_removed;
    }

    public boolean canMove() {

        Entity upperLeft = GameData.getEntityAt(Calc.pixelToTile(pixel_x + velocityX + Entity.TILE_SIZE / 4), Calc.pixelToTile(pixel_y + velocityY + Entity.TILE_SIZE / 4));
        Entity upperRight = GameData.getEntityAt(Calc.pixelToTile(pixel_x + velocityX + Entity.TILE_SIZE * 3 / 4), Calc.pixelToTile(pixel_y + velocityY + Entity.TILE_SIZE / 4));
        Entity downLeft = GameData.getEntityAt(Calc.pixelToTile(pixel_x + velocityX + Entity.TILE_SIZE / 4), Calc.pixelToTile(pixel_y + velocityY + Entity.TILE_SIZE * 3 / 4));
        Entity downRight = GameData.getEntityAt(Calc.pixelToTile(pixel_x + velocityX + Entity.TILE_SIZE * 3 / 4), Calc.pixelToTile(pixel_y + velocityY + Entity.TILE_SIZE * 3 / 4));
        if (upperLeft instanceof Wall || upperRight instanceof Wall ||
                downLeft instanceof Wall || downRight instanceof Wall) {
            return false;
        }
        if (upperLeft instanceof Brick ||
                upperRight instanceof Brick ||
                downLeft instanceof Brick ||
                downRight instanceof Brick) {
            return false;
        }

        return true;
    }

    /**
     * Được gọi khi đối tượng bị tiêu diệt
     */
    public abstract void kill();

    /**
     * Xử lý hiệu ứng bị tiêu diệt
     */
    protected abstract void afterKill();
}
