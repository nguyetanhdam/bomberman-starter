package uet.oop.bomberman.character.enemy;

import javafx.scene.image.Image;

import uet.oop.bomberman.character.enemy.AI.AILow;
import uet.oop.bomberman.graphics.Sprite;

public class Balloon extends Enemy{

    public static Image[] balloon_left;
    public static Image[] balloon_right;
    public static Image[] balloon_dead;

    public static double BALLOON_SPEED = 0.6;

    static {
        balloon_left = new Image[3];
        balloon_left[0] = Sprite.balloom_left1.getFxImage();
        balloon_left[1] = Sprite.balloom_left2.getFxImage();
        balloon_left[2] = Sprite.balloom_left3.getFxImage();

        balloon_right = new Image[3];
        balloon_right[0] = Sprite.balloom_right1.getFxImage();
        balloon_right[1] = Sprite.balloom_right2.getFxImage();
        balloon_right[2] = Sprite.balloom_right3.getFxImage();

        balloon_dead = new Image[1];
        balloon_dead[0] = Sprite.balloom_dead.getFxImage();
    }

    public Balloon(int x, int y) {
        super(x, y, balloon_left[0]);
        speed = BALLOON_SPEED;
        ai = new AILow();
        createFramesInfo();
    }

    @Override
    protected void createFramesInfo() {
        framesMove[0] = balloon_left;
        framesMove[1] = balloon_left;
        framesMove[2] = balloon_right;
        framesMove[3] = balloon_right;
        framesDead = balloon_dead;
    }

    @Override
    public void kill() {

    }

    @Override
    protected void afterKill() {

    }


}
