package main.bomberman.entities.character.enemy;

import main.bomberman.board.BoardGame;
import main.bomberman.entities.character.Bomber;
import main.bomberman.entities.character.enemy.ai.AI;
import main.bomberman.graphics.AnimatedCharacter;
import main.bomberman.sound.Sound;

import java.util.Random;

abstract public class Enemy extends AnimatedCharacter {
    protected AI brain;
    protected int MAX_STEPS = 96; //pass 6 grass then calc
    protected int steps = 0;
    protected Bomber bomber1;

    public Enemy() {
        setStatusMove("LEFT");
        speed = 3;
        duration = 0.1;
        setScale(3);
        bomber1 = BoardGame.getPlayer1();
    }

    /**
     * cập nhật trạng thái enemy
     * */
    @Override
    public void update(double time) {
        if (!alive)
            return;

        //nếu va chạm với bomber
        if (this.intersects(bomber1)) {
            this.kill();
            bomber1.kill();
        }

        //đi đến số bước tối đa
        if (steps >= MAX_STEPS) {
            calcMove();
        }

        //kiểm tra xem có thể di chuyển không
        if (canMove((int) (positionX + velocityX), (int) (positionY + velocityY))) {
            steps++;
            positionX += velocityX;
            positionY += velocityY;
        } else {
            calcMove();
        }
    }

    /**
     * tính toán hướng đi bằng AI
     **/
    public void calcMove() {
        steps = 0;
        setStatusMove(brain.calcDirection());
    }

    /**
     * hàm gọi sau khi enemy chết
     **/
    @Override
    public void kill() {
        alive = false;
        Sound.playSound(Sound.enemyDie);
        BoardGame.addScore(positionX, positionY);
    }
}
