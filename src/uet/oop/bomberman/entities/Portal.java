package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Portal extends Entity{
    public Portal(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public Portal(int xUnit, int yUnit) {
        super(xUnit, yUnit);
        this.img = Sprite.portal.getFxImage();
    }

    @Override
    public void update() {

    }
}
