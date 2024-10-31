package ru.nsu.vostrikov;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class HashTableTest {
    private HashTable<String, Integer> hashTable;

    @BeforeEach
    void setUp() {
        hashTable = new HashTable<>();
    }

    @Test
    void testPutAndGet() {
        hashTable.put("one", 1);
        hashTable.put("two", 2);
        hashTable.put("three", 3);
        assertEquals(1, hashTable.get("one"));
        assertEquals(2, hashTable.get("two"));
        assertEquals(3, hashTable.get("three"));
        assertNull(hashTable.get("four"));
    }

    @Test
    void testUpdate() {
        hashTable.put("one", 1);
        hashTable.update("one", 2);
        assertEquals(2, hashTable.get("one"));
    }

    @Test
    void testRemove() {
        hashTable.put("one", 1);
        hashTable.put("two", 2);
        assertEquals(1, hashTable.remove("one"));
        assertNull(hashTable.get("one"));
        assertEquals(2, hashTable.get("two"));
        assertNull(hashTable.remove("three"));
    }

    @Test
    void testContainsKey() {
        hashTable.put("one", 1);
        assertTrue(hashTable.containsKey("one"));
        assertFalse(hashTable.containsKey("two"));
    }

    @Test
    void testResize() {
        for (int i = 0; i < 10; i++) {
            hashTable.put("key" + i, i);
        }
        assertEquals(10, hashTable.size());
        for (int i = 0; i < 10; i++) {
            assertEquals(i, hashTable.get("key" + i));
        }
    }

    @Test
    void testSize() {
        assertEquals(0, hashTable.size());
        hashTable.put("one", 1);
        assertEquals(1, hashTable.size());
        hashTable.remove("one");
        assertEquals(0, hashTable.size());
    }

    @Test
    void testEquals() {
        HashTable<String, Integer> anotherTable = new HashTable<>();
        hashTable.put("one", 1);
        hashTable.put("two", 2);
        anotherTable.put("one", 1);
        anotherTable.put("two", 2);
        assertEquals(hashTable, anotherTable);
        anotherTable.put("three", 3);
        assertNotEquals(hashTable, anotherTable);
    }

    @Test
    void testToString() {
        hashTable.put("one", 1);
        hashTable.put("two", 2);
        String result = hashTable.toString();
        assertTrue(result.contains("one=1"));
        assertTrue(result.contains("two=2"));
    }

    @Test
    void testIteratorWithForEach() {
        HashTable<String, Integer> hashTable = new HashTable<>();
        hashTable.put("one", 1);
        hashTable.put("two", 2);
        Map<String, Integer> expectedEntries = new HashMap<>();
        expectedEntries.put("one", 1);
        expectedEntries.put("two", 2);
        for (Map.Entry<String, Integer> entry : hashTable) {
            assertTrue(expectedEntries.containsKey(entry.getKey()));
            assertEquals(expectedEntries.get(entry.getKey()), entry.getValue());
            expectedEntries.remove(entry.getKey());
        }
        assertTrue(expectedEntries.isEmpty());
    }

    @Test
    void testIteratorConcurrentModification() {
        hashTable.put("one", 1);
        Iterator<Map.Entry<String, Integer>> iterator = hashTable.iterator();
        hashTable.put("two", 2);
        assertThrows(ConcurrentModificationException.class, iterator::next);
    }
}