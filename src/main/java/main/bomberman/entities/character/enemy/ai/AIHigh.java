package main.bomberman.entities.character.enemy.ai;

import main.bomberman.board.BoardGame;
import main.bomberman.entities.character.Bomber;
import main.bomberman.entities.character.enemy.Enemy;

import java.util.Stack;

public class AIHigh extends AI {
    private Bomber bomber;
    private Enemy myseft;
    private int[][] map;
    private boolean find = false;
    private int n = 15, m = 23;
    private int desX, desY, posX, posY;
    private Point oldP;
    private Point newP;
    private boolean isThinking = false;
    private boolean canSolve = false;
    private final int MAX_SEARCH_STEP = 15;


    private Stack<Point> way = new Stack<>();

    public AIHigh(Bomber b, Enemy e) {
        this.bomber = b;
        this.myseft = e;
        find = false;
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

    public void nextDir() {
        if (way.size() < 1)
            return;
        else {
            oldP = newP;
            newP = way.firstElement();
//            System.out.println(newP.x + " " + newP.y);
            way.remove(0);
        }
    }

    /**
     * kiểm tra character đến được tile cần đến chưa
     * @param x pixel x của character
     * @param y pixel y của character
     * */
    public boolean checkPos(int x, int y) {
        if (x == newP.x * myseft.getWidth() && y == newP.y * myseft.getHeight()) {
            nextDir();
            return true;
        }
        return false;
    }

    /**
     * kiểm tra xem đường đi hiện tại kết thúc chưa*/
    public boolean inTheDes() {
        return way.size() == 0;
    }

    public boolean canSolve() {
        return canSolve;
    }

    /**
     * reset các biến
     * cập nhật vị trí của character và bomber
     * */
    private void resetProperties() {
        find = false;
        canSolve = false;
        map = BoardGame.getMap();
        posX = myseft.getPositionX() / myseft.getWidth();
        posY = myseft.getPositionY() / myseft.getHeight();
        desX = (bomber.getPositionX() + bomber.getWidth() / 2) / bomber.getWidth();
        desY = (bomber.getPositionY() + bomber.getHeight() / 2) / bomber.getHeight();
        newP = new Point(posX, posY);
        oldP = new Point(posX, posY);
        if (!way.empty())
            way.clear();
    }

    /**
     * tạo đường đi cho character
     * đường đi đặt vào biến Stack: way
     * */
    public void createWay() {
        isThinking = true;
        resetProperties();
        //System.out.println("thinking");

        if (posX >= desX) {
            if (posY >= desY) {
                searchTopLeft(posY, posX);
            } else {
                searchTopRight(posY, posX);
            }
        } else {
            if (posY >= desY) {
                searchBottomLeft(posY, posX);
            } else {
                searchBottomRight(posY, posX);
            }
        }

        if (!find) {
//            System.out.println("can't find");
            canSolve = false;
        } else {
            canSolve = true;
//            System.out.println("Find a way!");
        }
//        System.out.println("Solve Completed!");
        isThinking = false;
    }

    public class Point {
        private int x;
        private int y;

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

    public void searchTopLeft(int i, int j) {
        if (way.size() >= MAX_SEARCH_STEP) return;
        if (i >= 0 && j >= 0 && i < n && j < m && !find && map[i][j] != 0 && map[i][j] != 5) {
            map[i][j] = 5;
            way.push(new Point(j, i));
            if (i == desY && j == desX) {
                way.push(new Point(j, i));  //tránh hiện tượng không đi qua điểm cuối cùng
                find = true;
            } else {
                searchTopLeft(i - 1, j);    //top
                searchTopLeft(i, j - 1);    //left
                searchTopLeft(i, j + 1);    //right
                searchTopLeft(i + 1, j);    //down
            }
            if (!find)
                way.pop();
            map[i][j] = 1;
        }
    }

    public void searchTopRight(int i, int j) {
        if (way.size() >= MAX_SEARCH_STEP) return;
        if (i >= 0 && j >= 0 && i < n && j < m && !find && map[i][j] != 0 && map[i][j] != 5) {
            map[i][j] = 5;
            way.push(new Point(j, i));
            if (i == desY && j == desX) {
                way.push(new Point(j, i));
                find = true;
            } else {
                searchTopRight(i + 1, j);
                searchTopRight(i, j - 1);
                searchTopRight(i, j + 1);
                searchTopRight(i - 1, j);
            }
            if (!find)
                way.pop();
            map[i][j] = 1;
        }
    }

    public void searchBottomLeft(int i, int j) {

        if (way.size() >= MAX_SEARCH_STEP) return;
        if (i >= 0 && j >= 0 && i < n && j < m && !find && map[i][j] != 0 && map[i][j] != 5) {
            map[i][j] = 5;
            way.push(new Point(j, i));
            if (i == desY && j == desX) {
                way.push(new Point(j, i));
                find = true;
            } else {
                searchBottomLeft(i - 1, j);
                searchBottomLeft(i, j + 1);
                searchBottomLeft(i, j - 1);
                searchBottomLeft(i + 1, j);
            }
            if (!find)
                way.pop();
            map[i][j] = 1;
        }
    }

    public void searchBottomRight(int i, int j) {
        if (way.size() >= MAX_SEARCH_STEP) return;
        if (i >= 0 && j >= 0 && i < n && j < m && !find && map[i][j] != 0 && map[i][j] != 5) {
            map[i][j] = 5;
            way.push(new Point(j, i));
            if (i == desY && j == desX) {
                way.push(new Point(j, i));
                find = true;
            } else {
                searchBottomRight(i + 1, j);
                searchBottomRight(i, j + 1);
                searchBottomRight(i, j - 1);
                searchBottomRight(i - 1, j);
            }
            if (!find)
                way.pop();
            map[i][j] = 1;
        }
    }
}
