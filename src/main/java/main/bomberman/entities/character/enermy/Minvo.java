package main.bomberman.entities.character.enermy;

import main.bomberman.entities.character.enermy.ai.AISpecial;

public class Minvo extends Enemy{
    public Minvo(){
        brain = new AISpecial(this, bomber);
        MAX_STEPS = 12;
        setFrame("sprites\\minvo_left", "sprites\\minvo_left",
                "sprites\\minvo_right", "sprites\\minvo_right", 3);
        setAnimateDead("sprites\\minvo_dead", 1);
        setPosition(864, 48);
        speed = 4;
    }
}
