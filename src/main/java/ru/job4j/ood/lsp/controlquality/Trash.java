package ru.job4j.ood.lsp.controlquality;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Trash implements Store {
    List<Food> list = new ArrayList<>();

    @Override
    public boolean put(Food food) {
        if (check(food)) {
            list.add(food);
            return true;
        }
        return false;
    }

    @Override
    public List<Food> get() {
        return new ArrayList<>(list);
    }

    @Override
    public boolean check(Food food) {
        return LocalDate.now().isAfter(food.getExpiryDate());
    }
}
