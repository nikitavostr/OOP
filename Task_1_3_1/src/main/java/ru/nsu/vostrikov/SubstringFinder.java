package ru.nsu.vostrikov;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SubstringFinder {
    public static List<Integer> findSubstring(String fileName, String substring) throws IOException {
        List<Integer> indices = new ArrayList<>();
        int substringLength = substring.length();
        int currentIndex = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            char[] buffer = new char[substringLength];
            int charsRead = reader.read(buffer, 0, substringLength);
            if (charsRead < substringLength) {
                return indices;
            }
            while (true) {
                String bufferStr = new String(buffer);
                if (bufferStr.equals(substring)) {
                    indices.add(currentIndex);
                }
                int nextChar = reader.read();
                if (nextChar == -1) {
                    break;
                }
                currentIndex++;
                System.arraycopy(buffer, 1, buffer, 0, substringLength - 1);
                buffer[substringLength - 1] = (char) nextChar;
            }
        }
        return indices;
    }
}