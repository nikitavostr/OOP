package ru.nsu.vostrikov;

import java.util.List;

interface Graph<T> {
    void addVertex(T vertex);

    void deleteVertex(T vertex) throws IndexOutOfBoundsException;

    void addEdge(T from, T to) throws IndexOutOfBoundsException;

    void deleteEdge(T from, T to) throws IndexOutOfBoundsException;

    List<T> getNeighbors(T vertex) throws IndexOutOfBoundsException;

    int getVertexIdx(T vertex) throws IndexOutOfBoundsException;

    int getVertexCnt();

    T getVertex(int vertexIdx);
}
