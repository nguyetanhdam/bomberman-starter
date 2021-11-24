package main.bomberman.entities.character.enemy;

import main.bomberman.entities.character.Bomber;
import main.bomberman.entities.character.enemy.ai.AIHigh;

import java.util.Random;

public class Oneal extends Enemy {
    private int timeThinking = 200;
    public Oneal(Bomber b){
        brain = new AIHigh(b, this);

        setFrame("sprites\\oneal_left", "sprites\\oneal_left",
                "sprites\\oneal_right", "sprites\\oneal_right", 3);

        setPosition(864, 48);
        speed = 2;
        //brain.calcDirection();
    }

    @Override
    public void update(double time){
        if(!((AIHigh)brain).inTheDes()) {
            if (((AIHigh) brain).checkPos(positionX, positionY)) {
                setStatusMove(((AIHigh) brain).getNextDir());
            } else {
                positionX += velocityX;
                positionY += velocityY;
            }
        }
        else {
            if(!((AIHigh)brain).isThinking())
                ((AIHigh)brain).creatWay();
            else {
                if(!((AIHigh)brain).canSolve()){
                    Random random = new Random();
                    setStatusMove(random.nextInt(4));
                }
            }
        }
    }
}
