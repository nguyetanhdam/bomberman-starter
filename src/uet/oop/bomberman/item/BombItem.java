package uet.oop.bomberman.item;

import uet.oop.bomberman.graphics.Sprite;

public class BombItem extends GameItem{
    static {
        img_broken_true = Sprite.powerup_bombs.getFxImage();
    }

    public BombItem(int xUnit, int yUnit) {
        super(xUnit, yUnit);
    }

    @Override
    public void update() {

    }
}
