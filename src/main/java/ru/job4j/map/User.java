package ru.job4j.map;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import static java.util.Calendar.APRIL;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public static void main(String[] args) {
        User first = new User("Bob", 1, new GregorianCalendar(1970, APRIL, 12));
        User second = new User("Bob", 1, new GregorianCalendar(1970, APRIL, 12));
        Map<User, Object> map = new HashMap<>(16);
        map.put(first, new Object());
        map.put(second, new Object());
        for (User user : map.keySet()) {
            String key = user.toString();
            String value = map.get(user).toString();
            int hash = user == null ? 0 : user.hashCode() ^ (user.hashCode() >>> 16);
            int index = hash & (16 - 1);
            System.out.println("Key: " + key + ", Value: " + value + ", HashCode: " + hash + ", Index: " + index);
        }
    }
}
