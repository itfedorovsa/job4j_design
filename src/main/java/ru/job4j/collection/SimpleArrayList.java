package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;

    private int size;

    private int modCount;

    private int iterIndex = 0;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    public T[] expandArray() {
        container = size == container.length ? Arrays.copyOf(container, size * 2) : container;
        return container;
    }

    @Override
    public void add(T value) {
        expandArray();
        container[size++] = value;
        modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        Objects.checkIndex(index, container.length);
        T prev = container[index];
        container[index] = newValue;
        modCount++;
        return prev;
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, container.length);
        final int newSize = size - 1;
        T prev = container[index];
        if (newSize > index) {
            System.arraycopy(container, index + 1, container, index, newSize - index);
            size = newSize;
        } else {
            container[size] = null;
        }
            modCount++;
        return prev;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, container.length);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        int expectedModCount = modCount;
        iterIndex = 0;
        return new Iterator<T>() {

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return iterIndex < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[iterIndex++];
            }

        };
    }
}
