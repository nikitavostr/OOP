package ru.nsu.vostrikov;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameModel {
    private SnakeModel snake;
    private GameBoard board;
    private List<FoodModel> food = new ArrayList<>();
    private Direction direction = Direction.RIGHT;
    private boolean won = false;
    private boolean lost = false;
    private int foodCount;
    private Random random = new Random();

    public GameModel(int row, int col, int foodCount) {
        this.foodCount = foodCount;
        board = new GameBoard(row, col);
        snake = new SnakeModel(new Position(row/2, col/2), direction);
        for(int i = 0; i < foodCount; ++i) {
            generateFood();
        }
    }

    private void generateFood() {
        FoodModel newFood = null;
        do {
            int row = random.nextInt(board.getHeight());
            int col = random.nextInt(board.getWidth());
            newFood = new FoodModel(row, col);
        } while (snake.getBody().contains(newFood) || food.contains(newFood));
        food.add(newFood);
    }

    private boolean eat(Position head) {
        for (int i = 0; i < food.size(); ++i) {
            if (food.get(i).equals(head)) {
                food.remove(i);
                generateFood();
                return true;
            }
        }
        return false;
    }

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

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction newDirection) {
        if (newDirection != direction.opposite()) {
            direction = newDirection;
            snake.updateDirection(newDirection);
        }
    }

    public List<FoodModel> getFood() {
        return food;
    }

    public SnakeModel getSnake() {
        return snake;
    }

    public boolean isLost() {
        return lost;
    }

    public boolean isWon() {
        return won;
    }
}
