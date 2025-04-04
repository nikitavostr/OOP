package ru.nsu.vostrikov.snake;

/**
 * Game board class.
 */
public class GameBoard {
    private final int width;
    private final int height;

    public GameBoard(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * Get width.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Get height.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Get size.
     */
    public int getSize() {
        return width * height;
    }

    /**
     * Is within bounds?
     */
    public boolean isWithinBounds(Position pos) {
        return pos.getRow() >= 0 && pos.getRow() < height && pos.getCol() >= 0 && pos.getCol() < width;
    }
}
