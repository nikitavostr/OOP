package ru.nsu.vostrikov;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class IncidenceMatrix<T> implements Graph<T> {

    private ArrayList<ArrayList<Integer>> incMat;
    private HashMap<Integer, T> vertexValues;
    private HashMap<T, Integer> vertexToIndex;
    private int edgeCount = 0;
    private int nextIdx = 0;

    public IncidenceMatrix() {
        incMat = new ArrayList<>();
        vertexValues = new HashMap<>();
        vertexToIndex = new HashMap<>();
    }

    @Override
    public void addVertex(T vertex) {
        vertexValues.put(nextIdx, vertex);
        vertexToIndex.put(vertex, nextIdx);
        ArrayList<Integer> init = new ArrayList<>();
        for (int i = 0; i < edgeCount; i++) {
            init.add(0);
        }
        incMat.add(init);
        nextIdx++;
    }

    @Override
    public void deleteVertex(T vertex) throws IndexOutOfBoundsException {
        if (!vertexToIndex.containsKey(vertex)) {
            throw new IndexOutOfBoundsException("Invalid vertex");
        }

        int vertexIndex = vertexToIndex.get(vertex);
        vertexValues.remove(vertexIndex);
        vertexToIndex.remove(vertex);

        for (int i = 0; i < edgeCount; i++) {
            if (incMat.get(vertexIndex).get(i) != 0) {
                for (ArrayList<Integer> row : incMat) {
                    row.remove(i);
                }
                edgeCount--;
                i--;
            }
        }

        incMat.remove(vertexIndex);

        for (Map.Entry<T, Integer> entry : vertexToIndex.entrySet()) {
            if (entry.getValue() > vertexIndex) {
                vertexToIndex.put(entry.getKey(), entry.getValue() - 1);
            }
        }
    }

    @Override
    public void addEdge(T from, T to) throws IndexOutOfBoundsException {
        if (!vertexToIndex.containsKey(from) || !vertexToIndex.containsKey(to)) {
            throw new IndexOutOfBoundsException("Invalid vertex");
        }

        for (ArrayList<Integer> row : incMat) {
            row.add(0);
        }

        incMat.get(vertexToIndex.get(from)).set(edgeCount, 1);
        incMat.get(vertexToIndex.get(to)).set(edgeCount, -1);
        edgeCount++;
    }

    @Override
    public void deleteEdge(T from, T to) throws IndexOutOfBoundsException {
        if (!vertexToIndex.containsKey(from) || !vertexToIndex.containsKey(to)) {
            throw new IndexOutOfBoundsException("Invalid vertex");
        }

        int fromIdx = vertexToIndex.get(from);
        int toIdx = vertexToIndex.get(to);

        for (int i = 0; i < edgeCount; i++) {
            if (incMat.get(fromIdx).get(i) == 1 && incMat.get(toIdx).get(i) == -1) {
                for (ArrayList<Integer> row : incMat) {
                    row.remove(i);
                }
                edgeCount--;
                break;
            }
        }
    }

    @Override
    public List<T> getNeighbors(T vertex) throws IndexOutOfBoundsException {
        List<T> neighbors = new ArrayList<>();
        if (!vertexToIndex.containsKey(vertex)) {
            throw new IndexOutOfBoundsException("Invalid vertex");
        }

        int vertexIndex = vertexToIndex.get(vertex);

        for (int i = 0; i < edgeCount; i++) {
            if (incMat.get(vertexIndex).get(i) == 1) {
                for (int j = 0; j < incMat.size(); j++) {
                    if (incMat.get(j).get(i) == -1) {
                        neighbors.add(vertexValues.get(j));
                        break;
                    }
                }
            }
        }
        return neighbors;
    }

    @Override
    public int getVertexIdx(T vertex) throws IndexOutOfBoundsException {
        if (!vertexToIndex.containsKey(vertex)) {
            throw new IndexOutOfBoundsException("No such vertex");
        }
        return vertexToIndex.get(vertex);
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

