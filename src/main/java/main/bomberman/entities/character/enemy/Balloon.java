package main.bomberman.entities.character.enemy;

import main.bomberman.entities.character.enemy.ai.AILow;

public class Balloon extends Enemy {
    public Balloon(){
        brain = new AILow();

        setFrame("sprites\\balloom_left", "sprites\\balloom_left",
                "sprites\\balloom_right", "sprites\\balloom_right", 3);
        setAnimateDead("sprites\\balloom_dead", 1);
    }

}
