package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;

public class MaxMin {

    private <T> T calculate(List<T> value, Comparator<T> comparator) {
        if (value.isEmpty()) {
            throw new IllegalArgumentException();
        }
        T rsl = value.get(0);
        for (T v : value) {
            rsl = comparator.compare(rsl, v) > 0 ? rsl : v;
        }
        return rsl;
    }

    public <T> T max(List<T> value, Comparator<T> comparator) {
        return calculate(value, comparator);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return calculate(value, comparator.reversed());
    }

}
