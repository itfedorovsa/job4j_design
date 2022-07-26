package ru.job4j.design.srp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.function.Predicate;

public class JSONReport implements Report {

    private Store store;

    private final Gson gson;

    public JSONReport(Store store) {
        this.store = store;
        this.gson = new GsonBuilder().create();
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        final List<Employee> list = store.findBy(filter);
        return gson.toJson(list);
    }
}
