package ru.job4j.map;

import java.util.*;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(name, user.name) && children == user.children && Objects.equals(birthday, user.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }

    public static void main(String[] args) {
        Calendar date = new GregorianCalendar();
        date.set(Calendar.YEAR, 1987);
        date.set(Calendar.MONTH, 4);
        date.set(Calendar.DAY_OF_MONTH, 24);
        date.set(Calendar.HOUR, 12);
        date.set(Calendar.SECOND, 50);
        date.set(Calendar.MILLISECOND, 10);
        User first = new User("Bob", 1, date);
        User second = new User("Bob", 1, date);
        Map<User, Object> map = new HashMap<>(16);
        map.put(first, new Object());
        map.put(second, new Object());
        for (User user : map.keySet()) {
            String key = user.toString();
            String value = map.get(user).toString();

            int hash = user == null ? 0 : user.hashCode() ^ (user.hashCode() >>> 16);
            int index = hash & (16 - 1);
            System.out.println("Key: " + key + ", Value: " + value
                    + ", HashCode: " + user.hashCode() + ", Hash: " + hash + ", Index: " + index);
        }
        System.out.println("First key: " + first + ", First hash: " + (first.hashCode() ^ (first.hashCode() >>> 16)));
        System.out.println("Second key: " + second + ", Second hash: " + (second.hashCode() ^ (second.hashCode() >>> 16)));
    }
}
