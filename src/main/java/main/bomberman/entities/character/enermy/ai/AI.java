package main.bomberman.entities.character.enermy.ai;

import java.util.Random;

abstract public class AI {
    //0: down, 1: left, 2: right, 3: up
    protected Random random = new Random();

    public abstract int calcDirection();
}
