package uet.oop.bomberman.item;

import uet.oop.bomberman.graphics.Sprite;

public class FlameItem extends GameItem{
    static {
        img_broken_true = Sprite.powerup_flames.getFxImage();
    }

    public FlameItem(int xUnit, int yUnit) {
        super(xUnit, yUnit);
    }

    @Override
    public void update() {

    }
}
