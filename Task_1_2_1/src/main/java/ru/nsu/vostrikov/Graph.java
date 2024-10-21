package ru.nsu.vostrikov;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

/**
 * Интерфейс графа.
 */
interface Graph<T> {
    void addVertex(Vertex<T> vertex);

    void deleteVertex(Vertex<T> vertex) throws IndexOutOfBoundsException;

    void addEdge(Edge<T> edge) throws IndexOutOfBoundsException;

    void deleteEdge(Edge<T> edge) throws IndexOutOfBoundsException;

    List<Vertex<T>> getNeighbors(Vertex<T> vertex) throws IndexOutOfBoundsException;

    int getVertexCnt();

    List<Vertex<T>> getVertices();

    /**
     * Чтение файла.
     */
    default void readFile(String fileName, String typeCheck) throws FileNotFoundException {
        File file = new File(fileName);
        try (Scanner scanner = new Scanner(file)) {
            String line;
            int num = 0;
            boolean readingVertices = true;
            boolean readingInt = true;
            if (typeCheck.equals("string")) {
                readingInt = false;
            }
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                if (line.equals("Vertices")) {
                    continue;
                } else if (line.equals("Edges")) {
                    readingVertices = false;
                    continue;
                } else {
                    if (readingVertices) {
                        try {
                            num = Integer.parseInt(line);
                            readingInt = true;
                        } catch (NumberFormatException e) {
                            readingInt = false;
                        }
                        if (readingInt) {
                            addVertex(new Vertex<T>((T) Integer.valueOf(line)));
                        } else {
                            addVertex(new Vertex<T>((T) (line)));
                        }
                    }
                    else {
                        String[] arr = line.split(" ");
                        Vertex<T> v1 = getVertices().get(Integer.parseInt(arr[0]));
                        Vertex<T> v2 = getVertices().get(Integer.parseInt(arr[1]));
                        addEdge(new Edge<>(v1, v2));
                    }
                }
            }
        }
    }
}
