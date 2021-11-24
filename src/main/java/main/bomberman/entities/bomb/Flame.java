package main.bomberman.entities.bomb;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import main.bomberman.board.BoardGame;
import main.bomberman.entities.Entity;
import main.bomberman.entities.tile.Brick;
import main.bomberman.graphics.AnimatedImage;
import main.bomberman.graphics.Sprite;

import java.net.MalformedURLException;

public class Flame extends AnimatedImage {
    private Image[] center;
    private Image[] toplast;
    private Image[] bottomlast;
    private Image[] leftlast;
    private Image[] rightlast;

    private Image[][] vertical;
    private Image[][] horizontal;

    private int showed = 20; //20 ms
    private int length = 0;

    public Flame(int x, int y, int length){
        duration = 0.2;
        positionX = x;
        positionY = y;
        this.length = length;
        loadFrame();
        calcDestroyableBrick();
    }

    private void calcDestroyableBrick(){
        int upperCorner = (positionY - height*(length + 1))/height;
        int leftCorner = (positionX - width*(length + 1))/width;
        for(int i = upperCorner; i < upperCorner + 3 + length*2; i++){
            Entity e = BoardGame.getEntityAt(positionX/width, i);
            if(e instanceof Brick){
                ((Brick) e).setDestroyed(true);
            }
        }
        for(int i = leftCorner; i < leftCorner + 3 + length*2; i++){
            Entity e = BoardGame.getEntityAt(i, positionY/height);
            if(e instanceof Brick){
                ((Brick) e).setDestroyed(true);
            }
        }
    }

    public boolean isShowed(){
        return showed == 0;
    }

    public Image getFrame(Image[] im, double time){
        int index = (int)((time % (im.length * duration)) / duration);
        return im[index];
    }

    @Override
    public void render(GraphicsContext gc, double time) {
        if(--showed > 0) {
            gc.drawImage(getFrame(center, time), positionX, positionY);

            for(int i = 0; i < length; i++){
                gc.drawImage(getFrame(vertical[i], time), positionX, positionY - height*(i+1));
                gc.drawImage(getFrame(vertical[i], time), positionX, positionY + height*(i+1));
                gc.drawImage(getFrame(horizontal[i], time), positionX - width*(i+1), positionY);
                gc.drawImage(getFrame(horizontal[i], time), positionX + width*(i+1), positionY);
            }

            gc.drawImage(getFrame(toplast, time), positionX, positionY - height*(length + 1));
            gc.drawImage(getFrame(bottomlast, time), positionX, positionY + height*(length + 1));
            gc.drawImage(getFrame(leftlast, time), positionX - width*(length + 1), positionY);
            gc.drawImage(getFrame(rightlast, time), positionX + width*(length + 1), positionY);
        }
    }

    private void loadFrame() {
        try {
            center = Sprite.getListImage("sprites\\bomb_exploded", 3, 3);
            toplast = Sprite.getListImage("sprites\\explosion_vertical_top_last", 3, 3);
            bottomlast = Sprite.getListImage("sprites\\explosion_vertical_down_last", 3, 3);
            leftlast = Sprite.getListImage("sprites\\explosion_horizontal_left_last", 3, 3);
            rightlast = Sprite.getListImage("sprites\\explosion_horizontal_right_last", 3, 3);

            if(length > 0) {
                vertical = new Image[length][];
                horizontal = new Image[length][];
                for (int i = 0; i < length; i++) {
                    vertical[i] = Sprite.getListImage("sprites\\explosion_vertical", 3, 3);
                    horizontal[i] = Sprite.getListImage("sprites\\explosion_horizontal", 3, 3);
                }

            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
