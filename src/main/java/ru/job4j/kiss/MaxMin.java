package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;

public class MaxMin {

    private <T> List<T> sort(List<T> value, Comparator<T> comparator) {
        if (value.isEmpty()) {
                throw new IllegalArgumentException();
        }
        value.sort(comparator);
        return value;
    }

    public <T> T max(List<T> value, Comparator<T> comparator) {
        List<T> list = sort(value, comparator);
        return list.get(list.size() - 1);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        List<T> list = sort(value, comparator);
        return list.get(0);
    }

}
