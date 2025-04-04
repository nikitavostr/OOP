package ru.nsu.vostrikov.snake;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

import java.util.List;

/**
 * Controller class for the Snake Game.
 */
public class GameController {
    @FXML private Canvas gameCanvas;
    private GraphicsContext gc;
    private GameModel gameModel;
    private AnimationTimer timer;
    //private long lastUpdateTime;
    private final long updateInterval = 200_000_000;
    private final int CELL_SIZE = 20;

    @FXML
    public void initialize() {
        gc = gameCanvas.getGraphicsContext2D();
        gameCanvas.setFocusTraversable(true);
        gameCanvas.setOnKeyPressed(this::setupHandlers);
        int width = (int) (gameCanvas.getWidth() / CELL_SIZE);
        int height = (int) (gameCanvas.getHeight() / CELL_SIZE);
        gameModel = new GameModel(width, height, 5);
        setupTimer();
    }

    private void setupTimer() {
        timer = new AnimationTimer() {
            private long lastUpdateTime = 0;
            @Override
            public void handle(long now) {
                if (now - lastUpdateTime >= updateInterval) {
                    gameModel.movement();
                    render();
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

    public void startGame() {

        setupTimer();
    }

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

    private void render() {
        gc.clearRect(0, 0, gameCanvas.getWidth(), gameCanvas.getHeight());

        gc.setFill(Color.RED);
        for (FoodModel food : gameModel.getFood()) {
            gc.fillRect(food.getCol() * CELL_SIZE, food.getRow() * CELL_SIZE, CELL_SIZE, CELL_SIZE);
        }

        List<Position> body = gameModel.getSnake().getBody();
        for (int i = 0; i < body.size(); i++) {
            Position pos = body.get(i);
            if (i == 0) {
                gc.setFill(Color.DARKGREEN);
            } else {
                gc.setFill(Color.GREEN);
            }
            gc.fillRect(pos.getCol() * CELL_SIZE, pos.getRow() * CELL_SIZE, CELL_SIZE, CELL_SIZE);
        }

        gc.setFill(Color.BLACK);
        gc.fillText("Съедено яблок: " + gameModel.getEatenFood(), 10, 15);
    }

    private void restartGame() {
        if (timer != null) {
            timer.stop();
            timer = null;
        }
        initialize();
        gameCanvas.requestFocus();
    }
}