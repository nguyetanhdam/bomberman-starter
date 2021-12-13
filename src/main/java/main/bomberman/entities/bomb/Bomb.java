package main.bomberman.entities.bomb;

import javafx.scene.canvas.GraphicsContext;
import main.bomberman.board.BoardGame;
import main.bomberman.entities.Entity;
import main.bomberman.entities.character.Bomber;
import main.bomberman.entities.tile.Brick;
import main.bomberman.entities.tile.Wall;
import main.bomberman.graphics.AnimatedImage;
import main.bomberman.graphics.Sprite;
import main.bomberman.sound.Sound;

public class Bomb extends AnimatedImage {
    private static double BOMB_SPEED = 4.0;
    private boolean isExplored = false;
    private int timeToExplode = 120; //2s
    private Flame flame;
    private boolean isDestroyed = false;
    private int powerFlames = 0;
    private Bomber bomber;
    private boolean playerOut = false;
    private boolean move = false;

    public Bomb(Bomber bomber, int length) {
        Sound.playSound(Sound.placeBomb);
        this.bomber = bomber;
        duration = 0.2;
        int x = ((bomber.getPositionX() + bomber.getWidth() / 2) / bomber.getWidth()) * bomber.getWidth();
        int y = ((bomber.getPositionY() + 2 * bomber.getHeight() / 3) / bomber.getHeight()) * bomber.getHeight();
        setPosition(x, y);
        powerFlames = length;
        setFrame(Sprite.getListImage("sprites\\bomb_", 3, scale, false));
    }

    public void update() {
        //kiểm tra va chạm với bomber
        if (!playerOut && !this.intersects(bomber.getBoundaryImage())) {
            playerOut = true;
        }

        if (timeToExplode > 0) {    //nếu bom chưa nổ
            timeToExplode--;
            //bom bị đẩy bởi player
            if (!move && playerOut && bomber.hasGloves() && this.intersects(bomber.getBoundaryImage())) {
                //0: down 1: left 2: right 3: up
                int x = bomber.getPositionX() - positionX;
                int y = bomber.getPositionY() - positionY;

                //đẩy từ trái->phải
                if (this.intersects(bomber.getPositionX() + bomber.getWidth(), bomber.getPositionY() + bomber.getHeight() / 2, 1, 1)) {
                    setVelocity(BOMB_SPEED, 0);
                }
                //đẩy từ phải->trái
                else if (this.intersects(bomber.getPositionX(), bomber.getPositionY() + bomber.getHeight() / 2, 1, 1)) {
                    setVelocity(-BOMB_SPEED, 0);
                }
                //đẩy từ dưới->trên
                else if (this.intersects(bomber.getPositionX() + bomber.getWidth() / 2, bomber.getPositionY(), 1, 1)) {
                    setVelocity(0, -BOMB_SPEED);
                }
                //đẩy từ trên->dưới
                else {
                    setVelocity(0, BOMB_SPEED);
                }
                move = true;
            }

            //nếu bomb đang di chuyển
            if (move) {
                //nếu không có va chạm
                if (canMoveTo((int) (positionX + velocityX), (int) (positionY + velocityY))) {
                    positionX += velocityX;
                    positionY += velocityY;
                }
            }
        } else {    //bom đã nổ
            if (!isExplored) {
                isExplored = true;
                flame = new Flame(bomber, positionX, positionY, powerFlames);
            }
        }
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }

    @Override
    public void render(GraphicsContext gc, double time) {
        if (isExplored) {
            if (!isDestroyed) {
                flame.render(gc, time);

                if (flame.isShowed())
                    isDestroyed = true;
            }
        } else {
            super.render(gc, time);
        }
    }

    /**
     * kiểm tra xem có thể di chuyển được không
     * */
    private boolean canMoveTo(int x, int y) {
        Entity tren_trai = BoardGame.getEntityAt((x + 1) / width, (y + 1) / height);
        Entity tren_phai = BoardGame.getEntityAt((x + width - 1) / width, (y + 1) / height);
        Entity duoi_trai = BoardGame.getEntityAt((x + 1) / width, (y + height - 1) / height);
        Entity duoi_phai = BoardGame.getEntityAt((x + +width - 1) / width, (y + height - 1) / height);
        if (tren_trai instanceof Wall || tren_phai instanceof Wall ||
                duoi_trai instanceof Wall || duoi_phai instanceof Wall) {
            return false;
        }
        return (!(tren_trai instanceof Brick) || ((Brick) tren_trai).isDestroyed()) &&
                (!(tren_phai instanceof Brick) || ((Brick) tren_phai).isDestroyed()) &&
                (!(duoi_trai instanceof Brick) || ((Brick) duoi_trai).isDestroyed()) &&
                (!(duoi_phai instanceof Brick) || ((Brick) duoi_phai).isDestroyed());
    }
}
