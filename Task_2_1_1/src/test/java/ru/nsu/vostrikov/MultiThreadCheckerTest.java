package ru.nsu.vostrikov;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class MultiThreadCheckerTest {

    @Test
    void testAllPrimes() {
        int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23};
        MultiThreadChecker checker = new MultiThreadChecker(4);
        assertFalse(checker.hasComposite(primes));
    }

    @Test
    void testContainsComposite() {
        int[] numbers = {2, 3, 4, 5, 7};
        MultiThreadChecker checker = new MultiThreadChecker(4);
        assertTrue(checker.hasComposite(numbers));
    }

    @Test
    void testAllComposites() {
        int[] composites = {4, 6, 8, 9, 10, 12, 14};
        MultiThreadChecker checker = new MultiThreadChecker(4);
        assertTrue(checker.hasComposite(composites));
    }

    @Test
    void testSinglePrime() {
        int[] singlePrime = {17};
        MultiThreadChecker checker = new MultiThreadChecker(2);
        assertFalse(checker.hasComposite(singlePrime));
    }

    @Test
    void testSingleComposite() {
        int[] singleComposite = {15};
        MultiThreadChecker checker = new MultiThreadChecker(2);
        assertTrue(checker.hasComposite(singleComposite));
    }

    @Test
    void testEmptyArray() {
        int[] emptyArray = {};
        MultiThreadChecker checker = new MultiThreadChecker(2);
        assertFalse(checker.hasComposite(emptyArray));
    }
}
