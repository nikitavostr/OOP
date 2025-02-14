package ru.nsu.vostrikov;

import java.util.Arrays;

public class ParallelChecker implements NumberChecker {
    @Override
    public boolean hasComposite(int[] numbers) {
        return Arrays.stream(numbers).parallel().anyMatch(num -> !isPrime(num));
    }
}
