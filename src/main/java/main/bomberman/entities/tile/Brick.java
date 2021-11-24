package main.bomberman.entities.tile;

import javafx.scene.canvas.GraphicsContext;
import main.bomberman.graphics.AnimatedImage;

public class Brick extends AnimatedImage {
    private boolean isDestroyed;
    private boolean hasItem = false;
    private boolean collectedItem = false;
    private Item item;

    public Brick(){
        setScale(3);
        isDestroyed = false;
        setImg("sprites\\brick.png");
    }

    public Brick(String item){
        setScale(3);
        isDestroyed = false;
        hasItem = true;
        setImg("sprites\\brick.png");
        this.item = new Item(item);
    }

    public Brick(int x, int y){
        setScale(3);
        isDestroyed = false;
        setImg("sprites\\brick.png");
        setPosition(x, y);
    }

    @Override
    public void setPosition(int x, int y){
        positionX = x;
        positionY = y;
        if(hasItem){
            item.setPosition(x, y);
        }
    }

    public boolean isDestroyed(){
        return isDestroyed;
    }

    public void setDestroyed(boolean destroyed){
        isDestroyed = destroyed;
        setImg("sprites\\grass.png");
    }

    public boolean hasItem(){
        return hasItem && !collectedItem;
    }

    public void setCollectedItem(boolean collectedItem){
        this.collectedItem = collectedItem;
    }

    public Item getItem(){
        return item;
    }

    @Override
    public void render(GraphicsContext gc){
        if(isDestroyed && hasItem()){
            item.render(gc);
        }
        else {
            super.render(gc);
        }
    }
}
