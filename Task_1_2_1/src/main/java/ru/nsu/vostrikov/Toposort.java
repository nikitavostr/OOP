package ru.nsu.vostrikov;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

/**
 * Класс топологической сортировки.
 */
public class Toposort<T> {

    /**
     * Функция сортировки.
     */
    public static <T> List<Vertex<T>> toposort(Graph<T> graph) {
        boolean[] visited = new boolean[graph.getVertexCnt()];
        List<Vertex<T>> answer = new ArrayList<>();
        int cnt = graph.getVertexCnt();
        for (int i = 0; i < cnt; i++) {
            visited[i] = false;
        }
        for (int i = 0; i < cnt; ++i) {
            if (!visited[i]) {
                dfs(i, graph, visited, answer);
            }
        }

        Collections.reverse(answer);
        return answer;
    }

    /**
     * Обход в глубину.
     */
    private static <T> void dfs(int vert, Graph<T> graph, boolean[] visited, List<Vertex<T>> answer) {
        visited[vert] = true;
        int cnt = graph.getNeighbors(graph.getVertex(vert)).size();
        Vertex<T> vertex;
        for(int i = 0; i < cnt; ++i) {
            vertex = graph.getNeighbors(graph.getVertex(vert)).get(i);
            int to = graph.getVertexIdx(vertex);
            if (!visited[to]) {
                dfs(to, graph, visited, answer);
            }
        }
        answer.add(graph.getVertex(vert));
    }
}