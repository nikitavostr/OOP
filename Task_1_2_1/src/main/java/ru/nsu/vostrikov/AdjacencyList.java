package ru.nsu.vostrikov;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * Список смежности.
 */
public class AdjacencyList<T> implements Graph<T> {

    private List<Vertex<T>> vertices;
    private Map<Vertex<T>, List<Vertex<T>>> adjList;

    /**
     * конструктор.
     */
    public AdjacencyList() {
        vertices = new ArrayList<>();
        adjList = new HashMap<>();
    }

    /**
     * Добавление вершин.
     */
    @Override
    public void addVertex(Vertex<T> vertex) {
        if (adjList.containsKey(vertex)) {
            return;
        }

        vertices.add(vertex);
        adjList.put(vertex, new ArrayList<>());
    }

    /**
     * Удаление вершин.
     */
    @Override
    public void deleteVertex(Vertex<T> vertex) {
        int idx = getVertexIdx(vertex);
        adjList.remove(vertex);
        vertices.remove(vertex);
        for (List<Vertex<T>> v : adjList.values()) {
            v.remove(vertex);
        }
    }

    /**
     * Добавление ребер.
     */
    @Override
    public void addEdge(Edge<T> edge) {
        Vertex<T> from = edge.getFrom();
        Vertex<T> to = edge.getTo();
        if (adjList.get(from).contains(to)) {
            return;
        }
        adjList.get(from).add(to);
    }

    /**
     * Удаление ребер.
     */
    @Override
    public void deleteEdge(Edge<T> edge) {
        Vertex<T> from = edge.getFrom();
        Vertex<T> to = edge.getTo();
        if (!adjList.get(from).contains(to)) {
            return;
        }
        adjList.get(from).remove(to);
    }

    /**
     * Список всех соседей вершины.
     */
    @Override
    public List<Vertex<T>> getNeighbors(Vertex<T> vertex) throws IndexOutOfBoundsException {
        if (!vertices.contains(vertex)) {
            throw new IndexOutOfBoundsException("No such vertex");
        }
        return adjList.get(vertex);
    }

    /**
     * Индекс вершины.
     */

    public int getVertexIdx(Vertex<T> vertex) throws IndexOutOfBoundsException {
        int index = vertices.indexOf(vertex);
        if (index == -1) {
            throw new IndexOutOfBoundsException("No such vertex");
        }
        return index;
    }

    /**
     * Количество вершин.
     */
    @Override
    public int getVertexCnt() {
        return vertices.size();
    }

    /**
     * Вершина по индексу.
     */
    public Vertex<T> getVertex(int vertexIdx) throws IndexOutOfBoundsException {
        Vertex<T> vertex = vertices.get(vertexIdx);
        if (vertex != null) {
            return vertex;
        }
        throw new IndexOutOfBoundsException("Vertex not found");
    }

    /**
     * Все вершины.
     */
    @Override
    public List<Vertex<T>> getVertices() {
        return vertices;
    }
}