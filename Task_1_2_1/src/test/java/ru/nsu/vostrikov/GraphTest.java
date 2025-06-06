package ru.nsu.vostrikov;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

/**
 * Test class.
 */
public class GraphTest {

    /**
     * Test add vertex.
     */
    @ParameterizedTest
    @ArgumentsSource(TestArgumentsProvider.class)
    public void testAddVertex(Graph<String> graph) {
        Vertex<String> v1 = new Vertex<>("A");
        Vertex<String> v2 = new Vertex<>("B");
        graph.addVertex(v1);
        graph.addVertex(v2);
        assertEquals(v1, graph.getVertices().get(0));
        assertEquals(2, graph.getVertexCnt());
        assertEquals(v2, graph.getVertices().get(1));
    }

    /**
     * Test duplicate vertex.
     */
    @ParameterizedTest
    @ArgumentsSource(TestArgumentsProvider.class)
    public void testAddDuplicateVertex(Graph<String> graph) {
        Vertex<String> v1 = new Vertex<>("A");
        graph.addVertex(v1);
        graph.addVertex(v1);
        assertEquals(1, graph.getVertexCnt());
    }

    /**
     * Test delete vertex.
     */
    @ParameterizedTest
    @ArgumentsSource(TestArgumentsProvider.class)
    public void testDeleteVertex(Graph<String> graph) {
        Vertex<String> v1 = new Vertex<>("A");
        Vertex<String> v2 = new Vertex<>("B");

        graph.addVertex(v1);
        graph.addVertex(v2);
        assertEquals(2, graph.getVertexCnt());
        graph.deleteVertex(v1);
        assertEquals(1, graph.getVertexCnt());
        assertEquals(v2, graph.getVertices().get(0));
    }

    /**
     * Test delete non existing vertex.
     */
    @ParameterizedTest
    @ArgumentsSource(TestArgumentsProvider.class)
    public void testDeleteNonExistingVertex(Graph<String> graph) {
        Vertex<String> v1 = new Vertex<>("A");
        graph.addVertex(v1);
        Vertex<String> nonExistingVertex = new Vertex<>("C");
        assertThrows(IndexOutOfBoundsException.class, () -> graph.deleteVertex(nonExistingVertex));
    }

    /**
     * test add edge.
     */
    @ParameterizedTest
    @ArgumentsSource(TestArgumentsProvider.class)
    public void testAddEdge(Graph<String> graph) {
        Vertex<String> v1 = new Vertex<>("A");
        Vertex<String> v2 = new Vertex<>("B");
        graph.addVertex(v1);
        graph.addVertex(v2);
        Edge<String> edge = new Edge<>(v1, v2);
        graph.addEdge(edge);
        List<Vertex<String>> neighborsOfA = graph.getNeighbors(v1);
        assertTrue(neighborsOfA.contains(v2));
        List<Vertex<String>> neighborsOfB = graph.getNeighbors(v2);
        assertFalse(neighborsOfB.contains(v1));
    }

    /**
     * test delete edge.
     */
    @ParameterizedTest
    @ArgumentsSource(TestArgumentsProvider.class)
    public void testDeleteEdge(Graph<String> graph) {
        Vertex<String> v1 = new Vertex<>("A");
        Vertex<String> v2 = new Vertex<>("B");
        graph.addVertex(v1);
        graph.addVertex(v2);
        Edge<String> edge = new Edge<>(v1, v2);
        graph.addEdge(edge);
        assertTrue(graph.getNeighbors(v1).contains(v2));
        graph.deleteEdge(edge);
        assertFalse(graph.getNeighbors(v1).contains(v2));
    }

    /**
     * test get neighbors.
     */
    @ParameterizedTest
    @ArgumentsSource(TestArgumentsProvider.class)
    public void testGetNeighbors(Graph<String> graph) {
        Vertex<String> v1 = new Vertex<>("A");
        Vertex<String> v2 = new Vertex<>("B");
        Vertex<String> v3 = new Vertex<>("C");
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addEdge(new Edge<>(v1, v2));
        graph.addEdge(new Edge<>(v1, v3));
        List<Vertex<String>> neighborsOfA = graph.getNeighbors(v1);
        assertTrue(neighborsOfA.contains(v2));
        assertTrue(neighborsOfA.contains(v3));
        assertEquals(2, neighborsOfA.size());
        List<Vertex<String>> neighborsOfB = graph.getNeighbors(v2);
        assertFalse(neighborsOfB.contains(v1));
        assertEquals(0, neighborsOfB.size());
    }

    /**
     * test get vertex cnt.
     */
    @ParameterizedTest
    @ArgumentsSource(TestArgumentsProvider.class)
    public void testGetVertexCnt(Graph<String> graph) {
        assertEquals(0, graph.getVertexCnt());
        Vertex<String> v1 = new Vertex<>("A");
        Vertex<String> v2 = new Vertex<>("B");
        graph.addVertex(v1);
        graph.addVertex(v2);
        assertEquals(2, graph.getVertexCnt());
        graph.deleteVertex(v1);
        assertEquals(1, graph.getVertexCnt());
    }

    /**
     * test read file.
     */
    @ParameterizedTest
    @ArgumentsSource(TestArgumentsProvider.class)
    void readFromFileTest(Graph<String> graph) throws FileNotFoundException {
        graph.readFile("graph.txt", "string");
        assertEquals(4, graph.getVertexCnt());
        Vertex<String> v1 = graph.getVertices().get(0);
        Vertex<String> v2 = graph.getVertices().get(1);
        Vertex<String> v3 = graph.getVertices().get(2);
        Vertex<String> v4 = graph.getVertices().get(3);
        assertTrue(graph.getNeighbors(v1).contains(v2));
        assertTrue(graph.getNeighbors(v1).contains(v3));
        Vertex<String> nonExistingVertex = new Vertex<>("E");
        assertThrows(IndexOutOfBoundsException.class, () -> graph.deleteVertex(nonExistingVertex));
        assertEquals("A", graph.getVertices().get(0).getValue());
    }

    static class TestArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    Arguments.of(new AdjacencyList<String>()),
                    Arguments.of(new AdjacencyMatrix<String>()),
                    Arguments.of(new IncidenceMatrix<String>())
            );
        }
    }

}
