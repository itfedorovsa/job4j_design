package ru.job4j.ood.lsp.controlquality;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class ControlQuality {
    private final List<Store> storeList;

    public ControlQuality(List<Store> storeList) {
        this.storeList = storeList;
    }

    public static int getProductConditionPercentage(Food food) {
        double fullTime = ChronoUnit.DAYS.between(food.getCreateDate(), food.getExpiryDate());
        double passedTime = ChronoUnit.DAYS.between(food.getCreateDate(), LocalDate.now());
        if (fullTime <= 0 || passedTime < 0) {
            throw new IllegalArgumentException("Create date can't be equals or more than expiry date!");
        }
        return (int) (passedTime / fullTime * 100);
    }

    public void distribute(Food food) {
        storeList.forEach(f -> f.put(food));
    }

    public static void main(String[] args) {

    }
}
