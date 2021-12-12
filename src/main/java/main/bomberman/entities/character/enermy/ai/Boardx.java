package main.bomberman.entities.character.enermy.ai;

import java.util.*;

public class Boardx {
    private int[][] map;
    private int x;
    private int y;
    private int xB;
    private int yB;

    public Boardx(int[][] mapx, int xE, int yE, int xB, int yB){
        map = mapx;
        this.x = xE;
        this.y = yE;
        this.xB = xB;
        this.yB = yB;
    }

    public int getManhattan(){
        return Math.abs(x - 1) + Math.abs(y - 1);
    }

    public boolean isGoal(){
        return x == xB && y == yB;
    }

    public Iterable<Boardx> neighbors(){
        List<Boardx> iterator = new ArrayList<>();
        if(y-1 >= 0 && map[x][y-1] != 0){
            iterator.add(new Boardx(map, x, y-1, xB, yB));
        }
        if(x+1 < map.length && map[x+1][y] != 0){
            iterator.add(new Boardx(map, x+1, y, xB, yB));
        }
        if(x-1 >= 0 && map[x-1][y] != 0){
            iterator.add(new Boardx(map, x-1, y, xB, yB));
        }
        if(y+1 < map[0].length && map[x][y+1] != 0){
            iterator.add(new Boardx(map, x, y+1, xB, yB));
        }

        return iterator;
    }

    private int[][] clone(int[][] mapx){
        int[][] mx = new int[mapx.length][mapx[0].length];
        for(int i = 0; i < mapx.length; i++){
            for(int j = 0; j < mapx[0].length; j++){
                mx[i][j] = mapx[i][j];
            }
        }
        return mx;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void print() {
        System.out.println(x + " " + y);
    }

    public boolean equals(Object y) {
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;
        Boardx that = (Boardx) y;
        return (this.x == that.x) && (this.y == that.y);
    }
}
