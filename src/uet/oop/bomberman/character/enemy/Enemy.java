package uet.oop.bomberman.character.enemy;


import javafx.scene.image.Image;
import uet.oop.bomberman.character.GameCharacter;
import uet.oop.bomberman.character.enemy.AI.AI;
import uet.oop.bomberman.data.Calc;
import uet.oop.bomberman.data.GameData;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.tile_object.Brick;
import uet.oop.bomberman.tile_object.Wall;
import uet.oop.bomberman.tile_object.bomb.Bomb;
import uet.oop.bomberman.tile_object.bomb.FlameSegment;

public abstract class Enemy extends GameCharacter {
    protected int MAX_STEPS = 20;
    protected AI ai;
    protected int steps = 0;


    public Enemy(int x, int y, Image img) {
        super(x, y, img);
        setStatusMove("LEFT");
    }

    public boolean canMove() {

        Entity upperLeft = GameData.getEntityAt(Calc.pixelToTile(pixel_x + velocityX + Entity.TILE_SIZE / 4), Calc.pixelToTile(pixel_y + velocityY + Entity.TILE_SIZE / 4));
        Entity upperRight = GameData.getEntityAt(Calc.pixelToTile(pixel_x + velocityX + Entity.TILE_SIZE * 3 / 4), Calc.pixelToTile(pixel_y + velocityY + Entity.TILE_SIZE / 4));
        Entity downLeft = GameData.getEntityAt(Calc.pixelToTile(pixel_x + velocityX + Entity.TILE_SIZE / 4), Calc.pixelToTile(pixel_y + velocityY + Entity.TILE_SIZE * 3 / 4));
        Entity downRight = GameData.getEntityAt(Calc.pixelToTile(pixel_x + velocityX + Entity.TILE_SIZE * 3 / 4), Calc.pixelToTile(pixel_y + velocityY + Entity.TILE_SIZE * 3 / 4));

        if (upperLeft instanceof FlameSegment || upperRight instanceof FlameSegment ||
                downLeft instanceof FlameSegment || downRight instanceof FlameSegment) {
            dead();
            return true;
        }

        if (upperLeft instanceof Bomb) {
            return ((Bomb) upperLeft).canPass(this);
        }

        if (upperRight instanceof Bomb) {
            return ((Bomb) upperRight).canPass(this);
        }

        if (downLeft instanceof Bomb) {
            return ((Bomb) downLeft).canPass(this);
        }

        if (downRight instanceof Bomb) {
            return ((Bomb) downRight).canPass(this);
        }

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

    public void update(int frame_rendered) {

        if(is_alive) {
            if (steps >= MAX_STEPS) {
                calcMove();
            }

            if (canMove()) {
                steps++;
                pixel_x += velocityX;
                pixel_y += velocityY;
            } else {
                calcMove();
            }
        }else {
            dead_duration++;
            if(dead_duration == DEAD_TIME)is_removed = true;
        }

        this.img = getFrame(frame_rendered);
    }

    public void calcMove() {
        steps = 0;
        setStatusMove(ai.calcDirection());
    }
}
