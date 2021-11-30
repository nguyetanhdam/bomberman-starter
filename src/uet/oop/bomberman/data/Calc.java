package uet.oop.bomberman.data;

import uet.oop.bomberman.entities.Entity;

public class Calc {
    public static int pixelToTile(double i) {
        return (int)(i / Entity.TILE_SIZE);
    }

    public static int tileToPixel(int i) {
        return i * Entity.TILE_SIZE;
    }

    public static int tileToPixel(double i) {
        return (int)(i * Entity.TILE_SIZE);
    }
}
