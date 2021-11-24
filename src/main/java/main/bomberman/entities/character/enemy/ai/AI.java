package main.bomberman.entities.character.enemy.ai;

import java.util.Random;

abstract public class AI {
    protected Random random = new Random();

    public abstract int calcDirection();
}
