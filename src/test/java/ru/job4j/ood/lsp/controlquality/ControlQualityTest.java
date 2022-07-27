package ru.job4j.ood.lsp.controlquality;

import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.time.LocalDate;
import java.util.List;

public class ControlQualityTest {

    List<Store> listStore;

    ControlQuality control;

    @Before
    public void setup() {
        listStore = List.of(
        new Shop(),
        new Warehouse(),
        new Trash()
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenIncorrectDate() {
        Food bread = new Bread("Bread", LocalDate.now().minusMonths(2), LocalDate.now().minusMonths(1), 50.00f, 10.0f);
        control = new ControlQuality(listStore);
        control.distribute(bread);
    }

    @Test
    public void whenTrash() {
        Food bread = new Bread("Bread", LocalDate.now().minusMonths(1), LocalDate.now().minusMonths(2), 50.00f, 10.0f);
        control = new ControlQuality(listStore);
        control.distribute(bread);
        List<Food> expected = listStore.get(2).get();
        assertThat(expected.get(0).getFood(), is("Bread"));
    }

    @Test
    public void whenShopWithoutDiscount() {
        Food milk = new Milk("Milk", LocalDate.now().plusMonths(1), LocalDate.now().minusMonths(1), 50.00f, 10.0f);
        control = new ControlQuality(listStore);
        control.distribute(milk);
        List<Food> expected = listStore.get(0).get();
        assertThat(expected.get(0).getPrice(), is(50.00f));
    }

    @Test
    public void whenShopWithDiscount() {
        Food fruit = new Fruit("Fruit", LocalDate.now().plusDays(2), LocalDate.now().minusMonths(1), 50.00f, 10.0f);
        control = new ControlQuality(listStore);
        control.distribute(fruit);
        List<Food> expected = listStore.get(0).get();
        assertThat(expected.get(0).getPrice(), is(45.00f));
    }

    @Test
    public void whenWarehouse() {
        Food fruit = new Fruit("Fruit", LocalDate.now().plusMonths(1), LocalDate.now().minusDays(2), 50.00f, 10.0f);
        control = new ControlQuality(listStore);
        control.distribute(fruit);
        List<Food> expected = listStore.get(1).get();
        assertThat(expected.get(0).getFood(), is("Fruit"));
    }

}