package ru.job4j.ood.lsp.controlquality;

import java.util.List;

public interface Store {
    boolean put(Food food);

    List<Food> get();

    boolean check(Food food);
}
