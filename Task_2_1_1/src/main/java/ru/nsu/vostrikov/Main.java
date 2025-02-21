/*
package ru.nsu.vostrikov;

import java.util.Arrays;


public class Main {

    public static void main(String[] args) {
        int[] testData = new int[5_000_000];
        Arrays.fill(testData, 10000079);
        long start, end;

        start = System.nanoTime();
        NumberChecker simple = new SimpleChecker();
        simple.hasComposite(testData);
        end = System.nanoTime();
        System.out.println("Sequential: " + (end - start) / 1e9 + " s");

        for (int i = 1; i < 7; ++i) {
            NumberChecker MultiThreadChecker = new MultiThreadChecker(i);
            start = System.nanoTime();
            MultiThreadChecker.hasComposite(testData);
            end = System.nanoTime();
            System.out.println("MultiThread (" + i + " threads): " + (end - start) / 1e9 + " s");
        }


        start = System.nanoTime();
        NumberChecker parallel = new ParallelChecker();
        parallel.hasComposite(testData);
        end = System.nanoTime();
        System.out.println("ParallelStream: " + (end - start) / 1e9 + " s");
    }
}
*/