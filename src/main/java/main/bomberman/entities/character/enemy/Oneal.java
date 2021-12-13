package main.bomberman.entities.character.enemy;

import main.bomberman.entities.character.enemy.ai.AIMedium;

public class Oneal extends Enemy {
    public Oneal(){
        brain = new AIMedium(this, bomber1);
        setFrame("sprites\\oneal_left", "sprites\\oneal_left",
                "sprites\\oneal_right", "sprites\\oneal_right", 3);
        setAnimateDead("sprites\\oneal_dead", 1);
    }

}
