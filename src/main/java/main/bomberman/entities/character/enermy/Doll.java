package main.bomberman.entities.character.enermy;

import main.bomberman.entities.character.enermy.ai.AISpecial;

public class Doll extends Enemy{
    public Doll(){
        brain = new AISpecial(this, bomber);
        MAX_STEPS = 144; //8 grass
        setFrame("sprites\\doll_left", "sprites\\doll_left",
                "sprites\\doll_right", "sprites\\doll_right", 3);
        setAnimateDead("sprites\\doll_dead", 1);
        speed = 3;
    }
}
