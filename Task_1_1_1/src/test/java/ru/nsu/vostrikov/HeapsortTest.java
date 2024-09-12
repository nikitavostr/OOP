package ru.nsu.vostrikov;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.junit.jupiter.api.Test;


class HeapsortTest {
    @Test
    void oneNumTest() {
        int[] testArray = new int[]{7};
        Heapsort.heapSort(testArray, 1);
        assertArrayEquals(new int[]{7}, testArray);
    }

    @Test
    void zeroNumTest() {
        int[] testArray = new int[]{};
        Heapsort.heapSort(testArray, 0);
        assertArrayEquals(new int[]{}, testArray);
    }

    @Test
    void negativeNumTest() {
        int[] testArray = new int[]{-1, -5, -3, -10, -34};
        Heapsort.heapSort(testArray, 5);
        assertArrayEquals(new int[]{-34, -10, -5, -3, -1}, testArray);
    }

    @Test
    void sortedArrTest() {
        int[] testArray = new int[]{1, 2, 3, 4, 5};
        Heapsort.heapSort(testArray, 5);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, testArray);
    }

    @Test
    void descendingOrderTest() {
        int[] testArray = new int[]{5, 4, 3, 2, 1};
        Heapsort.heapSort(testArray, 5);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, testArray);
    }

    @Test
    void negativeAndPositiveNumTest() {
        int[] testArray = new int[]{-100, 54, 32, -154, 452};
        Heapsort.heapSort(testArray, 5);
        assertArrayEquals(new int[]{-154, -100, 32, 54, 452}, testArray);
    }

    @Test
    void sameNumTest() {
        int[] testArray = new int[]{1, 4, 6, 3, 1};
        Heapsort.heapSort(testArray, 5);
        assertArrayEquals(new int[]{1, 1, 3, 4, 6}, testArray);
    }
}