package ru.job4j.design.srp;

import java.util.List;
import java.util.function.Predicate;

public interface Store {
    public void add(Employee em);

    List<Employee> findBy(Predicate<Employee> filter);
}
