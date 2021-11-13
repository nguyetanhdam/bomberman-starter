package uet.oop.bomberman.item;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public abstract class GameItem extends Entity {
    protected boolean broken = false;

    protected static Image img_broken_false = Sprite.brick.getFxImage();

    protected static Image img_broken_true = null;

    public GameItem(int xUnit, int yUnit) {
        super(xUnit, yUnit);
        this.img = img_broken_false;
    }
}
