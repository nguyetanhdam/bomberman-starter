package uet.oop.bomberman;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import javafx.util.Duration;
import uet.oop.bomberman.data.GameData;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.keyboard_controller.Input;

public class BombermanGame extends Application {

    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;

    private GraphicsContext gc;
    private Canvas canvas;
    private int frame_rendered = 0;

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

        Input.setScene(scene);

        GameData.startGame();

        Timeline gameLoop = new Timeline();
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        final long timeStart = System.currentTimeMillis();
        final long[] lastNanoTime = {System.nanoTime()};

        KeyFrame kf = new KeyFrame(
                Duration.seconds(0.017),                // 60 FPS
                ae -> {
                    update(frame_rendered);
                    render(gc);
                });

        gameLoop.getKeyFrames().add(kf);
        gameLoop.play();

        stage.show();
    }

    public void update(int frame_rendered) {
        GameData.update(frame_rendered);
    }

    public void render(GraphicsContext gc) {
        frame_rendered++;
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        GameData.render(gc);
    }
}
