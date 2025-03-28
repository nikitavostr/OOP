package ru.nsu.vostrikov;

import java.util.ArrayList;
import java.util.List;

public class SnakeModel {
    private List<Position> body;
    private Direction direction;
    private Position previous;

    public SnakeModel(Position startPosition, Direction startDirection) {
        body = new ArrayList<>();
        body.add(startPosition);
        direction = startDirection;
    }

    public List<Position> getBody() {
        return body;
    }

    public int getLength() {
        return body.size();
    }

    public Position getHead() {
        return body.getFirst();
    }

    public void updateDirection(Direction d) {
        direction = d;
    }

    public void move() {
        Position head = body.get(0);
        Position newHead = switch (direction) {
            case UP -> new Position(head.getRow() - 1, head.getCol());
            case DOWN -> new Position(head.getRow() + 1, head.getCol());
            case LEFT -> new Position(head.getRow(), head.getCol() - 1);
            case RIGHT -> new Position(head.getRow(), head.getCol() + 1);
        };
        body.addFirst(newHead);
        previous = body.removeLast();
    }

    public void grow() {
        body.addLast(previous);
    }

    public boolean checkCollision(GameBoard board) {
        Position head = body.getFirst();
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