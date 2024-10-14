package ru.nsu.vostrikov;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Toposort<T> {
    private boolean[] visited;
    private Deque<Integer> stack;

    public List<T> sort(Graph<T> graph) {
        int verticesCnt = graph.getVertexCnt();
        visited = new boolean[verticesCnt];
        stack = new ArrayDeque<>();
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                dfs(i, graph);
            }
        }
        List<T> sortedList = new ArrayList<>();
        while (!stack.isEmpty()) {
            sortedList.add(graph.getVertex(stack.pop()));
        }
        return sortedList;
    }

    private void dfs(int vertexIdx, Graph<T> graph) {
        if (visited[vertexIdx]) {
            return;
        }
        visited[vertexIdx] = true;
        List<T> neighbors = graph.getNeighbors(graph.getVertex(vertexIdx));
        for (T neighbor : neighbors) {
            int neighborIdx = graph.getVertexIdx(neighbor);
            dfs(neighborIdx, graph);
        }
        stack.push(vertexIdx);
    }
}
