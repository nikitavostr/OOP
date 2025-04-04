package ru.nsu.vostrikov.snake;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests.
 */
class GameModelTest {
    private GameModel gameModel;

    @BeforeEach
    void setUp() {
        gameModel = new GameModel(10, 10, 1);
    }

    @Test
    void testInitialGameState() {
        assertNotNull(gameModel.getSnake());
        assertNotNull(gameModel.getFood());
        assertEquals(1, gameModel.getFood().size());
    }

    @Test
    void testSnakeMovesCorrectly() {
        Position initialHead = gameModel.getSnake().getHead();
        gameModel.setDirection(Direction.RIGHT);
        gameModel.movement();
        Position newHead = gameModel.getSnake().getHead();

        assertEquals(initialHead.getRow(), newHead.getRow());
        assertEquals(initialHead.getCol() + 1, newHead.getCol());
    }

    @Test
    void testSnakeEatsFood() {
        gameModel.getFood().clear();
        gameModel.getFood().add(new FoodModel(gameModel.getSnake().getHead().getRow(),
                gameModel.getSnake().getHead().getCol() + 1));

        int initialLength = gameModel.getSnake().getBody().size();
        gameModel.setDirection(Direction.RIGHT);
        gameModel.movement();

        assertEquals(initialLength + 1, gameModel.getSnake().getBody().size());
    }

    @Test
    void testSnakeCollidesWithWall() {
        gameModel.setDirection(Direction.LEFT);
        for (int i = 0; i < 10; i++) {
            gameModel.movement();
        }
        assertTrue(gameModel.isLost());
    }

    @Test
    void testFoodDoesNotSpawnInsideSnake() {
        Set<Position> snakeBody = new HashSet<>(gameModel.getSnake().getBody());
        for (FoodModel food : gameModel.getFood()) {
            assertFalse(snakeBody.contains(new Position(food.getRow(), food.getCol())));
        }
    }

    @Test
    void testSnakeCannotReverse() {
        gameModel.setDirection(Direction.RIGHT);
        gameModel.movement();
        gameModel.setDirection(Direction.LEFT);
        gameModel.movement();
        assertEquals(Direction.RIGHT, gameModel.getDirection());
    }
}