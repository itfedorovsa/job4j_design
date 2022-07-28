package ru.job4j.ood.isp.menu;

import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class SimpleMenuTest {
    public static final ActionDelegate STUB_ACTION = System.out::println;

    @Ignore
    @Test
    public void whenAddThenReturnSame() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Go to the store", STUB_ACTION);
        menu.add(Menu.ROOT, "Feed the dog", STUB_ACTION);
        menu.add("Go to the store", "Buy products", STUB_ACTION);
        menu.add("Buy products", "Buy bread", STUB_ACTION);
        menu.add("Buy products", "Buy milk", STUB_ACTION);
        assertEquals(
                new Menu.MenuItemInfo(
                        "Go to the store", List.of("Buy products"), STUB_ACTION, "1."
                ),
                menu.select("Go to the store").get()
        );
        assertEquals(
                new Menu.MenuItemInfo(
                        "Buy products", List.of("Buy bread", "Buy milk"), STUB_ACTION, "1.1."
                ),
                menu.select("Buy products").get()
        );
        assertEquals(
                new Menu.MenuItemInfo(
                        "Feed the dog", List.of(), STUB_ACTION, "2."
                ),
                menu.select("Feed the dog").get()
        );
        menu.forEach(i -> System.out.println(i.getNumber() + i.getName()));
    }
}