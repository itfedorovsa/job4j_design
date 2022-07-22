package ru.job4j.design.srp;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class ReportEngineHR implements Report {
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd:MM:yyyy HH:mm");

    private Store store;

    public ReportEngineHR(Store store) {
        this.store = store;
    }

    private List<Employee> descSort(List<Employee> employees) {
        employees.sort(Comparator.comparingDouble(Employee::getSalary).reversed());
        return employees;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Salary;")
                .append(System.lineSeparator());
        List<Employee> employees = store.findBy(filter);
        employees = descSort(employees);
        for (Employee employee : employees) {
            text.append(employee.getName()).append("; ")
                    .append(employee.getSalary()).append("; ")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
