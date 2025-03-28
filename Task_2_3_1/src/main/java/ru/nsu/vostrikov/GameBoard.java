package ru.nsu.vostrikov;

public class GameBoard {
    private final int width;
    private final int height;

    public GameBoard(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getSize() {
        return width * height;
    }

    public boolean isWithinBounds(Position pos) {
        return pos.getRow() >= 0 && pos.getRow() < height && pos.getCol() >= 0 && pos.getCol() < width;
    }
}
