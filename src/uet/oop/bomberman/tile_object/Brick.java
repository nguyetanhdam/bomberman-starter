package uet.oop.bomberman.tile_object;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.data.GameData;
import uet.oop.bomberman.graphics.Sprite;

public class Brick extends AnimatedTile {
    private static final Image[] animationImages;
    private static final int broken_duration = 30;

    private boolean is_broken = false;
    private boolean is_removed = false;
    private int animation_time = 0;

    static {
        animationImages = new Image[4];
        animationImages[0] = Sprite.brick.getFxImage();
        animationImages[1] = Sprite.brick_exploded.getFxImage();
        animationImages[2] = Sprite.brick_exploded1.getFxImage();
        animationImages[3] = Sprite.brick_exploded2.getFxImage();
    }

    public Brick(int x, int y) {
        super(x, y, animationImages[0]);
        frames = animationImages;
    }


    @Override
    public void update(int frame_rendered) {
        if (is_broken) {
            animation_time++;
            if (animation_time >= broken_duration) is_removed = true;
        }

        this.img = getImage();
    }

    public Image getImage() {
        int index = animation_time * frames.length / broken_duration;
        return frames[index];
    }

    public void broken() {
        is_broken = true;
    }

    public boolean isRemoved() {
        return is_removed;
    }
}
