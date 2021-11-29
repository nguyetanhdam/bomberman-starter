package uet.oop.bomberman.character.enemy.AI;

import uet.oop.bomberman.character.enemy.Enemy;
import uet.oop.bomberman.character.player.Player;
import uet.oop.bomberman.data.GameData;
import uet.oop.bomberman.entities.Entity;

import java.util.Stack;

public class AIHigh extends AI {
    private final Player player;
    private final Enemy myself;
    private int[][] map;
    private boolean find = false;
    private final int _h;
    private final int _w;
    private int desX, desY, posX, posY;
    private Point oldP;
    private Point newP;
    private boolean isThinking = false;
    private boolean canSolve = false;

    private final Stack<Point> way = new Stack<>();

    public AIHigh(Player p, Enemy e) {
        this.player = p;
        this.myself = e;
        _h = GameData.getMapHeight();
        _w = GameData.getMapWidth();
    }

    @Override
    public int calcDirection() {
        createWay();
        return 0;
    }

    public boolean isThinking() {
        return isThinking;
    }

    public String getNextDir() {
        if (way.size() == 0)
            return " ";
        if (newP.x > oldP.x) {
            return "RIGHT";
        } else if (newP.x < oldP.x) {
            return "LEFT";
        } else {
            if (newP.y > oldP.y) {
                return "DOWN";
            } else return "UP";
        }
    }

    /**
     * thực hiện nước đi tiếp theo
     */
    public void nextDir() {
        if (way.size() < 1) return;

        oldP = newP;
        newP = way.firstElement();
        way.remove(0);

    }

    /**
     * kiểm tra xem đã di chuyển xong đến vị trí tiếp theo chưa
     *
     * @return true: đã di chuyển đến vị trí tiếp theo + setting lại vị trí cũ và vị trí tiếp theo
     */
    public boolean checkPos(double x, double y) {
        if (x == newP.x * Entity.TILE_SIZE && y == newP.y * Entity.TILE_SIZE) {
            nextDir();
            return true;
        }
        return false;
    }

    /**
     * kiểm tra xem hiện tại có nước đi nào không
     */
    public boolean inTheDes() {
        return way.size() == 0;
    }

    public boolean canSolve() {
        return canSolve;
    }

    /**
     * reset lại tất cả data
     * way: những nước đi hiện tại
     * địa chỉ của bản thân và bomber
     */
    private void resetProperties() {
        find = false;
        map = GameData.getMapWay();
        posX = (int) ((myself.getPixelX() + Entity.TILE_SIZE / 2) / Entity.TILE_SIZE);
        posY = (int) ((myself.getPixelY() + Entity.TILE_SIZE / 2) / Entity.TILE_SIZE);
        desX = (int) ((player.getPixelX() + Entity.TILE_SIZE / 2) / Entity.TILE_SIZE);
        desY = (int) ((player.getPixelY() + Entity.TILE_SIZE / 2) / Entity.TILE_SIZE);
        newP = new Point(posX, posY);
        oldP = new Point(posX, posY);
        if (!way.empty())
            way.clear();
    }

    /**
     * tạo nước đi mới
     */
    public void createWay() {
        isThinking = true;
        resetProperties();
        System.out.println("thinking");

        search(posY, posX);
        if (!find) {
            System.out.println("can't find");
            canSolve = false;
        } else {
            canSolve = true;
            System.out.println("Find a way!");
        }
        System.out.println("Solve Completed!");
        isThinking = false;
    }

    public class Point {
        private final int x;
        private final int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public void print() {
            System.out.println("(" + x + "," + y + ")");
        }

    }

    /**
     * tìm nước đi
     * không tính toán việc tránh bom
     * thuật toán BFS
     */
    public void search(int y, int x) {
        if (y >= 0 && x >= 0 && y < _h && x < _w && !find && map[y][x] != 0 && map[y][x] != 5) {
            map[y][x] = 5;
            way.push(new Point(x, y));
            if (y == desY && x == desX) {
                find = true;
            } else {
                search(y + 1, x);
                search(y, x + 1);
                search(y - 1, x);
                search(y, x - 1);
            }
            if (!find) way.pop();

            map[y][x] = 1;
        }
    }
}
