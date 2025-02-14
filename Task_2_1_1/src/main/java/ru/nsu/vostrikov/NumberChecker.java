package ru.nsu.vostrikov;

/**
 * Interface.
 */
public interface NumberChecker {
    /**
     * Checking composite number existence.
     */
    boolean hasComposite(int[] numbers);

    /**
     * Check for prime.
     */
    default boolean isPrime(int number) {
        if (number < 2) {
            return false;
        }
        for (int i = 2; i * i <= number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
