package uet.oop.bomberman.tile_object.item;

import javafx.scene.image.Image;
import uet.oop.bomberman.character.player.Player;
import uet.oop.bomberman.entities.Entity;

public abstract class Item extends Entity {
    protected boolean removed = false;

    //0:speed, 1:flame, 2:bombs_num
    protected int[] value = new int[3];


    public Item(int x, int y, Image img) {
        super(x, y, img);
        value[0] = 0;
        value[1] = 0;
        value[2] = 0;
    }

    public void setPowerUp(Player p) {
        if (!removed) {
            p.addValue(value);
            removed = true;
        }
    }



    public boolean isRemoved() {
        return removed;
    }
}
