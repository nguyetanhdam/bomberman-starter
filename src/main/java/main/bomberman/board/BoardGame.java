package main.bomberman.board;

import javafx.scene.canvas.GraphicsContext;
import main.bomberman.entities.Entity;
import main.bomberman.entities.tile.Brick;
import main.bomberman.entities.tile.Wall;
import main.bomberman.level.LoadLevel;

import java.util.ArrayList;

public class BoardGame {
    private static ArrayList<Entity> map = LoadLevel.getMap(1);
    private static int width = LoadLevel.get_width();
    private static int height = LoadLevel.get_height();

    public BoardGame(){

    }

    public void render(GraphicsContext gc, double time){

    }

    public void renderMap(GraphicsContext gc){
        for(Entity e : map) {
            e.render(gc);
        }
    }

    public static Entity getEntityAt(int x, int y){
        if(x + y*width >= 0 && x + y*width < map.size())
            return map.get(x + y*width);
        return new Wall();
    }

    public static int[][] getMap(){
        int[][] res = new int[height][width];

        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                Entity e = getEntityAt(j, i);
                if(e instanceof Brick){
                    if(((Brick) e).isDestroyed())
                        res[i][j] = 1;
                    else res[i][j] = 0;
                }
                else res[i][j] = (LoadLevel.get_map()[i][j] == ' ')?(1):(0);
            }
        }

        return res;
    }
}
