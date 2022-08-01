package ru.job4j.ood.lsp.controlquality;

import java.util.ArrayList;
import java.util.List;

public class Shop implements Store {
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
        int conditionPercentage = getProductConditionPercentage(food);
        if (conditionPercentage >= 25 && conditionPercentage <= 75) {
           return true;
        } else if (conditionPercentage >= 75 && conditionPercentage < 100) {
            food.setPrice(food.getPrice() / 100 * (100 - food.getDiscount()));
            return true;
        }
        return false;
    }

    @Override
    public void clearStore() {
        list.clear();
    }
}
