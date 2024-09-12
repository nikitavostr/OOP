package ru.nsu.vostrikov;

import java.util.Random;

/**
 * heapsort algorithm
 *
 * @author n.vostrikov
 */

public class Heapsort {
    /**
     * this function build a heap.
     * @param arr - array
     * @param n - size of array
     * @param i - index of current element
     */

    public static void buildHeap(int[] arr, int n, int i)
    {
        int dad = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && arr[left] > arr[dad])
            dad = left; // if left child bigger than dad child becomes dad

        if (right < n && arr[right] > arr[dad])
            dad = right; // analogically

        if (dad != i) {
            int temp = arr[i];
            arr[i] = arr[dad];
            arr[dad] = temp; // if child outgrew his dad swap them

            buildHeap(arr, n, dad);
        }
    }

    /**
     * this is sorting function
     * @param arr - array
     * @param n - size of array
     */
    public static void heapSort(int[] arr, int n)
    {
        for (int i = n / 2 - 1; i >= 0; i--)
            buildHeap(arr, n, i); // call a build_heap function for build max heap
        for (int i = n - 1; i > 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp; // for each iteration we move max element of the heap to the of the array
            buildHeap(arr, i, 0); // and build max_heap again but for first i elements of the array
        }
    }

    /** testing complexity
     */
    public static void main(String[] args) {
        for(int i = 1000000; i <= 7000000; i += 1000000){
            int[] arr = new int[i];
            Random rd = new Random();
            for (int j = 0; j < i; j++) {
                arr[j] = rd.nextInt();
            }
            long t = System.currentTimeMillis();
            heapSort(arr, i);
            System.out.printf("size of array: %d    time: %d\n", i, System.currentTimeMillis() - t);
        }
    }
}