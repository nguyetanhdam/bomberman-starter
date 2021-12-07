package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.tile_object.Brick;
import uet.oop.bomberman.tile_object.bomb.Bomb;
import uet.oop.bomberman.tile_object.bomb.FlameSegment;
import uet.oop.bomberman.tile_object.item.Item;

import java.util.ArrayList;
import java.util.List;

public class TileEntities extends Entity {
    protected List<Entity> entities = new ArrayList<>();
    protected int tile_x;
    protected int tile_y;

    public TileEntities(int tile_x, int tile_y) {
        super(tile_x, tile_y, null);
        this.tile_x = tile_x;
        this.tile_y = tile_y;
    }

    public void removeDeletedEntity() {
        for(Entity e: entities) {
            if(e instanceof FlameSegment) {
                if(((FlameSegment) e).isRemoved()) entities.remove(e);
            }
            else if(e instanceof Bomb)
            {
                if(((Bomb) e).canRemove()) {
                    entities.remove(e);
                }
            }
            else if (e instanceof Brick)
            {
                if(((Brick) e).isRemoved()) entities.remove(e);
            }
            else if(e instanceof Item) {
                if(((Item) e).isRemoved()) entities.remove(e);
            }
        }
    }

    @Override
    public void update(int frame_rendered) {
        removeDeletedEntity();
        for (Entity e : entities) {
            e.update(frame_rendered);
        }
    }

    public void render(GraphicsContext gc) {
        for (Entity e : entities) {
            e.render(gc);
        }
    }

    public void addEntity(Entity e) {
        entities.add(e);
    }

    public Entity getEntity() {
        return entities.get(entities.size() - 1);
    }
}
