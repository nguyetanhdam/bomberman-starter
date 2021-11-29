package uet.oop.bomberman.tile_object;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Grass extends AnimatedTile {
    public static Image grass_img = Sprite.grass.getFxImage();

    public Grass(int x, int y) {
        super(x, y,grass_img);
    }

    @Override
    public void update(int frame_rendered) {

    }
}
