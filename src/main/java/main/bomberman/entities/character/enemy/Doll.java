package main.bomberman.entities.character.enemy;

import main.bomberman.entities.character.enemy.ai.AIMedium;

public class Doll extends Enemy{
    public Doll(){
        brain = new AIMedium(this, bomber1);
        MAX_STEPS = 144; //8 grass
        setFrame("sprites\\doll_left", "sprites\\doll_left",
                "sprites\\doll_right", "sprites\\doll_right", 3);
        setAnimateDead("sprites\\doll_dead", 1);
        speed = 2;
    }
}
