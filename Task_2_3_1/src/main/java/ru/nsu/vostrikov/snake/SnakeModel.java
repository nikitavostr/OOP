package ru.nsu.vostrikov.snake;

import java.util.ArrayList;
import java.util.List;

/**
 * Snake class.
 */
public class SnakeModel {
    private List<Position> body;
    private Direction direction;
    private Position previous;

    /**
     * Constructor.
     */
    public SnakeModel(Position startPosition, Direction startDirection) {
        body = new ArrayList<>();
        body.add(startPosition);
        direction = startDirection;
    }

    /**
     * Get body.
     */
    public List<Position> getBody() {
        return body;
    }

    /**
     * Get length.
     */
    public int getLength() {
        return body.size();
    }

    /**
     * Get head.
     */
    public Position getHead() {
        return body.get(0);
    }

    /**
     * Update direction.
     */
    public void updateDirection(Direction d) {
        direction = d;
    }

    /**
     * Move.
     */
    public void move() {
        Position head = body.get(0);
        Position newHead = switch (direction) {
            case UP -> new Position(head.getRow() - 1, head.getCol());
            case DOWN -> new Position(head.getRow() + 1, head.getCol());
            case LEFT -> new Position(head.getRow(), head.getCol() - 1);
            case RIGHT -> new Position(head.getRow(), head.getCol() + 1);
        };
        body.add(0, newHead);
        previous = body.remove(body.size() - 1);
    }

    /**
     * Grow.
     */
    public void grow() {
        body.add(body.size(), previous);
    }

    /**
     * Check collision.
     */
    public boolean checkCollision(GameBoard board) {
        Position head = body.get(0);
        if (!board.isWithinBounds(head)) {
            return true;
        }
        for (int i = 1; i < body.size(); i++) {
            if (head.equals(body.get(i))) {
                return true;
            }
        }
        return false;
    }
}