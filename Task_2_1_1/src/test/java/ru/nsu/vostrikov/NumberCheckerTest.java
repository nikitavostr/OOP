package ru.nsu.vostrikov;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

class NumberCheckerTest {
    @ParameterizedTest()
    @ArgumentsSource(NumberCheckerProvider.class)
    void testAllPrimes(NumberChecker numberChecker) {
        int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23};
        assertFalse(numberChecker.hasComposite(primes));
    }

    @ParameterizedTest()
    @ArgumentsSource(NumberCheckerProvider.class)
    void testContainsComposite(NumberChecker numberChecker) {
        int[] numbers = {2, 3, 4, 5, 7};
        assertTrue(numberChecker.hasComposite(numbers));
    }

    @ParameterizedTest()
    @ArgumentsSource(NumberCheckerProvider.class)
    void testAllComposites(NumberChecker numberChecker) {
        int[] composites = {4, 6, 8, 9, 10, 12, 14};
        assertTrue(numberChecker.hasComposite(composites));
    }

    @ParameterizedTest()
    @ArgumentsSource(NumberCheckerProvider.class)
    void testSinglePrime(NumberChecker numberChecker) {
        int[] singlePrime = {17};
        assertFalse(numberChecker.hasComposite(singlePrime));
    }

    @ParameterizedTest()
    @ArgumentsSource(NumberCheckerProvider.class)
    void testSingleComposite(NumberChecker numberChecker) {
        int[] singleComposite = {15};
        assertTrue(numberChecker.hasComposite(singleComposite));
    }

    @ParameterizedTest()
    @ArgumentsSource(NumberCheckerProvider.class)
    void testEmptyArray(NumberChecker numberChecker) {
        int[] emptyArray = {};
        assertFalse(numberChecker.hasComposite(emptyArray));
    }

    static class NumberCheckerProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    Arguments.of(new SimpleChecker()),
                    Arguments.of(new ParallelChecker()),
                    Arguments.of(new MultiThreadChecker(2)),
                    Arguments.of(new MultiThreadChecker(3)),
                    Arguments.of(new MultiThreadChecker(4)));

        }
    }
}