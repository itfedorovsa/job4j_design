package ru.job4j.ood.lsp.controlquality;

import java.util.List;

public class ControlQuality {
    private final List<Store> storeList;

    public ControlQuality(List<Store> storeList) {
        this.storeList = storeList;
    }

    public void distribute(Food food) {
        storeList.forEach(f -> f.put(food));
    }

}
