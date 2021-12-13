package main.bomberman.entities.character.enemy.ai;

import java.util.Random;

abstract public class AI {
    //0: down, 1: left, 2: right, 3: up
    protected Random random = new Random();

    /**
     * trả về hướng cần đi*/
    public abstract int calcDirection();
}
