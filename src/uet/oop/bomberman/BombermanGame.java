package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Grass;
import uet.oop.bomberman.entities.Wall;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.map.GameMap;

import java.util.ArrayList;
import java.util.List;

public class BombermanGame extends Application {

    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;
    
    private GraphicsContext gc;
    private Canvas canvas;



    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Game Bomberman");

        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root);

        // Them scene vao stage
        stage.setScene(scene);
        stage.show();

        GameMap.createMap(1);
        Entity bomberman = new Bomber(2, 1, Sprite.balloom_left1.getFxImage());

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                bomberman.render(gc);
                update();
            }
        };
        timer.start();



    }


    public void update() {
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        GameMap.render(gc);
    }
}
