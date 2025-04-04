package ru.nsu.vostrikov.snake;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Game model class.
 */
public class GameModel {
    private SnakeModel snake;
    private GameBoard board;
    private List<FoodModel> food = new ArrayList<>();
    private Direction direction = Direction.RIGHT;
    private boolean won = false;
    private boolean lost = false;
    private int foodCount;
    private int eatenFood;
    private Random random = new Random();

    /**
     * Constructor.
     */
    public GameModel(int row, int col, int foodCount) {
        this.foodCount = foodCount;
        board = new GameBoard(row, col);
        snake = new SnakeModel(new Position(row / 2, col / 2), direction);
        for (int i = 0; i < foodCount; ++i) {
            generateFood();
        }
    }

    /**
     * Generate food.
     */
    private void generateFood() {
        FoodModel newFood = null;
        do {
            int row = random.nextInt(board.getHeight());
            int col = random.nextInt(board.getWidth());
            newFood = new FoodModel(row, col);
        } while (snake.getBody().contains(newFood) || food.contains(newFood));
        food.add(newFood);
    }

    /**
     * Eat food.
     */
    private boolean eat(Position head) {
        for (int i = 0; i < food.size(); ++i) {
            if (food.get(i).equals(head)) {
                food.remove(i);
                generateFood();
                eatenFood++;
                return true;
            }
        }
        return false;
    }

    /**
     * Movement.
     */
    public void movement() {
        snake.move();
        Position head = snake.getHead();

        if (snake.checkCollision(board)) {
            lost = true;
            return;
        }

        if (eat(head)) {
            snake.grow();
        }

        if (snake.getLength() == board.getSize()) {
            won = true;
        }
    }

    /**
     * Get direction.
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * Set direction.
     */
    public void setDirection(Direction newDirection) {
        if (newDirection != direction.opposite()) {
            direction = newDirection;
            snake.updateDirection(newDirection);
        }
    }

    /**
     * Get food.
     */
    public List<FoodModel> getFood() {
        return food;
    }

    /**
     * Get snake.
     */
    public SnakeModel getSnake() {
        return snake;
    }

    /**
     * Get eaten food.
     */
    public int getEatenFood() {
        return eatenFood;
    }

    /**
     * Is lost.
     */
    public boolean isLost() {
        return lost;
    }

    /**
     * Is won.
     */
    public boolean isWon() {
        return won;
    }
}
