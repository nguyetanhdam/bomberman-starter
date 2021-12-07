package uet.oop.bomberman.character.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.data.GameData;
import uet.oop.bomberman.graphics.Sprite;

public class Oneal extends Enemy {
    public static Image[] left;
    public static Image[] right;
    public static Image[] dead;

    public static double ONEAL_SPEED = 0.5;

    static {
        left = new Image[3];
        left[0] = Sprite.oneal_left1.getFxImage();
        left[1] = Sprite.oneal_left2.getFxImage();
        left[2] = Sprite.oneal_left3.getFxImage();

        right = new Image[3];
        right[0] = Sprite.oneal_right1.getFxImage();
        right[1] = Sprite.oneal_right2.getFxImage();
        right[2] = Sprite.oneal_right3.getFxImage();

        dead = new Image[1];
        dead[0] = Sprite.oneal_dead.getFxImage();

    }

    public Oneal(int x, int y) {
        super(x, y, left[0]);
        speed = ONEAL_SPEED;
        createFramesInfo();
    }

    public void update(int frame_rendered) {

    }

    @Override
    public void kill() {

    }

    @Override
    protected void afterKill() {

    }

    @Override
    protected void createFramesInfo() {
        framesMove[0] = left;
        framesMove[1] = left;
        framesMove[2] = right;
        framesMove[3] = right;
        framesDead = dead;
    }
}
