package ru.job4j.ood.lsp.controlquality;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public interface Store {
    boolean put(Food food);

    List<Food> get();

    boolean check(Food food);

    void clearStore();

    default int getProductConditionPercentage(Food food) {
        double fullTime = ChronoUnit.DAYS.between(food.getCreateDate(), food.getExpiryDate());
        double passedTime = ChronoUnit.DAYS.between(food.getCreateDate(), LocalDate.now());
        if (fullTime <= 0 || passedTime < 0) {
            throw new IllegalArgumentException("Create date can't be equals or more than expiry date!");
        }
        return (int) (passedTime / fullTime * 100);
    }
}
