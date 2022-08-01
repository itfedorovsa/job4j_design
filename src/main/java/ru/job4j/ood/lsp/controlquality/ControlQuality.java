package ru.job4j.ood.lsp.controlquality;

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
        for (Store foodList : storeList) {
            for (int food = 0; food < foodList.get().size(); food++) {
                Food temp = foodList.get().get(food);
                foodList.get().remove(food);
                distribute(temp);
            }
        }
    }

}
