package uet.oop.bomberman.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public abstract class Enemy extends Entity {

    public static Image enemy_image = null;

    public Enemy(int xUnit, int yUnit) {
        super(xUnit, yUnit);
        this.img = enemy_image;
    }
}
