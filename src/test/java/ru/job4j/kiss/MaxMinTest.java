package ru.job4j.kiss;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MaxMinTest {

    @Test
    void whenIntegerListAndNaturalComparatorMaxThen5() {
        MaxMin maxMin = new MaxMin();
        List<Integer> list = new ArrayList<>();
        list.add(4);
        list.add(1);
        list.add(3);
        list.add(5);
        list.add(2);
        Comparator<Integer> comp = Comparator.naturalOrder();
        int expected = 5;
        int result = maxMin.max(list, comp);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void whenIntegerListAndNaturalComparatorMinThen1() {
        MaxMin maxMin = new MaxMin();
        List<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(7);
        list.add(1);
        list.add(3);
        list.add(9);
        Comparator<Integer> comp = Comparator.naturalOrder();
        int expected = 1;
        int result = maxMin.min(list, comp);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void whenListIsEmptyThenNull() {
        MaxMin maxMin = new MaxMin();
        List<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(7);
        list.add(1);
        list.add(3);
        list.add(9);
        Comparator<Integer> comp = Comparator.naturalOrder();
        int expected = 1;
        int result = maxMin.min(list, comp);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void whenListIsEmptyThenIllArgExc() {
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
                    MaxMin maxMin = new MaxMin();
                    List<Integer> list = new ArrayList<>();
                    Comparator<Integer> comp = Comparator.naturalOrder();
                    int result = maxMin.min(list, comp);
                });
    }
}