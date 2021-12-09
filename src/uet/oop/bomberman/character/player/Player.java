package uet.oop.bomberman.character.player;

import javafx.scene.image.Image;
import uet.oop.bomberman.character.GameCharacter;
import uet.oop.bomberman.data.Calc;
import uet.oop.bomberman.data.GameData;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.keyboard_controller.Input;
import uet.oop.bomberman.tile_object.Brick;
import uet.oop.bomberman.tile_object.Wall;
import uet.oop.bomberman.tile_object.bomb.Bomb;
import uet.oop.bomberman.tile_object.bomb.FlameSegment;
import uet.oop.bomberman.tile_object.item.Item;

import java.util.ArrayList;
import java.util.List;

public class Player extends GameCharacter {
    private static final Image[] left;
    private static final Image[] right;
    private static final Image[] up;
    private static final Image[] down;
    private static final Image[] dead;
    private static final int START_BOMBS = 1;
    private static final int START_SPEED = 2;
    private static final int START_FLAMES = 1;
    private static final int START_HEALTH = 3;

    private static final int TIME_BETWEEN_PUT_BOMBS = 20;//frame

    private int numberBomb;
    private int frame_length;
    private final Input input;
    private int timeToPlaceNextBomb;  //time left to put bomb
    private final int health;         //player health
    private List<Bomb> bombList = new ArrayList<Bomb>();

    static {
        left = new Image[3];
        left[0] = Sprite.player_left.getFxImage();
        left[1] = Sprite.player_left_1.getFxImage();
        left[2] = Sprite.player_left_2.getFxImage();

        right = new Image[3];
        right[0] = Sprite.player_right.getFxImage();
        right[1] = Sprite.player_right_1.getFxImage();
        right[2] = Sprite.player_right_2.getFxImage();

        up = new Image[3];
        up[0] = Sprite.player_up.getFxImage();
        up[1] = Sprite.player_up_1.getFxImage();
        up[2] = Sprite.player_up_2.getFxImage();

        down = new Image[3];
        down[0] = Sprite.player_down.getFxImage();
        down[1] = Sprite.player_down_1.getFxImage();
        down[2] = Sprite.player_down_2.getFxImage();

        dead = new Image[3];
        dead[0] = Sprite.player_dead1.getFxImage();
        dead[1] = Sprite.player_dead2.getFxImage();
        dead[2] = Sprite.player_dead3.getFxImage();


    }

    public Player(int x, int y) {
        super(x, y, left[0]);
        numberBomb = START_BOMBS;
        frame_length = START_FLAMES;
        speed = START_SPEED;
        timeToPlaceNextBomb = 0;
        health = START_HEALTH;
        createFramesInfo();
        input = GameData.getInput();        //tham chiếu đến input của GameData
    }

    public void readInput() {
        setVelocity(0, 0);
        isRunning = false;
        if (input.up()) {
            setStatusMove("UP");
        } else if (input.down()) {
            setStatusMove("DOWN");
        } else if (input.left()) {
            setStatusMove("LEFT");
        } else if (input.right()) {
            setStatusMove("RIGHT");
        }

        if (numberBomb > bombList.size() && input.placeBomb() && timeToPlaceNextBomb < 0) {

            Bomb b = new Bomb(Calc.pixelToTile(pixel_x + TILE_SIZE / 2),
                    Calc.pixelToTile(pixel_y + TILE_SIZE / 2), frame_length);
            GameData.addEntity(b);
            bombList.add(b);
            timeToPlaceNextBomb = TIME_BETWEEN_PUT_BOMBS;
        }
    }

    private void removeExplodedBomb() {
        for (int i = 0; i < bombList.size(); i++) {
            Bomb b = bombList.get(i);
            if (b.canRemove()) {
                bombList.remove(b);
                i--;
            }
        }
    }


    @Override
    protected void createFramesInfo() {
        framesMove[0] = down;
        framesMove[1] = left;
        framesMove[2] = right;
        framesMove[3] = up;
        framesDead = dead;
    }

    public void addPowerUp(Item item) {
        item.setPowerUp(this);
    }

    public void addValue(int[] value) {
        speed += value[0];
        frame_length += value[1];
        numberBomb += value[2];
    }

    @Override
    public void update(int frame_rendered) {
        removeExplodedBomb();
        if (is_alive) {
            timeToPlaceNextBomb--;
            readInput();
            if (canMove()) {
                pixel_x += velocityX;
                pixel_y += velocityY;
            }
        } else {
            dead_duration++;
        }
        this.img = getFrame(frame_rendered);


    }


    /**
     * kiểm tra va chạm với các Entity, setting cho các va chạm
     *
     * @return true nếu có thể di chuyển
     * @return false nếu không thể di chuyển
     */
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

        if (upperLeft instanceof Item) {
            addPowerUp((Item) upperLeft);
        }
        if (upperRight instanceof Item) {
            addPowerUp((Item) upperRight);
        }
        if (downLeft instanceof Item) {
            addPowerUp((Item) downLeft);
        }
        if (downRight instanceof Item) {
            addPowerUp((Item) downRight);
        }
        return true;
    }

    @Override
    public void kill() {

    }

    @Override
    protected void afterKill() {

    }
}
