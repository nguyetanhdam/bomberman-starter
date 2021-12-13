package main.bomberman.board;

import javafx.scene.canvas.GraphicsContext;
import main.bomberman.entities.Entity;
import main.bomberman.entities.Message;
import main.bomberman.entities.character.Bomber;
import main.bomberman.entities.character.enemy.Enemy;
import main.bomberman.entities.tile.Brick;
import main.bomberman.entities.tile.Wall;
import main.bomberman.graphics.Properties;
import main.bomberman.gui.ScoreView;
import main.bomberman.level.LoadLevel;

import java.util.ArrayList;

public class BoardGame {
    private static int width;
    private static int height;
    private static ArrayList<Entity> map = new ArrayList<>();
    private static ArrayList<Enemy> list_enemy = new ArrayList<>();
    private static Bomber player1;
    private static ArrayList<Message> listMessage = new ArrayList<>();
    private static Properties properties;
    private static boolean paused;
    private static boolean gameOver;


    public BoardGame() {
        map.clear();
        list_enemy.clear();

        player1 = new Bomber();
        LoadLevel.load(1);
        width = LoadLevel.get_width();
        height = LoadLevel.get_height();
        map = LoadLevel.getMap();
        list_enemy = LoadLevel.getListEnemy();
        properties = new Properties(player1);
        paused = false;
        gameOver = false;
    }

    public void update(double elapsedTime) {
        if (paused) { //trang thai pause
            return;
        }

        //update player
        player1.update(elapsedTime);

        //update enemy
        for (int i = 0; i < list_enemy.size(); i++) {
            list_enemy.get(i).update(elapsedTime);
            if (list_enemy.get(i).isKilled()) {
                list_enemy.remove(i);
                i--;
            }
        }
    }

    /**
     * render tất cả các thông tin của game
     * (map,object,properties)
     * */
    public void render(GraphicsContext gc, double time) {
        //render map object
        for (Entity e : map) {
            if (e instanceof Brick) {
                ((Brick) e).render(gc, time);
            } else
                e.render(gc);
        }

        //render bomber
        player1.render(gc, time);

        //render enemy
        for (Enemy enemy : list_enemy) {
            enemy.render(gc, time);
        }

        for (Message m : listMessage) {
            m.render(gc);
        }

        properties.render(gc);

        if (gameOver) {
            paused = true;
            properties.renderGameOver(gc);
        }
    }

    /**
     * lấy ra entity ở vị trí x và y
     */
    public static Entity getEntityAt(int x, int y) {
        if (x + y * width >= 0 && x + y * width < map.size())
            return map.get(x + y * width);
        return new Wall();
    }


    /**
     * lấy ra thông tin của map dưới dạng đường có thể đi
     * */
    public static int[][] getMap() {
        int[][] res = new int[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Entity e = getEntityAt(j, i);
                if (e instanceof Brick) {
                    if (((Brick) e).isDestroyed())
                        res[i][j] = 1;
                    else res[i][j] = 0;
                } else res[i][j] = (LoadLevel.get_map()[i][j] == '#') ? (0) : (1);
            }
        }

        return res;
    }

    public static ArrayList<Enemy> getListEnemy() {
        return list_enemy;
    }

    public static void addMessage(String content, int x, int y) {
        listMessage.add(new Message(content, x, y));
    }

    public static void pause() {
        paused = !paused;
    }

    public static void nextLevel() {
        map.clear();
        list_enemy.clear();

        player1 = new Bomber();
        properties.reset(player1);
        LoadLevel.load(LoadLevel.get_level() + 1);
        map = LoadLevel.getMap();
        list_enemy = LoadLevel.getListEnemy();

        paused = false;
        gameOver = false;
    }

    /**
     * thêm điểm
     */
    public static void addScore(int x, int y) {
        addMessage(properties.addScore(), x, y);
    }

    public static int getScore() {
        return properties.getScore();
    }

    public static void setGameOver(boolean ok) {
        ScoreView.saveScore();
        gameOver = ok;
    }

    public static boolean endGame() {
        return gameOver;
    }


    public static Bomber getPlayer1() {
        return player1;
    }
}
