package uet.oop.bomberman.tile_object.bomb;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.tile_object.AnimatedTile;

public class FlameSegment extends AnimatedTile {
    private final static Image[] middle;
    private final static Image[] horizontal;
    private final static Image[] vertical;
    private final static Image[] end_left;
    private final static Image[] end_right;
    private final static Image[] end_up;
    private final static Image[] end_down;
    private static int FLAME_DURATION = 20;       // 1/3 s

    public enum FlameType {
        MIDDLE, HORIZONTAL, VERTICAL,
        END_LEFT, END_RIGHT, END_UP, END_DOWN
    }

    static {
        middle = new Image[3];
        middle[0] = Sprite.bomb_exploded.getFxImage();
        middle[1] = Sprite.bomb_exploded1.getFxImage();
        middle[2] = Sprite.bomb_exploded2.getFxImage();

        horizontal = new Image[3];
        horizontal[0] = Sprite.explosion_horizontal.getFxImage();
        horizontal[1] = Sprite.explosion_horizontal1.getFxImage();
        horizontal[2] = Sprite.explosion_horizontal2.getFxImage();

        vertical = new Image[3];
        vertical[0] = Sprite.explosion_vertical.getFxImage();
        vertical[1] = Sprite.explosion_vertical1.getFxImage();
        vertical[2] = Sprite.explosion_vertical2.getFxImage();

        end_left = new Image[3];
        end_left[0] = Sprite.explosion_horizontal_left_last.getFxImage();
        end_left[1] = Sprite.explosion_horizontal_left_last1.getFxImage();
        end_left[2] = Sprite.explosion_horizontal_left_last2.getFxImage();

        end_right = new Image[3];
        end_right[0] = Sprite.explosion_horizontal_right_last.getFxImage();
        end_right[1] = Sprite.explosion_horizontal_right_last1.getFxImage();
        end_right[2] = Sprite.explosion_horizontal_right_last2.getFxImage();

        end_up = new Image[3];
        end_up[0] = Sprite.explosion_vertical_top_last.getFxImage();
        end_up[1] = Sprite.explosion_vertical_top_last1.getFxImage();
        end_up[2] = Sprite.explosion_vertical_top_last2.getFxImage();

        end_down = new Image[3];
        end_down[0] = Sprite.explosion_vertical_down_last.getFxImage();
        end_down[1] = Sprite.explosion_vertical_down_last1.getFxImage();
        end_down[2] = Sprite.explosion_vertical_down_last2.getFxImage();

    }

    private FlameType flameType;
    private int animated_time = 0;
    private boolean is_removed = false;

    public FlameSegment(int x, int y, FlameType f) {
        super(x, y, middle[0]);
        flameType = f;
        setFrames();
    }

    public Image getFrame(int frame_rendered) {
        //frame running: 0 1 2 2 1 0
        int index = (animated_time % (frames.length * FLAME_DURATION * 2)) / FLAME_DURATION;
        if (index > frames.length) {
            return frames[frames.length * 2 - 1 - index];
        }
        return frames[index];
    }

    private void setFrames() {
        switch (flameType) {
            case HORIZONTAL -> setFrames(horizontal);
            case VERTICAL -> setFrames(vertical);
            case END_LEFT -> setFrames(end_left);
            case END_RIGHT -> setFrames(end_right);
            case END_UP -> setFrames(end_up);
            case END_DOWN -> setFrames(end_down);
            default -> setFrames(middle);
        }
    }

    public boolean isRemoved() {
        return is_removed;
    }


    public void update(int frame_rendered) {
        if(is_removed)
            return;
        animated_time++;
        if(animated_time >= FLAME_DURATION) is_removed = true;
        this.img = getFrame(frame_rendered);
    }

}
