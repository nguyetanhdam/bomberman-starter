package uet.oop.bomberman.enemy;

import uet.oop.bomberman.graphics.Sprite;

public class Oneal extends Enemy{
    static {
        enemy_image = Sprite.oneal_right1.getFxImage();
    }

    public Oneal(int xUnit, int yUnit) {
        super(xUnit, yUnit);
    }

    @Override
    public void update() {

    }
}
