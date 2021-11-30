package uet.oop.bomberman.tile_object.item;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class SpeedItem extends Item {
    public static Image speed_item = Sprite.powerup_speed.getFxImage();

    public SpeedItem(int x, int y) {
        super(x, y, speed_item);
        value[0] = 1;
    }

    @Override
    public void update(int frame_rendered) {

    }

}
