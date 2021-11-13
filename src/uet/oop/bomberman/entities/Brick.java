package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Brick extends Entity{
    public Brick(int x, int y, Image img) {
        super(x, y, img);
    }

    public Brick(int x, int y) {
        super(x, y);
        this.img = Sprite.brick.getFxImage();
    }

    @Override
    public void update() {

    }
}
