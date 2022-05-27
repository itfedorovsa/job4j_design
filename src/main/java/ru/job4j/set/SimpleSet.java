package ru.job4j.set;

import ru.job4j.collection.SimpleArrayList;

import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<T> implements Set<T> {

    private SimpleArrayList<T> set = new SimpleArrayList<>(16);

    @Override
    public boolean add(T value) {
        boolean isAdd = false;
        if (!contains(value)) {
            set.add(value);
            isAdd = true;
        }
        return isAdd;
    }

    @Override
    public boolean contains(T value) {
        boolean isContains = false;
        for (T el : set) {
            if (Objects.equals(el, value)) {
                isContains = true;
                break;
            }
        }
        return isContains;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}
