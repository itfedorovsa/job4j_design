package ru.job4j.ood.lsp.controlquality;

import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.time.LocalDate;
import java.util.List;

public class ControlQualityTest {

    List<Store> listStore;
    Shop shop = new Shop();
    Warehouse warehouse = new Warehouse();
    Trash trash = new Trash();

    ControlQuality control;

    @Before
    public void setup() {
        listStore = List.of(shop, warehouse, trash);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenIncorrectDate() {
        Food bread = new Bread("Bread", LocalDate.now().minusMonths(2), LocalDate.now().minusMonths(1), 50.00f, 10.0f);
        control = new ControlQuality(listStore);
        control.distribute(bread);
    }

    @Test
    public void whenTrash() {
        Bread bread = new Bread("Bread", LocalDate.now().minusMonths(1), LocalDate.now().minusMonths(2), 50.00f, 10.0f);
        control = new ControlQuality(listStore);
        control.distribute(bread);
        assertThat(trash.get(), is(List.of(bread)));
    }

    @Test
    public void whenShopWithoutDiscount() {
        Food milk = new Milk("Milk", LocalDate.now().plusMonths(1), LocalDate.now().minusMonths(1), 50.00f, 10.0f);
        control = new ControlQuality(listStore);
        control.distribute(milk);
        assertThat(shop.get(), is(List.of(milk)));
    }

    @Test
    public void whenShopWithDiscount() {
        Food fruit = new Fruit("Fruit", LocalDate.now().plusDays(2), LocalDate.now().minusMonths(1), 50.00f, 10.0f);
        control = new ControlQuality(listStore);
        control.distribute(fruit);
        fruit.setPrice(45.00f);
        assertThat(shop.get(), is(List.of(fruit)));
    }

    @Test
    public void whenWarehouse() {
        Food fruit = new Fruit("Fruit", LocalDate.now().plusMonths(1), LocalDate.now().minusDays(2), 50.00f, 10.0f);
        control = new ControlQuality(listStore);
        control.distribute(fruit);
        assertThat(warehouse.get(), is(List.of(fruit)));
    }

    @Test
    public void whenDiverseStores() {
        Food fruit = new Fruit("Fruit", LocalDate.now().plusMonths(1), LocalDate.now().minusDays(2), 50.00f, 10.0f);
        Food milk = new Milk("Milk", LocalDate.now().plusMonths(1), LocalDate.now().minusMonths(1), 50.00f, 10.0f);
        Bread bread = new Bread("Bread", LocalDate.now().minusMonths(1), LocalDate.now().minusMonths(2), 50.00f, 10.0f);
        control = new ControlQuality(listStore);
        control.distribute(fruit);
        control.distribute(milk);
        control.distribute(bread);
        assertThat(trash.get(), is(List.of(bread)));
        assertThat(warehouse.get(), is(List.of(fruit)));
        assertThat(shop.get(), is(List.of(milk)));
    }

}