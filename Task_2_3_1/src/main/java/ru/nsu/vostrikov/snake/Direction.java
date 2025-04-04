package ru.nsu.vostrikov.snake;

/**
 * Direction.
 */
public enum Direction {
    UP, DOWN, LEFT, RIGHT;

    /**
     * Check illegal direction.
     */
    public Direction opposite() {
        switch (this) {
            case UP: return DOWN;
            case DOWN: return UP;
            case LEFT: return RIGHT;
            case RIGHT: return LEFT;
            default: throw new IllegalStateException("Неизвестное направление");
        }
    }
}
