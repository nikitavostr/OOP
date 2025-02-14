package ru.nsu.vostrikov;

public class SimpleChecker implements NumberChecker {
    @Override
    public boolean hasComposite(int[] numbers) {
        for (int num : numbers) {
            if (!isPrime(num)) {
                return true;
            }
        }
        return false;
    }
}
