package ru.nsu.vostrikov;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Test;

public class SubstringFinderTest {
    @Test
    void testEmptyFile() throws IOException {
        InputStream input = getClass().getClassLoader().getResourceAsStream("empty.txt");
        List<Long> result = SubstringFinder.findSubstring(input, "substring");
        assertEquals(List.of(), result);
    }

    @Test
    void testNoMatches() throws IOException {
        InputStream input = getClass().getClassLoader().getResourceAsStream("test.txt");
        List<Long> result = SubstringFinder.findSubstring(input, "someshit");
        assertEquals(List.of(), result);
    }

    @Test
    void testSingleMatch() throws IOException {
        InputStream input = getClass().getClassLoader().getResourceAsStream("test.txt");
        List<Long> result = SubstringFinder.findSubstring(input, "ome");
        assertEquals(List.of(1L), result);
    }

    @Test
    void testMultipleMatches() throws IOException {
        InputStream input = getClass().getClassLoader().getResourceAsStream("test.txt");
        List<Long> result = SubstringFinder.findSubstring(input, "string");
        assertEquals(List.of(4L, 21L), result);
    }

    @Test
    void testOverlapMatches() throws IOException {
        InputStream input = getClass().getClassLoader().getResourceAsStream("test.txt");
        List<Long> result = SubstringFinder.findSubstring(input, "yyyy");
        assertEquals(List.of(33L, 34L, 35L), result);
    }

    @Test
    void testKirillMatches() throws IOException {
        InputStream input = getClass().getClassLoader().getResourceAsStream("kirill.txt");
        List<Long> result = SubstringFinder.findSubstring(input, "ыыыыыы");
        assertEquals(List.of(0L, 1L), result);
    }

    @Test
    void testSubstringLargerThanFile() throws IOException {
        InputStream input = getClass().getClassLoader().getResourceAsStream("test.txt");
        List<Long> result = SubstringFinder.findSubstring(input,
                "lsfjgsldkjfglsd;jg;gjdssdfgsdgfsdfgl;gkjds;lgsdfg");
        assertEquals(List.of(), result);
    }

    @Test
    void testLargeFileSubstringSearch() throws IOException {
        long fileSize = 1024L * 1024L * 1024L;
        long writtenBytes = 0;
        File largeFile = File.createTempFile("largeFile", "txt");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(largeFile))) {
            while (writtenBytes + 4 < fileSize) {
                writer.write("abcd");
                writtenBytes += 4;
            }
            writer.write("qwer");
            writtenBytes += 4;
        }
        List<Long> result = SubstringFinder.findSubstring(new FileInputStream(largeFile), "qwer");
        assertEquals(List.of(1024L * 1024L * 1024L - 4L), result);
        assertEquals(fileSize, writtenBytes);
        if (largeFile.exists()) {
            assertTrue(largeFile.delete());
        }
    }

}