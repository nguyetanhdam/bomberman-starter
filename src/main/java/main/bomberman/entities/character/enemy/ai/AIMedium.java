package main.bomberman.entities.character.enemy.ai;

import main.bomberman.entities.character.Bomber;
import main.bomberman.entities.character.enemy.Enemy;

public class AIMedium extends AI{
    private Bomber bomber;
    private Enemy myseft;

    private final int MAX_RANGE = 250;      //khoảng cách pixel tối đa
    public AIMedium(Enemy e, Bomber b){
        myseft = e;
        bomber = b;
    }

    private int getDistanceX(){
        return  myseft.getPositionX() - bomber.getPositionX();
    }

    private int getDistanceY(){
        return  myseft.getPositionY() - bomber.getPositionY();
    }

    private double getDistance(){
        //return Math.sqrt(Math.pow(getDistanceX(), 2) + Math.pow(getDistanceY(), 2));
        return Math.abs(getDistanceX()) + Math.abs(getDistanceY());
    }

    @Override
    public int calcDirection() {
        int x = getDistanceX();
        int y = getDistanceY();
        if(getDistance() <= MAX_RANGE){
            //0: down, 1: left, 2: right, 3: up
            int[] dir = new int[2];

            if(x > 0)
                dir[0] = 1;
            else if(x < 0)
                dir[0] = 2;
            else
                dir[0] = -1;

            if(y > 0)
                dir[1] = 3;
            else if(y < 0)
                dir[1] = 0;
            else
                dir[1] = -1;

            if(dir[0] == -1){       //khoảng cách x = 0
                if(dir[1] == -1){       //khoảng cách x=y=0
                    return random.nextInt(4);
                }
                else                //kc x=0 , y!=0
                    return dir[1];
            }
            //khoảng cách x != 0
            else{
                if(dir[1] == -1)        //khoảng cách x!=0, y=0
                    return dir[0];
                else                    //kc x!=0,y!=0
                    return dir[random.nextInt(2)];  //ramdom chiều dọc hoặc ngang
            }
        } else
            return random.nextInt(4);
    }
}
