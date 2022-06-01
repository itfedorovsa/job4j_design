package ru.job4j.map;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class SimpleMapTest {

    SimpleMap<Integer, String> map;

    @Before
    public void initData() {
        map = new SimpleMap<>();
        map.put(1, "123");
        map.put(2, "345");
    }

    @Test
    public void whenPutThenTrue() {
        assertEquals(2, map.size());
        map.put(3, "678");
        map.put(4, "100");
        assertEquals(4, map.size());
    }

    @Test
    public void whenPutThenFalse() {
        assertFalse(map.put(1, "555"));
        assertFalse(map.put(2, "999"));
    }

    @Test
    public void whenGetThenTrue() {
        assertEquals("345", map.get(2));
        assertEquals("123", map.get(1));
    }

    @Test
    public void whenGetThenNull() {
        assertNull(map.get(3));
        assertNull(map.get(5));
    }

    @Test
    public void whenRemoveThenTrue() {
        map.put(3, "678");
        map.put(4, "100");
        map.remove(1);
        assertEquals(3, map.size());
    }

    @Test
    public void whenRemoveThenFalse() {
        assertFalse(map.remove(3));
    }

    @Test (expected = NoSuchElementException.class)
    public void whenIterateThenNoSuchElementE() {
        map.remove(1);
        map.remove(2);
        Iterator<Integer> iterator = map.iterator();
        iterator.next();
    }

    @Test (expected = ConcurrentModificationException.class)
    public void whenIterateThenConcurrentModificationE() {
        Iterator<Integer> iterator = map.iterator();
        map.remove(1);
        iterator.next();
    }

    @Test
    public void whenCheckIterator() {
        Iterator<Integer> iterator = map.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(Integer.valueOf(1), iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(Integer.valueOf(2), iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void whenNoPlaceThenCapacityIncreased() {
        IntStream.range(3, 25).forEach(v -> map.put(v, "111"));
    }

    @Test
    public void whenStartIteratorTwiceAndAlwaysFromBeginning() {
        assertEquals(Integer.valueOf(1), map.iterator().next());
        assertEquals(Integer.valueOf(1), map.iterator().next());
    }

    @Test
    public void whenAddAndResizeThenRehashing() {
        map.put(3, "678");
        map.put(4, "100");
        IntStream.range(5, 25).forEach(v -> map.put(v, "111"));
        assertEquals("345", map.get(2));
        assertEquals("100", map.get(4));

    }
}