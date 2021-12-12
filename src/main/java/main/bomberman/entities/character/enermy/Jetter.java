package main.bomberman.entities.character.enermy;

import main.bomberman.entities.bomb.Bomb;
import main.bomberman.entities.character.Bomber;
import main.bomberman.entities.character.enermy.ai.AI;
import main.bomberman.entities.character.enermy.ai.AILow;
import main.bomberman.graphics.Sprite;

import java.util.Random;

public class Jetter extends Bomber {
    private AI ai = new AILow();
    private int MAX_STEPS = 96; //pass 6 grass then calc
    private int steps = 0;
    private Random random;

    public Jetter() {
        super(1, 1);
        setScale(3);
        framesMove = Sprite.getListImage("sprites\\jetter1.png", 4, 3, scale);
        setAnimateDead("sprites\\player_dead", 3);
        setPosition(44, 40);
        alive = true;
        speed = 3;
    }

    @Override
    public void update(double time){
        if(!alive)
            return;
        timeToPlaceNextBomb--;

        if(listBomb.size() > 0) {
            for(int i = 0; i < listBomb.size(); i++) {
                Bomb bomb = listBomb.get(i);
                bomb.update();
                if (bomb.isDestroyed()) {
                    listBomb.remove(bomb);
                    i--;
                }
            }
        }

        if(steps >= MAX_STEPS){
            calcMove();
        }

        if(canMove((int) (positionX + velocityX), (int)(positionY + velocityY))){
            steps++;
            positionX += velocityX;
            positionY += velocityY;
        }
        else{
            calcMove();
        }
    }

    public void calcMove() {
        steps = 0;
        setStatusMove(ai.calcDirection());
        if(numberBomb > listBomb.size() && timeToPlaceNextBomb < 0) {
            listBomb.add(new Bomb(this, powerFlames));
        }
    }
}
