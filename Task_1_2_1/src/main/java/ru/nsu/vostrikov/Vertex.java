package ru.nsu.vostrikov;

/**
 * Вершина графа.
 */
public class Vertex<T> {
    private T value;

    /**
     * Конструктор.
     */
    public Vertex(T value) {
        this.value = value;
    }

    /**
     * Значение вершины.
     */
    public T getValue() {
        return value;
    }
}
