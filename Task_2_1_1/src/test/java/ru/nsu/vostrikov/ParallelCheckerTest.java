package ru.nsu.vostrikov;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ParallelCheckerTest {

    @Test
    void testAllPrimes() {
        int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23};
        ParallelChecker checker = new ParallelChecker();
        assertFalse(checker.hasComposite(primes));
    }

    @Test
    void testContainsComposite() {
        int[] numbers = {2, 3, 4, 5, 7};
        ParallelChecker checker = new ParallelChecker();
        assertTrue(checker.hasComposite(numbers));
    }

    @Test
    void testAllComposites() {
        int[] composites = {4, 6, 8, 9, 10, 12, 14};
        ParallelChecker checker = new ParallelChecker();
        assertTrue(checker.hasComposite(composites));
    }

    @Test
    void testSinglePrime() {
        int[] singlePrime = {17};
        ParallelChecker checker = new ParallelChecker();
        assertFalse(checker.hasComposite(singlePrime));
    }

    @Test
    void testSingleComposite() {
        int[] singleComposite = {15};
        ParallelChecker checker = new ParallelChecker();
        assertTrue(checker.hasComposite(singleComposite));
    }

    @Test
    void testEmptyArray() {
        int[] emptyArray = {};
        ParallelChecker checker = new ParallelChecker();
        assertFalse(checker.hasComposite(emptyArray));
    }
}
