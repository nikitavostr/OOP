package ru.nsu.vostrikov;

import java.util.ArrayList;
import java.util.List;

/**
 * Матрица смежности.
 */
public class AdjacencyMatrix<T> implements Graph<T>{

    private final ArrayList<ArrayList<Integer>> adjMat;
    private final List<Vertex<T>> vertices;
    private int nextId;

    /**
     * Конструктор.
     */
    public AdjacencyMatrix() {
        adjMat = new ArrayList<>();
        vertices = new ArrayList<>();
        nextId = 0;
    }

    /**
     * Добавление вершины
     */
    @Override
    public void addVertex(Vertex<T> vertex) {
        if (vertices.contains(vertex)) {
            return;
        }
        vertices.add(vertex);
        for (ArrayList<Integer> row : adjMat) {
            row.add(0);
        }
        ArrayList<Integer> newRow = new ArrayList<>();
        for (int i = 0; i <= nextId; i++) {
            newRow.add(0);
        }
        adjMat.add(newRow);
        nextId++;
    }

    /**
     * Удаление вершины
     */
    @Override
    public void deleteVertex(Vertex<T> vertex) {
        int vertexIndex = getVertexIdx(vertex);
        vertices.remove(vertex);
        adjMat.remove(vertexIndex);

        for (ArrayList<Integer> row : adjMat) {
            row.remove(vertexIndex);
        }
        nextId--;
    }

    /**
     * Добавление ребра.
     */
    @Override
    public void addEdge(Edge<T> edge) {
        Vertex<T> from = edge.getFrom();
        Vertex<T> to = edge.getTo();
        int fromIndex = getVertexIdx(from);
        int toIndex = getVertexIdx(to);
        adjMat.get(fromIndex).set(toIndex, 1);
    }

    /**
     * Удаление ребра.
     */
    @Override
    public void deleteEdge(Edge<T> edge) {
        Vertex<T> from = edge.getFrom();
        Vertex<T> to = edge.getTo();
        int fromIndex = getVertexIdx(from);
        int toIndex = getVertexIdx(to);
        adjMat.get(fromIndex).set(toIndex, 0);
    }

    /**
     * Соседи вершины.
     */
    @Override
    public List<Vertex<T>> getNeighbors(Vertex<T> vertex) {
        int vertexIndex = getVertexIdx(vertex);
        List<Vertex<T>> neighbors = new ArrayList<>();
        for (int i = 0; i < adjMat.size(); i++) {
            if (adjMat.get(vertexIndex).get(i) != 0) {
                neighbors.add(vertices.get(i));
            }
        }
        return neighbors;
    }

    /**
     * Индекс вершины.
     */
    @Override
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
    @Override
    public Vertex<T> getVertex(int vertexId) {
        return vertices.get(vertexId);
    }
}