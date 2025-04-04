package ru.nsu.vostrikov.snake;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.List;

/**
 * View class.
 */
public class View {
    private final GraphicsContext gc;
    private final Canvas canvas;
    private GameModel gameModel;
    private final int cellSize;

    /**
     * Class constructor.
     */
    public View(GraphicsContext gc, Canvas gameCanvas, GameModel gameModel, int cellSize) {
        this.gc = gc;
        this.canvas = gameCanvas;
        this.gameModel = gameModel;
        this.cellSize = cellSize;
    }

    /**
     * Render func.
     */
    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

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
}
