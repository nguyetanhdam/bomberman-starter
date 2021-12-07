package uet.oop.bomberman.character;

import javafx.scene.image.Image;

public class Bomber extends GameCharacter {

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    protected void createFramesInfo() {

    }

    @Override
    public void kill() {
        if(!is_alive) {
            return;
        }
        is_alive = false;
    }

    @Override
    protected void afterKill() {

    }
}
