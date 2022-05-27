package ru.job4j.iterator;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(input, is(Arrays.asList(1, 2, 3)));
    }

    @Test
    public void whenAddBeforeFirst() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2));
        ListUtils.addBefore(input, 0, 0);
        assertThat(input, is(Arrays.asList(0, 1, 2)));
    }

    @Test
    public void whenAddAfter() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 4));
        ListUtils.addAfter(input, 1, 3);
        assertThat(input, is(Arrays.asList(1, 2, 3, 4)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        assertThat(input, is(Arrays.asList(0, 1, 2, 3)));
    }

    @Test
    public void whenRemoveIfPredicate() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        Predicate<Integer> pred = e -> e > 1;
        ListUtils.removeIf(input, pred);
        assertThat(input, is(Arrays.asList(0, 1)));
    }

    @Test
    public void whenReplaceIfPredicate() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4));
        Predicate<Integer> pred = e ->  e % 2 == 0;
        ListUtils.replaceIf(input, pred, 5);
        assertThat(input, is(Arrays.asList(5, 1, 5, 3, 5)));
    }

    @Test
    public void whenRemoveAllElementsFromAnotherCollection() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
        List<Integer> elems = new ArrayList<>(Arrays.asList(1, 3, 5, 7, 9));
        ListUtils.removeAll(input, elems);
        assertThat(input, is(Arrays.asList(0, 2, 4, 6, 8)));
    }
}