package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if (count >= capacity * LOAD_FACTOR) {
            expand();
        }
        boolean isAdded = false;
        int index = key == null ? 0 : indexFor(hash(key.hashCode()));
        if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            isAdded = true;
            count++;
            modCount++;
        }
        return isAdded;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private void expand() {
        capacity *= 2;
        MapEntry<K, V>[] temp = table;
        table = new MapEntry[capacity];
        for (MapEntry<K, V> el : temp) {
            if (el != null) {
                this.put(el.key, el.value);
                count--;
            }
        }
    }

    @Override
    public V get(K key) {
        MapEntry<K, V> entry = table[indexFor(hash(key.hashCode()))];
        return (entry != null && entry.key.equals(key)) ? entry.value : null;
    }

    @Override
    public boolean remove(K key) {
        int index = indexFor(hash(key.hashCode()));
        boolean isRemoved = false;
        if (table[index] != null) {
            table[index] = null;
            isRemoved = true;
            count--;
            modCount++;
        }
        return isRemoved;
    }

    public int size() {
        return count;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            final int expectedModCount = modCount;
            int iterIndex = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (iterIndex < capacity && table[iterIndex] == null) {
                    iterIndex++;
                }
                return iterIndex < capacity;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[iterIndex++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }
}
