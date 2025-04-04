package ru.nsu.vostrikov.snake;

/**
 * Position.
 */
public class Position {
    private final int row;
    private final int col;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * Get row.
     */
    public int getRow() {
        return row;
    }

    /**
     * Get col.
     */
    public int getCol() {
        return col;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Position)) return false;
        Position p = (Position) o;
        return this.row == p.row && this.col == p.col;
    }

    @Override
    public int hashCode() {
        return 10000 * row + col;
    }
}
