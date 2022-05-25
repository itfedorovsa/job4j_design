package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements LinkedList<E> {

    private int modCount = 0;

    private int size = 0;

    private Node<E> first;

    private Node<E> last;

    private static class Node<E> {
        E item;
        Node<E> prev;
        Node<E> next;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.prev = prev;
            this.next = next;
        }
    }

    @Override
    public void add(E value) {
        Node<E> temp = last;
        Node<E> newNode = new Node<E>(temp, value, null);
        last = newNode;
        if (temp == null) {
            first = newNode;
        } else {
            temp.next = newNode;
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        E value = null;
        Node<E> temp;
            if (index < size / 2) {
                temp = first;
                for (int count = 0; count < index; count++) {
                    temp = temp.next;
                }
            } else {
                temp = last;
                for (int count = size - 1; count > index; count--) {
                    temp = temp.prev;
                }
            }
        return temp.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            final int expectedModCount = modCount;
            Node<E> current = first;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return current != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E value = current.item;
                current = current.next;
                return value;
            }
        };
    }

}
