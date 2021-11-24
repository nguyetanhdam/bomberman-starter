package main.bomberman.entities.character.enemy;

import main.bomberman.entities.character.Bomber;
import main.bomberman.entities.character.enemy.ai.AILow;

public class Balloon extends Enemy {
    public Balloon(Bomber b){
        brain = new AILow();

        setFrame("sprites\\balloom_left", "sprites\\balloom_left",
                "sprites\\balloom_right", "sprites\\balloom_right", 3);

        setPosition(576, 48);
    }

}
