package main.bomberman.entities.character.enemy.ai;

import main.bomberman.board.BoardGame;
import main.bomberman.entities.character.Bomber;
import main.bomberman.entities.character.enemy.Enemy;

import java.util.Stack;

public class AIHigh extends AI{
    private Bomber bomber;
    private Enemy myseft;
    private int[][] map;
    private boolean find = false;
    private int n = 15, m = 23;
    private int desX, desY, posX, posY;
    private Point oldP;
    private Point newP;
    private boolean isThinking = false;
    private boolean canSlove = false;

    private Stack<Point> way = new Stack<>();

    public AIHigh(Bomber b, Enemy e){
        this.bomber = b;
        this.myseft = e;
    }

    @Override
    public int calcDirection() {
        creatWay();
        return 0;
    }

    public boolean isThinking(){
        return isThinking;
    }

    public String getNextDir(){
        if(way.size() == 0)
            return " ";
        if(newP.x > oldP.x){
            return "RIGHT";
        }
        else if(newP.x < oldP.x){
            return "LEFT";
        }
        else{
            if(newP.y > oldP.y){
                return "DOWN";
            }
            else return "UP";
        }
    }

    public void nextDir(){
        if(way.size() < 1)
            return;
        else {
            oldP = newP;
            newP = way.firstElement();
            way.remove(0);
        }
    }

    public boolean checkPos(int x, int y){
        if(x == newP.x*myseft.getWidth() && y == newP.y*myseft.getHeight()) {
            nextDir();
            return true;
        }
        return false;
    }

    public boolean inTheDes(){
        return way.size() == 0;
    }

    public boolean canSolve(){
        return canSlove;
    }

    private void resetProperties(){
        find = false;
        map = BoardGame.getMap();
        posX = myseft.getPositionX()/myseft.getWidth();
        posY = myseft.getPositionY()/myseft.getHeight();
        desX = (bomber.getPositionX() + bomber.getWidth()/2)/bomber.getWidth();
        desY = (bomber.getPositionY() + bomber.getHeight()/2)/bomber.getHeight();
        newP = new Point(posX, posY);
        oldP = new Point(posX, posY);
        if(!way.empty())
            way.clear();
    }
    public void creatWay(){
        isThinking = true;
        resetProperties();
        System.out.println("thinking");

        search(posY, posX);
        if (!find){
            System.out.println("can't find");
            canSlove = false;
        }
        else{
            canSlove = true;
            System.out.println("Find a way!");
        }
        System.out.println("Solve Completed!");
        isThinking = false;
    }

    public class Point{
        private int x;
        private int y;

        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }

        public int getX(){
            return x;
        }

        public int getY(){
            return y;
        }

        public void print(){
            System.out.println("(" + x + "," + y + ")");
        }

    }

    public void search(int i, int j){
        if(i >= 0 && j >= 0 && i < n && j < m && !find && map[i][j] != 0 && map[i][j] != 5){
            map[i][j] = 5;
            way.push(new Point(j, i));
            if(i == desY && j == desX){
                find = true;
            }
            else{
                search(i+1, j);
                search(i, j+1);
                search(i-1, j);
                search(i, j-1);
            }
            if(!find)
                way.pop();
            map[i][j] = 1;
        }
    }
}
