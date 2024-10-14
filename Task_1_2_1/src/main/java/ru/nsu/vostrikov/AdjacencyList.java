package ru.nsu.vostrikov;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class AdjacencyList<T> implements Graph<T> {

    private HashMap<Integer, List<Integer>> adjList;
    private HashMap<Integer, T> vertexValues;
    private HashMap<T, Integer> vertexIndices;
    private int nextIdx = 0;

    public AdjacencyList() {
        vertexValues = new HashMap<>();
        adjList = new HashMap<>();
        vertexIndices = new HashMap<>();
    }

    @Override
    public void addVertex(T vertex) {
        vertexValues.put(nextIdx, vertex);
        vertexIndices.put(vertex, nextIdx);
        adjList.put(nextIdx, new ArrayList<>());
        nextIdx++;
    }

    @Override
    public void deleteVertex(T vertex) throws IndexOutOfBoundsException {
        Integer vertexId = vertexIndices.get(vertex);
        if (vertexId == null) {
            throw new IndexOutOfBoundsException("Invalid vertex");
        }
        vertexValues.remove(vertexId);
        vertexIndices.remove(vertex);
        adjList.remove(vertexId);
        for (List<Integer> neighbors : adjList.values()) {
            neighbors.remove(vertexId);
        }
    }

    @Override
    public void addEdge(T from, T to) throws IndexOutOfBoundsException {
        Integer fromId = vertexIndices.get(from);
        Integer toId = vertexIndices.get(to);
        if (fromId == null || toId == null) {
            throw new IndexOutOfBoundsException("No such vertices");
        }
        adjList.get(fromId).add(toId);
    }

    @Override
    public void deleteEdge(T from, T to) throws IndexOutOfBoundsException {
        Integer fromId = vertexIndices.get(from);
        Integer toId = vertexIndices.get(to);
        if (fromId == null || toId == null) {
            throw new IndexOutOfBoundsException("No such vertices");
        }
        adjList.get(fromId).remove(toId);
    }

    @Override
    public List<T> getNeighbors(T vertex) throws IndexOutOfBoundsException {
        Integer vertexId = vertexIndices.get(vertex);
        if (vertexId == null) {
            throw new IndexOutOfBoundsException("Invalid vertex");
        }
        List<T> neighbors = new ArrayList<>();
        for (Integer neighborId : adjList.get(vertexId)) {
            neighbors.add(vertexValues.get(neighborId));
        }
        return neighbors;
    }

    @Override
    public int getVertexIdx(T vertex) throws IndexOutOfBoundsException {
        Integer vertexId = vertexIndices.get(vertex);
        if (vertexId == null) {
            throw new IndexOutOfBoundsException("Invalid vertex");
        }
        return vertexId;
    }

    @Override
    public int getVertexCnt() {
        return vertexValues.size();
    }

    @Override
    public T getVertex(int vertexIdx) {
        return vertexValues.get(vertexIdx);
    }
}

