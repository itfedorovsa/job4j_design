package ru.job4j.ood.lsp.controlquality;

import java.util.ArrayList;
import java.util.List;

public class Warehouse implements Store {
    private final List<Food> list = new ArrayList<>();

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
        return ControlQuality.getProductConditionPercentage(food) < 25;
    }
}
