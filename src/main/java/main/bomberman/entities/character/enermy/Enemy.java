package main.bomberman.entities.character.enermy;

import main.bomberman.board.BoardGame;
import main.bomberman.entities.character.Bomber;
import main.bomberman.entities.character.enermy.ai.AI;
import main.bomberman.graphics.AnimatedCharacter;
import main.bomberman.sound.Sound;

import java.util.Random;

abstract public class Enemy extends AnimatedCharacter {
    protected AI brain;
    protected int MAX_STEPS = 96; //pass 6 grass then calc
    protected int steps = 0;
    protected Bomber bomber;
    protected Random random = new Random();

    public Enemy(){
        setStatusMove("LEFT");
        speed = 3;
        duration = 0.1;
        setScale(3);
        bomber = BoardGame.getPlayer1();
    }

    @Override
    public void update(double time){
        if(!alive)
            return;

        if(this.intersects(bomber)){
            this.kill();
            bomber.kill();
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
        setStatusMove(brain.calcDirection());
    }

    @Override
    public void kill(){
        alive = false;
        Sound.playSound(Sound.enemyDie);
        BoardGame.addScore(positionX, positionY);
    }

}
