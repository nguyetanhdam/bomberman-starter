package uet.oop.bomberman.enemy;

import uet.oop.bomberman.graphics.Sprite;

public class Balloon extends Enemy{
    static {
        enemy_image = Sprite.balloom_left1.getFxImage();
    }

    public Balloon(int xUnit, int yUnit) {
        super(xUnit, yUnit);
    }

    @Override
    public void update() {

    }
}
