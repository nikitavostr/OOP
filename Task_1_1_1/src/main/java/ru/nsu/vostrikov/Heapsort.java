package ru.nsu.vostrikov;

import java.util.Random;

/**
 * heapsort algorithm.
 *
 * @author n.vostrikov
 */

public class Heapsort {
    /**
     * this function build a heap.
     *
     * @param arr - array
     * @param len - length of array
     * @param i   - index of current element
     */

    public static void buildHeap(int[] arr, int len, int i) {
        int current = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < len && arr[left] > arr[current]) {
            current = left; // if left child bigger than dad child becomes dad
        }
        if (right < len && arr[right] > arr[current]) {
            current = right; // analogically
        }
        if (current != i) {
            int temp = arr[i];
            arr[i] = arr[current];
            arr[current] = temp; // if child outgrew his dad swap them

            buildHeap(arr, len, current);
        }
    }

    /**
     * this is sorting function.
     *
     * @param arr - array
     */

    public static void heapSort(int[] arr) {
        int len = arr.length;
        for (int i = len / 2 - 1; i >= 0; i--) {
            buildHeap(arr, len, i); // call a build_heap function for build max heap
        }
        for (int i = len - 1; i > 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp; // for each iteration we move max element of the heap to the of the array
            buildHeap(arr, i, 0); // and build max_heap again but for first i elements of the array
        }
    }

    /**
     * testing complexity.
     */
    public static void main(String[] args) {
        /*for (int i = 1000000; i <= 7000000; i += 1000000) {
            int[] arr = new int[i];
            Random rd = new Random();
            for (int j = 0; j < i; j++) {
                arr[j] = rd.nextInt();
            }
            long time = System.currentTimeMillis();
            heapSort(arr, i);
            System.out.printf("size of array: %d time: %d\n", i, System.currentTimeMillis() - time);
        }*/
    }
}