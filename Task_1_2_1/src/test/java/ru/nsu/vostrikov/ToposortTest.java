package ru.nsu.vostrikov;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

class ToposortTest {
    @ParameterizedTest
    @ArgumentsSource(TestArgumentsProvider.class)
    void topologicalSortTest(Graph<String> graph) throws FileNotFoundException {
        graph.readFile("graph.txt", "string");
        List<Vertex<String>> list = Toposort.toposort(graph);

        assertEquals("A", list.get(0).getValue());
        assertEquals("B", list.get(1).getValue());
        assertEquals("C", list.get(2).getValue());
        assertEquals("D", list.get(3).getValue());
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