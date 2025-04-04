package ru.nsu.vostrikov.snake;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        // Помещаем еду перед змеёй
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

}