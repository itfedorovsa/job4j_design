package ru.job4j.serialization.xml;

import java.util.Arrays;

public class Storage {
    private final String type;
    private final int capacity;
    private final boolean isFull;
    private final Contact phone;
    private final int[] goods;

    public Storage(String type, int capacity, boolean isFull, Contact phone, int... goods) {
        this.type = type;
        this.capacity = capacity;
        this.isFull = isFull;
        this.phone = phone;
        this.goods = goods;

    }

    @Override
    public String toString() {
        return "Storage{"
                + "type='" + type + '\''
                + ", capacity=" + capacity
                + ", isFull=" + isFull
                + ", phone=" + phone
                + ", goods=" + Arrays.toString(goods)
                + '}';
    }
}
