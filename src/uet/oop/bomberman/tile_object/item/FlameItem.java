package uet.oop.bomberman.tile_object.item;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class FlameItem extends Item {

    public static Image flame_item = Sprite.powerup_flames.getFxImage();

    public FlameItem(int x, int y) {
        super(x, y, flame_item);
        value[1] = 1;
    }

    @Override
    public void update(int frame_rendered) {

    }

}
