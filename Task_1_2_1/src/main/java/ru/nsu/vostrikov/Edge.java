package ru.nsu.vostrikov;

/**
 * Ребро.
 */
public class Edge<T> {
    private Vertex<T> from;
    private Vertex<T> to;

    /**
     * Конструктор.
     */
    public Edge(Vertex<T> from, Vertex<T> to) {
        this.from = from;
        this.to = to;
    }

    /**
     * Геттер исходящей вершины.
     */
    public Vertex<T> getFrom() {
        return from;
    }

    /**
     * Геттер входящей вершины.
     */
    public Vertex<T> getTo() {
        return to;
    }
}
