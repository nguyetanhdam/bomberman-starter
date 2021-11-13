package uet.oop.bomberman.item;

import uet.oop.bomberman.graphics.Sprite;

public class SpeedItem extends GameItem{
    static {
        img_broken_true = Sprite.powerup_speed.getFxImage();
    }

    public SpeedItem(int xUnit, int yUnit) {
        super(xUnit, yUnit);
    }

    @Override
    public void update() {

    }
}
