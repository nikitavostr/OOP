package ru.nsu.vostrikov;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Test;

public class SubstringFinderTest {
    @Test
    void testEmptyFile() throws IOException {
        List<Integer> result = SubstringFinder.findSubstring("empty.txt", "substring");
        assertEquals(List.of(), result);
    }

    @Test
    void testNoMatches() throws IOException {
        List<Integer> result = SubstringFinder.findSubstring("test.txt", "someshit");
        assertEquals(List.of(), result);
    }

    @Test
    void testSingleMatch() throws IOException {
        List<Integer> result = SubstringFinder.findSubstring("test.txt", "ome");
        assertEquals(List.of(1), result);
    }

    @Test
    void testMultipleMatches() throws IOException {
        List<Integer> result = SubstringFinder.findSubstring("test.txt", "string");
        assertEquals(List.of(4, 21), result);
    }

    @Test
    void testOverlapMatches() throws IOException {
        List<Integer> result = SubstringFinder.findSubstring("test.txt", "yyyy");
        assertEquals(List.of(33, 34, 35), result);
    }

    @Test
    void testSubstringLargerThanFile() throws IOException {
        List<Integer> result = SubstringFinder.findSubstring("test.txt",
                "lsfjgsldkjfglsd;jg;gjdssdfgsdgfsdfgl;gkjds;lgsdfg");
        assertEquals(List.of(), result);
    }
}