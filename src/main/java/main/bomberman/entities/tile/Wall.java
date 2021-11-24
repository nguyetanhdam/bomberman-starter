package main.bomberman.entities.tile;

import main.bomberman.entities.Entity;

public class Wall extends Entity {
    public Wall(){
        setScale(3);
        setImg("sprites\\wall.png");
    }
    public Wall(int x, int y) {
        setScale(3);
        setPosition(x, y);
        setImg("sprites\\wall.png");
    }
}
