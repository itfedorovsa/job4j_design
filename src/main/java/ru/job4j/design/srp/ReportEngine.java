package ru.job4j.design.srp;

import java.text.SimpleDateFormat;
import java.util.function.Predicate;

public class ReportEngine implements Report {

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd:MM:yyyy HH:mm");

    private Store store;

    public ReportEngine(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append("; ")
                    .append(DATE_FORMAT.format(employee.getHired().getTime())).append("; ")
                    .append(DATE_FORMAT.format(employee.getFired().getTime())).append("; ")
                    .append(employee.getSalary()).append("; ")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }

    public static void main(String[] args) {
        MemStore store = new MemStore();
        Employee worker1 = new Employee("John", 100);
        Employee worker2 = new Employee("Jade", 200);
        store.add(worker1);
        store.add(worker2);
        Report engine = new ReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())
                .append(worker2.getName()).append("; ")
                .append(worker2.getSalary()).append("; ")
                .append(System.lineSeparator())
                .append(worker1.getName()).append("; ")
                .append(worker1.getSalary()).append("; ");
        System.out.println(expect);
    }
}
