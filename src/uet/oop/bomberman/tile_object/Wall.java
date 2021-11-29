package uet.oop.bomberman.tile_object;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Wall extends AnimatedTile {

    public static Image wall_img = Sprite.wall.getFxImage();

    public Wall(int x, int y) {
        super(x, y, wall_img);
    }

    @Override
    public void update(int frame_rendered) {

    }
}
