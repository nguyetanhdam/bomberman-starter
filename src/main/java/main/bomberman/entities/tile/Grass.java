package main.bomberman.entities.tile;

import main.bomberman.entities.Entity;

public class Grass extends Entity {
    public Grass(){
        setScale(3);
        setImg("sprites\\grass.png");
    }
    public Grass(int x, int y) {
        setScale(3);
        setPosition(x, y);
        setImg("sprites\\grass.png");
    }

}
