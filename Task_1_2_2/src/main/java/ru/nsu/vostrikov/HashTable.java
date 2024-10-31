package ru.nsu.vostrikov;

import java.lang.Iterable;
import java.util.AbstractMap;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * HashTable class.
 */
public class HashTable<K, V> implements Iterable<Map.Entry<K, V>> {
    private List<Entry<K, V>>[] table;
    private int size;
    private int modCount;
    private int capacity;

    /**
     * constructor.
     */
    public HashTable() {
        capacity = 8;
        size = 0;
        modCount = 0;
        table = new List[capacity];
        for (int i = 0; i < capacity; i++) {
            table[i] = new LinkedList<>();
        }
    }

    /**
     * Element of HashTable.
     */
    private static class Entry<K, V> {
        final K key;
        V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    /**
     * Calculate hash.
     */
    private int hash(K key) {
        return Math.abs(key.hashCode() % capacity);
    }

    /**
     * Put element.
     */
    public void put(K key, V value) {
        int index = hash(key);
        for (Entry<K, V> entry : table[index]) {
            if (entry.key.equals(key)) {
                entry.value = value;
                return;
            }
        }
        table[index].add(new Entry<>(key, value));
        size++;
        modCount++;

        if (size >= capacity) {
            resize();
        }
    }

    /**
     * Get element.
     */
    public V get(K key) {
        int index = hash(key);
        for (Entry<K, V> entry : table[index]) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
        }
        return null;
    }

    /**
     * Remove element.
     */
    public V remove(K key) {
        int index = hash(key);
        Iterator<Entry<K, V>> iterator = table[index].iterator();
        while (iterator.hasNext()) {
            Entry<K, V> entry = iterator.next();
            if (entry.key.equals(key)) {
                iterator.remove();
                size--;
                modCount++;
                return entry.value;
            }
        }
        return null;
    }

    /**
     * Contains key or not.
     */
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    /**
     * Update value.
     */
    public void update(K key, V value) {
        put(key, value);
    }

    /**
     * size of HashTable.
     */
    public int size() {
        return size;
    }

    /**
     * Resize.
     */
    private void resize() {
        capacity *= 2;
        List<Entry<K, V>>[] newTable = new List[capacity];
        for (int i = 0; i < capacity; i++) {
            newTable[i] = new LinkedList<>();
        }

        for (List<Entry<K, V>> bucket : table) {
            for (Entry<K, V> entry : bucket) {
                int newIndex = hash(entry.key);
                newTable[newIndex].add(entry);
            }
        }
        table = newTable;
    }

    /**
     * To string.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (List<Entry<K, V>> bucket : table) {
            for (Entry<K, V> entry : bucket) {
                sb.append(entry.key).append("=").append(entry.value).append(", ");
            }
        }
        return "{" + sb.toString().replaceAll(", $", "") + "}";
    }

    /**
     * HashCode.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        for (List<Entry<K, V>> bucket : table) {
            for (Entry<K, V> entry : bucket) {
                hash += Objects.hash(entry.key, entry.value);
            }
        }
        return hash;
    }

    /**
     * equals.
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof HashTable)) {
            return false;
        }
        HashTable<K, V> other = (HashTable<K, V>) obj;
        if (size != other.size) {
            return false;
        }
        for (List<Entry<K, V>> list : table) {
            for (Entry<K, V> entry : list) {
                V testValue = other.get(entry.key);
                if (!Objects.equals(entry.value, testValue)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Iterator.
     */
    public Iterator<Map.Entry<K, V>> iterator() {
        return new Iterator<Map.Entry<K, V>>() {
            private final int expectedModCount = modCount;
            private int bucketIndex = 0;
            private Iterator<Entry<K, V>> bucketIterator = table[bucketIndex].iterator();

            /**
             * Has Next or not.
             */
            @Override
            public boolean hasNext() {
                checkModificationException();
                while (bucketIndex < capacity && !bucketIterator.hasNext()) {
                    bucketIndex++;
                    if (bucketIndex < capacity) {
                        bucketIterator = table[bucketIndex].iterator();
                    }
                }
                return bucketIndex < capacity && bucketIterator.hasNext();
            }

            /**
             * Next element.
             */
            @Override
            public Map.Entry<K, V> next() {
                checkModificationException();
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Entry<K, V> entry = bucketIterator.next();
                return new AbstractMap.SimpleEntry<>(entry.key, entry.value);
            }

            /**
             * Check modifications.
             */
            private void checkModificationException() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
            }
        };
    }
}