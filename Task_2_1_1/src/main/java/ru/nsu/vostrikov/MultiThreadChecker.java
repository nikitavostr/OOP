package ru.nsu.vostrikov;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Multi Thread class checker.
 */
public class MultiThreadChecker implements NumberChecker {
    private final int threadCount;

    public MultiThreadChecker(int threadCount) {
        this.threadCount = threadCount;
    }

    @Override
    public boolean hasComposite(int[] numbers) {
        List<Thread> threads = new ArrayList<>();
        AtomicBoolean result = new AtomicBoolean(false);
        int length = numbers.length;

        for (int i = 0; i < threadCount; i++) {
            final int start = i * length / threadCount;
            final int end = (i + 1) * length / threadCount;

            Thread thread = new Thread(() -> {
                for (int j = start; j < end && !result.get(); j++) {
                    if (!isPrime(numbers[j])) {
                        result.set(true);
                        break;
                    }
                }
            });
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return result.get();
    }
}