package main.bomberman;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.bomberman.Input.Input;
import main.bomberman.board.BoardGame;
import main.bomberman.entities.character.Bomber;
import main.bomberman.entities.character.enemy.Balloon;
import main.bomberman.entities.character.enemy.Oneal;

public class BombermanGame extends Application {
    private final int WIDTH = 1104;
    private final int HEIGHT = 720;

    @Override
    public void start(Stage theStage) {
        theStage.setTitle("BomberMan Game");

        Group root = new Group();
        Scene theScene = new Scene(root, WIDTH, HEIGHT, Color.WHITE);
        theStage.setScene(theScene);

        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        root.getChildren().add(canvas);

        GraphicsContext gc = canvas.getGraphicsContext2D();

        Bomber player = new Bomber();
        BoardGame boardGame = new BoardGame();
        Balloon balloon = new Balloon(player);
        Oneal oneal = new Oneal(player);

        Input.setScene(theScene);

        Timeline gameLoop = new Timeline();
        gameLoop.setCycleCount( Timeline.INDEFINITE );

        final long timeStart = System.currentTimeMillis();

        final long[] lastNanoTime = {System.nanoTime()};

        KeyFrame kf = new KeyFrame(
                Duration.seconds(0.017),                // 60 FPS
                new EventHandler<ActionEvent>()
                {
                    public void handle(ActionEvent ae)
                    {
                        double t = (System.currentTimeMillis() - timeStart) / 1000.0;

                        double elapsedTime = (System.nanoTime() - lastNanoTime[0]) / 1000000000.0;
                        lastNanoTime[0] = System.nanoTime();
                        player.readInput();
                        player.update(elapsedTime);
                        balloon.update(elapsedTime);
                        oneal.update(elapsedTime);

                        // render
                        gc.clearRect(0, 0, WIDTH,HEIGHT);
                        boardGame.renderMap(gc);
                        player.render(gc, t);
                        balloon.render(gc, t);
                        oneal.render(gc, t);
                    }
                });

        gameLoop.getKeyFrames().add(kf);
        gameLoop.play();

        theStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
