package ru.nsu.vostrikov;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class AdjacencyMatrix<T> implements Graph<T> {

    private final ArrayList<ArrayList<Integer>> adjMat;
    private final HashMap<T, Integer> vertexToIndex;
    private final List<T> vertices;
    private int nextIdx = 0;

    public AdjacencyMatrix() {
        adjMat = new ArrayList<>();
        vertexToIndex = new HashMap<>();
        vertices = new ArrayList<>();
    }

    @Override
    public void addVertex(T vertex) {
        if (vertexToIndex.containsKey(vertex)) {
            return;
        }

        vertexToIndex.put(vertex, nextIdx);
        vertices.add(vertex);
        int newIndex = adjMat.size();

        for (ArrayList<Integer> row : adjMat) {
            row.add(0);
        }

        ArrayList<Integer> newRow = new ArrayList<>();
        for (int i = 0; i <= newIndex; i++) {
            newRow.add(0);
        }
        adjMat.add(newRow);

        nextIdx++;
    }

    @Override
    public void deleteVertex(T vertex) throws IndexOutOfBoundsException{
        Integer vertexIndex = vertexToIndex.get(vertex);
        if (vertexIndex == null) {
            throw new IndexOutOfBoundsException("Invalid vertex");
        }

        vertices.remove(vertex);
        adjMat.remove((int) vertexIndex);

        for (ArrayList<Integer> row : adjMat) {
            row.remove((int) vertexIndex);
        }

        vertexToIndex.remove(vertex);
        for (Map.Entry<T, Integer> entry : vertexToIndex.entrySet()) {
            if (entry.getValue() > vertexIndex) {
                vertexToIndex.put(entry.getKey(), entry.getValue() - 1);
            }
        }

        nextIdx--;
    }

    @Override
    public void addEdge(T from, T to) throws IndexOutOfBoundsException{
        Integer fromIndex = vertexToIndex.get(from);
        Integer toIndex = vertexToIndex.get(to);
        if (fromIndex == null || toIndex == null) {
            throw new IndexOutOfBoundsException("Invalid vertex");
        }
        adjMat.get(fromIndex).set(toIndex, 1);
    }

    @Override
    public void deleteEdge(T from, T to) throws IndexOutOfBoundsException {
        Integer fromIndex = vertexToIndex.get(from);
        Integer toIndex = vertexToIndex.get(to);
        if (fromIndex == null || toIndex == null) {
            throw new IndexOutOfBoundsException("Invalid vertex");
        }
        adjMat.get(fromIndex).set(toIndex, 0);
    }

    @Override
    public List<T> getNeighbors(T vertex) throws IndexOutOfBoundsException {
        Integer vertexIndex = vertexToIndex.get(vertex);
        if (vertexIndex == null) {
            throw new IndexOutOfBoundsException("Invalid vertex");
        }

        List<T> neighbors = new ArrayList<>();
        for (int i = 0; i < adjMat.size(); i++) {
            if (adjMat.get(vertexIndex).get(i) != 0) {
                neighbors.add(vertices.get(i));
            }
        }
        return neighbors;
    }

    @Override
    public int getVertexIdx(T vertex) throws IndexOutOfBoundsException {
        Integer index = vertexToIndex.get(vertex);
        if (index == null) {
            throw new IndexOutOfBoundsException("No such vertex");
        }
        return index;
    }

    @Override
    public int getVertexCnt() {
        return vertices.size();
    }

    @Override
    public T getVertex(int vertexIdx) {
        return vertices.get(vertexIdx);
    }
}

