package ru.job4j.design.srp;

import java.text.SimpleDateFormat;
import java.util.function.Predicate;

public class ReportEngineAccountants implements Report {
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd:MM:yyyy HH:mm");
    public static final float CONVERT_USD_TO_EUR = 1.1f;

    private Store store;

    public ReportEngineAccountants(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary(Euro);")
                .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append("; ")
                    .append(DATE_FORMAT.format(employee.getHired().getTime())).append("; ")
                    .append(DATE_FORMAT.format(employee.getFired().getTime())).append("; ")
                    .append(employee.getSalary() * CONVERT_USD_TO_EUR).append("; ")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
