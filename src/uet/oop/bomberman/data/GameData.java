package uet.oop.bomberman.data;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.character.enemy.Enemy;
import uet.oop.bomberman.character.player.Player;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.TileEntities;
import uet.oop.bomberman.keyboard_controller.Input;
import uet.oop.bomberman.map.Level;
import uet.oop.bomberman.tile_object.Brick;
import uet.oop.bomberman.tile_object.Portal;
import uet.oop.bomberman.tile_object.Wall;
import uet.oop.bomberman.tile_object.bomb.Bomb;

import java.util.ArrayList;
import java.util.List;

public class GameData {
    private static List<TileEntities> mapObject = new ArrayList<>(); //mảng ghi các tile object
    private static List<Enemy> game_enemies = new ArrayList<Enemy>();   //list chứa các enemy
    private static Player player;    //người chơi
    private static Input game_input = new Input();

    public GameData() {
    }

    public static void startGame() {
        player = new Player(1, 1);
        Level.loadLevel(1);
        Level.createEntities();
    }

    /**
     * lấy ra entity ở vị trí tile_x, tile_y
     * nếu có nhiều hơn 1 entity ở vị trí đó:lay entity tren cung
     */
    public static Entity getEntityAt(int x, int y) {
        if (x >= 0 && x < getMapWidth() && y >= 0 && y < getMapHeight()) {
            return getTileAt(x, y).getEntity();
        }
        return new Wall(x, y);
    }

    public static void addEntity(Entity e) {
        int x = e.getTileX();
        int y = e.getTileY();

        if (x >= 0 && x < getMapWidth()
                && y >= 0 && y < getMapHeight()) {
            mapObject.get(x + y * getMapWidth()).addEntity(e);
        }
    }


    public static Player getPlayer() {
        return player;
    }

    public static void createTileEntity(TileEntities e) {
        mapObject.add(e);
    }


    public static void addEnemy(Enemy e) {
        game_enemies.add(e);
    }

    /**
     * kiem tra xem tile co the di chuyen duoc hay khong
     * 0: gặp vật cản
     * 1: đi được
     * 2: gặp bom
     */
    public static int[][] getMapWay() {
        int height = getMapHeight();
        int width = getMapWidth();
        int[][] res = new int[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {

                Entity e = getEntityAt(x, y);
                if (e instanceof Bomb) {
                    res[y][x] = 2;
                } else if (e instanceof Brick
                        || e instanceof Wall || e instanceof Portal) {
                    res[y][x] = 0;

                } else {
                    res[y][x] = 1;
                }
            }
        }
        return res;
    }

    public static int getMapWidth() {
        return Level.getWidth();
    }

    public static int getMapHeight() {
        return Level.getHeight();
    }

    public static Input getInput() {
        return game_input;
    }

    public static TileEntities getTileAt(int x, int y) {
        return mapObject.get(x + y * getMapWidth());
    }

    public static void setPlayerLocation(int x, int y) {
        player.setTileLocation(x, y);
    }

    public static void update(int frame_rendered) {
        for (TileEntities t : mapObject) {
            t.update(frame_rendered);
        }
        for (Enemy e : game_enemies) {
            if (e.isRemoved()) {
                game_enemies.remove(e);
            } else {
                e.update(frame_rendered);
            }
        }

        player.update(frame_rendered);
    }

    public static void render(GraphicsContext gc) {
        for (TileEntities t : mapObject) {
            t.render(gc);
        }
        for (Enemy e : game_enemies) {
            e.render(gc);
        }
        player.render(gc);
    }
}
