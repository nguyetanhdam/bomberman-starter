package uet.oop.bomberman.tile_object.bomb;

import javafx.scene.image.Image;
import uet.oop.bomberman.character.GameCharacter;
import uet.oop.bomberman.data.Calc;
import uet.oop.bomberman.data.GameData;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.tile_object.AnimatedTile;
import uet.oop.bomberman.tile_object.Brick;
import uet.oop.bomberman.tile_object.Wall;

public class Bomb extends AnimatedTile {

    private static final Image[] bombs_animation;
    private static final int EXPLOSION_DURATION = 120;
    private int animated_time;
    private boolean is_explored = false;
    private boolean flame_showed = false;
    private int length = 0;

    static {
        bombs_animation = new Image[3];
        bombs_animation[0] = Sprite.bomb.getFxImage();
        bombs_animation[1] = Sprite.bomb_1.getFxImage();
        bombs_animation[2] = Sprite.bomb_2.getFxImage();
    }

    public Bomb(int x, int y, int length) {
        super(x, y, bombs_animation[0]);
        animated_time = EXPLOSION_DURATION;
        frames = bombs_animation;
        this.length = length;
    }


    @Override
    public void update(int frame_rendered) {
        animated_time--;
        if (animated_time <= 0) is_explored = true;
        if (is_explored && !flame_showed) {
            addFlameTiles();
            flame_showed = true;
        }
        this.img = getFrame();
    }

    /**
     * kiểm tra xem character có thể đi qua bomb hay không
     * - character hiện tại đang ở vị trí bomb chưa thoát ra
     */
    public boolean canPass(GameCharacter p) {


        double p_x = p.getPixelX();
        double p_y = p.getPixelY();

        double b_x = getPixelX();
        double b_y = getPixelY();

        if (Math.abs(p_x - b_x) > TILE_SIZE * 3 / 4) {
            return false;
        }
        if (Math.abs(p_y - b_y) > TILE_SIZE * 3 / 4) {
            return false;
        }
        return true;


    }

    public void explore() {
        is_explored = true;
    }

    private void addFlameTiles() {
        int middle_x = Calc.pixelToTile(this.pixel_x);
        int middle_y = Calc.pixelToTile(this.pixel_y);

        //add middle frames
        GameData.addEntity(new FlameSegment(middle_x, middle_y, FlameSegment.FlameType.MIDDLE));

        //add top frames
        for (int _y = middle_y - 1; _y >= middle_y - length; _y--) {
            Entity e = GameData.getEntityAt(middle_x, _y);
            if (e instanceof Wall) {
                break;
            }
            if (e instanceof Brick) {
                ((Brick) e).broken();
                break;
            }

            if (e instanceof Bomb) {
                ((Bomb) e).explore();
            }

//            if (_y == middle_y - length) {
//                GameData.addEntity(new FlameSegment(middle_x, _y, FlameSegment.FlameType.END_UP));
//            } else {
//                GameData.addEntity(new FlameSegment(middle_x, _y, FlameSegment.FlameType.VERTICAL));
//            }
        }


        //add bottom frames
        for (int _y = middle_y + 1; _y <= middle_y + length; _y++) {
            Entity e = GameData.getEntityAt(middle_x, _y);
            if (e instanceof Wall) {
                break;
            }
            if (e instanceof Brick) {
                ((Brick) e).broken();
                break;
            }

            if (e instanceof Bomb) {
                ((Bomb) e).explore();
            }

//            if (_y == middle_y + length) {
//                GameData.addEntity(new FlameSegment(middle_x, _y, FlameSegment.FlameType.END_DOWN));
//            } else {
//                GameData.addEntity(new FlameSegment(middle_x, _y, FlameSegment.FlameType.VERTICAL));
//            }
        }

        //left frames
        for (int _x = middle_x - 1; _x >= middle_x - length; _x--) {
            Entity e = GameData.getEntityAt(_x, middle_y);
            if (e instanceof Wall) {
                break;
            }
            if (e instanceof Brick) {
                ((Brick) e).broken();
                break;
            }

            if (e instanceof Bomb) {
                ((Bomb) e).explore();
            }

//            if (_x == middle_x - length) {
//                GameData.addEntity(new FlameSegment(_x, middle_y, FlameSegment.FlameType.END_LEFT));
//            } else {
//                GameData.addEntity(new FlameSegment(_x, middle_y, FlameSegment.FlameType.HORIZONTAL));
//            }
        }

        //right frames
        for (int _x = middle_x + 1; _x <= middle_x + length; _x++) {
            Entity e = GameData.getEntityAt(_x, middle_y);
            if (e instanceof Wall) {
                break;
            }
            if (e instanceof Brick) {
                ((Brick) e).broken();
                break;
            }

            if (e instanceof Bomb) {
                ((Bomb) e).explore();
            }

//            if (_x == middle_x + length) {
//                GameData.addEntity(new FlameSegment(_x, middle_y, FlameSegment.FlameType.END_RIGHT));
//            } else {
//                GameData.addEntity(new FlameSegment(_x, middle_y, FlameSegment.FlameType.HORIZONTAL));
//            }
        }
    }

    public boolean canRemove() {
        return (is_explored && flame_showed);
    }

    private Image getFrame() {
        int index = (animated_time % (duration * frames.length)) / duration;
        return frames[index];
    }

}
