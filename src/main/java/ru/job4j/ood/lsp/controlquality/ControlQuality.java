package ru.job4j.ood.lsp.controlquality;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ControlQuality {
    private final List<Store> storeList;

    public ControlQuality(List<Store> storeList) {
        this.storeList = storeList;
    }

    public void distribute(Food food) {
        storeList.forEach(f -> f.put(food));
    }

    public void reSort() {
        List<Food> allFood = new ArrayList<>();
        for (Store store : storeList) {
            List<Food> foodList = store.get();
            allFood.addAll(foodList);
            store.clearStore();
        }
        for (Food food : allFood) {
            distribute(food);
        }
    }

}
