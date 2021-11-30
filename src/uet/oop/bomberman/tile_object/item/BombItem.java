package uet.oop.bomberman.tile_object.item;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class BombItem extends Item {
    public static Image bomb_item = Sprite.powerup_bombs.getFxImage();

    public BombItem(int x, int y) {
        super(x, y, bomb_item);
        value[2] = 1;
    }


    @Override
    public void update(int frame_rendered) {

    }
}
