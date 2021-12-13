package main.bomberman.entities.character.enemy;

import main.bomberman.entities.character.enemy.ai.AIHigh;

import java.util.Random;

public class Kondoria extends Enemy {
    public Kondoria() {
        brain = new AIHigh(bomber1, this);

        setFrame("sprites\\kondoria_left", "sprites\\kondoria_left",
                "sprites\\kondoria_right", "sprites\\kondoria_right", 3);
        setAnimateDead("sprites\\kondoria_dead", 1);
        speed = 2;
    }

    @Override
    public void update(double time) {
        if (!alive)
            return;

        if (this.intersects(bomber1)) {
            this.kill();
            bomber1.kill();
        }

        //nếu còn điểm đến tiếp theo
        if (!((AIHigh) brain).inTheDes()) {
            if (((AIHigh) brain).checkPos(positionX, positionY)) {
                setStatusMove(((AIHigh) brain).getNextDir());
            } else {
                positionX += velocityX;
                positionY += velocityY;
            }
        } else {
            //
            if (!((AIHigh) brain).isThinking())
                ((AIHigh) brain).createWay();
            else {
                if (!((AIHigh) brain).canSolve()) {
                    Random random = new Random();
                    setStatusMove(random.nextInt(4));
                }
            }
        }
    }
}
