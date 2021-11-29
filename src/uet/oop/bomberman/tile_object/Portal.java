package uet.oop.bomberman.tile_object;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Portal extends Entity {

    public static Image portal_img = Sprite.portal.getFxImage();

    public Portal(int x, int y) {
        super(x, y, portal_img);
    }

    @Override
    public void update(int frame_rendered) {

    }
}
