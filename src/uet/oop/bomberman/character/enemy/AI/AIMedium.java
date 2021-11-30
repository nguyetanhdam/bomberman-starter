package uet.oop.bomberman.character.enemy.AI;

import uet.oop.bomberman.character.enemy.Enemy;
import uet.oop.bomberman.character.player.Player;
import uet.oop.bomberman.data.GameData;

public class AIMedium extends AI{
    private Player player;
    private Enemy myself;

    private final int MAX_RANGE = 250;

    public AIMedium(Player p, Enemy e) {
        player = p;
        myself = e;
    }

    private int getDistanceX() {
        return myself.getTileX() - player.getTileX();
    }

    private int getDistanceY() {
        return myself.getTileY() - player.getTileY();
    }

    /**
     * distance in step*/
    private double getDistance() {
        //return Math.sqrt(Math.pow(getDistanceX(), 2) + Math.pow(getDistanceY(), 2));
        return Math.abs(getDistanceX()) + Math.abs(getDistanceY());
    }

    @Override
    /**
     * calculate by distance x, y from enemy to player
     * */
    public int calcDirection() {
        int x = getDistanceX();
        int y = getDistanceY();
        if (random.nextInt(15) % 5 != 0 && getDistance() <= MAX_RANGE) {
            //0: down, 1: left, 2: right, 3: up
            int[] dir = new int[2];

            if (x > 0)
                dir[0] = 1;
            else if (x < 0)
                dir[0] = 2;
            else
                dir[0] = -1;

            if (y > 0)
                dir[1] = 3;
            else if (y < 0)
                dir[1] = 0;
            else
                dir[1] = -1;

            if (dir[0] == -1) {     //same X
                if (dir[1] == -1) { //same X+Y
                    return random.nextInt(4);
                } else
                    return dir[1];
            } else {
                if (dir[1] == -1)   //same Y
                    return dir[0];
                else
                    return dir[random.nextInt(2)];
            }
        } else
            return random.nextInt(4);
    }
}
