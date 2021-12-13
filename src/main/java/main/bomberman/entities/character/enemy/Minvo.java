package main.bomberman.entities.character.enemy;


import main.bomberman.entities.character.enemy.ai.AIMedium;

public class Minvo extends Enemy{
    public Minvo(){
        brain = new AIMedium(this, bomber1);
        MAX_STEPS = 12;
        setFrame("sprites\\minvo_left", "sprites\\minvo_left",
                "sprites\\minvo_right", "sprites\\minvo_right", 3);
        setAnimateDead("sprites\\minvo_dead", 1);
        speed = 3;
    }
}
