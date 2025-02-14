package ru.nsu.vostrikov;

public interface NumberChecker {
    boolean hasComposite(int[] numbers);
    default boolean isPrime(int number) {
        if (number < 2) return false;
        for (int i = 2; i * i <= number; i++) {
            if (number % i == 0) return false;
        }
        return true;
    }
}
