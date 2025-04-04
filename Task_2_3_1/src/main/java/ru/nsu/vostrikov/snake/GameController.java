package ru.nsu.vostrikov.snake;

import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * Controller class for the Snake Game.
 */
public class GameController {
    @FXML private Canvas gameCanvas;
    @FXML private Text time;
    private GraphicsContext gc;
    private GameModel gameModel;
    private AnimationTimer timer;
    private final long updateInterval = 200_000_000;
    private final int cellSize = 20;
    private IntegerProperty elapsedTime = new SimpleIntegerProperty(0);

    /**
     * Init.
     */
    @FXML
    public void initialize() {
        gc = gameCanvas.getGraphicsContext2D();
        gameCanvas.setFocusTraversable(true);
        gameCanvas.setOnKeyPressed(this::setupHandlers);
        time.textProperty().bind(elapsedTime.asString("Время: %d сек"));
        int width = (int) (gameCanvas.getWidth() / cellSize);
        int height = (int) (gameCanvas.getHeight() / cellSize);
        gameModel = new GameModel(width, height, 5);
        setupTimer();
    }

    /**
     * Setup.
     */
    private void setupTimer() {
        timer = new AnimationTimer() {
            private long startTime = System.nanoTime();
            private long lastUpdateTime = 0;
            @Override
            public void handle(long now) {
                if (now - lastUpdateTime >= updateInterval) {
                    gameModel.movement();
                    render();
                    elapsedTime.set((int) ((now - startTime) / 1_000_000_000));
                    lastUpdateTime = now;

                    if (gameModel.isLost() || gameModel.isWon()) {
                        timer.stop();
                        if (gameModel.isLost()) {
                            System.out.println("Игра окончена");
                        } else if (gameModel.isWon()) {
                            System.out.println("Вы победили");
                        }
                    }
                }
            }
        };
        timer.start();
    }

    /**
     * Setup Handlers.
     */
    @FXML
    public void setupHandlers(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            restartGame();
            event.consume();
            return;
        }
        KeyCode code = event.getCode();
        if (code == KeyCode.UP && gameModel.getDirection() != Direction.DOWN) {
            gameModel.setDirection(Direction.UP);
        } else if (code == KeyCode.DOWN && gameModel.getDirection() != Direction.UP) {
            gameModel.setDirection(Direction.DOWN);
        } else if (code == KeyCode.LEFT && gameModel.getDirection() != Direction.RIGHT) {
            gameModel.setDirection(Direction.LEFT);
        } else if (code == KeyCode.RIGHT && gameModel.getDirection() != Direction.LEFT) {
            gameModel.setDirection(Direction.RIGHT);
        }
    }

    /**
     * Render.
     */
    private void render() {
        gc.clearRect(0, 0, gameCanvas.getWidth(), gameCanvas.getHeight());

        gc.setFill(Color.RED);
        for (FoodModel food : gameModel.getFood()) {
            gc.fillRect(food.getCol() * cellSize, food.getRow() * cellSize, cellSize, cellSize);
        }

        List<Position> body = gameModel.getSnake().getBody();
        for (int i = 0; i < body.size(); i++) {
            Position pos = body.get(i);
            if (i == 0) {
                gc.setFill(Color.DARKGREEN);
            } else {
                gc.setFill(Color.GREEN);
            }
            gc.fillRect(pos.getCol() * cellSize, pos.getRow() * cellSize, cellSize, cellSize);
        }
    }

    /**
     * Restart.
     */
    private void restartGame() {
        if (timer != null) {
            timer.stop();
            timer = null;
        }
        initialize();
        gameCanvas.requestFocus();
    }
}