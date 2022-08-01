package ru.job4j.ood.isp.menu;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleMenuTest {
    public static final ActionDelegate STUB_ACTION = System.out::println;

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

    @Test
    public void whenSelect() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Go to the store", STUB_ACTION);
        menu.add(Menu.ROOT, "Feed the dog", STUB_ACTION);
        menu.add("Go to the store", "Buy drinks", STUB_ACTION);
        menu.add("Buy drinks", "Buy juice", STUB_ACTION);
        menu.add("Buy drinks", "Buy cola", STUB_ACTION);
        assertEquals(
                new Menu.MenuItemInfo(
                        "Buy drinks", List.of("Buy juice", "Buy cola"), STUB_ACTION, "1.1."
                ),
                menu.select("Buy drinks").get()
        );
    }

    @Test
    public void whenPrint() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Go to the store", STUB_ACTION);
        menu.add(Menu.ROOT, "Feed the dog", STUB_ACTION);
        menu.add("Go to the store", "Buy drinks", STUB_ACTION);
        menu.add("Buy drinks", "Buy juice", STUB_ACTION);
        SimpleMenuPrinter printer = new SimpleMenuPrinter();
        printer.print(menu);
        String expected = """
                1.Go to the store\r
                    1.1.Buy drinks\r
                        1.1.1.Buy juice\r
                2.Feed the dog\r
                """;
 assertThat(printer.toString(), is(expected));
    }

}