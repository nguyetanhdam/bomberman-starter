package uet.oop.bomberman.tile_object;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public abstract class AnimatedTile extends Entity {
    protected Image[] frames;

    //frame duration per frame_rendered(for loop animated)
    protected static final int duration = 6;


    public AnimatedTile(int x, int y, Image img) {
        super(x, y, img);

    }

    public void setFrames(Image[] listFrame) {
        frames = listFrame;
    }

    public Image getFrame(int frame_rendered){
        int index = (frame_rendered % (frames.length * duration)) / duration;
        return frames[index];
    }

    public void update(int frame_rendered) {
        this.img = getFrame(frame_rendered);
    }

}
