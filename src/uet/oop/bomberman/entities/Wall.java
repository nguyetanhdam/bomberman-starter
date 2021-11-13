package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Wall extends Entity {

    public Wall(int x, int y, Image img) {
        super(x, y, img);
    }

    public Wall(int x, int y) {
        super(x,y);
        this.img = Sprite.wall.getFxImage();
    }

    @Override
    public void update() {

    }
}
