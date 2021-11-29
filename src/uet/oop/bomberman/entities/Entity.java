package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.data.Calc;

public abstract class Entity {
    public static int TILE_SIZE = 32;

    protected double pixel_x;

    protected double pixel_y;

    protected Image img;

    public Entity(int tile_x, int tile_y, Image img) {
        setTileLocation(tile_x, tile_y);
        this.img = img;
    }

    public void setTileLocation(int tile_x, int tile_y) {
        this.pixel_x = tile_x * TILE_SIZE;
        this.pixel_y = tile_y * TILE_SIZE;
    }

    public void setPixelLocation(double pixel_x, double pixel_y) {
        this.pixel_x = pixel_x;
        this.pixel_y = pixel_y;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, pixel_x, pixel_y);
    }

    public abstract void update(int frame_rendered);

    public double getPixelX() {
        return pixel_x;
    }

    public int getTileX() {
        return Calc.pixelToTile(pixel_x);
    }

    public double getPixelY() {
        return pixel_y;
    }

    public int getTileY() {
        return Calc.pixelToTile(pixel_y);
    }
}
