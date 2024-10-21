package ru.nsu.vostrikov;

import java.util.ArrayList;
import java.util.List;

/**
 * Матрица инцидентности.
 */
public class IncidenceMatrix<T> implements Graph<T> {

    private List<Edge<T>> edges;
    private List<Vertex<T>> vertices;
    private List<List<Integer>> incMat;
    private int edgeCount = 0;
    private int nextId = 0;

    /**
     * Конструктор.
     */
    public IncidenceMatrix() {
        edges = new ArrayList<>();
        vertices = new ArrayList<>();
        incMat = new ArrayList<>();
    }

    /**
     * Добавление вершины.
     */
    @Override
    public void addVertex(Vertex<T> vertex) {
        if (vertices.contains(vertex)) {
            return;
        }
        ArrayList<Integer> init = new ArrayList<>();
        for (int i = 0; i < edgeCount; i++) {
            init.add(0);
        }
        incMat.add(init);
        vertices.add(vertex);
        nextId++;
    }

    /**
     * Удаление вершины.
     */
    @Override
    public void deleteVertex(Vertex<T> vertex) {
        int idx = vertices.indexOf(vertex);
        List<Integer> incidentEdges = incMat.get(idx);
        for (int edgeIdx = 0; edgeIdx < incidentEdges.size(); edgeIdx++) {
            if (incidentEdges.get(edgeIdx) != 0) {
                deleteEdge(edges.get(edgeIdx));
            }
        }

        vertices.remove(idx);
        incMat.remove(idx);

    }

    /**
     * Добавление ребра.
     */
    @Override
    public void addEdge(Edge<T> edge) {
        Vertex<T> from = edge.getFrom();
        Vertex<T> to = edge.getTo();
        if (vertices.contains(from) && vertices.contains(to)) {
            edges.add(edge);
        }
        for (List<Integer> row : incMat) {
            row.add(0);
        }
        incMat.get(getVertexIdx(from)).set(edgeCount, 1);
        incMat.get(getVertexIdx(to)).set(edgeCount, -1);
        edgeCount++;
    }

    /**
     * Удаление ребра.
     */
    @Override
    public void deleteEdge(Edge<T> edge) {
        int edgeIdx = edges.indexOf(edge);
        if (edgeIdx == -1) {
            throw new IndexOutOfBoundsException("Edge not found");
        }
        edges.remove(edgeIdx);
        for (List<Integer> row : incMat) {
            row.remove(edgeIdx);
        }
        edgeCount--;
    }

    /**
     * Соседи вершины.
     */
    @Override
    public List<Vertex<T>> getNeighbors(Vertex<T> vertex) {
        int idx = getVertexIdx(vertex);
        List<Vertex<T>> neighbors = new ArrayList<>();
        List<Integer> incidentEdges = incMat.get(idx);
        for (int edgeIdx = 0; edgeIdx < incidentEdges.size(); edgeIdx++) {
            if (incidentEdges.get(edgeIdx) == 1) {
                Edge<T> edge = edges.get(edgeIdx);
                neighbors.add(edge.getTo());
            }
        }
        return neighbors;
    }

    /**
     * Индекс вершины.
     */
    public int getVertexIdx(Vertex<T> vertex) throws IndexOutOfBoundsException {
        int idx = vertices.indexOf(vertex);
        if (idx == -1){
            throw new IndexOutOfBoundsException("Vertex not found");
        }
        return idx;
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
        if (vertex == null) {
            throw new IndexOutOfBoundsException("Vertex not found");
        }
        return vertex;
    }

    /**
     * Все вершины.
     */
    @Override
    public List<Vertex<T>> getVertices() {
        return vertices;
    }
}